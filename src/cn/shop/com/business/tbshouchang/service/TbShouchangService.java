package cn.shop.com.business.tbshouchang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("tbShouchangService")
public class TbShouchangService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public int save(PageData pd)throws Exception{
		return (Integer) dao.save("TbProductMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("TbProductMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("TbProductMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TbProductMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TbProductMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TbProductMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TbProductMapper.deleteAll", ArrayDATA_IDS);
	}

	public List<PageData> findByByUserId(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbShouchangMapper.findByByUserId", page);
	}

	public List<PageData> mobileListAdd(Page page) throws Exception  {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbShouchangMapper.save", page);
	}

	public List<PageData> mobileListDelete(Page page) throws Exception  {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("TbShouchangMapper.delete", page);
	} 
 
}

