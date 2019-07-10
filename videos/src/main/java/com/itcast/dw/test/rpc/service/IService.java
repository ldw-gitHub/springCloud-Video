package com.itcast.dw.test.rpc.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote{
	
	public String queryName() throws RemoteException;

}
