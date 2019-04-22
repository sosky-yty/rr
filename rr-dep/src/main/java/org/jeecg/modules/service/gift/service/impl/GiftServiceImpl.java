package org.jeecg.modules.service.gift.service.impl;

import org.jeecg.modules.service.gift.entity.Gift;
import org.jeecg.modules.service.gift.entity.Express;
import org.jeecg.modules.service.gift.mapper.ExpressMapper;
import org.jeecg.modules.service.gift.mapper.GiftMapper;
import org.jeecg.modules.service.gift.service.IGiftService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 礼品兑换
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

	@Autowired
	private GiftMapper giftMapper;
	@Autowired
	private ExpressMapper expressMapper;
	
	@Override
	@Transactional
	public void saveMain(Gift gift, List<Express> expressList) {
		giftMapper.insert(gift);
		for(Express entity:expressList) {
			//外键设置
			entity.setGiftId(gift.getId());
			expressMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Gift gift,List<Express> expressList) {
		giftMapper.updateById(gift);
		
		//1.先删除子表数据
		expressMapper.deleteByMainId(gift.getId());
		
		//2.子表数据重新插入
		for(Express entity:expressList) {
			//外键设置
			entity.setGiftId(gift.getId());
			expressMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		giftMapper.deleteById(id);
		expressMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			giftMapper.deleteById(id);
			expressMapper.deleteByMainId(id.toString());
		}
	}
	
}
