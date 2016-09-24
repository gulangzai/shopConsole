package cn.shop.com.dao;

import java.util.List;

import cn.shop.com.dto.TreeNode;
import cn.shop.com.jopo.Role;

public interface RoleDao  extends BaseDao<Role>{

	List<TreeNode> getListByPid(int pid);
	
}
