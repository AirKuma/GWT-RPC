package com.h4.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ExServerClientImpl implements ExServerClientInt{

	private GreetingServiceAsync service;
	private MainGUI maingui;
	
	public ExServerClientImpl(String url){
		System.out.println(url);
		this.service = GWT.create(GreetingService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.maingui = new MainGUI(this);
	}
	
	@Override
	public void sayHello(String name) {
		this.service.sayHello(name, new DeFaultCallback());
		
	}
	
	public void sayHello1(String id,String name,String email) {
		this.service.sayHello1(id,name,email, new DeFaultCallback());
		
	}
	
	public void sayHello2(String name) {
		this.service.sayHello2(name, new DeFaultCallback());
		
	}
	
	public MainGUI getMainGUI(){
		return this.maingui;
	}
	
	private class DeFaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error!!");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String greeting = (String) result;
				maingui.updateLabel(greeting);
			}
			
		}
		
	}
}
