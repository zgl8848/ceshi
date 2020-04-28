package com.campus.grid.service.impl;

import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.ReseauSchoolBus;
import com.campus.grid.mapper.ReseauSchoolBusMapper;
import com.campus.grid.service.ReseauSchoolBusService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReseauSchoolBusServiceImpl implements ReseauSchoolBusService{

    private ReseauSchoolBusMapper reseauSchoolBusMapper;

    /**
     * 判断是否存在重复校车编号
     * @param carName 校车名称
     * @return 返回值为true时则说明不存在该校车可以添加,若返回值为false时则说明存在该校车提醒用户不能添加
     */
    @Override
    public boolean isBusId(String carName) {
        String busId = reseauSchoolBusMapper.isBusId(carName);
        if (StringUtils.isBlank(busId)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 新增校车设备
     * @param reseauSchoolBus 校车设备实体类
     * @return
     */
    @Override
    public boolean addSchoolBus(ReseauSchoolBus reseauSchoolBus) {
        //判断是否已经存在该子项检查
        String carReseauId = reseauSchoolBusMapper.isCheckName(reseauSchoolBus);
        if (StringUtils.isBlank(carReseauId)){
            //判断是否已经存在该校车
            String carId = reseauSchoolBusMapper.isCarId(reseauSchoolBus);
            if (StringUtils.isBlank(carId)){
                reseauSchoolBus.setCarId(UUID.randomUUID().toString().replaceAll("-", ""));
            }else {
                reseauSchoolBus.setCarId(carId);
            }
            //添加数据
            reseauSchoolBus.setCarReseauId(UUID.randomUUID().toString().replaceAll("-", ""));
            reseauSchoolBus.setDelFlag(0);
            reseauSchoolBus.setReseauAllot(0);
            reseauSchoolBus.setFounder(/*SecurityUtils.getUser().getId()*/"1");
            reseauSchoolBus.setCreationTime(new Date());
            return reseauSchoolBusMapper.addSchoolBus(reseauSchoolBus);
        }else {
            return false;
        }
    }

    /**
     * 编辑检查子项名称
     * @param reseauSchoolBus 校车设备实体类
     * @return
     */
    @Override
    public boolean updateCheckName(ReseauSchoolBus reseauSchoolBus) {
        //判断是否存在该数据
        String carReseauId = reseauSchoolBusMapper.isReseauByCarReseauId(reseauSchoolBus.getCarReseauId());
        if (StringUtils.isNotBlank(carReseauId)){
            if (StringUtils.isBlank(reseauSchoolBus.getCheckName())){
                return false;
            }
            //更新参数
            reseauSchoolBus.setChangePeople(SecurityUtils.getUser().getId());
            reseauSchoolBus.setChangeTime(new Date());
            return reseauSchoolBusMapper.updateCheckName(reseauSchoolBus);
        }else {
            return false;
        }
    }

    /**
     * 根据校车id删除校车网格(逻辑删除)
     * @param carId 校车id
     * @return
     */
    @Override
    public boolean deleteSchoolBusByCarId(String carId) {
        return reseauSchoolBusMapper.updateSchoolBusByCarId(carId);
    }

    /**
     * 删除子项检查
     * @param carReseauId 网格id
     * @return
     */
    @Override
    public boolean deleteCarReseauId(String carReseauId) {
        return reseauSchoolBusMapper.updateCarReseauId(carReseauId);
    }

    /**
     * 得到校车编号模块
     * @param schoolId 学校id
     * @param topType 网格类型
     * @return
     */
    @Override
    public List<ReseauSchoolBus> getCarName(String schoolId, String topType, String carId) {
        return reseauSchoolBusMapper.getCarName(schoolId, topType, carId);
    }
}
