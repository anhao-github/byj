package com.ruoyi.device.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.domain.DeviceType;

import java.util.List;

public interface IDeviceTypeService {
    /*
    * 新增
    * */
    public  int insertDerviceType(DeviceType deviceType);
    public AjaxResult deleteDeviceTypeByIds(Long[] ids);
    public int updateDeviceType(DeviceType deviceType);
    //public List<DeviceType> selectDeviceTypeList(DeviceType deviceType);
    public DeviceType selectDeviceTypeById(Long typeId);
    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType);
}
