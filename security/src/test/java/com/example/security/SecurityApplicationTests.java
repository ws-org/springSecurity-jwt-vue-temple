package com.example.security;

import com.example.security.jwt.JwtUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecurityApplicationTests {


    @Test
    public void contextLoads() {

    }

    @Test
    public void test_generateToken() {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserid("1");
        jwtUser.setUsername("admin");
        String token = this.generateToken(jwtUser);
        log.info("token:{}",  token);
    }




    public String generateToken(JwtUser jwtUser) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", jwtUser.getUsername());
        claims.put("userid", jwtUser.getUserid());
        claims.put("created", new Date());
        Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 60 * 1000);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, "eyJleHAiOjE1NDMyMDUyODUsInN1YiI6ImFkbWluIiwiY3JlYXRlZCI6MTU0MDYxMzI4N").compact();
    }


}

