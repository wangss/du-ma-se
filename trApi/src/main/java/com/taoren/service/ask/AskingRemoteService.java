package com.taoren.service.ask;

import com.taoren.model.ask.Asking;
import com.taoren.service.ask.model.AskingListRespDto;
import com.taoren.service.ask.model.AskingMediaListRespDto;
import com.taoren.service.ask.model.AskingRespDto;

import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public interface AskingRemoteService {
    /**
     * 增加asking
     */
    public AskingRespDto addAsking(Asking asking);
    public String addAsking4Json(String askingJson);


    /**
     * 删除asking
     */
    public AskingRespDto delAsking(Asking asking);
    public String delAsking4Json(String askingJson);

    /**
     * asking详情
     */
    public AskingListRespDto getAsking(Asking asking);
    public String getAsking4Json(String askingJson);

    /**
     * asking详情
     */
    public AskingMediaListRespDto getAskingMedia(long askingId);
    public String getAskingMedia4Json(long askingId);



    public AskingListRespDto askingList(Map map);
    public String askingList4Json(String paramJson);

}
