package spittr.data.hibernate4;

import net.sf.ehcache.CacheManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by wenda on 8/19/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.data.hibernate4")
@EnableCaching
public class HibernateEhcacheDataConfig {

    @Inject
    private SessionFactory sessionFactory;

    //1. dataSource
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        edb.addScript("spittr/data/hibernate4/schema.sql");
        edb.addScript("spittr/data/hibernate4/test-data.sql");
        EmbeddedDatabase embeddedDatabase = edb.build();
        return embeddedDatabase;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    //3. HibernateSessionFactoryBean
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan(new String[] { "spittr.domain" });
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        props.setProperty("hibernate.cache.use_second_level_cache", "true");
//        props.setProperty("hibernate.cache.use_query_cache", "true");
        props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.generate_statistics", "true");
        sfb.setHibernateProperties(props);
        return sfb;
    }

    //4. PostProcessorBean
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    //5. declare spring ehcache manager use a Ehcache cacheManager
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cm) {		//Configure EhCacheCacheManager, inject an Ehcache CacheManager
        return new EhCacheCacheManager(cm);
    }

    //6. generate an Ehcache cacheManager
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheFactoryBean.setShared(true);
        return ehCacheFactoryBean;
    }
}
