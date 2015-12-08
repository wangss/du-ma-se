package com.taoren.model.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.taoren.common.constant.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;


/**
 * Created by wangshuisheng on 2015/5/22.
 */
public class UserInfo extends User{
    private Long uid;//关联 User.id
    private String nickname;

    @JSONField(format = Constants.DATE_FORMAT)
    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private Date birthday;//生日，可计算年龄

    private Integer gender;//性别 1男;2女;3保密

    private String email;
    private String weChat;
    private String qq;

    private Integer astrology;//星座： 1白羊座; 2金牛座; 3双子座; 4巨蟹座; 5狮子座; 6处女座; 7天秤座; 8天蝎座; 9射手座; 10摩羯座; 11水瓶座; 12双鱼座;
    private Integer relationship;//情感 1保密;2单身；3恋爱中; 4已婚
    private String hometown;
    private String region;
    private String school;
    private String homepage;
    private String avatar;//头像

    private String job;//职业
    private String hobby;//兴趣爱好

    private String signature;//个性签名

    private Integer privacyFind;//1可通过手机号找到我; 2隐身
    private Integer privacyStrangerTalk;//接收陌生人消息; 2不接收陌生人消息

//    @JSONField(format = Constants.DATE_TIME_FORMAT)
    private Timestamp infoUpdateTime;//用户资料修改时间
    private String infoUpdateMsg;//用户资料修改msg


    private Double distance;//距离
    private Date activeTime;//用户资料修改时间

    private boolean friend;


    /**
     * 转化成json返回的时候去除不必要的信息
     *  wangshuisheng 2015-05-28
     */
    public void removeSomeUnneeded(){
        this.setRegisterPhone(null);
//        this.setAreaCode(null);
        this.setPassword(null);
        this.setAccountUpdateTime(null);
//        this.setCreateTime(null);
        this.setInfoUpdateTime(null);
        this.setStatus(null);
        this.setLongitude(null);
        this.setLatitude(null);
        this.setDeviceType(null);
        this.setId(null);
    }

    /**
     * 去除用户私有信息(非常重要)
     * wangshuisheng 2015-05-28
     */
    public void removeSomePrivacy(){

        removeSomeUnneeded();
        this.setPhone(null);//这个一定不能少
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getAstrology() {
        return astrology;
    }

    public void setAstrology(Integer astrology) {
        this.astrology = astrology;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public Timestamp getInfoUpdateTime() {
        return infoUpdateTime;
    }

    public void setInfoUpdateTime(Timestamp infoUpdateTime) {
        this.infoUpdateTime = infoUpdateTime;
    }

    public String getInfoUpdateMsg() {
        return infoUpdateMsg;
    }

    public void setInfoUpdateMsg(String infoUpdateMsg) {
        this.infoUpdateMsg = infoUpdateMsg;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
