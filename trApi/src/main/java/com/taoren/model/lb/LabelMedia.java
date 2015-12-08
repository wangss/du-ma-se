package com.taoren.model.lb;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangshuisheng on 2015/5/28.
 */
public class LabelMedia implements Serializable {
    private Long id;//id
    private Long labelId;//标签id
    private String url;//url
    private Integer mediaType;//标签类型 1图片 2声音 3视频 4...
    private Timestamp addTime; //添加时间
    private Timestamp editTime;
    private Integer seq;//排队序列
    private String msg;//msg
    private Integer status;//标签状态 1创建 2生效 3失效 4废除

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
