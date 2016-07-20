package security;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

public class Utility {
	private static final String ADMIN = "admin";
	private static final String PASS = "qwerty";
	private static final String RESOURCE_PATH = "main.java.security.";
	private static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "users");
	
	@PostConstruct
	public String getUserName() {
		return res.getString("adminuser");
	}
	
	@PostConstruct
	public String getPassword() {
		return res.getString("adminpassword");
	}

	public String getAdmin() {
		return ADMIN;
	}

	public String getPass() {
		return PASS;
	}
	
	
	
}
