package com.taoren.service.lb.model;

import com.taoren.model.lb.Label;
import com.taoren.service.base.model.BaseRespDto;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 */
public class LabelRespDto extends BaseRespDto{
    private Label label;
    private Long labelId;


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
