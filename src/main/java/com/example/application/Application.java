package com.example.application;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
		//Creating KeyPair generator object
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);
		//Generate the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();
		//Getting the public key from the key pair
		PublicKey publicKey = pair.getPublic();
		//Creating a Cipher object
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Initializing a Cipher object
		try {
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Decrypting the text
		byte[] inPass = pass.getBytes();
		byte[] decipheredText = null;
		try {
			decipheredText = cipher.doFinal(inPass);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decipheredText.toString();
	}
	
	public static String encrypt(String pass) {
		KeyPairGenerator keyPairGen = null;
		Cipher cipher = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		//Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);
		//Generate the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();
		//Getting the public key from the key pair
		PublicKey publicKey = pair.getPublic();
		//Creating a Cipher object
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			e1.printStackTrace();
		}
		//Initializing a Cipher object
		try {
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		//Adding data to the cipher	
		byte[] inPass = pass.getBytes();
		cipher.update(inPass);
		//Encrypting the data
		byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return cipherText.toString();
	}
	
	public static Cipher getCiphInstance() {
		KeyPairGenerator keyPairGen = null;
		Cipher cipher = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		//Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);
		//Generate the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();
		//Getting the public key from the key pair
		PublicKey publicKey = pair.getPublic();
		//Creating a Cipher object
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			e1.printStackTrace();
		}
		return cipher;
	}
	
	public static SQLConnect setPGM(String user, String pass) throws Exception {
		pgm = new SQLConnect(user, pass);
		return pgm;
	}
}
