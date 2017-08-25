package spittr.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import spittr.data.JdbcSpittleRepository;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

@Configuration
@ComponentScan(basePackages="spittr.data")
public class DataConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .addScript("data.sql")
            .build();
  }
  
  @Bean
  public JdbcOperations jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

//  @Bean
//  public NamedParameterJdbcTemplate namedParameterjdbcTemplate(DataSource dataSource) {
//    return new NamedParameterJdbcTemplate(dataSource);
//  }

  @Bean
  SpittleRepository spittleRepository(JdbcOperations jdbcOperations) {
    return new JdbcSpittleRepository(jdbcOperations);
  }

}
