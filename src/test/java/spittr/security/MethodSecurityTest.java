package spittr.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.asm.tree.analysis.SourceInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.Spittle;
import spittr.config.DataConfig;
import spittr.config.MethodSecurityConfig;
import spittr.config.SecurityConfig;
import spittr.config.ServiceConfig;
import spittr.service.SpittleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wenda on 8/13/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MethodSecurityConfig.class, DataConfig.class, ServiceConfig.class})
public class MethodSecurityTest {

    @Autowired
    private SpittleService spittleService;
//
//    @Autowired
//    private AuthenticationManagerBuilder auth;

    @Autowired
    private AuthenticationManager am;

//    @Before
//    public void setup() throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("spitter").password("password").roles("SPITTER").and()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("vip").password("password").roles("PREMIUM").and()
//                .withUser("admin").password("password").roles("SPITTER", "ADMIN");
//        am = auth.build();
//    }

    @Test
    public void spittleServiceNotNull() throws Exception {
        assertNotNull(spittleService);
//        assertNotNull(auth);
//        System.out.println(auth);
//        auth.build();
        System.out.println(am);
        assertNotNull(am);
    }

    @Test
    public void testSecuredMethod() throws Exception {
        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "password");
        System.out.println("token: " + token);
        Authentication authentication = am.authenticate(token);
        System.out.println("authentication: " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        spittleService.addSpittleUnSecured(new Spittle("method is secured", new Date()));
        spittleService.addSpittleJSR250(new Spittle("method is secured", new Date()));
        spittleService.addSpittle(new Spittle("method is secured", new Date()));
    }

    @Test
    public void testPreAuthorizeMethod() throws Exception {
//        Authentication token = new UsernamePasswordAuthenticationToken("vip", "password");
        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "password");
        Authentication authentication = am.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        spittleService.addSpittlePreAuthorize(new Spittle("spittle", new Date()));
        spittleService.addSpittlePreAuthorize(new Spittle("spittle's size is more than 10 ", new Date()));
    }

    @Test
    public void testPostAuthorizeMethod() throws Exception {
        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "password");
        Authentication authentication = am.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(spittleService.getSpittleById(1));
    }

    @Test
    public void testPostFilterMethod() throws Exception {
//        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "password");
//        Authentication token = new UsernamePasswordAuthenticationToken("admin", "password");
        Authentication token = new UsernamePasswordAuthenticationToken("user", "password");
        Authentication authentication = am.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<Spittle> spittleList = spittleService.getOffensiveSpittles();
        for (Spittle spittle: spittleList) {
            System.out.println(spittle);
        }
    }

    @Test
    public void testPreFilterMethod() throws Exception {
//        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "password");
//        Authentication token = new UsernamePasswordAuthenticationToken("admin", "password");
        Authentication token = new UsernamePasswordAuthenticationToken("user", "password");
        Authentication authentication = am.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<Spittle> spittleList = new ArrayList<Spittle>();
        for (int i = 0; i < 5; i++) {
            spittleList.add(new Spittle("message-" + i, new Date()));
        }
        spittleList.add(new Spittle("spitter", new Date()));
        spittleList.add(new Spittle("user", new Date()));
        spittleList.add(new Spittle("admin", new Date()));
        spittleService.deleteSpittles(spittleList);
    }

    @Test
    public void testPreFilterUsingPermissionEvaluator() throws Exception {
//        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "password");
//        Authentication token = new UsernamePasswordAuthenticationToken("admin", "password");
        Authentication token = new UsernamePasswordAuthenticationToken("user", "password");
        Authentication authentication = am.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<Spittle> spittleList = new ArrayList<Spittle>();
        for (int i = 0; i < 5; i++) {
            spittleList.add(new Spittle("message-" + i, new Date()));
        }
        spittleList.add(new Spittle("spitter", new Date()));
        spittleList.add(new Spittle("user", new Date()));
        spittleList.add(new Spittle("admin", new Date()));
        spittleService.deleteSpittles(spittleList);
    }






}
