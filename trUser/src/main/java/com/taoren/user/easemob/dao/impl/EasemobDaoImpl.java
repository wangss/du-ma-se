package com.taoren.user.easemob.dao.impl;

import com.taoren.common.constant.RedisConstants;
import com.taoren.common.util.StringUtils;
import com.taoren.user.base.dao.impl.BaseDaoImpl;
import com.taoren.user.easemob.dao.EasemobDao;
import com.taoren.user.easemob.apiUtil.utils.EasemobUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshuisheng on 2015/6/23.
 */
@Component
public class EasemobDaoImpl extends BaseDaoImpl implements EasemobDao {
    @Override
    protected String getNameSpace() {
        return null;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // inject the template as ListOperations


    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valueUserOps;

    public String getAppToken() {
        String appToken = (String)valueUserOps.get(RedisConstants.EASYMOB_TOKEN);
        if(StringUtils.isEmpty(appToken)){
            try{
                appToken =  EasemobUtil.getAppToken();
                if(appToken == null){//再调用一次
                    appToken =  EasemobUtil.getAppToken();
                }
                valueUserOps.set(RedisConstants.EASYMOB_TOKEN , appToken, 7, TimeUnit.DAYS);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return appToken;
    }
}
