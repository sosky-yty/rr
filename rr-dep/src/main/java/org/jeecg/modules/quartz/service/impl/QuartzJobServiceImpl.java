package org.jeecg.modules.quartz.service.impl;

import java.util.List;

import org.jeecg.modules.quartz.entity.QuartzJob;
import org.jeecg.modules.quartz.mapper.QuartzJobMapper;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 定时任务在线管理
 */
@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements IQuartzJobService {
	@Autowired
	private QuartzJobMapper quartzJobMapper;

	@Override
	public List<QuartzJob> findByJobClassName(String jobClassName) {
		return quartzJobMapper.findByJobClassName(jobClassName);
	}

}
