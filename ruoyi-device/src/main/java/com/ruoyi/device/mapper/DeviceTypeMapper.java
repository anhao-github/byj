package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.DeviceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  DeviceTypeMapper {
    public int insertDeviceType(DeviceType deviceType);

    public int deleteDeviceTypeByIds(Long[] ids);

    public int updateDeviceType(DeviceType deviceType);
//    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType);

    public DeviceType selectDeviceTypeById(Long typeId);

    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType);

}