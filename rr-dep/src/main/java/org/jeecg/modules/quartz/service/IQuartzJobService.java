package org.jeecg.modules.quartz.service;

import java.util.List;

import org.jeecg.modules.quartz.entity.QuartzJob;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 定时任务在线管理
 */
public interface IQuartzJobService extends IService<QuartzJob> {

	List<QuartzJob> findByJobClassName(String jobClassName);

}
