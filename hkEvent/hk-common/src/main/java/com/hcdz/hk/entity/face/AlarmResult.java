package com.hcdz.hk.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmResult  {
	/**
	 * 人脸信息
	 */
    private List<Faces > faces ;
    /**
     * 
     */
    private TargetAttrs targetAttrs;

}