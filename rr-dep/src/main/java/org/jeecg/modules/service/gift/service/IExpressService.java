package org.jeecg.modules.service.gift.service;

import org.jeecg.modules.service.gift.entity.Express;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 兑换快递信
 */
public interface IExpressService extends IService<Express> {

	public List<Express> selectByMainId(String mainId);
}
