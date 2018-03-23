package com.wkl.utils;

import redis.clients.jedis.Jedis;

public class RedisLockUtil {
	
	public static boolean lock(String key, String value, long time, Jedis jedis){
		int second = (int) (time / 1000);
		if(jedis.setnx(key, value) == 1) { //设置成功
			jedis.expire(key, second);
			return true;
		} else if (jedis.ttl(key) == -1 ){ //-1代表key没设置超时时间         -2代表不存在key
			jedis.expire(key, second);
			return true;
		}
		return false;
	}
	
	public static boolean releaseLock(String key, String value, Jedis jedis){
		
		boolean flag = false;
		while(true){
			jedis.watch(key);   //redis虽然是单进程的，但是get set方法执行总是有顺序，可能会出现并发问题
			if(value.equals(jedis.get(key))) {
				long i = jedis.del(key);
				if(i >= 1)          //1个或者多个key被删除
					flag = true;
				else if (i == 0)	//没有key被删除
					continue;
			}
			jedis.unwatch();
			break;
		}
		
		return flag;
	}
}
