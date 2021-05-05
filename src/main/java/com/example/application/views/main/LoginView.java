package com.example.application.views.main;

import java.sql.SQLException;

import com.example.application.Account;
import com.example.application.Application;
import com.example.application.data.entity.SamplePerson;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;

@Route(value = "Login", layout = MainView.class)
@PageTitle("Login")
//@CssImport("./views/paymentform/login-form-view.css")
public class LoginView extends Div{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HtmlComponent br = new HtmlComponent("br");
	private TextField email = new TextField("Email");
	private PasswordField pass = new PasswordField("Password");
	public static boolean loggedIn;
	public static Account user;
	public LoginView (){
		addClassName("login-form-view");
		
		add(createTitle());
        add(createFormLayout());
	    add(
			new Button("Login", event ->{
				if(loggedIn) {
					Notification.show("Already Logged In");
					
				}else  if(email.isEmpty() || pass.isEmpty()) {
					
						Notification.show("Please fill out fields");
						
				}else {
					try {
						Application.setPGM("user1", "pass");
					    
						loggedIn = Application.pgm.login(email.getValue(), pass.getValue());
						if(loggedIn) {
						//System.out.println(Application.pgm.login(email.getValue(), pass.getValue()));
						user = Application.pgm.getAcc(email.getValue());
						
						Notification.show("Successfully logged in");
						UI.getCurrent().navigate("Home-Page");
						}else {
							UI.getCurrent().navigate("Login");
							Notification.show("Login error");
							
						}
						
						
						//UI.getCurrent().navigate("Home-Page");
					} catch (Exception e) {
					// TODO Auto-generated catch block
						Notification.show("Login error");
						e.printStackTrace();
					}
				}
				}),
			br,
			new Button("Clear", event -> {
				email.setValue("");
				pass.setValue("");
			}),
			
			br,
			new Button("Sign Up", event ->{
				UI.getCurrent().navigate("SignUp");
			}),
			
			new Button("LogOut", event ->{
				loggedIn = false;
				UI.getCurrent().navigate("Home-Page");
			})
			);
     
	    }

	
	
	
	private Component createFormLayout() {
		// TODO Auto-generated method stub
		FormLayout formLayout = new FormLayout();
		email.setErrorMessage("Please enter a valid email address");
		formLayout.add(email, pass);
		return formLayout;
	}


	private Component createTitle() {
		// TODO Auto-generated method stub
		return new H3("Login");
	}
	public static Boolean setBoo(Boolean bool) {
		loggedIn = bool;
		return loggedIn;
	}

}
