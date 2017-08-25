package spittr.data.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/**
 * Created by wenda on 8/21/2017.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "spittr.data.neo4j")
public class Neo4jConfig extends Neo4jConfiguration {
    public Neo4jConfig() {								//Set model base package
        setBasePackage("spittr.data.neo4j");
    }

    @Bean(destroyMethod="shutdown")
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("c:\\tmp\\graphdb");		//Configure embedded database
    }
}
