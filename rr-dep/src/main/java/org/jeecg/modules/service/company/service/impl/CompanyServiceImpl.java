package org.jeecg.modules.service.company.service.impl;

import org.jeecg.modules.service.company.entity.Company;
import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.customer.mapper.OrderItemMapper;
import org.jeecg.modules.service.company.mapper.CompanyMapper;
import org.jeecg.modules.service.company.service.ICompanyService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 资源回收站
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	@Transactional
	public void saveMain(Company company, List<OrderItem> orderItemList) {
		companyMapper.insert(company);
		for(OrderItem entity:orderItemList) {
			//外键设置
			entity.setCustomerId(company.getId());
			//外键设置
			entity.setCollectorId(company.getId());
			//外键设置
			entity.setCompanyId(company.getId());
			orderItemMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Company company,List<OrderItem> orderItemList) {
		companyMapper.updateById(company);
		
		//1.先删除子表数据
		orderItemMapper.deleteByMainId(company.getId());
		
		//2.子表数据重新插入
		for(OrderItem entity:orderItemList) {
			//外键设置
			entity.setCustomerId(company.getId());
			//外键设置
			entity.setCollectorId(company.getId());
			//外键设置
			entity.setCompanyId(company.getId());
			orderItemMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		companyMapper.deleteById(id);
		orderItemMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			companyMapper.deleteById(id);
			orderItemMapper.deleteByMainId(id.toString());
		}
	}
	
}
