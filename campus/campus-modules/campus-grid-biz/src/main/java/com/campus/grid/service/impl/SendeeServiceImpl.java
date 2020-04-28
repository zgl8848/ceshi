package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.alarmManagement.Sendee;
import com.campus.grid.mapper.SendeeMapper;
import com.campus.grid.service.SendeeService;
import org.springframework.stereotype.Service;

@Service
public class SendeeServiceImpl extends ServiceImpl<SendeeMapper, Sendee> implements SendeeService {
}