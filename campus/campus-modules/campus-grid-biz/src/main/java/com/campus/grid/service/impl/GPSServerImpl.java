package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.GPS;
import com.campus.grid.mapper.GPSMapper;
import com.campus.grid.service.GPSServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GPSServerImpl extends ServiceImpl<GPSMapper, GPS> implements GPSServer {
}
