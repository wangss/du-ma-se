package com.taoren.lb.label.dao;


import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelZan;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface LabelDao extends BaseDao {


    public int insertLabel(Label label);
    public Label selectLabelById(Long id);
    public List<Label> selectLabel(Label label);
    public int updateLabel(Label label);
    public int deleteLabelById(Long id);
    public int deleteLabelByUid(Long uid);

    public int updateLabelZan(LabelZan zan);


    public Integer labelCount(Map map);
    public List<Label> labelList(Map map);


}
