package cn.shop.com.system.tbuser.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("tbUserService")
public class TbUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public int save(PageData pd)throws Exception{
		return (Integer) dao.save("TbUserMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("TbUserMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("TbUserMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TbUserMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TbUserMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TbUserMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TbUserMapper.deleteAll", ArrayDATA_IDS);
	}

	public List<PageData> login(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbUserMapper.login", pd);
	}

	public List<PageData> hasUser(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		  return (List<PageData>)dao.findForList("TbUserMapper.hasUser", pd);
	}

	public List<PageData> hasEmail(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbUserMapper.hasEmail", pd);
	}

	public List<PageData> register(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbUserMapper.register", pd);
	}

	public List<PageData> hasMobile(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbUserMapper.hasMobile", pd);
	}

	public List<PageData> updateInfo(PageData pd)throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbUserMapper.updateInfo", pd);
	}

	public int updatePassword(PageData pd)throws Exception { 
		int i = (Integer) dao.update("TbUserMapper.updatePassword", pd);
		return i;
	}
	
	public List<PageData> updateMobile(PageData pd)throws Exception {
		// TODO Auto-generated method stub 
		return (List<PageData>)dao.findForList("TbUserMapper.updateMobile", pd);
	}
	
}

