package com.thinkingcao.store.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thinkingcao.store.system.common.ActiverUser;
import com.thinkingcao.store.system.entity.User;
import com.thinkingcao.store.system.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc:
 * @author: cao_wencao
 * @date: 2020-09-21 22:53
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * @desc: 授权
     * @auth: cao_wencao
     * @date: 2020/9/21 23:16
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {



        return null;
    }

    /**
     * @desc: 授权
     * @auth: cao_wencao
     * @date: 2020/9/21 23:16
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname",authenticationToken.getPrincipal().toString());
        User user = userService.getOne(queryWrapper);
        if (null != user){
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            String pwd = user.getPwd();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, pwd, salt,
                    this.getName());
            return info;
        }
        return null;
    }
}
