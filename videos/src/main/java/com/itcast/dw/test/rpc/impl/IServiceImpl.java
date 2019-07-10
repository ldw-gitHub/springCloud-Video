package com.itcast.dw.test.rpc.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.springframework.stereotype.Service;

import com.itcast.dw.test.rpc.service.IService;
@Service
public class IServiceImpl extends UnicastRemoteObject implements IService{

	/**
	 * 意义，目的和功能
	 */
	private static final long serialVersionUID = -2504639650911120890L;

	public IServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String queryName() throws RemoteException {
		return "ldw";
	}

}
