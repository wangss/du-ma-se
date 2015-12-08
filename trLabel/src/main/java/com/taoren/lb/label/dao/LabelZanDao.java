package com.taoren.lb.label.dao;

import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.lb.LabelZan;
import com.taoren.model.lb.LabelZanUser;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface LabelZanDao extends BaseDao{

    public int insertZan(LabelZan labelZan);

    public int deleteZan(LabelZan labelZan);

    public List<LabelZanUser> zanUserList(long labelId);
}
