package com.wowjoy.boot.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.wowjoy.boot.shiro.cache.RedisCacheManager;
import com.wowjoy.boot.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scq
 * @date 2020-08-07 10:24:00
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建shiroFilter，负责拦截所有请求
     * @param webSecurityManager
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager webSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给Filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);
        // 配置系统受限资源
        Map<String, String> map = new HashMap<>();
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/user/registerview", "anon");
        map.put("register.html", "anon");
        map.put("login.html", "anon");
        map.put("/**", "authc");

        // 默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginview");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建安全管理器
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(CustomerRealm realm) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(realm);
        return webSecurityManager;
    }

    /**
     * 创建自定义realm
     * @return
     */
    @Bean
    public CustomerRealm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

//        customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("AuthenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("AuthorizationCache");
        return customerRealm;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
