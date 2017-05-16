package com.iit.cs;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xml.sax.InputSource;
public class MyServlet extends HttpServlet  {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Code for File containing the product information starts here
		
		//String differentiator = request.getParameter("productId");
		HttpSession session = request.getSession();
		session.setAttribute("differentiator", request.getParameter("productId"));
		String differentiator = (String)session.getAttribute("differentiator");
		ServletContext sc = request.getServletContext();
		String Filename= sc.getRealPath("WEB-INF/XMLFiles/ProductCatalog.xml");
		SaxParser4BestDealRetail dealRetail = new SaxParser4BestDealRetail(Filename);
		//Code for File containing the product information ends here
		
		
		//Code for Product Type Laptop starts here
		ArrayList<Laptop> alist = new ArrayList<Laptop>();
		PrintWriter out = response.getWriter();
		  
			//CODE TO ADD THE PRODUCT DATA TO THE DATABASE STARTS HERE			
			HashMap<String, ArrayList<ProductBean>> hpb = new HashMap<String, ArrayList<ProductBean>>();
			int resultLap = 0;
			int countProducts = 0;
			ArrayList<ProductBean> proBeans =  new ArrayList<ProductBean>();
			for( Laptop laps : dealRetail.laptops)
			{
				ProductBean productBean = new ProductBean();
				productBean.setId(laps.getId());
				productBean.setName(laps.getName());
				productBean.setPrice(laps.getPrice());
				productBean.setCondition(laps.getCondition());
				productBean.setImage(laps.getImage());
				productBean.setRetailer(laps.getRetailer());
				productBean.setType("LAPTOP");
				String accessories= "";
				for( String access : laps.getAccessories())
				{
					accessories += access + ",";
				}
				productBean.setAccessories(accessories);
				proBeans.add(productBean);
			}
			hpb.put("PRODUCTS", proBeans);
			
			MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
			countProducts = dataStoreUtilities.countProducts();
			if(countProducts == 0)
			{
				resultLap = dataStoreUtilities.addProducts(hpb);
						if(resultLap != 0)
						{
							System.out.println("The Products has been dumped into the Database  : Yesss !!!!!");
						}
						else
						{
							System.out.println("The Products has not been dumped into the Database  : No !!!!!");
						}
			}
			//CODE TO ADD THE PRODUCT DATA TO THE DATABASE ENDS HERE

			HashMap<String,List<Laptop>> hml = new HashMap<String,List<Laptop>>();
			hml.put("laptop", dealRetail.laptops);
	
		//Code for Product Type Laptop ends here
		
		
		//Code for Product Type Mobile starts here
		ArrayList<Mobile> moblist = new ArrayList<Mobile>();
		HashMap<String,List<Mobile>> hmob = new HashMap<String,List<Mobile>>();
		hmob.put("mobile", dealRetail.mobiles);
		
				//CODE TO ADD THE PRODUCT DATA TO THE DATABASE STARTS HERE				
				HashMap<String, ArrayList<ProductBean>> hpbMob = new HashMap<String, ArrayList<ProductBean>>();
				int result = 0;
				ArrayList<ProductBean> proBeansMob =  new ArrayList<ProductBean>();
				for( Mobile mobs : dealRetail.mobiles)	
				{
					ProductBean productBeanMob = new ProductBean();
					productBeanMob.setId(mobs.getId());
					productBeanMob.setName(mobs.getName());
					productBeanMob.setPrice(mobs.getPrice());
					productBeanMob.setCondition(mobs.getCondition());
					productBeanMob.setImage(mobs.getImage());
					productBeanMob.setRetailer(mobs.getRetailer());
					productBeanMob.setType("MOBILE");
					String accessories= "";
					for( String access : mobs.getAccessories())
					{
						accessories += access + ",";
					}
					productBeanMob.setAccessories(accessories);
					proBeansMob.add(productBeanMob);
				}
				hpbMob.put("PRODUCTS", proBeansMob);
			if(countProducts == 0)
			{

				result = dataStoreUtilities.addProducts(hpbMob);
				
				if(result != 0)
				{
					System.out.println("The Products has been dumped into the Database  : Yesss !!!!!");
				}
				else
				{
					System.out.println("The Products has not been dumped into the Database  : No !!!!!");
				}
			}	
			
		//CODE TO ADD THE PRODUCT DATA TO THE DATABASE ENDS HERE  

		
		//Code for Product Type Mobile ends here
	
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
        //CODE FOR SHOWING THE CART STARTS HERE
        
        HashMap<String,CartBean> showCart = new HashMap<String, CartBean>();
        showCart =(HashMap)session.getAttribute("sessionCart");
        if (showCart == null)
        {
        	showCart = new HashMap<String,CartBean>();
        }
        String countCart = String.valueOf(showCart.size());
        //CODE FOR SHOWING THE CART ENDS HERE
        out.println("<li><a href=\"index.html\">CART("+countCart+")</a></li>");

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
	
	
  	    //Iterating into the Laptops returned by SaxParser starts here
  	    if(differentiator.equals("laptop"))
  	    {
  	    	HashMap<String, ArrayList<ProductBean>> hvpbt = new HashMap<String, ArrayList<ProductBean>>();
  	    	hvpbt = dataStoreUtilities.viewProductsByType("LAPTOP");
		for(Map.Entry<String,ArrayList<ProductBean>> map : hvpbt.entrySet())
		{
			out.println("<form action='ViewProductServlet'>");
	        
	        for(ProductBean list : map.getValue())
	        {
	        out.println("<table>");
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

	        out.println("<input type ='hidden' name='id' value="+list.getId()+">");
	        String link="<a href='ViewProductServlet?id="+list.getId()+"'"+">View Product</a>";
	        String linkSubmitReview ="<a href='ReviewServlet?productId="+list.getId()+"&productName="+list.getName()+"&retailer="+list.getRetailer()+"&price="+list.getPrice()+"&category=Laptop"+"'"+">Submit Reviews</a>";
	        String linkViewReview   ="<a href='ViewReviews?productName="+list.getName()+"'"+">View Reviews</a>";
	        out.println("<tr>");
	        out.println("<td>");
	        out.println(link);
	        out.println("</td>");
	        out.println("<td>");
	  	    out.println(linkSubmitReview);
	  	    out.println("</td>");
	  	    out.println("<td>");
	  	    out.println(linkViewReview);
	  	    out.println("</td>");
	  	    out.println("</tr>");
	  	    out.println("</table>");
	        }
		}
		
		for(Laptop laptop : dealRetail.laptops)
		{
			alist.add(laptop);
		}
  	    }
		//Iterating into the Laptops returned by SaxParser ends here
		
		//Iterating into the Mobiles returned by SaxParser starts here
  	  if(differentiator.equals("mobile"))
  	  {
	    	HashMap<String, ArrayList<ProductBean>> hvpbt = new HashMap<String, ArrayList<ProductBean>>();
  	    	hvpbt = dataStoreUtilities.viewProductsByType("MOBILE");
	
		for(Map.Entry<String, ArrayList<ProductBean>> mobmap : hvpbt.entrySet())
		{
	        for(ProductBean mobile : mobmap.getValue())
	        {
	        
		        out.println("<table>");
		        out.println("<tr>");
		        out.println("<td>");
		        out.println("<h2>Product Id<h2>");
		        out.println("</td>");
		        
		        out.println("<td>");
		        out.println("<h2>" + mobile.getId() + "</h2>");
		        out.println("</td>");	
		        out.println("</tr>");
		        
		        out.println("<tr>");
		        out.println("<td>");
		        out.println("Retailer   	:");
		        out.println("</td>");
		        out.println("<td>");
		    	out.println("<a>"  + mobile.getRetailer() + "</a>");
		        out.println("</td>");	
		        out.println("</tr>");

		        out.println("<tr>");
		        out.println("<td>");
		        out.println("Product Name   :");
		        out.println("</td>");
		        out.println("<td>");
		        out.println("<a>"  + mobile.getName() + "</a>");
		        out.println("</td>");	
		        out.println("</tr>");

		        out.println("<tr>");
		        out.println("<td>");
		        out.println("Condition      :");
		        out.println("</td>");
		        out.println("<td>");
		        out.println("<a>"  + mobile.getCondition() + "</a>");
		        out.println("</td>");
		        out.println("<td>");

		        out.println("<tr>");
		        out.println("<td>");
		        out.println("Price 		    :");
		        out.println("</td>");
		        out.println("<td>");
		        out.println("<a>"  + mobile.getPrice() + "</a>");
		        out.println("</td>");
		        out.println("<td>");
	     
		    out.println("<input type ='hidden' name='id' value="+mobile.getId()+">");
	        String link="<a href='ViewProductServlet?id="+mobile.getId()+"'"+">View Product</a>";
	        String linkSubmitReview ="<a href='ReviewServlet?productId="+mobile.getId()+"&productName="+mobile.getName()+"&retailer="+mobile.getRetailer()+"&price="+mobile.getPrice()+"&category=Mobile"+"'"+">Submit Reviews</a>";
	        String linkViewReview   ="<a href='ViewReviews?productName="+mobile.getName()+"'"+">View Reviews</a>";
	        out.println("<tr>");
	        out.println("<td>");
	        out.println(link);
	        out.println("</td>");
	        out.println("<td>");
	  	    out.println(linkSubmitReview);
	  	    out.println("</td>");
	  	    out.println("<td>");
	  	    out.println(linkViewReview);
	  	    out.println("</td>");
	  	    out.println("</tr>");
	  	    out.println("</table>");
	        }

		}
		for(Mobile mob : dealRetail.mobiles)
		{
			moblist.add(mob);
		}
  	  }
		//Iterating into the Mobiles returned by SaxParser ends here
		
		
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
