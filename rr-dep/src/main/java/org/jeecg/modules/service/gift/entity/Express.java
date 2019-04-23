package org.jeecg.modules.service.gift.entity;

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
 * @Description: 兑换快递信息
 */
@Data
@TableName("express")
public class Express implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**礼品id*/
	private java.lang.String giftId;
	/**收件人地址*/
    @Excel(name = "收件人地址", width = 15)
	private java.lang.String address;
	/**收件人电话*/
    @Excel(name = "收件人电话", width = 15)
	private java.lang.String phone;
	/**收件人姓名*/
    @Excel(name = "收件人姓名", width = 15)
	private java.lang.String name;
}
