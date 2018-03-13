package com.lanou.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongren1.
 */
public class MyPerRealm extends AuthorizingRealm{

    @Override
    public String getName() {
        return "myPerRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 授权的方法

        // 将认证方法中返回的对象取出
        String username =
                (String) principalCollection.getPrimaryPrincipal();

        // ***********假数据开始************

        List<String> permissionList = new ArrayList<>();

        permissionList.add("user:create");
        permissionList.add("user:query");

        List<String> roleList = new ArrayList<>();

        roleList.add("CEO");
        roleList.add("DE");

        // ***********假数据结束************

        // 授权信息对象, 用来保存所有的角色和权限信息
        SimpleAuthorizationInfo info =
                new SimpleAuthorizationInfo();

        info.addStringPermissions(permissionList);
        info.addRoles(roleList);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 认证的方法


        System.out.println("===认证开始===");
        // 1. 获取token中的用户名

        String username =
                (String) authenticationToken.getPrincipal();

        System.out.println(username);
        // ************假数据开始**************
        if (!"wangwu".equals(username)) {
//            return null;
            throw new UnknownAccountException("未知账号/没有该账号");
        }
        // ************假数据结束**************

        // 如果账号存在, 接着判断密码

        String password =
                new String((char[]) authenticationToken.getCredentials());
        // ************假数据开始**************

        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        // ************假数据结束**************

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
