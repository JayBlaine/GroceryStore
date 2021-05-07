package com.example.application;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;

public class Item {
	
	
	private int id;
	private String name;
	
	private double price;
	private int quantity;
	private String desc;
	private String dept;
	private String pic;
	private java.util.Date exp;
	 private static DecimalFormat df2 = new DecimalFormat("0.00");
	public Item (int id, String name, double price, int quantity, String desc, String dept, String pic) {
		this.id = id;
		this.name = name;
		this.price = price;	
		this.desc = desc;
		this.dept = dept;
		this.pic = pic;
		this.quantity = 0;
	}
	
	
	public int getId() { return this.id; }
	public void setId(int newId) { this.id = newId; }
	public double getPrice() { return this.price; }
	public String getPriceString() {return df2.format(this.price);}
	public void setPrice(double newPrice) { this.price = newPrice; }
	public int getQuant() {return this.quantity; }
	public void setQuant(int newQ) { this.quantity = newQ; }
	public String getName() { return this.name; }
	public void setName(String newName) { this.name = newName; }
	public String getDesc() { return this.desc; }
	public void setDesc(String newDesc) { this.desc = newDesc; }
	public String getDept() { return this.dept; }
	public void setDept(String newDept) { this.dept = newDept; }
	
	public String getPic() { 
		String folder = "images/"; 
		return folder + this.pic;
		}
	public void setURL(String newP) { this.pic = newP; }
}
