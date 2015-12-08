package com.taoren.lb.ask.dao;


import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.ask.AskingMedia;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface AskingMediaDao extends BaseDao {
    
    public int insertAskingMedia(AskingMedia askingMedia);

    public int insertAskingMediaList(List<AskingMedia> AskingMediaList);

    public AskingMedia selectAskingMediaById(Long id);

    public List<AskingMedia> selectAskingMedia(AskingMedia AskingMedia);
    
    public int deleteAskingMediaById(Long id);
    
}
