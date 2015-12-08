package com.taoren.model.lb;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangshuisheng on 2015/7/2.
 */
public class LabelZan implements Serializable {
    private Long uid;
    private Long labelId;

    private Integer action;//1赞，0不赞

    private Timestamp createTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
