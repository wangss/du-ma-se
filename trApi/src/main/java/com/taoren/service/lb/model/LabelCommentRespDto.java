package com.taoren.service.lb.model;

import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelComment;
import com.taoren.service.base.model.BaseRespDto;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 */
public class LabelCommentRespDto extends BaseRespDto{
    private Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
