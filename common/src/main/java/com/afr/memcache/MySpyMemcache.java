package com.afr.memcache;

import com.afr.utils.MyLogger;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author Yan liang
 * @create 2019/2/19
 * @since 1.0.0
 */
public class MySpyMemcache implements DisposableBean {
    @Autowired
    MemcachedClientFactoryBean memcachedClientFactoryBean;

    static private Object object = new Object();

    private long timeout;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    private MemcachedClient _client;

    private MemcachedClient get_client() {
        synchronized (object) {
            if (_client == null) {
                _client =  getMemcacheClient();
            }
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

    public Boolean set(String key, Object o) {
        try {
            return get_client().set(key, 0, o).get(getTimeout(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            MyLogger.logger.error("设置缓存key：" + key + "异常" + e.getMessage());
        }
        return null;
    }

    public Object get(String key) {
        try {
            return get_client().asyncGet(key).get(getTimeout(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            MyLogger.logger.error("获取缓存key：" + key + "异常" + e.getMessage());
        }
        return null;
    }

    @Override
    public void destroy() throws Exception {

    }
}