package com.campus.grid.api.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author campus on 2019/5/15 10:49
 */
@Data
public class Banner implements Serializable {

    private String imgUrl;

    public Banner(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
