package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
	
	/**
	 * 保存授权/先删后增
	 * @param roleId
	 * @param permissionIds
	 */
	public void saveRolePermission(String roleId,String permissionIds);

}
