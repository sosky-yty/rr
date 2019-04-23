package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ISysDictItemService extends IService<SysDictItem> {
    public List<SysDictItem> selectItemsByMainId(String mainId);
}
