package cn.shop.com.service;

import java.util.List;

import cn.shop.com.dto.TreeNode;
import cn.shop.com.jopo.Role;

public interface RoleService {
	 
	public List<TreeNode> getListByPid(int pid);
	
	public void save(Role role);

	public void delete(Role role);

	public void update(Role role);
	
}
