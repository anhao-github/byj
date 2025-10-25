package com.ruoyi.device.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.domain.DeviceType;
import com.ruoyi.device.mapper.DeviceTypeMapper;
import com.ruoyi.device.service.IDeviceInfoService;
import com.ruoyi.device.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class DeviceTypeServiceImpl implements IDeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Override
    public int insertDerviceType(DeviceType deviceType) {
        return deviceTypeMapper.insertDeviceType(deviceType);
    }

    @Override
    public AjaxResult deleteDeviceTypeByIds(Long[] ids) {
        //ids 7, 8, 9
//    return deviceTypeMapper.deleteDeviceTypeByIds(ids);
        for(Long id : ids) {
            //判断信息表中有没有具体的设备
            //调用信息表的查询功能
            DeviceInfo deviceInfo = new DeviceInfo();
            // deviceInfo的对象中的属性只有typeId是有值的
            deviceInfo.setTypeId(id);
            //判断有没有查到数据
            List<DeviceInfo> deviceInfos = deviceInfoService.selectDeviceInfoList(deviceInfo);
            if (deviceInfos != null && !deviceInfos.isEmpty()) {
                return AjaxResult.warn("待删除的类别下有设备，删除失败");
            }
        }
        int ret = deviceTypeMapper.deleteDeviceTypeByIds(ids);
        return ret > 0 ? AjaxResult.success() : AjaxResult.error();
    }
    @Override
    public int updateDeviceType(DeviceType deviceType) {
        return deviceTypeMapper.updateDeviceType(deviceType);
    }

    @Override
    public DeviceType selectDeviceTypeById(Long typeId) {
        return deviceTypeMapper.selectDeviceTypeById( typeId);
    }

    @Override
    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType) {
        return deviceTypeMapper.selectDeviceTypeList(deviceType);
    }


}

