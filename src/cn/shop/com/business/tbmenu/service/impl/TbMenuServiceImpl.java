package cn.shop.com.business.tbmenu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.util.PageData;

import cn.shop.com.business.tbmenu.dao.TbMenuDao;
import cn.shop.com.business.tbmenu.service.TbMenuService;

@Service
public class TbMenuServiceImpl implements TbMenuService{
	
	@Autowired
	public TbMenuDao dao;
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TbMenuMapper.listAll", pd);
	}
	 
	
}
