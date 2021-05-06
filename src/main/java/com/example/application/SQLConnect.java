package com.example.application;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;

public class SQLConnect implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 87600184106574988L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	    // error constants
	public static final int ER_DUP_ENTRY = 1062;
	public static final int ER_DUP_ENTRY_WITH_KEY_NAME = 1586;
	ZoneId zid = ZoneId.of("America/Chicago");
	LocalDate date = LocalDate.now(zid);
	//boolean for customer view
	

	public SQLConnect(String user, String password) throws Exception
	{
		try
	    {
	            // This will load the MySQL driver, each DBMS has its own driver
	        Class.forName("com.mysql.cj.jdbc.Driver"); //just added .cj
	        this.connect = DriverManager.getConnection
	                ("jdbc:mysql://34.68.33.39:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Chicago"
	                , user
	                , password);
	        System.out.println("TEST2");
	        }
	    catch (Exception e) 
	    {
	        throw e;
	    } 
	}
	
	
	
	public boolean login(String email, String pass) throws SQLException {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from grocerystore.account where email=\"" + email+"\"");
		
		while(resultSet.next() != false) {
			System.out.println(resultSet.getString("pass"));
	    	if(pass.equals(Application.decrypt(resultSet.getString("pass")))) {
				
				return true;
			}
			
	    }
		return false;
	}
	
	
	
	
	public  Account getAcc(String inEmail) throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from grocerystore.account where email=\"" + inEmail+"\"");
			while(resultSet.next()) {
			Account ret = new Account(resultSet.getString("email"),
									  Application.decrypt(resultSet.getString("pass")),
									  resultSet.getString("first"), resultSet.getString("last"), resultSet.getString("address"), resultSet.getString("phone"));
			return ret;
			}
		}
		catch (Exception e) 
	       {
	           throw e;
	       }
		return null;
	}
	
	
	
	public boolean insertAcc(Account item) throws SQLException {
		
			preparedStatement = connect.prepareStatement("insert into grocerystore.account values " 
														+ "(?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, item.getEmail());
			preparedStatement.setString(2, Application.encrypt(item.getpass()));
			preparedStatement.setString(3, item.getFirst());
			preparedStatement.setString(4, item.getLast());
			preparedStatement.setString(5, item.getAdd());
			preparedStatement.setString(6, item.getPhone());
			
		
			try 
            {
                // Execute that insert statement
                preparedStatement.executeUpdate();
                return true;
            }
            catch (SQLException e)
            {
                switch (e.getErrorCode())
                {
                    case ER_DUP_ENTRY:
                    case ER_DUP_ENTRY_WITH_KEY_NAME:
                        System.out.printf("Duplicate key error: %s\n", e.getMessage());
                        return false;
                    default:
                        throw e;
                }
            }
	
            
	}
	
	
	//add changes for email clause as we did in 
	public boolean updatePass(Account item, String pass, String old) throws SQLException {
		if (this.login(item.getEmail(), old)) {
			statement = connect.createStatement();
			statement.executeUpdate("update grocerystore.account set password = " + Application.encrypt(pass)
			+ " where email = " + item.getEmail());
			return true;
		}
		return false;
	}
	
	
	
	public double getItem(Item item, int quant) throws SQLException { //get price for checkout
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from grocerystore.item where itemId=" + item.getId());
		while(resultSet.next()) {
			if(resultSet.getInt("quantity") >= quant) {
				double ret = resultSet.getDouble("price") * quant;
				statement.executeUpdate("update grocerystore.item set quantity=" + (resultSet.getInt("quantity")-quant) + " where itemId=" + item.getId());
				return ret;
			}
		}
		return -1;
	}
	
	public boolean enoughItem(Item item, int quant) throws SQLException {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from grocerystore.item where itemId=" + item.getId());
		while(resultSet.next()) {
			if(resultSet.getInt("quantity") >= quant) {
				return true; }
			else { return false; }
		}
		return false;
	}
	
	public List<Item> getAllItems() throws SQLException {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from grocerystore.item");
		
		List<Item> items = new ArrayList<Item>();
		//items.add(null);
		while(resultSet.next()) {
			
			int id = resultSet.getInt("itemId");
			String name = resultSet.getString("name");
			double price = resultSet.getDouble("price");
			int quantity = resultSet.getInt("quantity");			
			String desc = resultSet.getString("description");
			String dept = resultSet.getString("dept");
			String pic = resultSet.getString("img");
			Item tempIt = new Item(id, name, price, quantity, desc, dept, pic);
			
			items.add(tempIt);
		}
		return items;
		
	}
	
	
	
public void close() {
	    try {
	        if (resultSet != null) 
	            resultSet.close();
	
	        if (statement != null) 
	            statement.close();
	
	        if (connect != null) 
	            connect.close();
	    } 
	    catch (Exception e) {

	    }
	}
}
