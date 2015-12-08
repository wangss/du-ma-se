package com.taoren.service.lb;

import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelZan;
import com.taoren.service.lb.model.LabelListRespDto;
import com.taoren.service.lb.model.LabelRespDto;

import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public interface LabelRemoteService {
    /**
     * 增加label
     */
    public LabelRespDto addLabel(Label label);
    public String addLabel4Json(String labelJson);

    /**
     * 修改label
     */
    public LabelRespDto editLabel(Label label);
    public String editLabel4Json(String labelJson);

    /**
     * 删除label
     */

    public LabelRespDto delLabel(Label label);
    public String delLabel4Json(String labelJson);

    /**
     * label
     */
    public LabelListRespDto getLabel(Label label);
    public String getLabel4Json(String labelJson);

    /**
     * label点赞
     */
    public int zanLabel(LabelZan zan);
    public int zanLabel4Json(String zanJson);


    public LabelListRespDto labelList(Map map);
    public String labelList4Json(String paramJson);

}
