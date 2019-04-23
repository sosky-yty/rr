package org.jeecg.modules.service.customer.service.impl;

import org.jeecg.modules.service.customer.entity.Customer;
import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.customer.entity.AddrDetail;
import org.jeecg.modules.service.customer.mapper.OrderItemMapper;
import org.jeecg.modules.service.customer.mapper.AddrDetailMapper;
import org.jeecg.modules.service.customer.mapper.CustomerMapper;
import org.jeecg.modules.service.customer.service.ICustomerService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 用户管理

 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private AddrDetailMapper addrDetailMapper;
	
	@Override
	@Transactional
	public void saveMain(Customer customer, List<OrderItem> orderItemList,List<AddrDetail> addrDetailList) {
		customerMapper.insert(customer);
		for(OrderItem entity:orderItemList) {
			//外键设置
			entity.setCustomerId(customer.getId());
			//外键设置
			entity.setCollectorId(customer.getId());
			//外键设置
			entity.setCompanyId(customer.getId());
			orderItemMapper.insert(entity);
		}
		for(AddrDetail entity:addrDetailList) {
			//外键设置
			entity.setCustomerId(customer.getId());
			addrDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Customer customer,List<OrderItem> orderItemList,List<AddrDetail> addrDetailList) {
		customerMapper.updateById(customer);
		
		//1.先删除子表数据
		orderItemMapper.deleteByMainId(customer.getId());
		addrDetailMapper.deleteByMainId(customer.getId());
		
		//2.子表数据重新插入
		for(OrderItem entity:orderItemList) {
			//外键设置
			entity.setCustomerId(customer.getId());
			//外键设置
			entity.setCollectorId(customer.getId());
			//外键设置
			entity.setCompanyId(customer.getId());
			orderItemMapper.insert(entity);
		}
		for(AddrDetail entity:addrDetailList) {
			//外键设置
			entity.setCustomerId(customer.getId());
			addrDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		customerMapper.deleteById(id);
		orderItemMapper.deleteByMainId(id);
		addrDetailMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			customerMapper.deleteById(id);
			orderItemMapper.deleteByMainId(id.toString());
			addrDetailMapper.deleteByMainId(id.toString());
		}
	}
	
}
