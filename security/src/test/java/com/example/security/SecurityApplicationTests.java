package com.example.security;

import com.example.security.jwt.JwtUser;
import com.example.security.util.AesEncryptUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Test
    public void encrypt() {
        String passwd = AesEncryptUtil.encrypt("123456");
        log.info("passwod:{}", passwd);
        System.out.println("passwod:{}" + passwd);
        // 90f1381b138f7987e3685b38c82f7d7d
    }

    @Test
    public void decrypt() {
        String passwd = AesEncryptUtil.decrypt("90f1381b138f7987e3685b38c82f7d7d");
        log.info("passwod:{}", passwd);
    }

    @Test
    public void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);

    }
}

