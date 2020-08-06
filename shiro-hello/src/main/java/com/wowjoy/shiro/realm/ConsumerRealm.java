package com.wowjoy.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author scq
 * @date 2020-08-06 08:38:00
 */
public class ConsumerRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token中获取用户名
        String principal = (String) token.getPrincipal();
        System.out.println(principal);
        // 根据身份信息使用jdbc mybatis从数据库中查询相关信息
        if ("scq".equals(principal)) {
            // 参数1：返回数据库中正确的用户名
            // 参数2：返回数据库中正确的密码
            // 参数3：提供当前realm的名字
            return new SimpleAuthenticationInfo(principal, "scq355", this.getName());
        }
        return null;
    }
}
