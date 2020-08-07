package com.wowjoy.boot.shiro.realms;

import com.wowjoy.boot.shiro.entity.Perms;
import com.wowjoy.boot.shiro.entity.User;
import com.wowjoy.boot.shiro.salt.CustomerByteSource;
import com.wowjoy.boot.shiro.service.IUserService;
import com.wowjoy.boot.shiro.service.UserService;
import com.wowjoy.boot.shiro.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author scq
 * @date 2020-08-07 10:33:00
 */
public class CustomerRealm extends AuthorizingRealm {

    /**
     * 授权处理
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("授权调用：" + primaryPrincipal);
        // 根据主身份获取角色权限信息
        IUserService userService = (IUserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findRolesByUserName(primaryPrincipal);
        // 授权角色信息
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                authorizationInfo.addRole(role.getName());
                // 权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    perms.forEach(perm -> authorizationInfo.addStringPermission(perm.getName()));
                }
            });
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 认证处理
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("====================");
        String principal = (String) token.getPrincipal();
        IUserService userService = (IUserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUserName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), new CustomerByteSource(user.getSalt()), this.getName());
        }
        return null;
    }
}
