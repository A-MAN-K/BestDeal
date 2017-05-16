package com.iit.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

public class MySQLDataStoreUtilities {
	
	Connection connection=null;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	ResultSet rs = null; 
	
	
	//Code for adding a User ot the database module
	
	public int addUser(HashMap<String,UserBean> hmb) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		UserBean userBean = new UserBean();
		Set set = hmb.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext())
		{
			Map.Entry entry   = (Map.Entry)iterator.next();
			userBean = (UserBean)entry.getValue();
			
		}
		String userName = userBean.getUserName();
		String passWord = userBean.getPassWord();
		String rePass   = userBean.getPassWord();
		String role     = userBean.getRole();
		
	
		
		createConnection();
		String query = "INSERT INTO USER_REGISTRATION(USER_NAME,PASSWORD,REPASSWORD,USER_TYPE)"+"VALUES(?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1,userName);
		preparedStatement.setString(2,passWord);
		preparedStatement.setString(3,rePass);
		preparedStatement.setString(4,role);
		
		int result  = preparedStatement.executeUpdate();
	
		
		
		return result ;
	}
	
	
	// Code for retrieving a UserName 
	
	public int userExists( String userName){
		
		HashMap<String, UserBean> userList = new HashMap<String,UserBean>();
		UserBean userBean 	= new UserBean();
		ResultSet resultSet;
		int result =0;
		
		try {
			createConnection();
			String query = "SELECT USER_NAME FROM USER_REGISTRATION WHERE USER_NAME = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,userName);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next())
			{
				result = 0;
			}
			else
			{
				result = 1;
			}
			
			
			
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		} 
				
		return result;
		
	}
	
	// Code for Login and PassWord
	
	public HashMap<String, LoginBean> getLoginDetails(String userName)
	{
		HashMap<String, LoginBean> hlb = new HashMap<String, LoginBean>();
		ResultSet resultSet;
		LoginBean loginBean = new LoginBean();
		try {
			createConnection();
			String query = "SELECT USER_NAME,PASSWORD,USER_TYPE FROM USER_REGISTRATION WHERE USER_NAME = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,userName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				loginBean.setUserName(resultSet.getString("USER_NAME"));
				loginBean.setPassWord(resultSet.getString("PASSWORD"));
				loginBean.setRole(resultSet.getString("USER_TYPE"));
			}
			hlb.put(loginBean.getUserName(), loginBean);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}

		
		
		
		return hlb;
	}
	//CODE FOR LOGIN PASSWORD ENDS HERE 
	
	
	//CODE FOR ADDING ORDER DETAILS STARTS HERE
	
	public int addOrderDetails(HashMap<String,ArrayList<OrderBean>> halob ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
	 int result=0;
	 for(Map.Entry<String, ArrayList<OrderBean>> map : halob.entrySet())
	 {
		for(OrderBean orderBean : map.getValue())
		{
			String userName  	  = orderBean.getUserName();
			String productId 	  = orderBean.getProductId();
			double price     	  = orderBean.getPrice();
			int orderNumber  	  = orderBean.getOrderNumber();
			String productName	  = orderBean.getProductName();
			long creditCardNumber = orderBean.getCreditCardNumber();
			String address        = orderBean.getAddress();
			String deliveryDate   = orderBean.getDelliveryDate(); 
			int status			  = Integer.parseInt(orderBean.getStatus());	 
	
			
			createConnection();
			String query = "INSERT INTO ORDER_DETAILS(ORDER_NUMBER, PRODUCT_ID, PRICE, PRODUCT_NAME, USER_NAME, CREDITCARD_NUMBER, ADDRESS, DELIVERY_DATE, STATUS)"+"VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,orderNumber);
			preparedStatement.setString(2,productId);
			preparedStatement.setDouble(3,price);
			preparedStatement.setString(4,productName);
			preparedStatement.setString(5,userName);
			preparedStatement.setLong(6, creditCardNumber);
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, deliveryDate);
			preparedStatement.setInt(9, status);
			result = preparedStatement.executeUpdate();
			
		
		}
	 }
	 
	 
	 return result;	
	}
		
	//CODE FOR ADDING ORDER DETAILS ENDS HERE
	
	//CODE FOR GETTING MAX ORDER NUMBER STARTS HERE
		
		public int getMaxOrderNumber()
		{
			int maxOrderNumber =0;
			
			ResultSet resultSet;
			
			try {
				createConnection();
				String query = "SELECT MAX(ORDER_NUMBER) FROM ORDER_DETAILS";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next())
				{
					maxOrderNumber = resultSet.getInt(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			
			return maxOrderNumber;
		}
	
	//CODE FOR GETTING MAX ORDER NUMBER ENDS HERE
		
	//CODE FOR CHECKING WHETHER EXISTS OR NOT STARTS HERE	
		
		public int orderExists(int orderNumber)
		{
			int result = 0;
			ResultSet resultset;
			
			try {
				createConnection();
				String query = "SELECT PRODUCT_ID FROM ORDER_DETAILS WHERE ORDER_NUMBER = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, orderNumber);
				resultset = preparedStatement.executeQuery();
				
				if(!resultset.next())
				{
					result =0;
				}
				else
				{
					result =1;
				}
					
			}
			catch( Exception e) {
				e.printStackTrace();
			}
			return result;
		}
				
	//CODE FOR CHECKING WHETHER EXISTS OR NOT ENDS HERE
		
		
	//CODE FOR VIEWING THE ORDERS BY A CUSTMER STARTS HERE
		
		public HashMap<String,ArrayList<OrderBean>> getOrderDetails(int orderNumber)
		{
			HashMap<String,ArrayList<OrderBean>> hov = new HashMap<String, ArrayList<OrderBean>>();
			ArrayList<OrderBean> beansOrder = new  ArrayList<OrderBean>();
			
			ResultSet resultSet;
			
			try {
				createConnection();
				String query = "SELECT PRODUCT_ID, PRODUCT_NAME, PRICE, USER_NAME, CREDITCARD_NUMBER, ADDRESS, DELIVERY_DATE, STATUS  FROM ORDER_DETAILS WHERE ORDER_NUMBER = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, orderNumber);
				resultSet = preparedStatement.executeQuery();
				
			while(resultSet.next())
					{
						OrderBean orderBean = new OrderBean();
						orderBean.setProductId(resultSet.getString("PRODUCT_ID"));
						orderBean.setProductName(resultSet.getString("PRODUCT_NAME"));
						orderBean.setPrice(resultSet.getDouble("PRICE"));
						orderBean.setUserName(resultSet.getString("USER_NAME"));
						orderBean.setCreditCardNumber(resultSet.getLong("CREDITCARD_NUMBER"));
						orderBean.setAddress(resultSet.getString("ADDRESS"));
						orderBean.setDelliveryDate(resultSet.getString("DELIVERY_DATE"));
						orderBean.setStatus(String.valueOf(resultSet.getInt("STATUS")));
						beansOrder.add(orderBean);
					}
					hov.put("ViewOrder", beansOrder);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return hov;
		}		
		
	//CODE FOR VIEWING THE ORDERS BY A CUSTMER ENDS HERE	
		
	// CODE FOR CANCELLING THE ORDER STARTS HERE
		
		public int cancelOrder(int orderNumber)
		{
			int result =0;
			
			try{
				createConnection();
				String query = "DELETE FROM ORDER_DETAILS WHERE ORDER_NUMBER = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, orderNumber);
				result = preparedStatement.executeUpdate();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
			
	// CODE FOR CANCELLING THE ORDER ENDS HERE
		
	// CODE FOR GETTING SALESMAN DATA STARTS HERE
		
		public HashMap<String, ArrayList<OrderBean>> getSalesOrder()
		{
			HashMap<String, ArrayList<OrderBean>> hsob = new HashMap<String, ArrayList<OrderBean>>();
			ArrayList<OrderBean> arrayList = new ArrayList<OrderBean>();
			ResultSet resultSet= null;
			
			try {
				createConnection();
				String query = "SELECT ORDER_NUMBER, PRODUCT_ID, PRODUCT_NAME, PRICE, USER_NAME, CREDITCARD_NUMBER, ADDRESS, DELIVERY_DATE, STATUS  FROM ORDER_DETAILS";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next())
				{
					OrderBean orderBean = new OrderBean();
					orderBean.setOrderNumber(resultSet.getInt("ORDER_NUMBER"));
					orderBean.setProductId(resultSet.getString("PRODUCT_ID"));
					orderBean.setProductName(resultSet.getString("PRODUCT_NAME"));
					orderBean.setPrice(resultSet.getDouble("PRICE"));
					orderBean.setUserName(resultSet.getString("USER_NAME"));
					orderBean.setCreditCardNumber(resultSet.getLong("CREDITCARD_NUMBER"));
					orderBean.setAddress(resultSet.getString("ADDRESS"));
					orderBean.setDelliveryDate(resultSet.getString("DELIVERY_DATE"));
					orderBean.setStatus(String.valueOf(resultSet.getInt("STATUS")));
					arrayList.add(orderBean);

				}
				
				hsob.put("hsob", arrayList);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return hsob;
		}
		
		
	// CODE FOR GETTING SALESMAN DATA ENDS HERE	
		
	
	// CODE FOR UPDATING SALESMAN DATA STARTS HERE
	
		public int updateSalesOrder(int orderNumber)
		{
			int result= 0;
			
			try {
				createConnection();
				String query = "UPDATE ORDER_DETAILS SET STATUS=2 WHERE ORDER_NUMBER = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, orderNumber);
				result = preparedStatement.executeUpdate();
				
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return result;
		}
		
	// CODE FOR UPDATING SALESMAN DATA ENDS HERE	
	
	//CODE FOR ADDING A PRODUCT STARTS HERE
		
	  public int addProducts(HashMap<String, ArrayList<ProductBean>> hpb)
	  {
		  int result= 0;
		  
		  ProductBean productBean = new ProductBean();
		  for(Map.Entry<String, ArrayList<ProductBean>> map : hpb.entrySet())
		  {
			  for(ProductBean bean : map.getValue())
			  {
				  productBean = bean;
				  try {
						createConnection();
						String query ="INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME, CONDITIONS, PRICE, ACCESSORIES, TYPEP, RETAILER) VALUES(?,?,?,?,?,?,?)";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, productBean.getId());
						preparedStatement.setString(2, productBean.getName());
						preparedStatement.setString(3, productBean.getCondition());
						preparedStatement.setDouble(4, productBean.getPrice());
						preparedStatement.setString(5, productBean.getAccessories());
						preparedStatement.setString(6, productBean.getType());
						preparedStatement.setString(7, productBean.getRetailer());
						result = preparedStatement.executeUpdate();
									
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}

			  }
				  
		  }
		  
		  
		  
		  return result;
	  }
		
	//CODE FOR ADDING A PRODUCT ENDS HERE
		
		
	//CODE FOR VIEWING ALL PRODUCTS STARTS HERE
		
	  public HashMap<String,ArrayList<ProductBean>> viewAllProducts()
	  {
		  HashMap<String,ArrayList<ProductBean>> hpb = new HashMap<String,ArrayList<ProductBean>>();
		  ResultSet resultSet = null;
		  ArrayList<ProductBean> proBeans = new ArrayList<ProductBean>();
		  try {
			createConnection();
			String query = "SELECT PRODUCT_ID, PRODUCT_NAME, CONDITIONS, PRICE, ACCESSORIES, TYPEP, RETAILER FROM PRODUCT";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				ProductBean productBean = new ProductBean();
				productBean.setId(resultSet.getString("PRODUCT_ID"));
				productBean.setName(resultSet.getString("PRODUCT_NAME"));
				productBean.setCondition(resultSet.getString("CONDITIONS"));
				productBean.setPrice(resultSet.getDouble("PRICE"));
				productBean.setAccessories(resultSet.getString("ACCESSORIES"));
				productBean.setType(resultSet.getString("TYPEP"));
				productBean.setRetailer(resultSet.getString("RETAILER"));
				proBeans.add(productBean);
			}
		  hpb.put("PRODUCTS", proBeans);
		  
		  
		  } catch (Exception e) {
			
			e.printStackTrace();
		}   
		  
		  return hpb;
	  }
	  
	  
	//CODE FOR VIEWING ALL PRODUCTS ENDS HERE	
	  
	//CODE FOR VIEWING ALL PRODUCTS BY THEIR TYPES STARTS HERE
	  
	  public HashMap<String,ArrayList<ProductBean>> viewProductsByType(String type)
	  {
		  HashMap<String,ArrayList<ProductBean>> hpb = new HashMap<String,ArrayList<ProductBean>>();
		  ResultSet resultSet = null;		  
		  ArrayList<ProductBean> proBeans = new ArrayList<ProductBean>();
		  try {
			createConnection();
			String query = "SELECT PRODUCT_ID, PRODUCT_NAME, CONDITIONS, PRICE, ACCESSORIES, TYPEP, RETAILER FROM PRODUCT WHERE TYPEP = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, type);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				ProductBean productBean = new ProductBean();
				productBean.setId(resultSet.getString("PRODUCT_ID"));
				productBean.setName(resultSet.getString("PRODUCT_NAME"));
				productBean.setCondition(resultSet.getString("CONDITIONS"));
				productBean.setPrice(resultSet.getDouble("PRICE"));
				productBean.setAccessories(resultSet.getString("ACCESSORIES"));
				productBean.setType(resultSet.getString("TYPEP"));
				productBean.setRetailer(resultSet.getString("RETAILER"));
				proBeans.add(productBean);
			}
		  hpb.put("PRODUCTS", proBeans);
		  
		  
		  } catch (Exception e) {
			
			e.printStackTrace();
		}   
		  
		  return hpb;
	  }
	  
	//CODE FOR VIEWING ALL PRODUCTS BY THEIR TYPES ENDS HERE  
		
	//CODE FOR PRODUCT COUNT STARTS HERE   
	
	  public int countProducts()
	  {
		  int result =0;
		  ResultSet resultSet = null;
		  try {
			createConnection();
			String query = "SELECT COUNT(1) AS COUNT_PRODUCTS FROM PRODUCT";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next())
			{
				result = 0;
			}
			else
			{
				result = resultSet.getInt("COUNT_PRODUCTS");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}   
		  return result;
	  }
	  
	//CODE FOR PRODUCT COUNT ENDS HERE   
	  
	//CODE FOR VIEWING A SINGLE PRODUCT STARTS HERE 
	
	  public HashMap<String,ArrayList<ProductBean>> viewProductsById(String productId)
	  {
		  HashMap<String,ArrayList<ProductBean>> hpb = new HashMap<String,ArrayList<ProductBean>>();
		  ResultSet resultSet = null;
		  ArrayList<ProductBean> proBeans = new ArrayList<ProductBean>();
		  try {
			createConnection();
			String query = "SELECT PRODUCT_ID, PRODUCT_NAME, CONDITIONS, PRICE, ACCESSORIES, TYPEP, RETAILER FROM PRODUCT WHERE PRODUCT_ID  = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				ProductBean productBean = new ProductBean();
				productBean.setId(resultSet.getString("PRODUCT_ID"));
				productBean.setName(resultSet.getString("PRODUCT_NAME"));
				productBean.setCondition(resultSet.getString("CONDITIONS"));
				productBean.setPrice(resultSet.getDouble("PRICE"));
				productBean.setAccessories(resultSet.getString("ACCESSORIES"));
				productBean.setType(resultSet.getString("TYPEP"));
				productBean.setRetailer(resultSet.getString("RETAILER"));
				proBeans.add(productBean);
			}
		  hpb.put("PRODUCTS", proBeans);
		  
		  
		  
		  } catch (Exception e) {
			
			e.printStackTrace();
		}   
		  
		  return hpb;
	  }
	  
	  
	//CODE FOR VIEWING A SINGLE PRODUCT ENDS HERE
	  
	//CODE ADDED TO DELETE A PRODUCT BY ID STARTS HERE
	  
	  public int deleteProduct(String productId)
	  {
		  int result = 0;		  
		  try {
			createConnection();
			String query = "DELETE FROM PRODUCT WHERE PRODUCT_ID= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productId);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return result;
	  }
	  
	//CODE ADDED TO DELETE A PRODUCT BY ID ENDS HERE   
	  
	//CODE FOR UPDATING A PRODUCT STARTS HERE
	  
	  public int updateProduct(ProductBean productBean)
	  {
		  int result = 0;
		  
		  try {
			createConnection();
			String query = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRICE = ?, RETAILER = ?, CONDITIONS = ?, ACCESSORIES = ?  where PRODUCT_ID = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productBean.getName());
			preparedStatement.setDouble(2, productBean.getPrice());
			preparedStatement.setString(3, productBean.getRetailer());
			preparedStatement.setString(4, productBean.getCondition());
			preparedStatement.setString(5, productBean.getAccessories());
			preparedStatement.setString(6, productBean.getId());
			
			result = preparedStatement.executeUpdate();
			
			
			
			
		} catch (Exception e) {
						
			e.printStackTrace();
		}  
		  return result;
	  }
	  
	  
	//CODE FOR UPDATING A CODE ENDS HERE   
	  
	  
	// Code for Connection
	public Connection createConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String driverName="com.mysql.jdbc.Driver";
		Class.forName(driverName).newInstance();
		String serverName="localhost";
		String portNumber="3306";
		String sid="bestdealdatabase";
		String url="jdbc:mysql://"+serverName+":"+portNumber+"/"+sid;
		String username="root";
		String password="root";
		connection=DriverManager.getConnection(url, username, password);
		
		statement=connection.createStatement();
		return connection;
	}

}
