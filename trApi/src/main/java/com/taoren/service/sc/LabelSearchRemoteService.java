package com.taoren.service.sc;

import com.taoren.model.lb.Label;
import com.taoren.service.user.model.EsPositionDto;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public interface LabelSearchRemoteService {

    public void indexLabel(Label label);
    public void indexLabel4Json(String labelJson);

    public void updateLabel(Label label);
    public void updateLabel4Json(String labelJson);

    public void deleteLabel(long uid, long labelId);

    public void zanLabel(long uid, long labelId, int action);

    public void commentLabel(long uid, long labelId, int action);


    public EsPositionDto getLabelPosition(long uid, long labelId);
    public String getUserPosition4Json(long uid, long labelId);

}
