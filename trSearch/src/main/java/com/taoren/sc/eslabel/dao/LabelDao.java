package com.taoren.sc.eslabel.dao;

import com.taoren.model.lb.Label;
import com.taoren.sc.base.dao.BaseDao;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
public interface LabelDao extends BaseDao {

    public Label selectLabelById(Long id);
    public List<Label> selectLabel(Label label);
}
