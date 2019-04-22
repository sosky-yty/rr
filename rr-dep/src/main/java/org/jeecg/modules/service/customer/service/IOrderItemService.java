package org.jeecg.modules.service.customer.service;

import org.jeecg.modules.service.customer.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单信息
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
public interface IOrderItemService extends IService<OrderItem> {

	public List<OrderItem> selectByMainId(String mainId);
}
