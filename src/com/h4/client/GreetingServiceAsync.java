package com.h4.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void sayHello(String name,AsyncCallback callback)
			throws IllegalArgumentException;
	
	void sayHello1(String id,String name,String email,AsyncCallback callback)
			throws IllegalArgumentException;
	
	void sayHello2(String name,AsyncCallback callback)
			throws IllegalArgumentException;
}
