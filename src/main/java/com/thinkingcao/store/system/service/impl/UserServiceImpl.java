package com.thinkingcao.store.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkingcao.store.system.entity.User;
import com.thinkingcao.store.system.mapper.UserMapper;
import com.thinkingcao.store.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @author: cao_wencao
 * @date: 2020-09-21 23:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
