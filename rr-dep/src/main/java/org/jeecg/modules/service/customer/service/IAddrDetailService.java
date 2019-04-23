package org.jeecg.modules.service.customer.service;

import org.jeecg.modules.service.customer.entity.AddrDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 用户地址

 */
public interface IAddrDetailService extends IService<AddrDetail> {

	public List<AddrDetail> selectByMainId(String mainId);
}
