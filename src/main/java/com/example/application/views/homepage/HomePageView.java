package com.example.application.views.homepage;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
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
import com.vaadin.flow.component.html.DescriptionList.Term;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.dialog.*;



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
	Dialog dialog = new Dialog();
	Button confirmButton = new Button("Confirm");
	Button cancelButton = new Button("Cancel");
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
        if(LoginView.loggedIn == false) {
        	add(new Label("Welcome, to begin shopping login or create an account"));
        	add(br);
        	add(br);
        	add(new Button("Login", event ->{ UI.getCurrent().navigate("Login");}));
        	add(new Button("Sign Up", event ->{UI.getCurrent().navigate("SignUp");}));
        	
        }
        if(LoginView.loggedIn)
        	add(new Button("Logout", event->{LoginView.loggedIn = false; UI.getCurrent().navigate("Login");}));
        //////////////////////////////////////////////////////////////////////////////
        //Grid
        ///////////////////////////////////////////////////////////////////////////////
        Grid<Item> grid = new Grid<>();
        
        try {
			grid.setItems(Application.pgm.getAllItems());
			grid.addColumn(new ComponentRenderer<>(item -> {
			    Image image = new Image(item.getPic(),
			            item.getName());
			    image.setWidth(100, Unit.PIXELS);
			    image.setHeight(100, Unit.PIXELS);
			    return image;
			})).setHeader("Image");
			grid.addColumn(new ComponentRenderer<>(item ->{
				NumberField amount = new NumberField();
				amount.addValueChangeListener(event ->{	
					if((amount).getValue() > 0) {
							double temp;
							temp = (double)amount.getValue();
							Integer temp2 = (int)temp;
							//System.out.println("Adding to cart");
							user.addToCart(item, temp2);
							Notification.show("Adding " + amount.getValue() + " of " + item.getName() + " to cart");
					}else
						if(user.removeFromCart(item))
							Notification.show("Removed " + item.getName() + " from cart");
				});
				Double val = (double) item.getQuant();
				amount.setValue(val);
				return amount;
			})).setHeader("Quantity");
			grid.addColumn(Item::getName).setHeader("Name");
			grid.addColumn(Item::getPriceString).setHeader("Price");
			grid.addColumn(Item::getDesc).setHeader("Description");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
       
       if(LoginView.loggedIn) {
    	   add(grid);
    	   Dialog dialog = new Dialog();
    	   add(new Button("Checkout", event ->{
    		   if(user.getCart().size() == 0) {
    			   Notification.show("Nothing in cart");
    			   
    		   }else {
    		   Boolean erro = false;
    		   dialog.setCloseOnEsc(false);
        	   dialog.setCloseOnOutsideClick(false);
        	   Span message = new Span();
        	   Button confirmButton = new Button("Confirm", e -> {
        	       dialog.removeAll();
        		   dialog.close();
        	       UI.getCurrent().navigate("payment-form");
        	   });
        	   Button cancelButton = new Button("Cancel", e-> {
        		   dialog.removeAll();
        	       dialog.close();
        	   });
        	   // Cancel action on ESC press
        	   Shortcuts.addShortcutListener(dialog, () -> {
        		   dialog.removeAll();
        	       message.setText("Cancelled...");
        	       dialog.close();
        	   }, Key.ESCAPE);
        	   
        	   
    	   //set pgm again 
    		   try {
    			   Application.setPGM("user1", "pass");
    		   } catch (Exception e) {
			// TODO Auto-generated catch block
    			   e.printStackTrace();
    		   }
    	   
    		   ArrayList<Item> cur = user.getCart();
    	   //check if we have enough inventory
    		   for(Item i: cur) {
    			   //setup pgm
    			   try {
    				   Application.setPGM("user1", "pass");
    			   } catch (Exception e) {
    				   // TODO Auto-generated catch block
    				   e.printStackTrace();
    			   }
    			   try {
    		   
    				   if(Application.pgm.enoughItem(i, i.getQuant()) == false) {
    					   Notification.show("Error: not enough inventory for " + i.getName());
    					   user.removeFromCart(i);
    					   erro = true;
    				   }
    			   } catch (SQLException e) {
				
    			   }
    		   
    		   }
    		   
    		   if(erro) {
    			   dialog.close();
    		   }else {
    			   dialog.add(new Term("Once you leave this page you cannot alter the cart. Proceed to Payment Page?"));
    			   dialog.add(new Div( confirmButton, cancelButton));
    			   dialog.open();
    		   }
    	    }
    	   }

       ));
       

    }
   }
}
