package com.taoren.user.account.dao.impl;

import com.taoren.common.constant.RedisConstants;
import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;
import com.taoren.user.account.dao.MessageDao;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.base.dao.impl.BaseDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // inject the template as ListOperations

    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valueUserOps;

    @Resource(name="redisTemplate")
    private ListOperations<String, Object> listOperations;

    @Override
    protected String getNameSpace() {
        return "UserMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public void pushUserMessage(long uid, String msg) {
        listOperations.leftPush(RedisConstants.APNS_MSG_UID + uid, msg);
    }

    public String popUserMessage(long uid) {
        return (String)listOperations.rightPop(RedisConstants.APNS_MSG_UID + uid);
    }


    public long userMessageSize(long uid) {
        return listOperations.size(RedisConstants.APNS_MSG_UID + uid);
    }
}
