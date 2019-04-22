package org.jeecg.modules.service.collector.service.impl;

import org.jeecg.modules.service.collector.entity.Collector;

import org.jeecg.modules.service.customer.mapper.OrderItemMapper;
import org.jeecg.modules.service.collector.mapper.CollectorMapper;
import org.jeecg.modules.service.collector.service.ICollectorService;
import org.jeecg.modules.service.customer.entity.OrderItem;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 用户管理
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
@Service
public class CollectorServiceImpl extends ServiceImpl<CollectorMapper, Collector> implements ICollectorService {

	@Autowired
	private CollectorMapper collectorMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	@Transactional
	public void saveMain(Collector collector, List<OrderItem> orderItemList) {
		collectorMapper.insert(collector);
		for(OrderItem entity:orderItemList) {
			//外键设置
			entity.setCustomerId(collector.getId());
			//外键设置
			entity.setCollectorId(collector.getId());
			//外键设置
			entity.setCompanyId(collector.getId());
			orderItemMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Collector collector,List<OrderItem> orderItemList) {
		collectorMapper.updateById(collector);
		
		//1.先删除子表数据
		orderItemMapper.deleteByMainId(collector.getId());
		
		//2.子表数据重新插入
		for(OrderItem entity:orderItemList) {
			//外键设置
			entity.setCustomerId(collector.getId());
			//外键设置
			entity.setCollectorId(collector.getId());
			//外键设置
			entity.setCompanyId(collector.getId());
			orderItemMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		collectorMapper.deleteById(id);
		orderItemMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			collectorMapper.deleteById(id);
			orderItemMapper.deleteByMainId(id.toString());
		}
	}
	
}
