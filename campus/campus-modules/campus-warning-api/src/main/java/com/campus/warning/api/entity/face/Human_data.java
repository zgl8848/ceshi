package com.campus.warning.api.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human_data {
	/**
	 * 	比对到的人脸的图片URL
	 */
    private String face_picurl;

}