package com.example.security.service.impl;

import com.example.security.entity.Permission;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.jwt.JwtUser;
import com.example.security.mapper.PermissionMapper;
import com.example.security.mapper.UserMapper;
import com.example.security.vo.PermissionItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public JwtUser loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException(String.format("'%s'.这个用户不存在", s));
        }
        List<SimpleGrantedAuthority> collect = user.getRoles().stream()
                .map(Role::getRolename)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        List<Long> roleIds = user.getRoles()
                .stream()
                .map(role -> role.getId())
                .collect(Collectors.toList());

        List<Permission> permissions = permissionMapper.getPermissionByRoleIds(roleIds);

        // 权限集合
        List<PermissionItem> permissionItems = Optional.ofNullable(permissions).orElse(new ArrayList<>())
                .stream()
                .map(permission -> getPermissionItem(permission))
                .collect(Collectors.toList());

        return new JwtUser(user.getId(),user.getUsername(), user.getPassword(), user.getState(), collect, permissionItems);
    }

    public PermissionItem getPermissionItem(Permission permission) {
        PermissionItem item = new PermissionItem();
        item.setId(permission.getPer_id());
        item.setName(permission.getPer_name());
        item.setCode(permission.getPer_resource());
        return item;
    }
}
