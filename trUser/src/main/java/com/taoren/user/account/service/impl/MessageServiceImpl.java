package com.taoren.user.account.service.impl;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.taoren.service.user.MessageRemoteService;
import com.taoren.user.account.dao.MessageDao;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.account.util.ApnsConstants;
import com.taoren.user.easemob.apiUtil.utils.EasemobUtil;
import com.taoren.user.easemob.dao.EasemobDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
@Service
public class MessageServiceImpl implements MessageRemoteService {

    Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Autowired
    private MessageDao messageDao;


    @Autowired
    private EasemobDao easemobDao;


    public void sendMessage(Long targetUid, String msg) {
        EasemobUtil.sendMessages(easemobDao.getAppToken(), targetUid, msg);
    }

    public void pushMessage(String deviceToken, String msg, int count) {
//        System.out.println("deviceToken: " + deviceToken);
        String keyPath = ApnsConstants.APNS_KEY_PATH;
        String password= ApnsConstants.APNS_PASSWORD;
        ApnsService apnsService= APNS.newService()
                .withCert(keyPath, password)
                .withSandboxDestination()
                .build();

        String payload = APNS.newPayload()
                .alertBody(msg)
                .sound("default")
                .badge(1)
                .build();

        apnsService.push(deviceToken, payload);

    }

    public void saveMessageWhenLogout(long uid, String msg){
        messageDao.pushUserMessage(uid, msg);
    }

    public void pushMessageWhenLogin(String deviceToken, long uid) {
        long size = messageDao.userMessageSize(uid);
        if(size > 0){
            for(long i=0; i<size; i++){
                String msg = messageDao.popUserMessage(uid);
                pushMessage(deviceToken, msg, 1);
            }
        }
    }
}
