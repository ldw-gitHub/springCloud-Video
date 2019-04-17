package com.itcast.dw.page;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;  
import java.lang.reflect.Type;

import org.apache.commons.lang.StringUtils;

/** 
 * 反射工具类 
 */
public class ReflectUtils {
	/** 
     * 通过反射获得超类的参数类型，取第一个参数类型 
     * @param <T> 类型参数 
     * @param clazz 超类类型 
     */  
    @SuppressWarnings({"rawtypes", "unchecked"})  
    public static <T> Class<T> getClassGenricType(final Class clazz) {  
        return getClassGenricType(clazz, 0);  
    }  
      
    /** 
     * 通过反射根据索引获得超类的参数类型 
     * @param clazz 超类类型 
     * @param index 索引 
     */  
    @SuppressWarnings("rawtypes")  
    public static Class getClassGenricType(final Class clazz, final int index) {  
        Type genType = clazz.getGenericSuperclass();
        
        if (!(genType instanceof ParameterizedType)) {  
            return Object.class;  
        }  
        
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();  
        
        if (index >= params.length || index < 0) {  
            return Object.class;  
        }
        
        if (!(params[index] instanceof Class)) {  
            return Object.class;  
        } 
        
        return (Class) params[index];  
    }  
    
    /**
     * 反射判断属性值是否为空
     * */
    public <T> T checkNull(T t){
    	Class<?> type=t.getClass();
		Field[] fields=type.getDeclaredFields();
		for (Field field : fields) {
			 Method method = null;
             Object value = null;
             String name=field.getName();
             String upperName = name.substring(0, 1).toUpperCase()+name.substring(1);   //首字母大写
             try {
            	 method=type.getMethod("get"+ upperName);
            	 value=method.invoke(t);
            	 if(!value.equals("")&&value!=null){
            		 return t;
            	 }
			 } catch (Exception e) {
			 }
		}
		return null;
    }
    
    

	/**
	 * 根据属性名获取属性值 
	 * @param fieldName
	 * @param o
	 * @return
	 */
     public static Object getFieldValueByName(String fieldName, Object o) {  
	      try{    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           if(value != null && value instanceof String){
	        	   String vs = value.toString();
	        	   if(StringUtils.isEmpty(vs)){
	        		   return null;
	        	   }
	           }
	           return value;    
	       } catch (Exception e) {    
	           return null;    
	       }    
	 }   
     
     /**
      * 获得对象的所有属性
      * @param o
      * @return
      */
     public static String[] getFieldsByClass(Object o){
    	if(o == null){
    	   return new String[1];
    	}
    	 
 		Field[] fields = ((Class<?>)o).getDeclaredFields();
 		if(fields == null || fields.length == 0){
 			return new String[1];
 		}
 		
 		String[] fs = new String[fields.length];
 		Field f = null;
 		for(int i = 0; i < fields.length; i++){
 			f = fields[i];
 			fs[i] = f.getName().toLowerCase();
 		}
 		
 		return fs;
     }
}
