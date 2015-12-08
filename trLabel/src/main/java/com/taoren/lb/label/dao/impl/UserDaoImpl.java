package com.taoren.lb.label.dao.impl;

import com.taoren.common.constant.RedisConstants;
import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.lb.label.dao.UserDao;
import com.taoren.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangshuisheng on 2015/5/27.
 *
 * 此处userdao只为取redis中的数据
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    protected String getNameSpace() {
        return null;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // inject the template as ListOperations


    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valueUserOps;



    public User getTokenUser(String token) {
        return (User)valueUserOps.get(RedisConstants.TOKEN_PRE + token);
    }

}
