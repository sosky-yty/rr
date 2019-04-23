package org.jeecg.modules.service.company.service;

import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.company.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 资源回收站
 */
public interface ICompanyService extends IService<Company> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Company company,List<OrderItem> orderItemList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Company company,List<OrderItem> orderItemList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
