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
 * @Description: 订单信息
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
@Data
@TableName("order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**废品名称*/
    @Excel(name = "废品名称", width = 15)
	private java.lang.String name;
	/**该废品详情的重量*/
    @Excel(name = "该废品详情的重量", width = 15)
	private java.lang.Double weight;
	/**交易时废品单价*/
    @Excel(name = "交易时废品单价", width = 15)
	private java.lang.Double price;
	/**完成时间*/
	private java.util.Date finishTime;
	/**用户方便的上门回收开始时间*/
	private java.util.Date collectFromTime;
	/**用户方便的上门回收开始时间*/
	private java.util.Date collectEndTime;
	/**订单状态，0代表回收员未接单，0/回收员已接单未回收,1/回收员已回收未交接,2/回收员已交接，订单完成*/
    @Excel(name = "订单状态，0代表回收员未接单，0/回收员已接单未回收,1/回收员已回收未交接,2/回收员已交接，订单完成", width = 15)
	private java.lang.Integer state;
	/**外键,创建订单的用户id*/
	private java.lang.String customerId;
	/**外键,接单的回收员id*/
	private java.lang.String collectorId;
	/**外键,回收员接单时所属企业的id*/
	private java.lang.String companyId;
	/**订单交易地点*/
    @Excel(name = "订单交易地点", width = 15)
	private java.lang.String addrDetail;
	/**用户对本订单评分*/
    @Excel(name = "用户对本订单评分", width = 15)
	private java.lang.Integer customerGrade;
	/**回收员对本订单评分*/
    @Excel(name = "回收员对本订单评分", width = 15)
	private java.lang.Integer collectorGrade;
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
