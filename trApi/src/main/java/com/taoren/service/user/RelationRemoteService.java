package com.taoren.service.user;

import com.taoren.service.user.model.RelationDto;
import com.taoren.service.user.model.UserRefuseRespDto;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface RelationRemoteService {


    /**
     * 拒绝接收对方消息
     */
    public UserRefuseRespDto addUserRefuse(long uid, long refuseUid);
    public String addUserRefuse4Json(long uid, long refuseUid);

    /**
     * 解除 拒绝对方消息
     */
    public UserRefuseRespDto delUserRefuse(long uid, long refuseUid);
    public String delUserRefuse4Json(long uid, long refuseUid);


    /**
     * 对方是否拒绝我的对话
     * @param uid
     * @param targetUid
     * @return
     */
    public RelationDto isRefused(long uid, long targetUid);
    public String isRefused4Json(long uid, long targetUid);



    /**
     * 获取与对方的所有关系
     * @param uid
     * @param targetUid
     * @return
     */
    public RelationDto getRelation(long uid, long targetUid);
    public String getRelation4Json(long uid, long targetUid);

}
