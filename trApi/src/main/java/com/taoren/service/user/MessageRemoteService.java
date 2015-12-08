package com.taoren.service.user;

import com.taoren.model.user.User;
import com.taoren.service.user.model.UserRespDto;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface MessageRemoteService {

    /**
     * 给用户发送消息
     * @param targetUid
     * @param msg
     * @return
     */
    public void sendMessage(Long targetUid, String msg);

    public void pushMessage(String deviceToken, String msg, int count);

    public void saveMessageWhenLogout(long uid, String msg);
    public void pushMessageWhenLogin(String deviceToken,long uid);



//    http://www.mamicode.com/info-detail-443177.html
//
//    https://github.com/notnoop/java-apns

}
