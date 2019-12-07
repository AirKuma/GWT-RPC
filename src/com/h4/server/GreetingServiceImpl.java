package com.h4.server;

import java.sql.SQLException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.h4.client.GreetingService;
import com.h4.shared.FieldVerifier;
import com.h4.Login;
import com.h4.PMF;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.UserService;*/


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public String sayHello(String name) throws IllegalArgumentException {
		String greeting = "";
		int i = 0;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery("SELECT FROM " + Login.class.getName()
				+ " WHERE userid == p " + " PARAMETERS String p");
		List<Login> ls = (List<Login>) query.execute(name);
		for (Login ful : ls) {
			name =  ("Id: "+ful.getUserid()+" name: "+ful.getName()+" Email: "+ful.getEmail()).toString();
			i++;
		}		
		pm.close();
		
		if (name == null || name.length() == 0 || i==0)
			greeting = "Not found.";		
		else
			greeting = name;	
		
		return greeting;
	}
	public String sayHello1(String id,String name,String email) throws IllegalArgumentException {	
		String greeting = "";

		Login add = new Login(id,name,email);
		if(id == null || id.length() == 0)
			greeting = "Id must not be empty!";
		else{
		if (add.save()) {
			greeting = "Added!";
		} else {
			greeting = "The id is existed!"; 
		}
		}
		return greeting;
	}
	
public String sayHello2(String name) throws IllegalArgumentException {
		String greeting = "";
		int i = 0;
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery("SELECT FROM " + Login.class.getName()
				+ " WHERE userid == p " + " PARAMETERS String p");

		List<Login> ls = (List<Login>) query.execute(name);
		for (Login ful : ls) {
			pm.deletePersistent(ful);
			i++;
		}
		pm.close();
		
		if (name == null || name.length() == 0 || i==0)
			greeting = "Not found,unable to delete.";		
		else
			greeting = "Deleted!";	
		
		return greeting;
	}
}
