package org.jeecg.modules.service.gift.vo;

import java.util.List;
import org.jeecg.modules.service.gift.entity.Gift;
import org.jeecg.modules.service.gift.entity.Express;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class GiftPage {
	
	/**主键*/
	private java.lang.String id;
	/**礼品名称*/
  	@Excel(name = "礼品名称", width = 15)
	private java.lang.String name;
	/**礼品兑换积分值*/
  	@Excel(name = "礼品兑换积分值", width = 15)
	private java.lang.Integer point;
	/**礼品库存量*/
  	@Excel(name = "礼品库存量", width = 15)
	private java.lang.Integer inventory;
	
	@ExcelCollection(name="兑换快递信息")
	private List<Express> expressList;
	
}
