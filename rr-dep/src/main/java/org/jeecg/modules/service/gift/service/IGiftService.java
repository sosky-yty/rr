package org.jeecg.modules.service.gift.service;

import org.jeecg.modules.service.gift.entity.Express;
import org.jeecg.modules.service.gift.entity.Gift;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 礼品兑换
 */
public interface IGiftService extends IService<Gift> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Gift gift,List<Express> expressList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Gift gift,List<Express> expressList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
