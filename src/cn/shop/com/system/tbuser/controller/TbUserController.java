package cn.shop.com.system.tbuser.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;

import cn.lanbao.com.base.ResultAction;
import cn.shop.com.system.tbuser.service.TbUserService;

import com.fh.util.Jurisdiction;
import com.fh.util.MD5; 

/** 
 * 类名称：TUserController
 * 创建人：FH 
 * 创建时间：2016-06-05
 */
@Controller
@RequestMapping(value="/tbUserController")
public class TbUserController extends BaseController {
	
	String menuUrl = "tbUserController/list.do"; //菜单地址(权限用)
	
	@Resource(name="tbUserService")
	private TbUserService tbUserService;
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增TUserController");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("F_USER_ID", this.get32UUID());	//主键
		pd.put("F_Password", MD5.createMD5("123456"));
		int UserId = tbUserService.save(pd); 
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除TUserController");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			tbUserService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改TUserController");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		tbUserService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表TUserController");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData(); 
			page.setPd(pd);
			List<PageData>	varList = tbUserService.list(page);	//列出TUserController列表
			mv.setViewName("system/tbuser/tbuser_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增TUserController页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/tbuser/tbuser_add");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改TUserController页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = tbUserService.findById(pd);	//根据ID读取
			mv.setViewName("system/tbuser/tbuser_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除TUserController");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("USER_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				tbUserService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出TUserController到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户ID");	//1
			titles.add("用户名");	//2
			titles.add("性别");	//3
			titles.add("邮箱");	//4
			titles.add("手机号");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = tbUserService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("F_USER_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("F_UserName"));	//2
				String sex="男";
				if(varOList.get(i).get("F_Sex").toString().equals("1")){
					sex="女";
				}
				vpd.put("var3", sex);	//3
				vpd.put("var4", varOList.get(i).getString("F_Email"));	//4
				vpd.put("var5", varOList.get(i).get("F_Mobile").toString());	//5
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	/**
	 * 请求登录，验证用户
	 */ 
	@RequestMapping(value="/mobileLogin" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONPObject login(HttpServletRequest req)throws Exception{
		 String callbackFunName =req.getParameter("callbackparam");//得到js函数名称 
		Map<String,String> map = new HashMap<String,String>();
	
		PageData pd = new PageData();
		pd = this.getPageData();   
		pd.put("F_Password", MD5.createMD5((String)pd.get("F_Password")));
		JSONPObject jp = null;
		List<PageData> varList =  tbUserService.login(pd);
		ResultAction ra = new ResultAction();
		List<PageData> isExistUser = tbUserService.hasUser(pd);
		if(isExistUser.size()==0){
			ra.setIserror(true);
			ra.setMessage("不存在该用户");
			jp = new JSONPObject(callbackFunName, ra);
			return jp;
		}
		if(varList.size()==0){
			ra.setIserror(true);
			ra.setMessage("用户密码错误");
			jp = new JSONPObject(callbackFunName, ra); 
			return jp;
		}else{
			PageData newpd = varList.get(0);
			if(pd.get("F_Password").equals(newpd.get("F_Password"))){
				ra.setIserror(false);
				ra.setMessage("登陆成功");
				ra.setData(newpd);
				jp = new JSONPObject(callbackFunName, ra); 
			}
		} 
		return jp; 
	}
	
	/**
	 * 请求登录，验证用户
	 */ 
	@RequestMapping(value="/mobileRegister" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONPObject register(HttpServletRequest req)throws Exception{
		 String callbackFunName =req.getParameter("callbackparam");//得到js函数名称 
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();   
		pd.put("F_Password", MD5.createMD5((String)pd.get("F_Password")));
		pd.put("F_USER_ID", UUID.randomUUID().toString()); 
		pd.put("F_Deleted", "0");
		JSONPObject jp = null; 
		ResultAction ra = new ResultAction();
		List<PageData> isExistUser = tbUserService.hasUser(pd);
		if(isExistUser.size()!=0){
			ra.setIserror(true);
			ra.setMessage("存在该用户,请用别的用户名");
			jp = new JSONPObject(callbackFunName, ra);
			return jp;
		}
		List<PageData> isExistEmail = tbUserService.hasEmail(pd);
		if(isExistEmail.size()!=0){
			ra.setIserror(true);
			ra.setMessage("该邮箱已被使用");
			jp = new JSONPObject(callbackFunName, ra);
			return jp;
		}
		List<PageData> isExistMobile = tbUserService.hasMobile(pd);
		if(isExistMobile.size()!=0){
			ra.setIserror(true);
			ra.setMessage("该手机号已被使用");
			jp = new JSONPObject(callbackFunName, ra);
			return jp;
		}
		
		List<PageData> varList =  tbUserService.register(pd); 
		ra.setIserror(false);
		ra.setMessage("注册成功");
		jp = new JSONPObject(callbackFunName, ra);  
		return jp; 
	}
	
	/**
	 * 修改用户信息
	 */ 
	@RequestMapping(value="/mobileUpdateInfo" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONPObject mobileUpdateInfo(HttpServletRequest req)throws Exception{
		 String callbackFunName =req.getParameter("callbackparam");//得到js函数名称 
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();    
		JSONPObject jp = null; 
		ResultAction ra = new ResultAction(); 
		List<PageData> varList =  tbUserService.updateInfo(pd); 
		ra.setIserror(false);
		ra.setMessage("修改信息成功");
		jp = new JSONPObject(callbackFunName, ra);  
		return jp; 
	}
	
	/**
	 * 修改用户密码
	 */ 
	@RequestMapping(value="/mobileUpdatePassword" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONPObject mobileUpdatePassword(HttpServletRequest req)throws Exception{
		 String callbackFunName =req.getParameter("callbackparam");//得到js函数名称 
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();    
		JSONPObject jp = null; 
		ResultAction ra = new ResultAction(); 
		pd.put("F_Password", MD5.createMD5(pd.getString("F_Password")));
		pd.put("NEW_F_Password", MD5.createMD5(pd.getString("NEW_F_Password")));
		int result =  tbUserService.updatePassword(pd); 
		if(result==1){
			ra.setIserror(false);
			ra.setMessage("修改密码成功");
		}else{
			ra.setIserror(true);	
			ra.setMessage("修改密码失败");
		}
	
		jp = new JSONPObject(callbackFunName, ra);  
		return jp; 
	}
	
	/**
	 * 修改用户手机号
	 */ 
	@RequestMapping(value="/mobileUpdateMobile" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONPObject mobileUpdateMobile(HttpServletRequest req)throws Exception{
		 String callbackFunName =req.getParameter("callbackparam");//得到js函数名称 
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();    
		JSONPObject jp = null; 
		ResultAction ra = new ResultAction(); 
		List<PageData> varList =  tbUserService.updateMobile(pd); 
		ra.setIserror(false);
		ra.setMessage("修改密码成功");
		jp = new JSONPObject(callbackFunName, ra);  
		return jp; 
	}
	
	
	
	
	
	
	/**
	 * 通过userId获取用户信息
	 */ 
	@RequestMapping(value="/mobileGetUserById" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONPObject mobileGetUserById(HttpServletRequest req)throws Exception{
		 String callbackFunName =req.getParameter("callbackparam");//得到js函数名称 
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();    
		JSONPObject jp = null; 
		ResultAction ra = new ResultAction();
	    pd = tbUserService.findById(pd); 
		jp = new JSONPObject(callbackFunName, pd);  
		return jp; 
	}
}
