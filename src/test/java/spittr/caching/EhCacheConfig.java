package spittr.caching;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by wenda on 8/21/2017.
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cm) {		//Configure EhCacheCacheManager, inject an Ehcache CacheManager
        return new EhCacheCacheManager(cm);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {                                                //EhCacheManagerFactoryBean, generates an Ehcache CacheManager
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
//        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("spittr/spittr.cache.spittr.cache/spittr.cache/spittr.cache.xml"));
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheFactoryBean;

    }
}
