package com.ruoyi.device.controller;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.device.domain.DeviceBorrow;
import com.ruoyi.device.service.IDeviceBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Anonymous
@RestController
@RequestMapping("/device/borrow")
public class DeviceBorrowController extends BaseController
{
    @Autowired
    private IDeviceBorrowService deviceBorrowService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceBorrow deviceBorrow)
    {
        startPage();
        List<DeviceBorrow> list = deviceBorrowService.selectDeviceBorrowList(deviceBorrow);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceBorrow deviceBorrow)
    {
        List<DeviceBorrow> list = deviceBorrowService.selectDeviceBorrowList(deviceBorrow);
        ExcelUtil<DeviceBorrow> util = new ExcelUtil<DeviceBorrow>(DeviceBorrow.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }
    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:query')")
    @GetMapping(value = "/{borrowId}")
    public AjaxResult getInfo(@PathVariable("borrowId") Long borrowId)
    {
        return success(deviceBorrowService.selectDeviceBorrowByBorrowId(borrowId));
    }
    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceBorrow deviceBorrow)
    {
        return toAjax(deviceBorrowService.insertDeviceBorrow(deviceBorrow));
    }
    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceBorrow deviceBorrow)
    {
        return toAjax(deviceBorrowService.updateDeviceBorrow(deviceBorrow));
    }
    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{borrowIds}")
    public AjaxResult remove(@PathVariable Long[] borrowIds)
    {
        return toAjax(deviceBorrowService.deleteDeviceBorrowByBorrowIds(borrowIds));
    }
}
