package org.jeecg.modules.message.service;

import org.jeecg.modules.message.entity.SysMessageTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 消息模板
 */
public interface ISysMessageTemplateService extends IService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
