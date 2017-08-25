package spittr.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import spittr.data.redis.Product;
import spittr.domain.Spittle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenda on 8/22/2017.
 */
@Configuration
@EnableCaching
public class CompositeConfig {

    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm) {
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<CacheManager> managers = new ArrayList<CacheManager>();
//        managers.add(new JCacheCacheManager(jcm));
        managers.add(new EhCacheCacheManager(cm));
        managers.add(new RedisCacheManager(redisTemplate(redisConnectionFactory())));
        cacheManager.setCacheManagers(managers);
        return cacheManager;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Spittle> redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String, Spittle> redisTemplate = new RedisTemplate<String, Spittle>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {                                                //EhCacheManagerFactoryBean, generates an Ehcache CacheManager
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
//        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("spittr/spittr.cache.spittr.cache/spittr.cache/spittr.cache.xml"));
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheFactoryBean;

    }

}
