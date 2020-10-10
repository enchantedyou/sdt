package com.ssy.api.utils.system;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description	redis操作助手
 * @Author sunshaoyu
 * @Date 2020/7/27-17:46
 */
@Component
@Slf4j
public class RedisHelper {
	
	private static RedisTemplate<Object, Object> redisTemplate;
	@Autowired
	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		RedisHelper.redisTemplate = redisTemplate;
	}

	/**
	 * @Description	使某个缓存失效
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:16
	 * @param redisKey
	 */
	public static void expired(Object redisKey){
		if(CommUtil.isNotNull(redisKey)){
			delete(redisKey);
			log.info("Remove key [{}] from redis cache", redisKey);
		}
	}

	/**
	 * @Description	从redis缓存中获取集合并转为List对象
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:24
	 * @param key
	 * @param clazz
	 * @return java.util.List<T>
	 */
	public static <T> List<T> getList(Object key, Class<T> clazz){
		log.info("Get list from redis cache according to key [{}], class [{}]", key, clazz);
		return JSON.parseArray(JSON.toJSONString(getList(key)), clazz);
	}

	/**
	 * @Description	从redis缓存中获取Map
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:31
	 * @param key
	 * @return java.util.Map<java.lang.Object,java.lang.Object>
	 */
	public static Map<Object, Object> getMap(Object key) {
		log.info("Get map from redis cache according to key [{}]", key);
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * @Description	指定redis缓存中某个数据的过期时间,单位为秒
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:33
	 * @param key
	 * @param timeout
	 */
	public static void expire(Object key, long timeout){
		if(timeout > 0){
			log.info("Specify the valid time of the data with the key [{}] in the redis cache as [{}] seconds", key, timeout);
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * @Description	获取redis缓存中某个key的有效剩余时间,单位为秒
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:34
	 * @param key
	 * @return long
	 */
	public static long getExpire(Object key){
		log.info("Get the valid time of the data with key [{}] in the redis cache", key);
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * @Description	从redis缓存中添加一个键值对或设置某键值对的值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:35
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static void addAndSetValue(Object key, Object value, long timeout){
		log.info("Add or set the value [{}] of key [{}] from the redis cache", value, key);
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * @Description	从redis缓存中获取某个key的值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:36
	 * @param key
	 * @return java.lang.Object
	 */
	public static Object getValue(Object key){
		log.info("Get the value with key [{}] from the redis cache", key);
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * @Description	从尾部添加List
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:39
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static <T> void pushAllAsList(Object key, List<T> value, long timeout){
		log.info("Add a list with key [{}] and valid time [{}] seconds from the redis cache", key, timeout);
		redisTemplate.opsForList().rightPushAll(key, value);
		expire(key, timeout);
	}

	/**
	 * @Description	从List的尾部插入一个值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:42
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static void addValueToList(Object key, Object value, long timeout){
		log.info("Add a value [{}] from the list with key [{}] in the redis cache", value, key);
		redisTemplate.opsForList().rightPush(key, value);
		expire(key, timeout);
	}

	/**
	 * @Description	重置List指定下标的值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:43
	 * @param key
	 * @param index
	 * @param value
	 * @param timeout
	 */
	public static void setValueFromList(Object key, long index, Object value, long timeout){
		log.info("Reset the subscript of list [{}] to the value [{}] of [{}] from the redis cache", key, value, index);
		redisTemplate.opsForList().set(key, index, value);
		expire(key, timeout);
	}

	/**
	 * @Description	获取List中某个下标的值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:44
	 * @param key
	 * @param index
	 * @return java.lang.Object
	 */
	public static Object getListValue(Object key, long index){
		log.info("Get the value of the subscript [{}] of the list with key [{}] from the redis cache", key, index);
		return redisTemplate.opsForList().index(key, index);
	}

	/**
	 * @Description	从redis缓存中获取List
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:17
	 * @param key
	 * @return java.lang.Object
	 */
	public static Object getList(Object key){
		log.info("Get list from redis cache according to key {}", key);
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * @Description	从List删除某个值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:45
	 * @param key
	 * @param value
	 * @param count	大于0表示删除等于从头到尾移动的值的元素;小于0表示删除等于从尾到头移动的值的元素;等于0表示删除等于value的所有元素。
	 */
	public static void removeFromList(Object key, Object value, long count){
		log.info("Delete the value [{}] of the list with key [{}] from the redis cache", value, key);
		redisTemplate.opsForList().remove(key, count, value);
	}
	
	/**
	 * @Description	向redis缓存中的某个Map添加或设置(如果该subKey已存在)值,不设置过期时间
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:47
	 * @param key
	 * @param subKey
	 * @param value
	 */
	public static void putAndSetToHash(Object key,Object subKey, Object value){
		log.info("Add or set (if the subKey [{}] already exists) value to a Map [{}] in the redis cache without setting the expiration time", subKey, key);
		putAndSetToHash(key, subKey, value,-1);
	}
	
	/**
	 * @Description	向redis缓存中的某个Map添加或设置(如果该subKey已存在)值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:48
	 * @param key
	 * @param subKey
	 * @param value
	 * @param timeout
	 */
	public static void putAndSetToHash(Object key, Object subKey, Object value,long timeout){
		log.info("Add or set (if the subKey [{}] already exists) value to a map [{}] in the redis cache", subKey, key);
		redisTemplate.opsForHash().put(key, subKey, value);
		expire(key, timeout);
	}

	/**
	 * @Description	检索Map中是否存在键为subKey的值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:49
	 * @param key
	 * @param subKey
	 * @return boolean
	 */
	public static boolean hashKeyFromHash(Object key, Object subKey){
		log.info("Check if there is a value of [{}] in the map [{}]", subKey, key);
		return redisTemplate.opsForHash().hasKey(key, subKey);
	}
	
	/**
	 * @Description	从redis缓存中移除Map的某个值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:50
	 * @param key
	 * @param subKey
	 */
	public static void removeFromHash(Object key, Object subKey){
		log.info("Remove a value with subKey [{}] in the map [{}] from the redis cache", subKey, key);
		redisTemplate.opsForHash().delete(key, subKey);
	}
	
	/**
	 * @Description	将整个Map存入redis缓存
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:51
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static void pushAllAsHash(Object key, Map<Object, Object> value, long timeout){
		log.info("Store the entire map [{}] in the redis cache", key);
		redisTemplate.opsForHash().putAll(key, value);
		expire(key, timeout);
	}
	
	/**
	 * @Description	从redis缓存中获取Map中的某个键的值
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:52
	 * @param key
	 * @param subKey
	 * @return java.lang.Object
	 */
	public static Object getHashValue(Object key, Object subKey){
		log.info("Get the value of key [{}] in the map [{}] from the redis cache", subKey, key);
		return redisTemplate.opsForHash().get(key, subKey);
	}
	
	/**
	 * @Description	从redis缓存中删除键为key的缓存
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:52
	 * @param key
	 */
	public static void delete(Object key){
		log.info("Delete the cache with key [{}] from the redis cache", key);
		redisTemplate.delete(key);
	}

	/**
	 * @Description	检索redis缓存中是否存在key的缓存
	 * @Author sunshaoyu
	 * @Date 2020/7/28-10:53
	 * @param key
	 * @return boolean
	 */
	public static boolean hasKey(Object key){
		log.info("Check whether there is a cache with key [{}] in the redis cache", key);
		return redisTemplate.hasKey(key);
	}
}
