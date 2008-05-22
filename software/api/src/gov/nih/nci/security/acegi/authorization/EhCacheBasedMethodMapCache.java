package gov.nih.nci.security.acegi.authorization;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

public class EhCacheBasedMethodMapCache implements MethodMapCache,
		InitializingBean {

	private static final Log logger = LogFactory
			.getLog(EhCacheBasedMethodMapCache.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Cache getCache() {
		return cache;
	}

	public Map getMethodMapFromCache(String mapName) {
		Element element = null;

		try {
			element = cache.get(mapName);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("Cache failure: "
					+ cacheException.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Cache hit: " + (element != null) + "; mapName: "
					+ mapName);
		}

		if (element == null) {
			return null;
		} else {
			return (Map) element.getValue();
		}
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "cache mandatory");
	}

	public void putMethodMapInCache(String mapName, Map methodMap) {
		Element element = new Element(mapName, new HashMap(methodMap));

		if (logger.isDebugEnabled()) {
			logger.debug("Cache put: " + element.getKey());
		}

		cache.put(element);
	}

	public void removeMethodMapFromCache(String mapName) {
		cache.remove(mapName);
	}
}