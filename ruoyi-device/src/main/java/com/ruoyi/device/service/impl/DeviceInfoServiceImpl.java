package com.ruoyi.device.service.impl;

import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.mapper.DeviceInfoMapper;
import com.ruoyi.device.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoServiceImpl implements IDeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;


    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo) {
        return  deviceInfoMapper.insertDeviceInfo(deviceInfo);
    }

    @Override
    public int deleteDeviceInfoByIds(Long[] ids) {
      return deviceInfoMapper.deleteDeviceInfoByIds(ids);
    }

    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) {
       return deviceInfoMapper.updateDeviceInfo(deviceInfo);
    }

    @Override
    public DeviceInfo selectDeviceInfoById(Long deviceId) {
        return deviceInfoMapper.selectDeviceInfoById( deviceId);
    }

    @Override
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo) {
        return deviceInfoMapper.selectDeviceInfoList(deviceInfo);
    }


}