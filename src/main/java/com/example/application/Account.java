package com.example.application;

import java.sql.SQLException;
import java.util.ArrayList;
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
	private ArrayList<Item> cart = new ArrayList<>();
	//private SQLConnect conn;
	
	public Account(String email, String pass, String first, String last, String add, String phone)  {
		
	   
		this.email = email;
		this.pass = pass;
		this.first = first;
		this.last = last;
		this.address = add;
		this.phone = phone;
		
	}
	
	/*public void setCart(ArrayList<Item> newcart) {
		//this.cart = newcart;
		this.cart.clear();
		this.cart.addAll(0, newcart);
	}*/
	
	public void addToCart(Item item, int amt) {
		if(!cart.contains(item)) {
			item.setQuant(amt);
			this.cart.add(item);
			System.out.println(this.cart.get(this.cart.size()-1).getName());
		}else {
			Integer index = cart.indexOf(item);
			cart.get(index).setQuant(amt);
		}
		
	}
	
	public boolean removeFromCart(Item item) {
		if(this.cart.contains(item)) {
			this.cart.remove(item);
			return true;
		}
			return false;
	}
	
	public double checkout(SQLConnect pgm) throws SQLException {
		double cost = 0.0;
		try {
			Application.setPGM("user1", "pass");
		}catch(Exception e) {
			
		}
		for(Item i:cart) {
			cost+=Application.pgm.getItem(i,  i.getQuant());
		}
		return cost;
	}
	
	public ArrayList<Item> getCart(){
		return this.cart;
	}

	
	public boolean storeAccount(SQLConnect pgm, Account item) throws Exception {
		return pgm.insertAcc(item);
	}
	
	public boolean changePass(SQLConnect pgm, Account item, String newP, String oldP) throws SQLException {
		return pgm.updatePass(item, newP, oldP);
		//TODO: Update value
	}
	
	public void clearCart() {
		this.cart.clear();
	}
	public int getCartSize() {
		return this.cart.size();
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
