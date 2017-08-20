package spittr.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.Spitter;
import spittr.config.DataConfig;
import spittr.config.RootConfig;
import spittr.web.HomeController;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by wenda on 8/19/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class JDBCTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void spitterRepositoryShouldNotBeNull() {
        assertNotNull(dataSource);
        System.out.println(dataSource);
        assertNotNull(jdbcOperations);
        System.out.println(jdbcOperations);
        assertNotNull(spitterRepository);
        System.out.println(spitterRepository);
    }

    @Test
    public void testFindByUser() {
        Spitter spitter = spitterRepository.findByUsername("habuma");
        System.out.println(spitter);
    }

    @Test
    public void testFindByUserUsingTemplate() {
        String username = "daniel";
        Spitter spitter = jdbcOperations.queryForObject("select id, username, password, first_name, last_name, email from Spitter where username=?",
                new RowMapper<Spitter>() {
                    @Override
                    public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Spitter spitter = new Spitter();
                        spitter.setId(rs.getLong(1));
                        spitter.setUsername(rs.getString(2));
                        spitter.setFirstName(rs.getString(3));
                        spitter.setLastName(rs.getString(4));
                        spitter.setEmail(rs.getString(5));
                        return spitter;
                    }
                }, username);
        System.out.println(spitter);
    }

//    @Test
//    public void testFindByUserUsingTemplateAndLambda() {
//        String username = "habuma";
//        Spitter resultSpitter = jdbcOperations.queryForObject("select id, username, password, first_name, last_name, email from Spitter where username=?",
//                (rs, rowNum) -> {
//                        Spitter spitter = new Spitter();
//                        spitter.setId(rs.getLong(1));
//                        spitter.setUsername(rs.getString(2));
//                        spitter.setFirstName(rs.getString(3));
//                        spitter.setLastName(rs.getString(4));
//                        spitter.setEmail(rs.getString(5));
//                        return spitter;
//                }, username);
//        System.out.println(resultSpitter);
//    }

//    @Test
//    public void testFindByUserUsingTemplateAndMethodReferences() {
//        String username = "danielwen";
//        Spitter resultSpitter = jdbcOperations.queryForObject("select id, username, password, first_name, last_name, email from Spitter where username=?",
//                this::mapSpitter, username);
//        System.out.println(resultSpitter);
//    }

    private Spitter mapSpitter(ResultSet rs, int row) throws SQLException {
        Spitter spitter = new Spitter();
        spitter.setId(rs.getLong(1));
        spitter.setUsername(rs.getString(2));
        spitter.setFirstName(rs.getString(3));
        spitter.setLastName(rs.getString(4));
        spitter.setEmail(rs.getString(5));
        return spitter;
    };

//    @Test
//    public void testNamedParameterJdbcTemplate() {
//        String INSERT_SPITTER = "insert into Spitter (username, password, first_name, last_name, email) values (:username, :password, :first_name, :last_name, :email)";
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("username", "danielwen");
//        paramMap.put("password", "123456");
//        paramMap.put("first_name", "Daniel");
//        paramMap.put("last_name", "Wen");
//        paramMap.put("email", "name@company.com");
//        namedParameterJdbcTemplate.update(INSERT_SPITTER, paramMap);
//        String username = "danielwen";
//        Spitter resultSpitter = jdbcOperations.queryForObject("select id, username, password, first_name, last_name, email from Spitter where username=?",
//                this::mapSpitter, username);
//        System.out.println(resultSpitter);
//
//    }

}
