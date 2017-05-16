package com.iit.cs;
public class CartBean {
	
	private String productId;
	private String productName;
	double price;
	int quantity;
	
	public CartBean(String productId, String productName, double price, int quantity )
	{
		this.productId  = productId;
		this.productName= productName;
		this.price      = price;
		this.quantity   = quantity; 
	}

	public CartBean() {
		}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
