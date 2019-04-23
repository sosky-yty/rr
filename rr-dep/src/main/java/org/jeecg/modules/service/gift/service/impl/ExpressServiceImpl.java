package org.jeecg.modules.service.gift.service.impl;

import org.jeecg.modules.service.gift.entity.Express;
import org.jeecg.modules.service.gift.mapper.ExpressMapper;
import org.jeecg.modules.service.gift.service.IExpressService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 兑换快递信息
 */
@Service
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements IExpressService {
	
	@Autowired
	private ExpressMapper expressMapper;
	
	@Override
	public List<Express> selectByMainId(String mainId) {
		return expressMapper.selectByMainId(mainId);
	}
}
