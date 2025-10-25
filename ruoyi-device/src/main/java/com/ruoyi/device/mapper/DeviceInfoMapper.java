package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceInfoMapper
{
    public int insertDeviceInfo(DeviceInfo deviceInfo);
    public int deleteDeviceInfoByIds(Long[] ids);

    public int updateDeviceInfo(DeviceInfo deviceInfo);

    public DeviceInfo selectDeviceInfoById(Long deviceId);
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo);
}