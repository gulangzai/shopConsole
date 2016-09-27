package cn.shop.com.business.tbmenu.service;

import java.util.List;

import com.fh.util.PageData;

public interface TbMenuService {

	List<PageData> listAll(PageData pds)throws Exception;

}
