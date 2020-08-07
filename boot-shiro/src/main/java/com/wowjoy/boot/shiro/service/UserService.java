package com.wowjoy.boot.shiro.service;

import com.wowjoy.boot.shiro.dao.UserMapper;
import com.wowjoy.boot.shiro.entity.Perms;
import com.wowjoy.boot.shiro.entity.Role;
import com.wowjoy.boot.shiro.entity.User;
import com.wowjoy.boot.shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author scq
 * @date 2020-08-07 11:13:00
 */
@Transactional
@Service("userService")
public class UserService implements IUserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param user
     */
    public void register(User user) {
        // 生成随机盐
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        // 明文密码 MD5 + salt + hash 散列
        System.out.println("注册：用户信息：" + user.toString());
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userMapper.save(user);
    }

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUsername(username);
    }

    public List<Perms> findPermsByRoleId(Integer roleId) {
        return userMapper.findPermsByRoleId(roleId);
    }

}
