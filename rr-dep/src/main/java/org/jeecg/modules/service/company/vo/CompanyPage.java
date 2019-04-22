package org.jeecg.modules.service.company.vo;

import java.util.List;
import org.jeecg.modules.service.company.entity.Company;
import org.jeecg.modules.service.customer.entity.OrderItem;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class CompanyPage {
	
	/**主键*/
	private java.lang.String id;
	/**资源回收站名称*/
  	@Excel(name = "资源回收站名称", width = 15)
	private java.lang.String name;
	/**管理区域*/
  	@Excel(name = "管理区域", width = 15)
	private java.lang.String addr;
	/**回收总量价值*/
  	@Excel(name = "回收总量价值", width = 15)
	private java.lang.String price;
	/**回收站电话号码*/
  	@Excel(name = "回收站电话号码", width = 15)
	private java.lang.String phone;
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
	
}
