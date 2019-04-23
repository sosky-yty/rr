package org.jeecg.modules.service.gift.mapper;

import java.util.List;
import org.jeecg.modules.service.gift.entity.Express;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 兑换快递信息
 */
public interface ExpressMapper extends BaseMapper<Express> {

	public boolean deleteByMainId(String mainId);
    
	public List<Express> selectByMainId(String mainId);
}
