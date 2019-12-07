package com.h4;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Login {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	Long id;
	
	@Persistent
	String name;
	
	@Persistent
	String userid;
	
	@Persistent
	String email;
	

	public Login(String userid,String name,String email) {
		super();
		this.name = name;
		this.userid = userid;
		this.email = email;
	}
	
	public boolean save() {
		boolean saved = false;

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Login.class);
		query.setFilter("userid == uid");
		query.declareParameters("String uid");

		List<Login> members = (List<Login>) query.execute(this.userid);
		if (members.isEmpty()) {
			pm.makePersistent(this);
			saved = true;
		}
		pm.close();
		return saved;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.userid = email;
	}
	
}