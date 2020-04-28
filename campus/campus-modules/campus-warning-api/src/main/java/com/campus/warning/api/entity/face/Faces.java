package com.campus.warning.api.entity.face;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faces  {
	/**
	 * 	抓拍到的人脸图片的URL(可能位于设备或ASW服务上)
	 */
    private String URL ;
    /**
     * 年龄信息
     */
    private Age age;
    /**
     * 人脸坐标
     */
    private FaceRect faceRect;
    /**
     * 	性别信息
     */
    private Gender gender;
    /**
     * 	是否戴眼镜
     */
    private Glass glass;
    /**
     * 	识别信息
     */
    private List<Identify> identify;

}