package com.ruoyi.device.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceBorrow extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long borrowId;

    /** 设备id */
    private Long deviceId;

    /** 借用用户id */
    private Long userId;

    /** 借用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "借用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date borrowTime;

    /** 借用说明 */
    @Excel(name = "借用说明")
    private String borrowRemark;

    /** 计划归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /** 归还状态,0未归还,1已归还 */
    @Excel(name = "归还状态,0未归还,1已归还")
    private Integer returnStatus;

    /** 归还说明 */
    @Excel(name = "归还说明")
    private String returnRemark;
}