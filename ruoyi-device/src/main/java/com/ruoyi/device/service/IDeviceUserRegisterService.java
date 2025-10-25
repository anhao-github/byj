package com.ruoyi.device.service;

import com.ruoyi.device.domain.DeviceUserRegister;

import java.util.List;

public interface IDeviceUserRegisterService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param regId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public DeviceUserRegister selectDeviceUserRegisterByRegId(Long regId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param deviceUserRegister 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DeviceUserRegister> selectDeviceUserRegisterList(DeviceUserRegister deviceUserRegister);

    /**
     * 新增【请填写功能名称】
     *
     * @param deviceUserRegister 【请填写功能名称】
     * @return 结果
     */
    public int insertDeviceUserRegister(DeviceUserRegister deviceUserRegister);

    /**
     * 修改【请填写功能名称】
     *
     * @param deviceUserRegister 【请填写功能名称】
     * @return 结果
     */
    public int updateDeviceUserRegister(DeviceUserRegister deviceUserRegister);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param regIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteDeviceUserRegisterByRegIds(Long[] regIds);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param regId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDeviceUserRegisterByRegId(Long regId);
}
