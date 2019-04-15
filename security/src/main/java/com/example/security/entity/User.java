package com.example.security.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Autoor:
 * @Date:2019/1/4
 * @Description：
 */
@Data
public class User implements Serializable {

    private String id;

    private String avatar;

    private String username;

    private String password;

    private String phone;


    private Integer state;



    private Set<Role> roles;
}
