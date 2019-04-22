package org.jeecg.modules.service.customer.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 用户地址
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
@Data
@TableName("addr_detail")
public class AddrDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**地址名称*/
    @Excel(name = "地址名称", width = 15)
	private java.lang.String name;
	/**详细地址*/
    @Excel(name = "详细地址", width = 15)
	private java.lang.String addr;
	/**地址类型  0/小区,1/家庭住址*/
    @Excel(name = "地址类型  0/小区,1/家庭住址", width = 15)
	private java.lang.Integer kind;
	/**外键*/
	private java.lang.String customerId;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
    @Excel(name = "修改人", width = 15)
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
