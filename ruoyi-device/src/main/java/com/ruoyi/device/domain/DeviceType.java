package com.ruoyi.device.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceType extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long typeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;
    /** 类型说明 */
    @Excel(name = "类型说明")
    private String typeRemark;
    /** 类型状态 */
    @Excel(name = "类型状态", readConverterExp = "0=正常, 1=删除")
    private Integer typeStatus;
}