package com.taoren.lb.label.dao;


import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.lb.LabelMedia;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface LabelMediaDao extends BaseDao {


    public int insertLabelMedia(LabelMedia labelMedia);

    public int insertLabelMediaList(List<LabelMedia> labelMediaList);

    public LabelMedia selectLabelMediaById(Long id);

    public List<LabelMedia> selectLabelMedia(LabelMedia labelMedia);

    public List<LabelMedia> selectLabelMediaList(LabelMedia labelMedia);

    public int updateLabelMedia(LabelMedia labelMedia);

    public int updateLabelMediaList(List<LabelMedia> labelMediaList);

    public int deleteLabelMediaById(Long id);



}
