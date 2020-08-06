package com.wowjoy.shiro.authenticator;

import com.wowjoy.shiro.realm.ConsumerMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @author scq
 * @date 2020-08-06 09:13:00
 */
public class ConsumerRealmMD5Authenticator {
    public static void main(String[] args) {
        // 创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 设置为自定义的Realm获取认证数据
        ConsumerMD5Realm consumerRealm = new ConsumerMD5Realm();
        // 设置MD5加密
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        consumerRealm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(consumerRealm);
        // 设置安全工具类的默认安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        // 创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaohong", "scq355");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        /**
         * 权限控制
         * RBAC(Role-Based Access Control):基于角色的访问控制（以角色为中心的访问控制）
         * RBAC(Resource_Based Access Control):基于资源的访问控制（以资源为中心的访问控制）
         */
        if (subject.isAuthenticated()) {
            // 基于角色权限控制
            System.out.println(subject.hasRole("super"));
            // 基于多角色权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));
            // 是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }
            System.out.println("============================");
            // 基于权限字符串的访问控制 资源标识符:操作:资源类型
            System.out.println("权限: " + subject.isPermitted("user:*:*"));
            System.out.println("权限: " + subject.isPermitted("product:create:01"));
            System.out.println("权限: " + subject.isPermitted("product:create:02"));
            // 分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:01", "order:*:01");
            for (boolean b : permitted) {
                System.out.println(b);
            }
            // 同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("user:*:01", "product:*");
            System.out.println(permittedAll);

        }
    }
}
