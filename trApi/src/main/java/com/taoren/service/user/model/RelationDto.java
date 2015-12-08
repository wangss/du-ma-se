package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;

/**
 * Created by Administrator on 2015/5/21.
 */
public class RelationDto extends BaseRespDto {

    private Boolean refuse;
    private Boolean refused;

    private Boolean friend;

    private Integer privacyFind;//1可通过手机号找到我; 2隐身
    private Integer privacyStrangerTalk;//接收陌生人消息; 2不接收陌生人消息


    public Boolean getRefuse() {
        return refuse;
    }

    public void setRefuse(Boolean refuse) {
        this.refuse = refuse;
    }

    public Boolean getRefused() {
        return refused;
    }

    public void setRefused(Boolean refused) {
        this.refused = refused;
    }

    public Boolean getFriend() {
        return friend;
    }

    public void setFriend(Boolean friend) {
        this.friend = friend;
    }

    public Integer getPrivacyFind() {
        return privacyFind;
    }

    public void setPrivacyFind(Integer privacyFind) {
        this.privacyFind = privacyFind;
    }

    public Integer getPrivacyStrangerTalk() {
        return privacyStrangerTalk;
    }

    public void setPrivacyStrangerTalk(Integer privacyStrangerTalk) {
        this.privacyStrangerTalk = privacyStrangerTalk;
    }
}
