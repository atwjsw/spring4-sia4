package spittr.data.mongo;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by wenda on 8/20/2017.
 */
@Configuration
@EnableMongoRepositories(basePackages = "spittr.data.mongo" )
public class MongoConfig {
    @Bean
    public MongoFactoryBean mongo() {							//MongoClient bean, like a dataSource
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }

    @Bean
    public MongoOperations mongoTemplate(Mongo mongo) {			//MongoTemplate bean. ref to Mongo instance and db name
        return new MongoTemplate(mongo, "OrdersDB");
    }

}
