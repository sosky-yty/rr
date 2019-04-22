package org.jeecg.modules.service.collector.service;


import org.jeecg.modules.service.collector.entity.Collector;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.service.customer.entity.OrderItem;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 用户管理
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
public interface ICollectorService extends IService<Collector> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Collector collector,List<OrderItem> orderItemList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Collector collector,List<OrderItem> orderItemList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
