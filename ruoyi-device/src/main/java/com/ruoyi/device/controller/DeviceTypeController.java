package com.ruoyi.device.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.domain.DeviceType;
import com.ruoyi.device.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Anonymous

@RestController
@RequestMapping("/device/deviceType")
public class DeviceTypeController extends BaseController
{
    @Autowired
    private IDeviceTypeService deviceTypeService;
    /**
     * 新增
     */
    @PostMapping
    public AjaxResult save(@RequestBody DeviceType deviceType){

        System.out.println(deviceType);
        return  toAjax(deviceTypeService.insertDerviceType(deviceType));
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return deviceTypeService.deleteDeviceTypeByIds(ids);
    }
    /**
     * 修改
     *
     */
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceType deviceType)
    {
        return  toAjax(deviceTypeService.updateDeviceType(deviceType));
    }
    /**
     * 查询
     */
    @GetMapping("/list")
    public TableDataInfo list(DeviceType deviceType)
    {
        startPage();
        List<DeviceType> list= deviceTypeService.selectDeviceTypeList(deviceType);
        return getDataTable(list);
    }
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(deviceTypeService.selectDeviceTypeById(typeId));
    }


    /**
     * 导出
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceType deviceType) {
        List<DeviceType> list = deviceTypeService.selectDeviceTypeList(deviceType);
        ExcelUtil<DeviceType> util = new ExcelUtil<>(DeviceType.class);
        util.exportExcel(response, list, "设备分类数据");
    }


}