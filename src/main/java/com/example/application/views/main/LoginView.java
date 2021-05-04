package com.example.application.views.main;

import java.sql.SQLException;

import com.example.application.Application;
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
	public static boolean loggedIn = false;
	 
	public LoginView (){
		addClassName("login-form-view");
		
		add(createTitle());
        add(createFormLayout());
	    add(
			new Button("Login", event ->{
				try {
					Application.setPGM(email.getValue(), pass.getValue());
					Application.pgm.login(email.getValue(), pass.getValue());
					 Notification.show("Successfully logged in");
					 LoginView.setBoo(true);
					UI.getCurrent().navigate("Home-Page");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					 Notification.show("Login error");
					e.printStackTrace();
				}
			}),
			br,
			new Button("Sign Up", event ->{
				UI.getCurrent().navigate("SignUp");
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
