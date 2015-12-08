package com.taoren.service.sc;


import com.taoren.model.ask.Asking;
import com.taoren.service.ask.model.EsAskingDto;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public interface AskingSearchRemoteService {

    public void indexAsking(Asking asking);
    public void indexAsking4Json(String askingJson);


    public void deleteAsking(long uid, long askingId);
    public void replyAsking(long uid, long askingId, int action);


    public EsAskingDto getEsAsking(long uid, long askingId);

}
