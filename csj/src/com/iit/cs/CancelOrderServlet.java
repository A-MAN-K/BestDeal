package com.iit.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelOrderServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int orderNumber  = Integer.parseInt(request.getParameter("orderNumber"));
		String userName  = (String)session.getAttribute("userName");
		
		
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
  	    
  	    
  	    //CODE TO CANCEL ORDER STARTS HERE 
  	    out.println("<form>");
  	    out.println("<table>");
        HashMap<String, ArrayList<OrderBean>> hocv = new HashMap<String, ArrayList<OrderBean>>();
        MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
        hocv = dataStoreUtilities.getOrderDetails(orderNumber);
        
        String deliveryDateOfOrderCan = null;
        String userNameOfOrderCan;
        Date orderDate = null;
        Date currentDate = null;
        int difference = 0;
        
        for(Map.Entry<String, ArrayList<OrderBean>> map : hocv.entrySet())
        {
        	for(OrderBean orderBean : map.getValue())
        	{
        		deliveryDateOfOrderCan = orderBean.getDelliveryDate();
        		userNameOfOrderCan     = orderBean.getUserName();
        		break;
        	}
        	break;
        }
        
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
			try {
				orderDate	 			= format.parse(deliveryDateOfOrderCan);
			} catch (ParseException e) {

				e.printStackTrace();
			}
	    
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        currentDate = calendar.getTime();
        
        difference = datedifference(orderDate, currentDate);
        
        
        if(difference > 5)
        {
        	int result = 0;
        	MySQLDataStoreUtilities dataStoreUtilities2 = new MySQLDataStoreUtilities();
        	result = dataStoreUtilities2.cancelOrder(orderNumber);
        	if(result != 0)
        	{
        		out.println("<tr>");	
        		out.println("<td>");
        		out.println("<br/>");
        		out.println("</td>");
        		out.println("</tr>");

        		out.println("<tr>");	
        		out.println("<td>");
        		out.println("<br/>");
        		out.println("</td>");
        		out.println("</tr>");
        		
        		out.println("<tr>");	
        		out.println("<td>");

        		out.println("The Order Number "+orderNumber+" has been cancelled"+"<br/>");
        		
        		out.println("</td>");	
        		out.println("</tr>");

        	}
        	else
        	{
        		out.println("<tr>");	
        		out.println("<td>");
        		out.println("<br/>");
        		out.println("</td>");
        		out.println("</tr>");

        		out.println("<tr>");	
        		out.println("<td>");
        		out.println("<br/>");
        		out.println("</td>");
        		out.println("</tr>");

        		out.println("<tr>");	
        		out.println("<td>");

        		out.println("The Order Number "+orderNumber+" has not been cancelled");
        		
        		out.println("</td>");	
        		out.println("</tr>");

        	}	
        	
        }
        else
        {
    		out.println("<tr>");	
    		out.println("<td>");
    		out.println("<br/>");
    		out.println("</td>");
    		out.println("</tr>");

    		out.println("<tr>");	
    		out.println("<td>");
    		out.println("<br/>");
    		out.println("</td>");
    		out.println("</tr>");

    		out.println("<tr>");	
    		out.println("<td>");

        	out.println("The order cannot be cancel within the 5 days of delivery "+"<br/>");
        	
    		out.println("</td>");	
    		out.println("</tr>");

        }
        String link="<a href='customer.html'>Return to Main Menu</a>";
		out.println("<tr>");	
		out.println("<td>");
		out.println("<br/>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");	
		out.println("<td>");
        out.println(link);
  		out.println("</td>");	
		out.println("</tr>");

        out.println("</table>");
        out.println("</form>");
 

        	
        //CODE TO CANCEL ORDER ENDS HERE 
        	
        
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
	
	int datedifference(Date orderDate, Date currentDate)
	{
		int difference = 0;
		
		difference = (int)((orderDate.getTime() - currentDate.getTime())/(1000*60*60*24));
		
		return difference;
	}

}
