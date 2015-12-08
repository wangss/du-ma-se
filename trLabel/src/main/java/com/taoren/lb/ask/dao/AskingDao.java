package com.taoren.lb.ask.dao;


import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.ask.Asking;
import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelZan;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface AskingDao extends BaseDao {


    public int insertAsking(Asking asking);
    public Asking selectAskingById(Long id);
    public List<Asking> selectAsking(Asking asking);
    public int updateAsking(Asking asking);
    public int deleteAskingById(Long id);
    public int deleteAsking(Asking asking);

    public Integer askingCount(Map map);
    public List<Asking> askingList(Map map);


}
