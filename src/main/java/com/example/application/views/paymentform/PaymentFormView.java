package com.example.application.views.paymentform;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.sql.SQLException;

import com.example.application.Account;
import com.example.application.Application;
import com.example.application.Item;
import com.example.application.views.homepage.HomePageView;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;

@Route(value = "payment-form", layout = MainView.class)
@PageTitle("Payment Form")
@CssImport("./views/paymentform/payment-form-view.css")
public class PaymentFormView extends Div {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField cardNumber = new TextField("Credit card number");
    private TextField cardholderName = new TextField("Cardholder name");
    private Select<Integer> month = new Select<>();
    private Select<Integer> year = new Select<>();
    private ExpirationDateField expiration = new ExpirationDateField("Expiration date", month, year);
    private PasswordField csc = new PasswordField("CSC");
    public static Account user = new Account("pat", "patter", "pa", "p", "patricia", "i");
    private Button cancel = new Button("Cancel");
    private Button submit = new Button("Submit");
    private Label total = new Label();
    
    public PaymentFormView() {
        addClassName("payment-form-view");

        add(createTitle());
 Grid<Item> grid = new Grid<>();
        
        try {
				Application.setPGM("user1", "pass");
		} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
			grid.setItems(HomePageView.user.getCart());
			grid.addColumn(Item::getQuant).setHeader("Quantity");
			grid.addColumn(Item::getName).setHeader("Name");
			grid.addColumn(Item::getPrice).setHeader("Price");
			
		try {
			Application.setPGM("user1", "pass");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
		try {
			total.setText("$" + Double.toString(HomePageView.user.checkout(Application.pgm)));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        add(grid);
        add(total);
        add(createFormLayout());
        add(createButtonLayout());

        cancel.addClickListener(e -> {
            Notification.show("Not implemented");
        });
        submit.addClickListener(e -> {
            Notification.show("Not implemented");
        });
        
        
    
    }

    private Component createTitle() {
        return new H3("Credit Card");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(cardNumber, cardholderName, expiration, csc);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        cardNumber.setPlaceholder("1234 5678 9123 4567");
        cardNumber.setPattern("[\\d ]*");
        cardNumber.setPreventInvalidInput(true);
        cardNumber.setRequired(true);
        cardNumber.setErrorMessage("Please enter a valid credit card number");
        month.setPlaceholder("Month");
        month.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        year.setPlaceholder("Year");
        year.setItems(20, 21, 22, 23, 24, 25);
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(submit);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    private class ExpirationDateField extends CustomField<String> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExpirationDateField(String label, Select<Integer> month, Select<Integer> year) {
            setLabel(label);
            HorizontalLayout layout = new HorizontalLayout(month, year);
            layout.setFlexGrow(1.0, month, year);
            month.setWidth("100px");
            year.setWidth("100px");
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            // Unused as month and year fields part are of the outer class
            return "";
        }

        @Override
        protected void setPresentationValue(String newPresentationValue) {
            // Unused as month and year fields part are of the outer class
        }

    }

}
