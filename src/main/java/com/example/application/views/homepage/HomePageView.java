package com.example.application.views.homepage;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import java.awt.Component;
import java.awt.GridLayout;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.lang.*;

import com.example.application.Account;
import com.example.application.Application;
import com.example.application.Item;
import com.example.application.views.main.LoginView;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H1;

@Route(value = "Home-Page", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Home Page")
@CssImport("./views/homepage/home-page-view.css")
public class HomePageView extends HorizontalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField search;
    private Button Search;
	private Component Button;
	public Component Text;
	public String item;
	public com.vaadin.flow.component.button.Button message;
	public Set<Item> temp = new HashSet<>();
	public HashMap <Item, Integer> quantity = new HashMap<Item, Integer>();
	//public TextField input = new TextField("Enter item amount");
	HtmlComponent br = new HtmlComponent("br");
	//Dummy account to save item selection for user
	public static Account user = new Account("pat", "patter", "pa", "p", "patricia", "i");

    public HomePageView() {
    	
    	 try {
 			Application.setPGM("user1", "pass");
 		} catch (Exception e2) {
 			// TODO Auto-generated catch block
 			e2.printStackTrace();
 		}
        addClassName("home-page-view");
       
        add(new Button("Login", event ->{ UI.getCurrent().navigate("Login");})
        	
        );
        //////////////////////////////////////////////////////////////////////////////
        //Grid
        ///////////////////////////////////////////////////////////////////////////////
        Grid<Item> grid = new Grid<>();
        
        try {
			grid.setItems(Application.pgm.getAllItems());
			grid.addColumn(new ComponentRenderer<>(item ->{
				NumberField amount = new NumberField();
				amount.addValueChangeListener(event ->{
					//Item.setQuant(event.getValue());
					if((amount).getValue() > 0) {
						double temp;
						temp = (double)amount.getValue();
						Integer temp2 = (int)temp;
						System.out.println("Adding to cart");
						user.addToCart(item, temp2);		
					}
					/*else if((amount.getValue() == 0)) {
						user.removeFromCart(item);
					}*/
				});
				amount.setValue(0.0);
				
				return amount;
			})).setHeader("Quantity");
			grid.addColumn(Item::getName).setHeader("Name");
			grid.addColumn(Item::getPrice).setHeader("Price");
			grid.addColumn(Item::getDesc).setHeader("Description");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        GridMultiSelectionModel<Item> selectionModel = (GridMultiSelectionModel<Item>) grid.setSelectionMode(SelectionMode.MULTI);
      //selectionModel.selectAll();
       selectionModel.addMultiSelectionListener(event -> {
		message.setText(String.format("%s item add, % item removed", event.getAddedSelection().size(), event.getRemovedSelection().size()));
    	  
       });
       
       
       if(LoginView.loggedIn) {
    	   add(grid);
       
    	   add(new Button("Checkout", event ->{
    	   //set pgm again
    	   try {
			Application.setPGM("user1", "pass");
    	   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	   }
    	   //call checkout
    	   try {
			user.checkout(Application.pgm);
    	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	   }
    	   UI.getCurrent().navigate("payment-form");
    	   }
    	  
    	   
       ));
       

    }
   }
}
