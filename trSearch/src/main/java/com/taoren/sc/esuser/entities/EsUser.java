package com.taoren.sc.esuser.entities;

import com.taoren.sc.constants.IndexTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Created by wangshuisheng on 2015/6/10.
 */
@Document(indexName = IndexTypes.TAOREN_INDEX, type = IndexTypes.TAOREN_TYPE_USER)
public class EsUser {

    @Id
    private String uid;//

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String trId;//

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String phone;//

    private String nickname;

    private Date birthday;

    private Integer gender;//性别

    private String avatar;//头像

    private String signature;//个性签名

    private Integer privacyFind;//1能找到我，2别人找不到我


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
