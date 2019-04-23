package org.jeecg.modules.service.customer.service;

import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.customer.entity.AddrDetail;
import org.jeecg.modules.service.customer.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 用户管理

 */
public interface ICustomerService extends IService<Customer> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Customer customer,List<OrderItem> orderItemList,List<AddrDetail> addrDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Customer customer,List<OrderItem> orderItemList,List<AddrDetail> addrDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
