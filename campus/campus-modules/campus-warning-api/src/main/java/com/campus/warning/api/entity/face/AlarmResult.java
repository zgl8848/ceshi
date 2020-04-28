package com.campus.warning.api.entity.face;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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