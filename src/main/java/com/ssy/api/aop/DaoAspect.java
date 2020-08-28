package com.ssy.api.aop;

import com.github.pagehelper.PageHelper;
import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.SelectPageWithCount;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_DATASOURCETYPE;
import com.ssy.api.entity.enums.E_ODBOPERATE;
import com.ssy.api.entity.session.UserInfo;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.SystemParamService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Description Data Access Object层的切面
 * @Author sunshaoyu
 * @Date 2020年07月02日-16:59
 */
@Aspect
@Component
@Slf4j
public class DaoAspect {

    @Autowired
    private SystemParamService systemParamService;

    /**
     * @Description 查询前增强处理(开启分页)
     * @Author sunshaoyu
     * @Date 2020/7/17-13:04
     * @param point
     */
    @Before(value="execution(* com.ssy.api.dao.mapper.*.*.select*(..))")
    public void selectWithPageAdvice(JoinPoint point){
        if(MethodSignature.class.cast(point.getSignature()).getMethod().isAnnotationPresent(SelectPageWithCount.class)){
            //分页处理
            PageHelper.startPage(BizUtil.getRunEnvs().getCurrentPage(), BizUtil.getRunEnvs().getPageSize());
        }
    }

    /**
     * @Description 切入insert方法,在插表前初始化公共字段
     * @Author sunshaoyu
     * @Date 2020/7/2-17:08
     * @param point
     */
    @Before(value="execution(* com.ssy.api.dao.mapper.*.*.insert*(..))")
    public void insertAdvice(JoinPoint point){
        Class[] argTypeArr = MethodSignature.class.cast(point.getSignature()).getParameterTypes();
        if(CommUtil.isNotNull(argTypeArr)){
            for(int i = 0;i < argTypeArr.length;i++){
                refreshTableCommField(point.getArgs()[i], argTypeArr[i], E_ODBOPERATE.INSERT);
            }
        }
    }

    /**
     * @Description 切入update方法,在插表前更新公共字段
     * @Author sunshaoyu
     * @Date 2020/7/2-17:58
     * @param point
     */
    @Before(value="execution(* com.ssy.api.dao.mapper.local.*.update*(..))")
    public void updateAdvice(JoinPoint point){
        Class[] argTypeArr = MethodSignature.class.cast(point.getSignature()).getParameterTypes();
        if(CommUtil.isNotNull(argTypeArr)){
            for(int i = 0;i < argTypeArr.length;i++){
                refreshTableCommField(point.getArgs()[i], argTypeArr[i], E_ODBOPERATE.UPDATE);
            }
        }
    }

    /**
     * @Description 增强本地dao方法,实现本地数据源和动态数据源的自动转换
     * @Author sunshaoyu
     * @Date 2020/7/3-13:41
     * @param point
     */
    @Around(value="execution(* com.ssy.api.dao.mapper.local.*.*(..)))")
    public Object localDaoAdvTice(ProceedingJoinPoint point) throws Throwable {
        DBContextHolder.determineCurrentDataSourceType(E_DATASOURCETYPE.LOCAL);
        Object returnVal = point.proceed(point.getArgs());
        if(CommUtil.isNotNull(DBContextHolder.getCurrentDataSource())){
            DBContextHolder.determineCurrentDataSourceType(E_DATASOURCETYPE.REMOTE);
        }
        return returnVal;
    }

    /**
     * @Description 增强远程dao方法,在执行dao之前切换到用户当前的动态数据源,执行完成后恢复
     * @Author sunshaoyu
     * @Date 2020/7/15-14:23
     * @param point
     * @return java.lang.Object
     */
    /*@Around(value="execution(* com.ssy.api.dao.mapper.ap.*.*(..))) && execution(* com.ssy.api.dao.mapper.edsp.*.*(..)))" +
            "&& execution(* com.ssy.api.dao.mapper.msap.*.*(..))) && execution(* com.ssy.api.dao.mapper.system.*.*(..)))")
    public Object remoteDaoAdvice(ProceedingJoinPoint point) throws Throwable {
        DBContextHolder.determineCurrentDataSourceType(E_DATASOURCETYPE.REMOTE);
        return point.proceed(point.getArgs());
    }*/

    /**
     * @Description odb查询增强:查询的结果为空时抛出异常
     * @Author sunshaoyu
     * @Date 2020/7/2-18:52
     * @param point
     */
    @AfterReturning(pointcut="@annotation(com.ssy.api.entity.annotation.EnableNotNull)", returning="result")
    public void odbQueryNotNullAdvice(JoinPoint point, Object result) throws NoSuchMethodException {
        if(CommUtil.isNull(result)){
            Object[] args = point.getArgs();
            Class[] argTypeArr = MethodSignature.class.cast(point.getSignature()).getParameterTypes();
            Class<?> declaringType = point.getSignature().getDeclaringType();
            Method targetMethod = declaringType.getMethod(point.getSignature().getName(), argTypeArr);
            int len = args.length;

            if(CommUtil.isNotNull(args) && args[len - 1] instanceof Boolean
                    && Boolean.TRUE.equals(args[len - 1]) && targetMethod.isAnnotationPresent(EnableNotNull.class)){
                List<Object> argList = CommUtil.asList(args);
                argList.remove(len - 1);
                throw SdtServError.E0003(declaringType.getAnnotation(TableType.class).desc(), argList.toArray(new String[]{}));
            }
        }
    }

    /**
     * @Description 刷新表公共字段
     * @Author sunshaoyu
     * @Date 2020/7/2-17:21
     * @param obj
     * @param clazz
     * @param operateType
     * @return void
     */
    private void refreshTableCommField(Object obj, Class<?> clazz, E_ODBOPERATE operateType) {
        Field[] fields = clazz.getDeclaredFields();
        UserInfo userInfo = SpringContextUtil.getSessionAttribute(SdtConst.USER_INFO, UserInfo.class);
        String currentUser = CommUtil.isNull(userInfo) ? systemParamService.getValue(SdtConst.DEFAULT_TELLER) : userInfo.getUserAcct();

        try{
            for(Field field : fields){
                /** 数据创建者 **/
                if(CommUtil.equals(field.getName(), SdtDict.A.data_create_user.getId()) && operateType == E_ODBOPERATE.INSERT){
                    clazz.getMethod(CommUtil.buildSetterMethodName(SdtDict.A.data_create_user.getId()), String.class).invoke(obj, currentUser);
                }

                /** 数据创建时间 **/
                if(CommUtil.equals(field.getName(), SdtDict.A.data_create_time.getId()) && operateType == E_ODBOPERATE.INSERT){
                    clazz.getMethod(CommUtil.buildSetterMethodName(SdtDict.A.data_create_time.getId()), String.class).invoke(obj, BizUtil.getCurSysTime());
                }

                /** 数据更新者 **/
                if(CommUtil.equals(field.getName(), SdtDict.A.data_update_user.getId()) && operateType == E_ODBOPERATE.UPDATE){
                    clazz.getMethod(CommUtil.buildSetterMethodName(SdtDict.A.data_update_user.getId()), String.class).invoke(obj, currentUser);
                }

                /** 数据更新时间 **/
                if(CommUtil.equals(field.getName(), SdtDict.A.data_update_time.getId()) && operateType == E_ODBOPERATE.UPDATE){
                    clazz.getMethod(CommUtil.buildSetterMethodName(SdtDict.A.data_update_time.getId()), String.class).invoke(obj, BizUtil.getCurSysTime());
                }

                /** 数据版本号 **/
                if(CommUtil.equals(field.getName(), SdtDict.A.data_version.getId())){
                    if(operateType == E_ODBOPERATE.INSERT){
                        clazz.getMethod(CommUtil.buildSetterMethodName(SdtDict.A.data_version.getId()), Integer.class).invoke(obj, 0);
                    }else if(operateType == E_ODBOPERATE.UPDATE){
                        Integer dataVersion = (Integer) clazz.getMethod(CommUtil.buildGetterMethodName(SdtDict.A.data_version.getId())).invoke(obj);
                        clazz.getMethod(CommUtil.buildSetterMethodName(SdtDict.A.data_version.getId()), Integer.class).invoke(obj, CommUtil.nvl(dataVersion, 0) + 1);
                    }
                }
            }
        }catch (Exception e){
            throw new SdtException(e);
        }
    }
}
