package org.jeecg.modules.service.customer.vo;

import java.util.List;
import org.jeecg.modules.service.customer.entity.Customer;
import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.customer.entity.AddrDetail;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class CustomerPage {
	
	/**主键*/
	private java.lang.String id;
	/**用户姓名*/
  	@Excel(name = "用户姓名", width = 15)
	private java.lang.String name;
	/**用户密码*/
  	@Excel(name = "用户密码", width = 15)
	private java.lang.String password;
	/**用户个人头像*/
  	@Excel(name = "用户个人头像", width = 15)
	private java.lang.String imageUrl;
	/**性别，0/男,1/女*/
  	@Excel(name = "性别，0/男,1/女", width = 15)
	private java.lang.String gender;
	/**用户电话号码*/
  	@Excel(name = "用户电话号码", width = 15)
	private java.lang.String phone;
	/**用户积分值，由用户在平台上交易产生，可用于兑换礼品*/
  	@Excel(name = "用户积分值，由用户在平台上交易产生，可用于兑换礼品", width = 15)
	private java.lang.Integer point;
	/**用户平台经验值，用于划分等级*/
  	@Excel(name = "用户平台经验值，用于划分等级", width = 15)
	private java.lang.Integer experience;
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
	
	@ExcelCollection(name="订单信息")
	private List<OrderItem> orderItemList;
	@ExcelCollection(name="用户地址")
	private List<AddrDetail> addrDetailList;
	
}
