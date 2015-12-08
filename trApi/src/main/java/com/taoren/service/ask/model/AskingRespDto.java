package com.taoren.service.ask.model;

import com.taoren.model.ask.Asking;
import com.taoren.model.lb.Label;
import com.taoren.service.base.model.BaseRespDto;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 */
public class AskingRespDto extends BaseRespDto{
    private Long askingId;
    private Asking asking;

    public Long getAskingId() {
        return askingId;
    }

    public void setAskingId(Long askingId) {
        this.askingId = askingId;
    }

    public Asking getAsking() {
        return asking;
    }

    public void setAsking(Asking asking) {
        this.asking = asking;
    }
}
