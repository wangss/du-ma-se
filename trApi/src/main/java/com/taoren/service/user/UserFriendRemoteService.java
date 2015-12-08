package com.taoren.service.user;

import com.taoren.service.base.model.BaseRespDto;
import com.taoren.service.user.model.RelationDto;
import com.taoren.service.user.model.UserFriendRespDto;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface UserFriendRemoteService {

    /**
     * 添加好友
     * @return
     */
    public RelationDto addUserFriend(long uid, long friend);
    public String addUserFriend4Json(long uid, long friend);

    /**
     * 删除好友
     */
    public RelationDto delUserFriend(long uid, long friend);
    public String delUserFriend4Json(long uid, long friend);


    /**
     * 获取所有好友的id
     * @param uid
     * @return
     */
    public List<Long> getUserFriendIds(long uid);
    public String getUserFriendIds4Json(long uid);


    /**
     * 判断是否为好友
     * @param uid
     * @param targetUid
     * @return
     */
    public RelationDto isFriend(long uid, long targetUid);
    public String isFriend4Json(long uid, long targetUid);


}
