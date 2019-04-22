package org.jeecg.modules.service.gift.service;

import org.jeecg.modules.service.gift.entity.Express;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 兑换快递信息
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
public interface IExpressService extends IService<Express> {

	public List<Express> selectByMainId(String mainId);
}
