package cn.shop.com.controller;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import cn.shop.com.jopo.Student;
import cn.shop.com.service.MyStudentService;

public class StudentController  {
	
	private MyStudentService myStudentService;
	 
	public MyStudentService getMyStudentService() {
		return myStudentService;
	}
 
	public void setMyStudentService(MyStudentService myStudentService) {
		this.myStudentService = myStudentService;
	}
 
	public ModelAndView save(Student student){
		myStudentService.save(student);
		ModelAndView modelAndView = new ModelAndView("success");
		return modelAndView;
	}
	 
}
