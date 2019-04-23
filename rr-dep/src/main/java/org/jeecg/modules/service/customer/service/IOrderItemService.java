package org.jeecg.modules.service.customer.service;

import org.jeecg.modules.service.customer.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单信息

 */
public interface IOrderItemService extends IService<OrderItem> {

	public List<OrderItem> selectByMainId(String mainId);
}
