package com.example.application.views.main;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.Account;
import com.example.application.Application;
import com.example.application.SQLConnect;

@Route(value = "SignUp", layout = MainView.class)
@PageTitle("SignUp")
public class SignUpView extends Div{
	private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField password = new TextField("Password");
    private TextField confirm = new TextField("Confirm Password");
    private EmailField email = new EmailField("Email address");
    private PhoneNumberField phone = new PhoneNumberField("Phone number");
    private TextField street = new TextField("Street address");
    private TextField postalCode = new TextField("Postal code");
    private TextField city = new TextField("City");
    private ComboBox<String> state = new ComboBox<>("State");
    private ComboBox<String> country = new ComboBox<>("Country");
    public Account newAcc;
    public String test;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("SignUp");

    private Binder<SamplePerson> binder = new Binder<SamplePerson>(SamplePerson.class);

    public SignUpView(SamplePersonService personService) {
        addClassName("signup-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> {
        clearForm(); 
        UI.getCurrent().navigate("SignUp");});
        save.addClickListener(e -> {
        	
        	try {
				newAcc = new Account(email.getValue(), password.getValue(), firstName.getValue(), lastName.getValue(), street.getValue(), phone.getValue());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            try {
            	
				newAcc.storeAccount(Application.setPGM("user1", "pass"), newAcc);
				Notification.show("Account created successfully");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				test = firstName.getValue();
				String test2 = email.getValue();
				//Notification.show(test + " " + test2);
				Notification.show("Error creating Account");
				e1.printStackTrace();
			}
        	//personService.update(binder.getBean());
            //Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        	
        });
        
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, phone, email);
        formLayout.add(street, 2);
        postalCode.setPattern("\\d*");
        postalCode.setPreventInvalidInput(true);
        country.setItems("United States of America", "Canada", "Mexico");
        state.setItems("Arizona", "Texas", "Colorado", "Oklahoma");
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
