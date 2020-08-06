package com.wowjoy.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author scq
 * @date 2020-08-06 09:15:00
 */
public class ConsumerMD5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息：" + principal);

        //根据身份信息 用户名 获取当前用户的角色信息以及权限信息，scq-admin,user
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin");
        authorizationInfo.addRole("user");
        // 将数据库中查询的权限信息赋值给权限对象
        authorizationInfo.addStringPermission("user:update:*");
        authorizationInfo.addStringPermission("user:*:01");
        authorizationInfo.addStringPermission("product:create");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        if ("scq".equals(principal)) {
            String password = "7db8bbf11221ac7b363374664e96339f";
            String salt = "OlD*(n3";
            /**
             * 参数1：数据库用户名
             * 参数2：数据库md5+salt之后的密码
             * 参数3：注册时的随机盐
             * 参数4：realm的名字
             */
            return new SimpleAuthenticationInfo(principal,
                    password,
                    ByteSource.Util.bytes(salt),
                    this.getName());
        }
        return null;
    }
}
