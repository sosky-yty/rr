package org.jeecg.modules.service.gift.mapper;

import java.util.List;
import org.jeecg.modules.service.gift.entity.Express;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 兑换快递信息
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
public interface ExpressMapper extends BaseMapper<Express> {

	public boolean deleteByMainId(String mainId);
    
	public List<Express> selectByMainId(String mainId);
}
