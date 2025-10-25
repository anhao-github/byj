package com.ruoyi.device.controller;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.device.domain.DeviceUserRegister;
import com.ruoyi.device.service.IDeviceUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Anonymous
@RestController
@RequestMapping("/device/deviceUserRegister")
public class DeviceUserRegisterController extends BaseController
{
    @Autowired
    private IDeviceUserRegisterService deviceUserRegisterService;

    /**
     * 查询【请填写功能名称】列表
     */

    @GetMapping("/list")
    public TableDataInfo list(DeviceUserRegister deviceUserRegister)
    {
        startPage();
        List<DeviceUserRegister> list = deviceUserRegisterService.selectDeviceUserRegisterList(deviceUserRegister);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceUserRegister deviceUserRegister)
    {
        List<DeviceUserRegister> list = deviceUserRegisterService.selectDeviceUserRegisterList(deviceUserRegister);
        ExcelUtil<DeviceUserRegister> util = new ExcelUtil<DeviceUserRegister>(DeviceUserRegister.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{regId}")
    public AjaxResult getInfo(@PathVariable("regId") Long regId)
    {
        return success(deviceUserRegisterService.selectDeviceUserRegisterByRegId(regId));
    }

    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceUserRegister deviceUserRegister)
    {
        return toAjax(deviceUserRegisterService.insertDeviceUserRegister(deviceUserRegister));
    }

    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceUserRegister deviceUserRegister)
    {
        return toAjax(deviceUserRegisterService.updateDeviceUserRegister(deviceUserRegister));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{regIds}")
    public AjaxResult remove(@PathVariable Long[] regIds)
    {
        return toAjax(deviceUserRegisterService.deleteDeviceUserRegisterByRegIds(regIds));
    }
}