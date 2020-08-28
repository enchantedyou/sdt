package com.ssy.api.utils.system;

import com.ssy.api.dao.mapper.system.ProcessMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.plugins.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description	JDBC工具类
 * @Author sunshaoyu
 * @Date 2020/7/30-9:56
 */
@Slf4j
@Component
public class JDBCHelper {

	@Resource(name= SdtConst.DYNAMIC_DATASOURCE)
	private DataSource dataSource;
	@Autowired
	private ProcessMapper processMapper;

	/**
	 * @Description	获取记录条数
	 * @Author sunshaoyu
	 * @Date 2020/7/30-10:10
	 * @param resultSet
	 * @return int
	 */
	private int getRecordCount(ResultSet resultSet) throws SQLException {
		resultSet.last();
		int rowCount = resultSet.getRow();
		resultSet.beforeFirst();
		return rowCount;
	}

	/**
	 * @Description	转驼峰表示法
	 * @Author sunshaoyu
	 * @Date 2020/7/30-10:14
	 * @param str
	 * @return java.lang.String
	 */
	private String parseHumpStr(String str){
		String[] array = str.toLowerCase().split("_");
		StringBuffer buffer = new StringBuffer();
		for(String s : array){
			buffer.append(s.substring(0,1).toUpperCase() + s.substring(1, s.length()));
		}
		return buffer.substring(0,1).toLowerCase() + buffer.substring(1, buffer.length());
	}

	/**
	 * @Description	获取列Map
	 * @Author sunshaoyu
	 * @Date 2020/7/30-10:23
	 * @param resultSet
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 */
	private Map<String, String> getColumnMap(ResultSet resultSet) throws SQLException {
		Map<String, String> map = new HashMap<>();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		for(int i = 1;i <= rsmd.getColumnCount();i++){
			String colName = rsmd.getColumnName(i).toLowerCase();
			map.put(parseHumpStr(colName), colName);
		}
		return map;
	}

	/**
	 * @Description	ResultSet映射单个实体
	 * @Author sunshaoyu
	 * @Date 2020/7/30-10:27
	 * @param resultSet
	 * @param clazz
	 * @return T
	 */
	private <T> T entityMapping(ResultSet resultSet, Class<T> clazz) {
		try{
			Map<String, String> colMap = getColumnMap(resultSet);

			if(resultSet.next()){
				T obj = clazz.newInstance();
				for(Field field : clazz.getDeclaredFields()){
					Method method = clazz.getMethod(CommUtil.buildSetterMethodName(field.getName()), field.getClass());
					method.invoke(obj, resultSet.getObject(colMap.get(field.getName())));
				}
				return obj;
			}
		}catch (Exception e){
			throw new SdtException("Mapping from result set ["+resultSet+"] to entity class ["+clazz+"] failed", e);
		}
		return null;
	}

	/**
	 * @Description	ResultSet映射列表
	 * @Author sunshaoyu
	 * @Date 2020/7/30-10:30
	 * @param resultSet
	 * @param clazz
	 * @return java.util.List<T>
	 */
	private <T> List<T> listMapping(ResultSet resultSet, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		T obj = entityMapping(resultSet, clazz);
		while(CommUtil.isNotNull(obj)){
			list.add(obj);
			obj = entityMapping(resultSet, clazz);
		}
		return list;
	}

	/**
	 * @Description	无参update
	 * @Author sunshaoyu
	 * @Date 2020/7/30-10:47
	 * @param sql
	 * @return int
	 */
	@Transactional
	public int executeUpdate(String sql) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw SdtServError.E0018(e);
		}
	}

	/**
	 * @Description 数据库解锁
	 * @Author sunshaoyu
	 * @Date 2020/8/28-15:23
	 * @param datasourceId
	 * @return int
	 */
	public int unlock(String datasourceId) {
		if(CommUtil.isNotNull(datasourceId)){
			DBContextHolder.switchToDataSource(datasourceId);
		}

		AtomicInteger size = new AtomicInteger();
		processMapper.showProcessList().forEach(dbProcess -> {
			try{
				processMapper.kill(dbProcess.getId());
				log.info("Kill the process[{}] of database [{}]", dbProcess.getId(), DBContextHolder.getCurrentDataSource());
				size.getAndIncrement();
			}catch (Exception e){
				log.info(e.getMessage());
			}
		});
		return size.get();
	}
}
