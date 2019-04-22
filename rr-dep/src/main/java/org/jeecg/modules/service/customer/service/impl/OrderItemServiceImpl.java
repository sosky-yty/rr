package org.jeecg.modules.service.customer.service.impl;

import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.customer.mapper.OrderItemMapper;
import org.jeecg.modules.service.customer.service.IOrderItemService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单信息
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	public List<OrderItem> selectByMainId(String mainId) {
		return orderItemMapper.selectByMainId(mainId);
	}
}
