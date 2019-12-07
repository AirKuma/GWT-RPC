package com.h4.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

	
	public class MainGUI extends Composite {
		private VerticalPanel vPanel = new VerticalPanel();
		private TextBox txt1,txt2,txt3;
		private Label myLbl;
		
		private ExServerClientImpl serviceImpl;
		
		public MainGUI(ExServerClientImpl serviceImpl){
			initWidget(this.vPanel);
			this.serviceImpl = serviceImpl;
			
			HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
			vPanel.add(horizontalPanel_1);
			
			Label lblId = new Label("Id:");
			horizontalPanel_1.add(lblId);
			lblId.setWidth("40px");
			
			this.txt1 = new TextBox();
			horizontalPanel_1.add(txt1);
			
			HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
			vPanel.add(horizontalPanel_2);
			
			Label lblName = new Label("Name:");
			horizontalPanel_2.add(lblName);
			
			this.txt2 = new TextBox();
			horizontalPanel_2.add(txt2);
			
			HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
			vPanel.add(horizontalPanel_3);
			
			Label lblEmail = new Label("Email:");
			horizontalPanel_3.add(lblEmail);
			
			this.txt3 = new TextBox();
			horizontalPanel_3.add(txt3);			
			
			HorizontalPanel horizontalPanel = new HorizontalPanel();
			vPanel.add(horizontalPanel);
			horizontalPanel.setWidth("300px");
			
			Button btn1 = new Button("Query");
			horizontalPanel.add(btn1);
			btn1.addClickHandler(new Btn1ClickHandler());
			
			Button btn2 = new Button("Add");
			horizontalPanel.add(btn2);
			btn2.addClickHandler(new Btn1ClickHandler1());
			
			Button btn3 = new Button("Delete");
			horizontalPanel.add(btn3);
			btn3.addClickHandler(new Btn1ClickHandler2());
			
			Label lblKeyIsId = new Label("Enter id as key");
			horizontalPanel.add(lblKeyIsId);
			
			
			this.myLbl = new Label("");
			this.vPanel.add(this.myLbl);
		}
	
	public void updateLabel(String greeting){
		this.myLbl.setText(greeting);
	}
	
	private class Btn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String name = txt1.getText();
			serviceImpl.sayHello(name);
		}
		
	}
	private class Btn1ClickHandler1 implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String id = txt1.getText();
			String name = txt2.getText();
			String email = txt3.getText();
			serviceImpl.sayHello1(id,name,email);
		}
		
	}
	private class Btn1ClickHandler2 implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String name = txt1.getText();
			serviceImpl.sayHello2(name);
		}
		
	}
}

