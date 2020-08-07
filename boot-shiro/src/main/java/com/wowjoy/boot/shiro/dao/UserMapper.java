package com.wowjoy.boot.shiro.dao;

import com.wowjoy.boot.shiro.entity.Perms;
import com.wowjoy.boot.shiro.entity.Role;
import com.wowjoy.boot.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author scq
 * @date 2020-08-07 11:12:00
 */
@Mapper
public interface UserMapper {
    void save(User user);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUserName(String username);

    User findRolesByUsername(String username);

    List<Perms> findPermsByRoleId(Integer roleId);
}
