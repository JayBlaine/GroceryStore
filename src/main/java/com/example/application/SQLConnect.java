package com.example.application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class SQLConnect {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	    // error constants
	public static final int ER_DUP_ENTRY = 1062;
	public static final int ER_DUP_ENTRY_WITH_KEY_NAME = 1586;
	ZoneId zid = ZoneId.of("America/Chicago");
	LocalDate date = LocalDate.now(zid);

	public SQLConnect(String user, String password) throws Exception
	{
		try
	    {
	            // This will load the MySQL driver, each DBMS has its own driver
	        Class.forName("com.mysql.cj.jdbc.Driver"); //just added .cj
	        this.connect = DriverManager.getConnection
	                ("jdbc:mysql://35.223.169.93:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Chicago"
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
		resultSet = statement.executeQuery("select pass from grocerystore.account where email=" + email);
		
		if (resultSet.getFetchSize() == 1) {
			if(pass == Application.decrypt(resultSet.getString("pass"))) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public Account getAcc(String inEmail) throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from grocerystore.account where email=" + inEmail);
			if(resultSet.getFetchSize() != 1)
				return null;
			Account ret = new Account(resultSet.getString("email"),
									  Application.decrypt(resultSet.getString("pass")),
									  resultSet.getString("first"), resultSet.getString("last"), resultSet.getString("address"), resultSet.getString("phone"));
			return ret;

		}
		catch (Exception e) 
	       {
	           throw e;
	       }
	}
	
	
	
	public boolean insertAcc(Account item) throws SQLException {
		
			preparedStatement = connect.prepareStatement("insert into grocerystore.account values " 
														+ "(?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(0, item.getEmail());
			preparedStatement.setString(1, Application.encrypt(item.getpass()));
			preparedStatement.setString(2, item.getFirst());
			preparedStatement.setString(3, item.getLast());
			preparedStatement.setString(4, item.getAdd());
			preparedStatement.setString(5, item.getPhone());
			
		
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
		if(resultSet.getInt("quantity") >= quant) {
			double ret = resultSet.getDouble("price") * quant;
			statement.executeUpdate("update groceryStore.item set quantity=" + (resultSet.getInt("quantity")-quant));
			return ret;
		}
		return -1;
	}
	
	public HashMap<Integer, Item> getAllItems() throws SQLException {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from groceryStore.item");
		
		HashMap<Integer, Item> items = new HashMap<Integer, Item>();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("itemId");
			String name = resultSet.getString("name");
			double price = resultSet.getDouble("price");
			int quantity = resultSet.getInt("quantity");
			Date exp = resultSet.getDate("exp");
			String desc = resultSet.getString("description");
			String dept = resultSet.getString("dept");
			String pic = resultSet.getString("img");
			Item tempIt = new Item(id, name, price, quantity, exp, desc, dept, pic);
			
			items.put(id,  tempIt);
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
