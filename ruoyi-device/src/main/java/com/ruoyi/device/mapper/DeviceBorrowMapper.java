package com.ruoyi.device.mapper;


import com.ruoyi.device.domain.DeviceBorrow;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2025-05-07
 */
public interface DeviceBorrowMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param borrowId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public DeviceBorrow selectDeviceBorrowByBorrowId(Long borrowId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param deviceBorrow 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DeviceBorrow> selectDeviceBorrowList(DeviceBorrow deviceBorrow);

    /**
     * 新增【请填写功能名称】
     *
     * @param deviceBorrow 【请填写功能名称】
     * @return 结果
     */
    public int insertDeviceBorrow(DeviceBorrow deviceBorrow);

    /**
     * 修改【请填写功能名称】
     *
     * @param deviceBorrow 【请填写功能名称】
     * @return 结果
     */
    public int updateDeviceBorrow(DeviceBorrow deviceBorrow);

    /**
     * 删除【请填写功能名称】
     *
     * @param borrowId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDeviceBorrowByBorrowId(Long borrowId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param borrowIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceBorrowByBorrowIds(Long[] borrowIds);
}
