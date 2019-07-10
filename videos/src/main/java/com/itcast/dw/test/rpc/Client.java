package com.itcast.dw.test.rpc;

public class Client {/*
	
	public static void main(String[] args) {
		Registry registry = null;
		 try {
			 //获取服务注册管理
			registry = LocateRegistry.getRegistry("10.0.0.92",8762);
			//列出所有注册服务
			String[] list = registry.list();
			@SuppressWarnings("unchecked")
			List<String> arrayToList = Collections.arrayToList(list);
			arrayToList.stream().forEach(s -> System.out.println(s));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			 //根据命名获取服务
			IService lookup = (IService) registry.lookup("rmiTest");
			//调用远程方法
			String queryName = lookup.queryName();
			
			System.out.println(queryName);
			
			
			
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

*/}
