package cn.shop.com.system.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("tbRoleService")
public class TbRoleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public int save(PageData pd)throws Exception{
		return (Integer) dao.save("TbRoleMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("TbRoleMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("TbRoleMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TbRoleMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TbRoleMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TbRoleMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TbUserMapper.deleteAll", ArrayDATA_IDS);
	}

	public List<PageData> getUserRole(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbRoleMapper.getUserRole", pd);
	}

	public void saveUserRole(PageData pd) throws Exception { 
	   dao.findForList("TbRoleMapper.saveUserRole", pd);
	}

	public void deleteRoleByUser(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		 dao.findForList("TbRoleMapper.deleteRoleByUser", pd);
	}
	
}

