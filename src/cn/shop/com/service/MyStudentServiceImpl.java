package cn.shop.com.service;

import org.springframework.stereotype.Service;

import cn.shop.com.dao.StudentDao;
import cn.shop.com.jopo.Student;

@Service("myStudentService")
public class MyStudentServiceImpl implements MyStudentService {

	
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
