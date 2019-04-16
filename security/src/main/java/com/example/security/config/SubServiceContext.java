package com.example.security.config;

import com.example.security.util.DateTypeAdapter;
import com.example.security.util.SpringfoxJsonToGsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;


@Configuration
public class SubServiceContext {

    @Bean
    public Gson gson(){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class,new DateTypeAdapter());
        gsonBuilder.registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter());
        gsonBuilder.disableHtmlEscaping();
        gsonBuilder.serializeNulls(); // null、空指也进行转换
        return gsonBuilder.create();
    }

}
