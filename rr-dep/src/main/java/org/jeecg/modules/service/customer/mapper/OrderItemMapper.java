package org.jeecg.modules.service.customer.mapper;

import java.util.List;
import org.jeecg.modules.service.customer.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单信息
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

	public boolean deleteByMainId(String mainId);
    
	public List<OrderItem> selectByMainId(String mainId);
}
