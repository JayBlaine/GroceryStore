package com.example.application.views.customerform;

import java.sql.SQLException;

import com.example.application.Account;
import com.example.application.Application;
import com.example.application.SQLConnect;
import com.example.application.views.main.LoginView;
import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
//import com.vaadin.flow.component.checkbox.Checkbox;
//import com.vaadin.flow.data.renderer.TemplateRenderer;

@Route(value = "customer-form", layout = MainView.class)
@PageTitle("Account")
@CssImport("./views/customerform/customer-form-view.css")
public class CustomerFormView extends Div {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField password = new TextField("Password");
    private TextField confirm = new TextField("Confirm Password");
    private TextField email = new TextField("Email address");
    private TextField phone = new TextField("Phone number");
    private TextField street = new TextField("Street address");
    private TextField postalCode = new TextField("Postal code");
    private TextField city = new TextField("City");
    private ComboBox<String> state = new ComboBox<>("State");
    private ComboBox<String> country = new ComboBox<>("Country");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Binder<SamplePerson> binder = new Binder<SamplePerson>(SamplePerson.class);
    public Account use;
    
    public CustomerFormView(SamplePersonService personService) throws Exception {
        addClassName("customer-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        
        save.addClickListener(e -> {
            if(!LoginView.user.getpass().equals(password.getValue())){
            	try {
					Application.setPGM("user1", "pass");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	try {
					Application.pgm.updatePass(LoginView.user, password.getValue(), LoginView.user.getpass());
					Notification.show("Password changed successfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }

        });
        if(LoginView.loggedIn) {
        	 firstName.setValue(LoginView.user.getFirst());
        	 lastName.setValue(LoginView.user.getLast());
        	 password.setValue(LoginView.user.getpass());
        	 email.setValue(LoginView.user.getEmail());
        	 phone.setValue(LoginView.user.getPhone());
        	 confirm.setValue(LoginView.user.getpass());
        	 String loop = LoginView.user.getAdd();
        	 String temp[] = loop.split("-");
        	 street.setValue(temp[0]);
        	 city.setValue(temp[1]);
        	 state.setValue(temp[2]);
        	 country.setValue(temp[3]);
        	 postalCode.setValue(temp[4]);
        }
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Account Details");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, phone, email);
        formLayout.add(street, 2);
        postalCode.setPattern("\\d*");
        postalCode.setPreventInvalidInput(true);
        country.setItems("Country 1", "Country 2", "Country 3");
        state.setItems("State A", "State B", "State C", "State D");
        formLayout.add(postalCode, city, state, country);
        formLayout.add(password, confirm);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    private static class PhoneNumberField extends CustomField<String> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
        private TextField number = new TextField();

        public PhoneNumberField(String label) {
            setLabel(label);
            number.setPattern("\\d*");
            number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout( number);
            layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            if (number.getValue() != null) {
                String s = number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String phoneNumber) {
            String[] parts = phoneNumber != null ? phoneNumber.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                number.setValue(parts[0]);
            } else if (parts.length == 2) {
                number.setValue(parts[1]);
            } else {
                number.clear();
            }
        }
    }

}
