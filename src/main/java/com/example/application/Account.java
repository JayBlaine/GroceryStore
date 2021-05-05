package com.example.application;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Account {
	private String email;
	private String pass;
	private String first;
	private String last;
	private String address;
	private String phone;
	private HashMap <Item, Integer> cart = new HashMap<Item, Integer>();
	//private SQLConnect conn;
	
	public Account(String email, String pass, String first, String last, String add, String phone)  {
		
	   
		this.email = email;
		this.pass = pass;
		this.first = first;
		this.last = last;
		this.address = add;
		this.phone = phone;
		
	}
	
	public void addToCart(Item item, Integer amt) {
		if(!cart.containsKey(item))
			this.cart.put(item, amt);
		else
			this.cart.put(item, amt);
		for (Entry<Item, Integer> entry : this.cart.entrySet()) {
			System.out.println(entry.getKey().getName());
		}
	}
	
	public void removeFromCart(Item item) {
		this.cart.put(item, this.cart.get(item)-1);
		if(this.cart.get(item) < 1) //Removed last of that item
			this.cart.remove(item);
	}
	
	public double checkout(SQLConnect pgm) throws SQLException {
		double cost = 0.0;
		for (Entry<Item, Integer> entry : this.cart.entrySet()) {
		    Item item = entry.getKey();
		    int quant = entry.getValue();
		    double retVal = pgm.getItem(item, quant);
		    if(retVal < 0)
		    	return -1.0;
		    else
		    	cost+=retVal;
		    
		}
		return cost;
	}

	
	public boolean storeAccount(SQLConnect pgm, Account item) throws Exception {
		return pgm.insertAcc(item);
	}
	
	public boolean changePass(SQLConnect pgm, Account item, String newP, String oldP) throws SQLException {
		return pgm.updatePass(item, newP, oldP);
		//TODO: Update value
	}
	
	
	
	public String getEmail() {return this.email; }
	public void setEmail(String in) { this.email = in; }
	public String getpass() {return this.pass; }
	public void setPass(String in) { this.pass = in; }
	public String getFirst() {return this.first; }
	public void setFirst(String in) { this.first = in; }
	public String getLast() {return this.last; }
	public void setLast(String in) { this.last = in; }
	public String getAdd() {return this.address; }
	public void setAdd(String inA) { this.address = inA; }
	public String getPhone() {return this.phone; }
	public void setPhone(String inP) { this.phone = inP; }

}
