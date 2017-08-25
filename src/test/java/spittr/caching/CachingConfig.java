package spittr.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wenda on 8/21/2017.
 */
@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {					//Declare a spittr.cache.spittr.cache manager
        return new ConcurrentMapCacheManager();
    }

}
