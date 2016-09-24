package cn.shop.com.service;

import java.util.List;

import cn.shop.com.dao.RoleDao;
import cn.shop.com.dto.TreeNode;
import cn.shop.com.jopo.Role;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	 
	public RoleDao getRoleDao() {
		return roleDao;
	}
 
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}




	@Override
	public List<TreeNode> getListByPid(int pid) {
		// TODO Auto-generated method stub
		return roleDao.getListByPid(pid);
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

}
