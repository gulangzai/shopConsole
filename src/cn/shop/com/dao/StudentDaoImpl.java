package cn.shop.com.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport; 
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import cn.shop.com.jopo.Student;

public class StudentDaoImpl extends SqlMapClientDaoSupport implements StudentDao {

	
	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("insertStudent",student);
	}
	
	/* (non-Javadoc)
	 * @see cn.shop.com.dao.StudentDao#queryAllStudent()
	 */
	@Override
	public List<Student> queryAllStudent(){
	   return getSqlMapClientTemplate().queryForList("selectAllStudents");
	}

	public static void main(String[] args) {
		StudentDao studentDao = new StudentDaoImpl();
		studentDao.queryAllStudent();
	}
}
