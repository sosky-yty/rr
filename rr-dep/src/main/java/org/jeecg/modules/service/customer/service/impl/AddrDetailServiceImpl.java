package org.jeecg.modules.service.customer.service.impl;

import org.jeecg.modules.service.customer.entity.AddrDetail;
import org.jeecg.modules.service.customer.mapper.AddrDetailMapper;
import org.jeecg.modules.service.customer.service.IAddrDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 用户地址
 */
@Service
public class AddrDetailServiceImpl extends ServiceImpl<AddrDetailMapper, AddrDetail> implements IAddrDetailService {
	
	@Autowired
	private AddrDetailMapper addrDetailMapper;
	
	@Override
	public List<AddrDetail> selectByMainId(String mainId) {
		return addrDetailMapper.selectByMainId(mainId);
	}
}
