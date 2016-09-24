package cn.shop.com.business.tbapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("tbAppService")
public class TbAppService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public int save(PageData pd)throws Exception{
		return (Integer) dao.save("TbAppMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("TbAppMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("TbAppMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TbAppMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TbAppMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TbAppMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TbAppMapper.deleteAll", ArrayDATA_IDS);
	}

	public List<PageData> login(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.login", pd);
	}

	public List<PageData> hasUser(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		  return (List<PageData>)dao.findForList("TbAppMapper.hasUser", pd);
	}

	public List<PageData> hasEmail(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.hasEmail", pd);
	}

	public List<PageData> register(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.register", pd);
	}

	public List<PageData> hasMobile(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.hasMobile", pd);
	}

	public List<PageData> updateInfo(PageData pd)throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.updateInfo", pd);
	}

	public List<PageData> updatePassword(PageData pd)throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.updatePassword", pd);
	}
	
	public List<PageData> updateMobile(PageData pd)throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbAppMapper.updateMobile", pd);
	}

	public List<PageData> mobileUpdateApp(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		  return (List<PageData>)dao.findForList("TbAppMapper.hasAll", pd);
	}
	
}

