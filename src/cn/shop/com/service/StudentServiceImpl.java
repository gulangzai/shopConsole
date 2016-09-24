package cn.shop.com.service;

import cn.shop.com.dao.StudentDao;
import cn.shop.com.jopo.Student;

public class StudentServiceImpl implements StudentService {

	
	private StudentDao studentDao;
	
	
	public StudentDao getStudentDao() {
		return studentDao;
	}


	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}


	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
         studentDao.save(student);
	}

}
