package com.iit.cs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DealsMatches extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		PrintWriter out = response.getWriter();
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
	    out.println("<div class='content'>");
	//HTML code chunk part 1 ends here
		
			
		
		HashMap<String, ProductBean> selectedProducts = new HashMap<String, ProductBean>();
		
		try
		{
	        out.println("<table>");
	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>");
			out.println("<a href='#'>Welcome to Best Deal</a></h2>");
	        out.println("</td>");
	        out.println("</tr>");

	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>The World trust us for The best deals website</h2>");
	        out.println("</td>");
	        out.println("</tr>");

	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>We beat our competitor in all aspects. Price-Match Guaranteed</h2>");
	        out.println("</td>");
	        out.println("</tr>");

			String line= null;
			HashMap<String, ArrayList<ProductBean>> productMap = new HashMap<String, ArrayList<ProductBean>>();
			productMap = dataStoreUtilities.viewAllProducts();
			
			for(Map.Entry<String, ArrayList<ProductBean>> map : productMap.entrySet())
			{
				for(ProductBean bean : map.getValue())
				{
					
					if((selectedProducts.size() < 2 ) && !selectedProducts.containsKey(bean.getName()))
					{

						BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:/apache-tomcat-7.0.34/webapps/csj/DealMatches.txt")));
						line = bufferedReader.readLine();
						
						if(line == null)
						{
					        out.println("<tr>");
					        out.println("<td>");

							out.println("<h2 align='center'>NO OFFERS FOUND</h2>");
							out.println("</td>");
					        out.println("</tr>");

							break;	
							
					        
						}
						else
						{
							
						do
						{
							if(line.contains(bean.getName()))
							{
								out.println("<tr>");
						        out.println("<td>");

							    out.print("<h3 style='color:blue;'>"+line+"</h3>");								
								selectedProducts.put(bean.getId(), bean);
								out.println("</td>");
						        out.println("</tr>");						        
						        						        
							}	
							}
							while((line = bufferedReader.readLine()) != null);
						
						}
					  }					
				    }
			      }
			

			
			 out.println("</table>");
				Set set = selectedProducts.entrySet();
				Iterator iterator = set.iterator();
				
			out.println("<table>");	
			out.println("<tr>");	
				while(iterator.hasNext())
				{
					Map.Entry<String, ProductBean> map = (Map.Entry)iterator.next();
					ProductBean bean = (ProductBean)map.getValue();
					
					out.println("<td>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<td >");
			        out.println("<h2>Product Id<h2>");
			        out.println("</td>");
			        
			        out.println("<td>");
			        out.println("<h2>" + bean.getId() + "</h2>");
			        out.println("</td>");	
			        out.println("</tr>");
			        
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Retailer   	:");
			        out.println("</td>");
			        out.println("<td>");
			    	out.println("<a>"  + bean.getRetailer() + "</a>");
			        out.println("</td>");	
			        out.println("</tr>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Product Name   :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + bean.getName() + "</a>");
			        out.println("</td>");	
			        out.println("</tr>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Condition      :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + bean.getCondition() + "</a>");
			        out.println("</td>");
			        out.println("<td>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Price 		    :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + bean.getPrice() + "</a>");
			        out.println("</td>");
			        out.println("<td>");

			        out.println("<input type ='hidden' name='id' value="+bean.getId()+">");
			        String link="<a href='ViewProductServlet?id="+bean.getId()+"'"+">View Product</a>";
			        String linkSubmitReview ="<a href='ReviewServlet?productId="+bean.getId()+"&productName="+bean.getName()+"&retailer="+bean.getRetailer()+"&price="+bean.getPrice()+"&category=Laptop"+"'"+">Submit Reviews</a>";
			        String linkViewReview   ="<a href='ViewReviews?productName="+bean.getName()+"'"+">View Reviews</a>";
			        out.println("<tr>");
			        out.println("<td>");
			        out.println(link);
			        out.println("</td>");
			        out.println("<td text-align='left'>");
			  	    out.println(linkSubmitReview);
			  	    out.println("</td>");
			  	    out.println("<td text-align='left'>");
			  	    out.println(linkViewReview);
			  	    out.println("</td>");
			  	    out.println("</tr>");
			  	    out.println("</table>");
			  	    out.println("</td>");
			  	    

					
				}	
				out.println("</tr>");
				out.println("</table>");
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	

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
	       out.println("<li><a href=\"index.html\">HOME</a></li>");
	       out.println("<li><a href='login.html'>SIGN IN</a></li>");
	       out.println("<li><a href='index.html'>WHY US!</a></li>");
	       out.println("<li><a href='signUp.html'>CREATE ACCOUNT</a></li>");
	       out.println("<li><a href='index.html'>CONTACT</a></li>");
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
		
		
	
	}

}
