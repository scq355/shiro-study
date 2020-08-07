package com.wowjoy.boot.shiro.service;

import com.wowjoy.boot.shiro.entity.Perms;
import com.wowjoy.boot.shiro.entity.User;

import java.util.List;

public interface IUserService {
    void register(User user);

    User findByUserName(String userName);

    User findRolesByUserName(String username);

    List<Perms> findPermsByRoleId(Integer roleId);
}
