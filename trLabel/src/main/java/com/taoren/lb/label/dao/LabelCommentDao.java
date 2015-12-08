package com.taoren.lb.label.dao;


import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelComment;
import com.taoren.model.lb.LabelZan;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface LabelCommentDao extends BaseDao {


    public int insertLabelComment(LabelComment labelComment);
    public List<LabelComment> selectLabelComment(LabelComment labelComment);

    public int updateLabelComment(LabelComment labelComment);

    public int deleteLabelCommentById(Long id);
    public int deleteLabelComment(LabelComment labelComment);


    public Integer labelCommentCount(Map map);
    public List<LabelComment> labelCommentList(Map map);


}
