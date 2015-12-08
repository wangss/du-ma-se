package com.taoren.service.lb;

import com.taoren.model.lb.LabelComment;
import com.taoren.service.lb.model.LabelCommentListRespDto;
import com.taoren.service.lb.model.LabelCommentRespDto;

import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public interface LabelCommentRemoteService {
    /**
     * 增加labelComment
     */
    public LabelCommentRespDto addLabelComment(LabelComment labelComment);
    public String addLabelComment4Json(String commentJson);


    /**
     * 删除label
     */

    public LabelCommentRespDto delLabelComment(LabelComment labelComment);
    public String delLabelComment4Json(String commentJson);

    /**
     * label详情
     */
    public LabelCommentListRespDto getLabelComment(LabelComment labelComment);
    public String getLabelComment4Json(String commentJson);



    public LabelCommentListRespDto labelCommentList(Map map);
    public String labelCommentList4Json(String paramJson);

}
