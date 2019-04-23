package org.jeecg.modules.service.gift.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 礼品兑换
 */
@Data
@TableName("gift")
public class Gift implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**礼品名称*/
	private java.lang.String name;
	/**礼品兑换积分值*/
	private java.lang.Integer point;
	/**礼品库存量*/
	private java.lang.Integer inventory;
}
