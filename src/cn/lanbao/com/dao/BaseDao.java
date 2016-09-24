package cn.lanbao.com.dao;

import java.util.List;

public interface BaseDao<T> {
	
	//hibernate funciton
	public  void h_save(T t);
	 
	public  void h_update(T t);
	
	public  void h_delete(Object obj);
	
	public void h_deleteById(Long id);
	
	public List<T> h_getByIds(Long[] ids);
	
	public T h_getById(Long id);
	
	public List<T> h_getList();

	//redis function
	public T r_get(String key, Class<T> cls);

	void r_put(String key, T t, Class<T> cls);
 
	
}
