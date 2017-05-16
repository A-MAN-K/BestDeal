package com.iit.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StoreManagerProServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String process 	 = request.getParameter("process");
		String productId = request.getParameter("id");
		
		//Code for getting the differentiator and HashMap from the session
		
		String differentiator = (String)session.getAttribute("differentiator");
		String type = "";
		if(differentiator.equals("laptop"))
		{
			type = "LAPTOP";
		}
		else
		{
			type = "MOBILE";
		}
		
		MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();


		
		//HTML code chunk part 1 starts here
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");
		out.println("<title>Best Deal Website</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />");
		out.println("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />");
		out.println("</head>");
		out.println("<body bgcolor=\"#550403\" style=\"leftmargin=0px; topmargin=0px; marginwidth=0px; marginheight=0px;\">");
		out.println("<span class=\"style6\"></span>");
		out.println("<div id=\"body\">");
		out.println("<br />");
		out.println("<br />");
		out.println("<br />");
		out.println("<table style=\"width=1001px; height=1001px;\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">");
		out.println("<tr>");
		out.println("<td colspan=\"2\" rowspan=\"3\"><div id=\"logo\">");
		out.println("<div class=\"logo\"><a href=\"index.html\"><span class=\"style5\">BEST<br />");
		out.println("DEAL</span></a></div>");
		out.println("</div></td>");
		out.println("<td colspan=\"3\"><div class=\"style1\" id=\"header\">");
		out.println("<br />");
		out.println("ONLINE <span class=\"style3\">SHOPPING</span>");
        out.println("<span class=\"style4\"><br />");
        out.println("OUR SERVICES MAKES YOUR SHOPPING BETTER!</span></div></td>");
	    out.println("<td>");
		out.println(" <img src=\"images/spacer.gif\" width=\"1\" height=\"185\" alt=\"\" /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><div class=\"top_menu\">");
		out.println("<ul>");
		out.println("<li><a href=\"index.html\">HOME</a></li>");
        out.println("<li><a href=\"signUp.html\">SIGN IN</a></li>");
        out.println("<li><a href=\"index.html\">PRODUCTS</a></li>");
        out.println("</ul>");
        out.println("</div></td>");
	    out.println("<td colspan=\"2\">");
	    out.println("<img src=\"images/index_04.jpg\" width=\"128\" height=\"42\" alt=\"\" /></td>");
	    out.println("<td>");
	    out.println("<img src=\"images/spacer.gif\" width=\"1\" height=\"42\" alt=\"\" /></td>");
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td colspan=\"2\" rowspan=\"2\"><div id=\"content\">");
	    out.println("<div class=\"title\"></div>");
  	    out.println("<div class=\" content\">");
  	//HTML code chunk part 1 ends here
  	    
  	    out.println("<form action='UpdateProductServlet'>");
		out.println("<table>");
 
		int result = 0;
			if(process.equals("delete"))
			{
				result = dataStoreUtilities.deleteProduct(productId);
				if(result != 0)
				{
					System.out.println("The Product has been deleted");
				}
				else
				{
					System.out.println("The Product has not been deleted");
				}
			
			
		  dataStoreUtilities = new MySQLDataStoreUtilities();	
		  HashMap<String, ArrayList<ProductBean>> hpbv = new HashMap<String, ArrayList<ProductBean>>();
		  hpbv = dataStoreUtilities.viewProductsByType(type);
 	
		  out.println("<br/>");	
		  out.println("<tr><td>The Selected Product has been deleted</td></tr>");
		  for(Map.Entry<String, ArrayList<ProductBean>> map : hpbv.entrySet())
		  {
			  for( ProductBean list : map.getValue())
			  {
	
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("<h2>Product Id<h2>");
			        out.println("</td>");
			        
			        out.println("<td>");
			        out.println("<h2>" + list.getId() + "</h2>");
			        out.println("</td>");	
			        out.println("</tr>");
			        
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Retailer   	:");
			        out.println("</td>");
			        out.println("<td>");
			    	out.println("<a>"  + list.getRetailer() + "</a>");
			        out.println("</td>");	
			        out.println("</tr>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Product Name   :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + list.getName() + "</a>");
			        out.println("</td>");	
			        out.println("</tr>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Condition      :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + list.getCondition() + "</a>");
			        out.println("</td>");
			        out.println("<td>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Price 		    :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + list.getPrice() + "</a>");
			        out.println("</td>");
			        out.println("<td>");

			    	
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Accessories 	:");
			        out.println("</td>");
			        
			       
			    	out.println("<td>");
			        out.println("<a>"  + list.getAccessories() + "</a>");
			        out.println("</td>");
			       
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("<a href='storeManager.html'>Return to Main Menu</a>");	
			        out.println("</td>");
			        out.println("</tr>");
			        

			  }
		  }
	  }
			
			if(process.equals("update"))
			{
				HashMap<String, ArrayList<ProductBean>> hupd = new HashMap<String, ArrayList<ProductBean>>();
				hupd = dataStoreUtilities.viewProductsById(productId);
				
				for(Map.Entry<String, ArrayList<ProductBean>> map : hupd.entrySet())
				{
					for(ProductBean bean : map.getValue())
					{
					  
					  	out.println("<tr>");
					  	out.println("<td>Product Id</td>");
					  	out.println("<td><input type='text' readonly height='20' width='30' name='updId' value='"+bean.getId()+"'"+"></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Product Name</td>");
					  	out.println("<td> <input type='text' name='updName' value='"+bean.getName()+"'"+"></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Product Price</td>");
					  	out.println("<td> <input type='text' name='updPrice' value='"+bean.getPrice()+"'"+"></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Retailer Name</td>");
					  	out.println("<td> <input type='text' name='updRetailer' value='"+bean.getRetailer()+"'"+"></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Condition</td>");
					  	out.println("<td> <select name='updCondition'><option>"+bean.getCondition()+"</option><option>New</option><option>Refurbished</option></select></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Accessories</td>");
					  	out.println("<td> <input type='text' name='updAccess' width='200 px' value='"+bean.getAccessories()+"'"+"></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Accessories</td>");
					  	out.println("<td> <input type='hidden' name='updType' value='"+bean.getType()+"'"+"></td>");
					  	out.println("</tr>");
					  	out.println("<tr>");
					  	out.println("<td> Update Product</td>");
					  	out.println("<td> <input type='submit' name='update' value='Update Product'"+"></td>");
					  	out.println("</tr>");

					}
				}
				
				
			}
			
			out.println("</table>");
			out.println("</form>");

	  
  	    
  	    
		//HTML code chunk part 2 starts here
		
           out.println("</div>");
           out.println("</div></td>");
           out.println("<td rowspan=\"3\">");
	       out.println("<img src=\"images/index_06.jpg\" width=\"27\" height=\"773\" alt=\"\" /></td>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"1\" height=\"76\" alt=\"\" /></td>");
	       out.println(" </tr>");
	       out.println("<tr>");
	       out.println("<td rowspan=\"2\"><img src=\"images/index_07.jpg\" width=\"70\" height=\"697\" alt=\"\"/></td>");
	       out.println("<td><div class=\"left_menu\">");
	       out.println(" <ul>");
	       out.println(" <li><a href=\"index.html\">HOME</a></li>");
	       out.println("<li><a href=\"MyServlet\">LAPTOPS</a></li>");
	       out.println("<li><a href=\"index.html\">MOBILE PHONES</a></li>");
	       out.println("<li><a href=\"index.html\">TABLETS</a></li>");
	       out.println(" <li><a href=\"index.html\">TELEVISION</a></li>");
	       out.println("</ul>");
	       out.println("</div></td>");
	       out.println("<td>");
	       out.println(" <img src=\"images/spacer.gif\" width=\"1\" height=\"651\" alt=\"\" /></td>");
	       out.println("</tr>");
	       out.println("<tr>");
	       out.println("<td colspan=\"3\"><div id=\"footer\">COPYRIGHT (C) 2010 &quot;YOUR SITE&quot;. DESIGN BY <a href=\"http://www.merkkleding-outlet.nl\">merkkleding online</a></div></td>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"1\" height=\"46\" alt=\"\" /></td>");
	       out.println("</tr>");
	       out.println("<tr>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"70\" height=\"1\" alt=\"\" /></td>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"179\" height=\"1\" alt=\"\" /></td>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"623\" height=\"1\" alt=\"\" /></td>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"101\" height=\"1\" alt=\"\" /></td>");
	       out.println("<td>");
	       out.println("<img src=\"images/spacer.gif\" width=\"27\" height=\"1\" alt=\"\" /></td");
	       out.println("<td></td>");
	       out.println("</tr>");
	       out.println("</table>");
	       out.println("<div id=\"lft\"><a href=\"http://www.merkkleding-outlet.nl\">merkkleding online</a></div><br />");
	       out.println("<br />");
	       out.println("</div>");
	       out.println("<!-- End Save for Web Slices -->");
	       out.println("</body>");
	       out.println("</html>");
	       
	     //HTML code chunk part 2 ends here

	
	}

}
