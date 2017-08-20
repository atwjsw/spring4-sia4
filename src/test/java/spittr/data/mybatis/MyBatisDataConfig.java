package spittr.data.mybatis;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wenda on 8/20/2017.
 */
@Configuration
@MapperScan(basePackages = "spittr.data.mybatis")
public class MyBatisDataConfig {

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
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("spittr.domain");
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setTypeAliasesPackage("com.huang.domain");
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        return sessionFactory;
//    }

//    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//    <property name="dataSource" ref="dataSource"/>
//    <property name="configLocation" value="classpath:mybatis-config.xml"/>
//    <property name="typeAliasesPackage" value="org.seckill.entity"/>
//    <property name="mapperLocations" value="classpath:mapper/*.xml"/>
//    </bean>
//
//    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
//    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
//    <property name="basePackage" value="org.seckill.dao"/>
//    </bean>