package com.example.application.views.homepage;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.example.application.Item;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;

@Route(value = "Home-Page", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Home Page")
@CssImport("./views/homepage/home-page-view.css")
public class HomePageView extends HorizontalLayout {

    private TextField search;
    private Button Search;
	private Component Button;
	HtmlComponent br = new HtmlComponent("br");

    public HomePageView() {
        addClassName("home-page-view");
        search = new TextField("Search Item");
        Search = new Button("Search");
        add(search, Search);
        setVerticalComponentAlignment(Alignment.END, search, Search);
        Search.addClickListener(e -> {
            Notification.show( search.getValue() + " not found");
        });
        add(new Button("Login", event ->{ UI.getCurrent().navigate("Login");}));
   
       /* Grid<Item> itemGrid = new Grid<>();
        itemGrid.addColumn(Item);*/
    }

}
