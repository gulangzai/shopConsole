package cn.shop.com.dao;

import java.util.List;
import cn.shop.com.jopo.Student;

public interface StudentDao {

	void save(Student student);

	List<Student> queryAllStudent();

}
