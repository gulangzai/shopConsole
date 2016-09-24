package cn.lanbao.com.utils;

import java.util.UUID;

public class DbUtil {
	
	public static String getKey(){
		return UUID.randomUUID().toString();
	} 
}
