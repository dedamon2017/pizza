package security;


import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;




@ApplicationScoped
public class Utility {
	
	private static final Logger LOGGER = Logger.getLogger(Utility.class.getName());
	
	private Properties properties = new Properties();
	private final String fileName = "users.properties"; 
	private Map<String, String> tokenDB = new ConcurrentHashMap<>();;
	
	@PostConstruct
	public void loadPropertiesAndInit() {
		
		try (InputStream inputStream = Utility.class.getClassLoader().getResourceAsStream(fileName)) {
			properties.load(inputStream);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public String getAdminUserName() {
		return properties.getProperty("adminuser");
	}
	
	public String getAdminPassword() {
		return properties.getProperty("adminpassword");
	}
	
	public void addToken(String userName, String token) {
		tokenDB.put(userName, token);
		LOGGER.info(String.format("Map %s", tokenDB.toString()));
	}

	public Map<String, String> getTokenDB() {
		return tokenDB;
	}
	
	
	
	
}
