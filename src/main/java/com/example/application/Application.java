package com.example.application;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Random;

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
		String crypTxt = "";
		for (int i=0;i<pass.length();i++)  
        {  
			if(i % 4 == 0)
				continue;
            char ch=Character.toLowerCase(pass.charAt(i));  
            switch (ch)  
            {  
                case '{':  
                    crypTxt=crypTxt+"A";  
                    break;  
                case '}':  
                    crypTxt=crypTxt+"B";  
                    break;  
                case '#':  
                    crypTxt=crypTxt+"C";  
                    break;  
                case '~':  
                    crypTxt=crypTxt+"D";  
                    break;  
                case '+':  
                    crypTxt=crypTxt+"E";  
                    break;  
                case '-':  
                    crypTxt=crypTxt+"F";  
                    break;  
                case '*':  
                    crypTxt=crypTxt+"G";  
                    break;  
                case '@':  
                    crypTxt=crypTxt+"H";  
                    break;  
                case '/':  
                    crypTxt=crypTxt+"I";  
                    break;  
                case '\\':  
                    crypTxt=crypTxt+"J";  
                    break;  
                case '?':  
                    crypTxt=crypTxt+"K";  
                    break;  
                case '$':  
                    crypTxt=crypTxt+"L";  
                    break;  
                case '!':  
                    crypTxt=crypTxt+"M";  
                    break;  
                case '^':  
                    crypTxt=crypTxt+"N";  
                    break;  
                case '(':  
                    crypTxt=crypTxt+"O";  
                    break;  
                case ')':  
                    crypTxt=crypTxt+"P";  
                    break;  
                case '<':  
                    crypTxt=crypTxt+"Q";  
                    break;  
                case '>':  
                    crypTxt=crypTxt+"R";  
                    break;  
                case '=' :  
                    crypTxt=crypTxt+"S";  
                    break;  
                case ';':  
                    crypTxt=crypTxt+"T";  
                    break;  
                case ',':  
                    crypTxt=crypTxt+"U";  
                    break;  
                case '_' :  
                    crypTxt=crypTxt+"V";  
                    break;  
                case '[':  
                    crypTxt=crypTxt+"W";  
                    break;  
                case ']' :  
                    crypTxt=crypTxt+"X";  
                    break;  
                case ':':  
                    crypTxt=crypTxt+"Y";  
                    break;  
                case '\"' :  
                    crypTxt=crypTxt+"Z";  
                    break;       
                case '1':  
                    crypTxt=crypTxt+"r";  
                    break;  
                case '2':  
                    crypTxt=crypTxt+"k";  
                    break;  
                case '3':  
                    crypTxt=crypTxt+"b";  
                    break;  
                case '4':  
                    crypTxt = crypTxt+"e";  
                    break;  
                case '5':  
                    crypTxt = crypTxt+"q";  
                    break;  
                case '6':  
                    crypTxt = crypTxt+"h";  
                    break;  
                case '7':  
                    crypTxt = crypTxt+"u";  
                    break;  
                case '8' :  
                    crypTxt= crypTxt+"y";  
                    break;  
                case '9':  
                    crypTxt = crypTxt+"w";  
                    break;  
                case '0':  
                    crypTxt = crypTxt+"z";  
                    break;  
                 default:  
                    crypTxt=crypTxt+"0";  
                    break;  
            }  
        }
		return crypTxt;  
          
	}
	
	public static String encrypt(String pass) {
		String crypTxt = "";
		Random rand = new Random();
		char [] randChar = {'$', '!', '^', '%', '(', ')', '3', '7', 'L', 'W', 'S'};
		for (int i=0;i<pass.length();i++)  
        {  
			if(i % 4 == 0)
				crypTxt=crypTxt+randChar[rand.nextInt() % 11];
            char ch=Character.toLowerCase(pass.charAt(i));  
            switch (ch)  
            {  
                case 'a':  
                    crypTxt=crypTxt+"{";  
                    break;  
                case 'b':  
                    crypTxt=crypTxt+"}";  
                    break;  
                case 'c':  
                    crypTxt=crypTxt+"#";  
                    break;  
                case 'd':  
                    crypTxt=crypTxt+"~";  
                    break;  
                case 'e':  
                    crypTxt=crypTxt+"+";  
                    break;  
                case 'f':  
                    crypTxt=crypTxt+"-";  
                    break;  
                case 'g':  
                    crypTxt=crypTxt+"*";  
                    break;  
                case 'h':  
                    crypTxt=crypTxt+"@";  
                    break;  
                case 'i':  
                    crypTxt=crypTxt+"/";  
                    break;  
                case 'j':  
                    crypTxt=crypTxt+"\\";  
                    break;  
                case 'k':  
                    crypTxt=crypTxt+"?";  
                    break;  
                case 'l':  
                    crypTxt=crypTxt+"$";  
                    break;  
                case 'm':  
                    crypTxt=crypTxt+"!";  
                    break;  
                case 'n':  
                    crypTxt=crypTxt+"^";  
                    break;  
                case 'o':  
                    crypTxt=crypTxt+"(";  
                    break;  
                case 'p':  
                    crypTxt=crypTxt+")";  
                    break;  
                case 'q':  
                    crypTxt=crypTxt+"<";  
                    break;  
                case 'r':  
                    crypTxt=crypTxt+">";  
                    break;  
                case 's' :  
                    crypTxt=crypTxt+"=";  
                    break;  
                case 't':  
                    crypTxt=crypTxt+";";  
                    break;  
                case 'u':  
                    crypTxt=crypTxt+",";  
                    break;  
                case 'v' :  
                    crypTxt=crypTxt+"_";  
                    break;  
                case 'w':  
                    crypTxt=crypTxt+"[";  
                    break;  
                case 'x' :  
                    crypTxt=crypTxt+"]";  
                    break;  
                case 'y':  
                    crypTxt=crypTxt+":";  
                    break;  
                case 'z' :  
                    crypTxt=crypTxt+"\"";  
                    break;  
                case ' ' :  
                    crypTxt=crypTxt+" ";  
                    break;  
                case '.':  
                    crypTxt=crypTxt+'3';  
                    break;  
                case ',':  
                    crypTxt=crypTxt+"1";  
                    break;  
                case '(':  
                    crypTxt=crypTxt+'4';  
                    break;  
                case '\"':  
                    crypTxt=crypTxt+'5';  
                    break;  
                case ')' :  
                    crypTxt=crypTxt+"7";  
                    break;  
                case '?' :  
                    crypTxt= crypTxt+"2";  
                    break;  
                case '!':  
                    crypTxt= crypTxt+"8";  
                    break;  
                case '-' :  
                    crypTxt= crypTxt+"6";  
                    break;  
                case '%' :  
                    crypTxt = crypTxt+"9";  
                    break;  
                case '1':  
                    crypTxt=crypTxt+"r";  
                    break;  
                case '2':  
                    crypTxt=crypTxt+"k";  
                    break;  
                case '3':  
                    crypTxt=crypTxt+"b";  
                    break;  
                case '4':  
                    crypTxt = crypTxt+"e";  
                    break;  
                case '5':  
                    crypTxt = crypTxt+"q";  
                    break;  
                case '6':  
                    crypTxt = crypTxt+"h";  
                    break;  
                case '7':  
                    crypTxt = crypTxt+"u";  
                    break;  
                case '8' :  
                    crypTxt= crypTxt+"y";  
                    break;  
                case '9':  
                    crypTxt = crypTxt+"w";  
                    break;  
                case '0':  
                    crypTxt = crypTxt+"z";  
                    break;  
                 default:  
                    crypTxt=crypTxt+"0";  
                    break;  
            }
        }
		return crypTxt;
	}
	
	public static SQLConnect setPGM(String user, String pass) throws Exception {
		pgm = new SQLConnect(user, pass);
		return pgm;
	}
}
