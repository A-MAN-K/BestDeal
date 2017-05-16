package com.iit.cs;
import java.util.ArrayList;
import java.util.List;

public class Laptop {

    private String retailer;
    private String name;
    private String id;
    private String image;
    private String condition;
    private int price;
    private List<String> accessories;


    
    public Laptop(String retailer,String name, String id, String image, String condition, int price, List<String> accessories)
    {
    	this.retailer = retailer;
    	this.price = price;
    	this.name = name;
    	this.id = id;
    	this.image = image;
    	this.setAccessories(accessories);
    	
    }

    
    
    public Laptop(){
        accessories=new ArrayList<String>();
    }

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<String> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;
	}
    
	
}