package com.itcast.dw.test.thread.dbThreadPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {
	
	static class ConnectionHandler implements InvocationHandler{

		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
			if(arg1.getName().equals("commit")){
				TimeUnit.MILLISECONDS.sleep(100);
			}
			return null;
		}
	}
	
	//创建一个connection代理，在commit时休眠10秒
    public static final Connection createConnection(){
    	return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class<?>[]{Connection.class}, new ConnectionHandler());
    }
}
