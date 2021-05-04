package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;
import com.example.application.SQLConnect;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	
public static SQLConnect pgm;
	
							
	
    public static void main(String[] args) throws Exception {
    	SQLConnect pgm = new SQLConnect("user1", "pass");
   
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
        
        pgm.close();
    }
    
	public static String decrypt(String pass) {
		
		return pass;
	}
	
	public static String encrypt(String pass) {
		
		return pass;
	}
	public static SQLConnect setPGM(String user, String pass) throws Exception {
		pgm = new SQLConnect(user, pass);
		return pgm;
	}
}
