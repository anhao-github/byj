package com.ruoyi.device.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.device.domain.DeviceUserRegister;
import com.ruoyi.device.mapper.DeviceUserRegisterMapper;
import com.ruoyi.device.service.IDeviceUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceUserRegisterServiceImpl implements IDeviceUserRegisterService
{
    @Autowired
    private DeviceUserRegisterMapper deviceUserRegisterMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param regId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DeviceUserRegister selectDeviceUserRegisterByRegId(Long regId)
    {
        return deviceUserRegisterMapper.selectDeviceUserRegisterByRegId(regId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param deviceUserRegister 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DeviceUserRegister> selectDeviceUserRegisterList(DeviceUserRegister deviceUserRegister)
    {
        return deviceUserRegisterMapper.selectDeviceUserRegisterList(deviceUserRegister);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param deviceUserRegister 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDeviceUserRegister(DeviceUserRegister deviceUserRegister)
    {
        deviceUserRegister.setCreateTime(DateUtils.getNowDate());
        return deviceUserRegisterMapper.insertDeviceUserRegister(deviceUserRegister);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param deviceUserRegister 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDeviceUserRegister(DeviceUserRegister deviceUserRegister)
    {
        deviceUserRegister.setUpdateTime(DateUtils.getNowDate());
        return deviceUserRegisterMapper.updateDeviceUserRegister(deviceUserRegister);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param regIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUserRegisterByRegIds(Long[] regIds)
    {
        return deviceUserRegisterMapper.deleteDeviceUserRegisterByRegIds(regIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param regId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUserRegisterByRegId(Long regId)
    {
        return deviceUserRegisterMapper.deleteDeviceUserRegisterByRegId(regId);
    }
}
