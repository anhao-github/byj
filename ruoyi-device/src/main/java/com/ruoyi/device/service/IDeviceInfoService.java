package com.ruoyi.device.service;

import com.ruoyi.device.domain.DeviceInfo;

import java.util.List;

public interface IDeviceInfoService
{
    public int insertDeviceInfo(DeviceInfo deviceInfo);
    public int deleteDeviceInfoByIds(Long[] ids);
    public int updateDeviceInfo(DeviceInfo deviceInfo);
    public DeviceInfo selectDeviceInfoById(Long deviceId);
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo);
}