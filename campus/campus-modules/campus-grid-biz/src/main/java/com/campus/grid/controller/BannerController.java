package com.campus.grid.controller;

import com.campus.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.grid.api.entity.Banner;

import java.util.*;

/**
 * <p>
 * Banner图
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/banner")
@Api(value = "banner", description = "Banner图")
public class BannerController {

    @GetMapping("/list")
    public R<Map<String, List<Banner>>> getList() {
        Map<String, List<Banner>> map = new HashMap<>();

        List<Banner> list = new ArrayList<>();
        Banner banner_1 = new Banner("admin/file/campus-6ecb9ae3ba9e493abe938b481cf184a0.jpg");
        Banner banner_2 = new Banner("admin/file/campus-1a8b47ee2ead40f3bb28d6e1e4403aee.jpg");
        list.add(banner_1);
        list.add(banner_2);

        map.put("records", list);
        return new R<>(map);
    }
}
