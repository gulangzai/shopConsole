package cn.shop.com.business.tbclass.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;  
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.DelAllFile;
import com.fh.util.FileUpload;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.Watermark;

import cn.lanbao.com.base.ResultAction;
import cn.shop.com.business.pictures.service.PicturesService;
import cn.shop.com.business.tbclass.service.ClassService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * 类名称：PicturesController
 * 创建人：FH 
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/goodClass")
public class GoodClassController extends BaseController {
	
	String menuUrl = "goodClass/list.do"; //菜单地址(权限用)
	@Resource(name="picturesService")
	private PicturesService picturesService;
	 
	@Resource(name="classService")
	private ClassService classService;
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(
			@RequestParam(required=false) MultipartFile file
			) throws Exception{
		logBefore(logger, "新增Pictures");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			}else{
				System.out.println("上传失败");
			}
			
			pd.put("PICTURES_ID", this.get32UUID());			//主键
			pd.put("TITLE", "图片");								//标题
			pd.put("NAME", fileName);							//文件名
			pd.put("PATH", ffile + "/" + fileName);				//路径
			pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
			pd.put("MASTER_ID", "1");							//附属与
			pd.put("BZ", "图片管理处上传");						//备注
			//加水印
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
			picturesService.save(pd);
		}
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	

	/**
	 * 删除商品类型
	 */
	@RequestMapping(value="/deleteClass")
	public void deleteU(PrintWriter out){
		PageData pd = new PageData();
		try{
			pd = this.getPageData(); 
			classService.delete(pd); 
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		} 
	}
	
	/**
	 * 修改
	 */
/*	@RequestMapping(value="/edit")
	public ModelAndView edit(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile file,
			@RequestParam(value="tpz",required=false) String tpz,
			@RequestParam(value="PICTURES_ID",required=false) String PICTURES_ID,
			@RequestParam(value="TITLE",required=false) String TITLE,
			@RequestParam(value="MASTER_ID",required=false) String MASTER_ID,
			@RequestParam(value="BZ",required=false) String BZ
			) throws Exception{
		logBefore(logger, "修改Pictures");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
			pd.put("PICTURES_ID", PICTURES_ID);		//图片ID
			pd.put("TITLE", TITLE);					//标题
			pd.put("MASTER_ID", MASTER_ID);			//属于ID
			pd.put("BZ", BZ);						//备注
			
			if(null == tpz){tpz = "";}
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
				pd.put("PATH", ffile + "/" + fileName);				//路径
				pd.put("NAME", fileName);
			}else{
				pd.put("PATH", tpz);
			}
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			picturesService.edit(pd);				//执行修改数据库
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}*/
	
	/**
	 * 修改
	 */
 	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit(
			HttpServletRequest request,
			@RequestParam(value="F_ClsId",required=true) String F_ClsId,
			@RequestParam(value="F_ClsName",required=false) String F_ClsName,
			@RequestParam(value="F_Status",required=false) String F_Status,
			@RequestParam(value="F_Order",required=false) String F_Order,
			@RequestParam(value="F_PClsId",required=true) String F_PClsId
			) throws Exception{ 
 		HashMap map = new HashMap();
 		ModelAndView mv = this.getModelAndView();
 		PageData pd = new PageData();
 		pd = this.getPageData();
		classService.edit(pd);		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map); 
	}
	
	/**
	 * 列表
	 */
 	@RequestMapping(value="/listAll")
	public ModelAndView listAll(Page page,Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id",null);
		page.setPd(pd);
		List<PageData> varList;
		List<PageData> classList;
		try {  
			varList = classService.listAll(pd); 
			JSONArray classes = JSONArray.fromObject(varList);
			mv.setViewName("information/goodClass/goodClass_list");
			mv.addObject("classes", classes.toString());
			mv.addObject("pd", pd); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//列出Pictures列表
 
		return mv; 
	} 
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/classTree")
	public ModelAndView classTree(Page page,Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id",null);
		page.setPd(pd);
		List<PageData> varList;
		List<PageData> classList;
		try {  
			varList = classService.listAll(pd); 
			JSONArray classes = JSONArray.fromObject(varList);
			mv.setViewName("information/goodClass/class_tree");
			mv.addObject("classes", classes.toString());
			mv.addObject("pd", pd); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//列出Pictures列表
 
		return mv; 
	}
	
	
	@RequestMapping(value="/list")
	public ModelAndView listClass(Page page,Model model){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData(); 
		page.setPd(pd); 
		List<PageData> classList;
		try {  
			classList = classService.list(page);  
			List<PageData> varList = classService.listAll(pd); 
			JSONArray classes = JSONArray.fromObject(varList);
			
			mv.setViewName("information/goodClass/class_list");  
			mv.addObject("varList",classList);
			mv.addObject("pd",pd); 
			mv.addObject("classes", classes.toString());
			
			model.addAttribute("F_ClsId", pd.get("F_ClsId"));
			model.addAttribute("classList",classList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//列出Pictures列表 
		return mv; 
	}
	
	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(@RequestParam(value="F_PClsId",required=true) String F_PClsId){
		logBefore(logger, "去新增类页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {  
			mv.setViewName("information/goodClass/tbclass_add");
			mv.addObject("pd", pd);
			mv.addObject("F_PClsId",F_PClsId);
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
		logBefore(logger, "去修改goodClass页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = classService.findById(pd);	//根据ID读取
			mv.setViewName("information/goodClass/tbclass_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST) 
	@ResponseBody
	public Object add(String F_PClsId,HttpServletRequest request) throws Exception{
		logBefore(logger, "新增goodClass");
		Map<String,String> map = new HashMap<String,String>(); 
		PageData pd = new PageData();
		pd = this.getPageData();
		classService.add(pd);
		map.put("result", "ok");
		ModelAndView mv = this.getModelAndView();
		mv.addObject("msg","success"); 
		//mv.setViewName("save_result");
		JSONObject obj = new JSONObject();
		obj.put("F_PClsId", F_PClsId);
		map.put("result", "ok");
		map.put("F_ClsId", F_PClsId);
		request.setAttribute("F_ClsId", F_PClsId);
		return AppUtil.returnObject(pd, map);
		//return obj.toString();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Pictures");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){
				List<PageData> pdList = new ArrayList<PageData>();
				List<PageData> pathList = new ArrayList<PageData>();
				String DATA_IDS = pd.getString("DATA_IDS");
				if(null != DATA_IDS && !"".equals(DATA_IDS)){
					String ArrayDATA_IDS[] = DATA_IDS.split(",");
					pathList = picturesService.getAllById(ArrayDATA_IDS);
					//删除图片
					for(int i=0;i<pathList.size();i++){
						DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pathList.get(i).getString("PATH"));
					}
					picturesService.deleteAll(ArrayDATA_IDS);
					pd.put("msg", "ok");
				}else{
					pd.put("msg", "no");
				}
				pdList.add(pd);
				map.put("list", pdList);
			}
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
		logBefore(logger, "导出Pictures到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("标题");	//1
			titles.add("文件名");	//2
			titles.add("路径");	//3
			titles.add("创建时间");	//4
			titles.add("属于");	//5
			titles.add("备注");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = picturesService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TITLE"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("PATH"));	//3
				vpd.put("var4", varOList.get(i).getString("CREATETIME"));	//4
				vpd.put("var5", varOList.get(i).getString("MASTER_ID"));	//5
				vpd.put("var6", varOList.get(i).getString("BZ"));	//6
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
	
	
	//删除图片
	@RequestMapping(value="/deltp")
	public void deltp(PrintWriter out) {
		logBefore(logger, "删除图片");
		try{
			PageData pd = new PageData();
			pd = this.getPageData();
			String PATH = pd.getString("PATH");													 		//图片路径
			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("PATH")); 	//删除图片
			if(PATH != null){
				picturesService.delTp(pd);																//删除数据中图片数据
			}	
			out.write("success");
			out.close();
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
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
	
	/*--------------------------------给移动端接口-------------------------*/
	
	@RequestMapping(value="/mobileListAll")
	@ResponseBody
	public JSONPObject  mobileListAll(Page page,Model model,HttpServletRequest req,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
	    String callbackFunName =req.getParameter("callbackparam");//得到js函数名称  
	    
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id",null);
		page.setPd(pd);
		List<PageData> varList = null;
		List<PageData> classList;
		ResultAction resultAction = new ResultAction();
		try {  
			varList = classService.listAll(pd); 
			JSONArray classes = JSONArray.fromObject(varList);
			resultAction.setData("dd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//列出Pictures列表
		 return new JSONPObject(callbackFunName, varList);    
	} 
	
	@RequestMapping(value="/mobileListSon")
	@ResponseBody
	public JSONPObject  mobileListSon(Page page,Model model,HttpServletRequest req,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
	    String callbackFunName =req.getParameter("callbackparam");//得到js函数名称  
	    
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData(); 
		page.setPd(pd);
		List<PageData> varList = null;  
		try {  
			varList = classService.list(page); 
			JSONArray classes = JSONArray.fromObject(varList); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//列出Pictures列表
		 return new JSONPObject(callbackFunName, varList);    
	} 
	
	
}
