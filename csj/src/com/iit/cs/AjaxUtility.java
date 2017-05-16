package com.iit.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AjaxUtility {
	
	// GETTING THE MATCHING CODE IN A STRINGBUFFER STARTS HERE 
	
		public StringBuffer streamData(String hint)
		{
			StringBuffer buffer = new StringBuffer();
			HashMap<String, ArrayList<ProductBean>> hacpltn = new HashMap<String, ArrayList<ProductBean>>();
			
			hacpltn = autoCompletion();
			
			for(Map.Entry<String, ArrayList<ProductBean>> map : hacpltn.entrySet())
			{
				for(ProductBean bean : map.getValue())
				{
					if(bean.getName().toLowerCase().startsWith(hint))
					{
						buffer.append("<product>");
						buffer.append("<id>"+bean.getId()+"</id>");
						buffer.append("<productName>"+bean.getName()+"</productName>");
						buffer.append("</product>");
					}
				}
			}
			
			
			
			return buffer;
		}
		
	
	
	// GETTING THE MATCHING CODE IN A STRINGBUFFER ENDS HERE 

	//CODE FOR AUTO COMPLETION STARTS HERE 
	
	public HashMap<String, ArrayList<ProductBean>> autoCompletion()
	{
		HashMap<String, ArrayList<ProductBean>> hacpltn = new HashMap<String, ArrayList<ProductBean>>();
		ArrayList<ProductBean> products = new ArrayList<ProductBean>();
		MySQLDataStoreUtilities dataStoreUtilities =  new MySQLDataStoreUtilities();
		ResultSet resultSet = null;
		
		try {
			dataStoreUtilities.createConnection();
			
			String query = "SELECT PRODUCT_NAME, PRODUCT_ID FROM PRODUCT";
			PreparedStatement preparedstatement = dataStoreUtilities.connection.prepareStatement(query);
			resultSet = preparedstatement.executeQuery();
			
			while(resultSet.next())
			{
				ProductBean productBean = new ProductBean();
				productBean.setId(resultSet.getString("PRODUCT_ID"));
				productBean.setName(resultSet.getString("PRODUCT_NAME"));
				
				products.add(productBean);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} 		
		hacpltn.put("PRODUCTS", products);				
		return hacpltn;
	}
		
	
	//CODE FOR AUTOCOMPLETION ENDS HERE 
	
}
