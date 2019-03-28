package com.afr.memcache;

import com.afr.utils.MyLogger;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yan liang
 * @create 2019/2/19
 * @since 1.0.0
 */
public class MySpyMemcache implements DisposableBean {
    @Autowired
    MemcachedClientFactoryBean memcachedClientFactoryBean;

    private MemcachedClient _client;

    private MemcachedClient get_client() {
        if (_client == null) {
            return getMemcacheClient();
        }
        return _client;
    }

    private MemcachedClient getMemcacheClient() {
        try {
            return (MemcachedClient) memcachedClientFactoryBean.getObject();
        } catch (Exception e) {
            MyLogger.logger.error("获取memcachedClient异常：" + e.getMessage());
        }
        return null;
    }

    public OperationFuture<Boolean> set(String key, Object o) {
        try {
            return get_client().set(key, 0, o);
        } catch (Exception e) {
            MyLogger.logger.error("设置缓存key：" + key + "异常" + e.getMessage());
        }
        return null;
    }

    public Object get(String key) {
        try {
            return get_client().get(key);
        } catch (Exception e) {
            MyLogger.logger.error("获取缓存key：" + key + "异常" + e.getMessage());
        }
        return null;
    }

    @Override
    public void destroy() throws Exception {

    }
}