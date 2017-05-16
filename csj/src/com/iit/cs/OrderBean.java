package com.iit.cs;
import java.util.Date;

public class OrderBean {

	private String productId;
	private double price;
	private String productName;
	private int orderNumber;
	private String userName;
	private String delliveryDate;
	private long creditCardNumber;
	private String address;
	private String status;
	public OrderBean(String productId, double price, String productName,int orderNumber,String userName, String deliveryDate, Long creditCardNumber, String address, String status) {
		this.productId = productId;
		this.price = price;
		this.productName = productName;
		this.orderNumber = orderNumber;
		this.userName=  userName;
		this.delliveryDate = deliveryDate;
		this.creditCardNumber = creditCardNumber;
		this.address = address;
		this.status = status;
	}

	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDelliveryDate() {
		return delliveryDate;
	}

	public void setDelliveryDate(String delliveryDate) {
		this.delliveryDate = delliveryDate;
	}

	public OrderBean() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
