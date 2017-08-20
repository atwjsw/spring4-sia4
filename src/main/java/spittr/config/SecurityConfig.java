package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import spittr.data.SpitterRepository;
import spittr.security.SpitterUserService;

import javax.sql.DataSource;

/**
 * Created by wenda on 8/11/2017.
 */
@Configuration
@EnableWebSecurity    //enables web security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    1. In memory user store
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("SPITTER").and()
                .withUser("admin").password("password").roles("SPITTER", "ADMIN");
    }

//    2. jdbc user store
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, true from Spitter where username=?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?");
//    }

//    3. LDAP user store
    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .contextSource()
//                .root("dc=habuma,dc=com")
//                .ldif("classpath:users.ldif");
//    }

    //    4. custom user store
//
//    @Autowired
//    SpitterRepository spitterRepository;
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new SpitterUserService(spitterRepository));
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .logoutUrl("/signout")
//                .and()
//                .httpBasic()
//                .realmName("Spittr")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("spittrKey")
                .and()
                .authorizeRequests()
                .antMatchers("/admin")
                .access("isAuthenticated() and principal.username=='admin'")
                .antMatchers("/spitter/me").authenticated()
////                .antMatchers("/spitter/me").access("hasRole('ROLE_USER')")
////                .antMatchers("/spitter/me").access("hasRole('ROLE_USER') and hasIpAddress('192.168.198.1')")
                .antMatchers(HttpMethod.POST, "/spittles").authenticated()
                .anyRequest().permitAll()
//                .and()
//                .requiresChannel()
//                .antMatchers("/spitter/register").requiresSecure()
                .and()
                .csrf()
                .disable();
    }
}
