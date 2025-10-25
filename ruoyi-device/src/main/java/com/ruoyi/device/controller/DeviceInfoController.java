package com.ruoyi.device.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Anonymous
@RestController
@RequestMapping("/device/deviceInfo")
public class DeviceInfoController extends BaseController
{
    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 新增设备信息
     */
    @PostMapping
  public AjaxResult save(@RequestBody DeviceInfo deviceInfo){
        System.out.println(deviceInfo);
        return  toAjax(deviceInfoService.insertDeviceInfo(deviceInfo));
    }

    /**
     * 删除设备信息
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids){
        return  toAjax(deviceInfoService.deleteDeviceInfoByIds(ids));
    }


    /**
     * 修改设备信息
     */
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceInfo deviceInfo){
        return  toAjax(deviceInfoService.updateDeviceInfo(deviceInfo));
    }

    /**
     * 基于Id查询
     */
    @GetMapping(value = "/{deviceId}")
    public AjaxResult geInfo(@PathVariable("deviceId")Long deviceId)
    {
        return AjaxResult.success(deviceInfoService.selectDeviceInfoById(deviceId));
    }

    /**
     * 查询设备信息列表
     */
    @GetMapping("/list")
  public TableDataInfo list(DeviceInfo deviceInfo)
    {
       System.out.println(deviceInfo);
       startPage();
       List<DeviceInfo> list= deviceInfoService.selectDeviceInfoList(deviceInfo);
       return getDataTable(list);
    }
    /**
     * 导出设备信息列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceInfo deviceInfo){
        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfo);
        ExcelUtil<DeviceInfo> util = new ExcelUtil<>(DeviceInfo.class);
        util.exportExcel(response, list,"设备管理");
    }

}