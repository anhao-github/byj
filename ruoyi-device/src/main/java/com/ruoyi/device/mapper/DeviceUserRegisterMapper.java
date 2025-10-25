package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.DeviceUserRegister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceUserRegisterMapper
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
     * 删除【请填写功能名称】
     *
     * @param regId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDeviceUserRegisterByRegId(Long regId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param regIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceUserRegisterByRegIds(Long[] regIds);
}
