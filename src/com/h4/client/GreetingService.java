package com.h4.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String sayHello(String name) throws IllegalArgumentException;
	String sayHello1(String id,String name,String email) throws IllegalArgumentException;
	String sayHello2(String name) throws IllegalArgumentException;
}
