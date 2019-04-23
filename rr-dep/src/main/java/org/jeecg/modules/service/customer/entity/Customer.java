package org.jeecg.modules.service.customer.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 用户管理

 */
@Data
@TableName("customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**用户姓名*/
	private java.lang.String name;
	/**用户密码*/
	private java.lang.String password;
	/**用户个人头像*/
	private java.lang.String imageUrl;
	/**性别，0/男,1/女*/
	private java.lang.String gender;
	/**用户电话号码*/
	private java.lang.String phone;
	/**用户积分值，由用户在平台上交易产生，可用于兑换礼品*/
	private java.lang.Integer point;
	/**用户平台经验值，用于划分等级*/
	private java.lang.Integer experience;
	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
