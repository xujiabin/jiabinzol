package com.zol.backserver.cache;

import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MemCachedCacheService implements CacheService {
	private Logger logger = LoggerFactory.getLogger(MemCachedCacheService.class);

	MemcachedClient memcachedClient;

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}
	
	 
	

	public <T> void set(String key, T t) {
		try {
			memcachedClient.set(key, 0, t);
		} catch (TimeoutException e) {
			logger.error("memcache set操作异常", e);
		} catch (InterruptedException e) {
			logger.error("memcache set操作异常", e);
		} catch (MemcachedException e) {
			logger.error("memcache set操作异常", e);
		}
	}
	
	
	/**
	 * 
	 * @param key 建
	 * @param exp  单位分钟
	 * @param t
	 * @see com.jd.backserver.cache.CacheService#set(java.lang.String, java.lang.Integer, java.lang.Object)
	 * @return
	 */
	public <T> void set(String key, Integer exp, T t) {
		if (exp == null) {
			exp = 0;
		}
		try {
			memcachedClient.set(key, exp, t);
		} catch (TimeoutException e) {
			logger.error("memcache set操作异常", e);
		} catch (InterruptedException e) {
			logger.error("memcache set操作异常", e);
		} catch (MemcachedException e) {
			logger.error("memcache set操作异常", e);
		}
	}

	public <T> T get(String key) {
		T t = null;
		try {
			t = memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.error("memcache get操作异常", e);
		} catch (InterruptedException e) {
			logger.error("memcache get操作异常", e);
		} catch (MemcachedException e) {
			logger.error("memcache get操作异常", e);
		}
		return t;
	}

	public <T> void checkToSet(String key, T t) {
		T temp = null;
		try {
			temp = memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.error("memcache checkToSet操作异常", e);
		} catch (InterruptedException e) {
			logger.error("memcache checkToSet操作异常", e);
		} catch (MemcachedException e) {
			logger.error("memcache checkToSet操作异常", e);
		}
		if (temp == null) {
			set(key, t);
		}
	}

	public void delete(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException e) {
			logger.error("memcache delete操作异常", e);
		} catch (MemcachedException e) {
			logger.error("memcache delete操作异常", e);
		}
	}

	public void flushAll() {
		try {
			memcachedClient.flushAll();
		} catch (TimeoutException e) {
			logger.error("memcache flushAll操作异常", e);
		} catch (InterruptedException e) {
			logger.error("memcache flushAll操作异常", e);
		} catch (MemcachedException e) {
			logger.error("memcache flushAll操作异常", e);
		}
	}
}
