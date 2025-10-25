package com.ruoyi.device.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 主键，此设备的Id */
    private Long deviceId;
    /** 此设备的所属设备分类Id */
    private Long typeId;
    @Excel(name = "设备类别", targetAttr = "typeName", type = Excel.Type.EXPORT)
    private DeviceType type;
    /** 设备名称 */
    private String deviceName;
    /** 设备型号，多个同型号设备，在型号后添加序号 */
    @Excel(name = "设备型号")
    private String deviceModel;
    /** 设备图片 */
    @Excel(name = "设备图片")
    private String deviceImg;
    /** 设备说明 */
    @Excel(name = "设备说明")
    private String deviceRemark;
    /** 设备状态 */
    @Excel(name = "设备状态", readConverterExp = "-1=报废，0=正常，1=借出，2=维修")
    private Integer deviceStatus;
}
