package com.ruoyi.device.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.device.domain.DeviceBorrow;
import com.ruoyi.device.mapper.DeviceBorrowMapper;
import com.ruoyi.device.service.IDeviceBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceBorrowServiceImpl implements IDeviceBorrowService
{
    @Autowired
    private DeviceBorrowMapper deviceBorrowMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param borrowId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DeviceBorrow selectDeviceBorrowByBorrowId(Long borrowId)
    {
        return deviceBorrowMapper.selectDeviceBorrowByBorrowId(borrowId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param deviceBorrow 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DeviceBorrow> selectDeviceBorrowList(DeviceBorrow deviceBorrow)
    {
        return deviceBorrowMapper.selectDeviceBorrowList(deviceBorrow);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param deviceBorrow 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDeviceBorrow(DeviceBorrow deviceBorrow)
    {
        deviceBorrow.setCreateTime(DateUtils.getNowDate());
        return deviceBorrowMapper.insertDeviceBorrow(deviceBorrow);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param deviceBorrow 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDeviceBorrow(DeviceBorrow deviceBorrow)
    {
        deviceBorrow.setUpdateTime(DateUtils.getNowDate());
        return deviceBorrowMapper.updateDeviceBorrow(deviceBorrow);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param borrowIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBorrowByBorrowIds(Long[] borrowIds)
    {
        return deviceBorrowMapper.deleteDeviceBorrowByBorrowIds(borrowIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param borrowId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBorrowByBorrowId(Long borrowId)
    {
        return deviceBorrowMapper.deleteDeviceBorrowByBorrowId(borrowId);
    }
}