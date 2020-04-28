package com.campus.admin.api.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrueNameVO {

    private String userId;  //巡查人
    private String rectification;   //整改人

    public TrueNameVO(TrueNameVO trueNameVO) {
        this.userId=trueNameVO.getUserId();
        this.rectification=trueNameVO.getRectification();
    }
}
