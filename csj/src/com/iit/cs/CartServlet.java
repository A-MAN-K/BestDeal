package com.iit.cs;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {
    
    
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		HashMap<String,CartBean> hcb = new HashMap<String,CartBean>();
		hcb = (HashMap)session.getAttribute("sessionCart");	
		PrintWriter out = response.getWriter();
		

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
        out.println("<li><a href='MyServlet'>PRODUCTS</a></li>");
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
		
		
     	
        out.println("<form action='OrderServlet'>");
        out.println("<table>");
        Set set = hcb.entrySet();
        Iterator iterator = set.iterator();
        double amount= 0.0;
        
        while(iterator.hasNext())
        {	
        Map.Entry entry =  (Map.Entry)iterator.next();
        CartBean bean = (CartBean)entry.getValue();
        out.println("<tr>");
        out.println("<td>");
        out.println("<h2>Product Id</h2>");
        out.println("</td>");
        out.println("<td>");
        out.println("<h2>" + bean.getProductId()+ "</h2>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Name      :");
        out.println("</td>");
        out.println("<td>");
        out.println("<a>"  + bean.getProductName() + "</a>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Price     :");
        out.println("</td>");
        out.println("<td>");
        out.println("<a>"  + bean.getPrice() + "</a>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Quantity  :");
        out.println("</td>");
        out.println("<td>");
        out.println("<a>"  + bean.getQuantity() + "</a>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        String link="<a href='RemoveCartServlet?productId="+bean.getProductId()+"'"+">Remove from Cart</a>";
        out.println(link);
        out.println("</td>");
        out.println("</tr>");
        
        amount += bean.getPrice();

        }
        out.println("<tr>");
        out.println("<td>");
        out.println("<br/>");
        out.println("</td>");
        out.println("<td>");
        out.println("<br/>");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Proceed to Check Out");
        out.println("</td>");
        out.println("<td>");
        out.println("   ");
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Total Amount");
        out.println("</td>");
        out.println("<td>");
        out.println(amount);
        out.println("</td>");
        out.println("</tr>");

        
        out.println("<tr>");
        out.println("<td>");
        out.println("CARD NUMBER");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type ='text' name='creditCardNumber' />");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("ADDRESS");
        out.println("</td>");
        out.println("<td>");
        out.println("<textarea name='address' rows='2' columns='50'></textarea>") ;
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<input type='submit' value='Place Order'>");
        out.println("</td>");
        out.println("<td>");
        out.println("<a href='customer.html'>Return to Main Menu</a>"+"<br/>");
        out.println("</td>");
        out.println("</tr>");
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
	       out.println("<li><a href='MyServlet'>LAPTOPS</a></li>");
	       out.println("<li><a href='MyServlet'>MOBILE PHONES</a></li>");
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
