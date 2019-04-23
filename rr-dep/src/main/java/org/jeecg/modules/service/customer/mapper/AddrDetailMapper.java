package org.jeecg.modules.service.customer.mapper;

import java.util.List;
import org.jeecg.modules.service.customer.entity.AddrDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 用户地址
 */
public interface AddrDetailMapper extends BaseMapper<AddrDetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<AddrDetail> selectByMainId(String mainId);
}
