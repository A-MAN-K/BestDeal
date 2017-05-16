package com.iit.cs;
import java.io.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class DataAnalytics extends HttpServlet {
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  processRequest(request, response);
		
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
		DB db = mongo.getDB("BestDealMongoDB");
		DBCollection dbCollection = db.getCollection("bestDealReviews");
		BasicDBObject allQuery = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject();
		fields.put("productNameMongo", 1);
		ArrayList productList= new ArrayList();
		DBCursor cursor = dbCollection.find(allQuery, fields);
		while (cursor.hasNext()) {
		BasicDBObject obj = (BasicDBObject) cursor.next();
		 productList.add(obj.get("productNameMongo"));
		
		}
		
		
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

  	    //CODE FOR DATA ANALYTICS STARTS HERE
		out.println("<center>");
		out.println("<h3> Find Data </h3>");
        out.println("<form method='post' action='DataAnalyticsServlet'>");
        out.println("<table BORDER=1>");
		out.println("<tr>");
		out.println("<td colspan = '4' ALIGN=CENTER> <b> Simple Search </b> </td>");
		out.println("</tr>");
        out.println("<tr>");
		out.println("<td> <input type='checkbox' name='queryCheckBox' value='productName' checked> Select </td>");
        out.println("<td ALIGN=CENTER> Product Name: </td>");
        out.println("<td>");
        out.println("<select name='productName'>");
		out.println("<option value='ALL_PRODUCTS'>All Products</option>"); 
		 
		for (int i =0;i<productList.size();i++){
        out.println("<option value="+productList.get(i)+">"+productList.get(i)+"</option>");
        }
		
        out.println("</td>");
		out.println("<td><input type='checkbox' name='advanced' value='mostliked'>Most Liked<br>");
        out.println("<input type='checkbox' name='advanced' value='mostdisliked'>Most DisLiked - Retailer<br>");
		out.println("<input type='checkbox' name='advanced' value='mostdislikedz'>Most DisLiked - Zip Code</td>");
		out.println("</tr>");
        out.println("<tr>");
		out.println("<td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>");
        out.println("<td ALIGN=CENTER> Product Price: </td>");
        out.println("<td>");
        out.println("<input type='number' name='productPrice' value = '0' size=10/> </td>");
		out.println("<td>");
		out.println("<input type='radio' name='comparePrice' value='EQUALS_TO' checked> Equals <br>");
		out.println("<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>");
		out.println("<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than");
		out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
		out.println("<td> <input type='checkbox' name='queryCheckBox' value='retailerZipcode'> Select </td>");
        out.println("<td ALIGN=CENTER> Retailer Zip code: </td>");
        out.println("<td>");
        out.println("<input type='number' name='retailerZipcode' value = '0' size=10/> </td>");
		out.println("<td><input type='checkbox' name='advanced' value='highestPriceZip'> Highest Price  </td>");
        out.println("</tr>");
		
        out.println("<tr>");
		out.println("<td> <input type='checkbox' name='queryCheckBox' value='retailerCity'> Select </td>");
        out.println("<td ALIGN=CENTER> Retailer City: </td>");
        out.println("<td>");
        out.println("<input type='text' name='retailerCity' value = 'All' /> </td>");
		out.println("<td><input type='checkbox' name='advanced' value='highestPriceCity'>Highest Product Price</td>");
        out.println("</tr>");
        out.println("<tr>");
		out.println("<td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>");
        out.println("<td ALIGN=CENTER> Review Rating: </td>");
        out.println("<td>");
        out.println("<select name='reviewRating'>");
        out.println("<option value='1' selected>1</option>");
        out.println("<option value='2'>2</option>");
        out.println("<option value='3'>3</option>");
        out.println("<option value='4'>4</option>");
        out.println("<option value='5'>5</option>");
        out.println("</td>");
		out.println("<td>");
		out.println("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
		out.println("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than");
		out.println("</td>");
        out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td> <input type='checkbox' name='queryCheckBox' value='User_ID'> Select </td>");
		out.println("<td ALIGN=CENTER> User ID: </td>");
		out.println("<td><input type='text' name='userID'></td>");
		out.println("<td></td>");	
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println("Return:");
		out.println("</td>");
		out.println("<td colspan = '4'> ");
		out.println("<select name='returnValue'>");
		out.println("<option value='ALL' selected>All</option>");
        out.println("<option value='TOP_5'>Top 5 </option>");
        out.println("<option value='TOP_10'>Top 10 </option>");
		out.println("<option value='LATEST_5'>Latest 5 </option>");
		out.println("<option value='LATEST_10'>Latest 10 </option>");
        out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan = '4' ALIGN=CENTER> <b> Grouping </b> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
        out.println("<input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By:");
		out.println("</td>");
		out.println("<td colspan = '3'>");
		out.println("<select name='groupByDropdown'>");
        out.println("<option value='GROUP_BY_CITY' selected>City</option>");
        out.println("<option value='GROUP_BY_PRODUCT'>Product Name</option> ");
		out.println("<option value='GROUP_BY_RETAILER_NAME'>Retailer Name</option> ");
		out.println("</td>");
        out.println("</tr>");
		out.println("<tr>");
        out.println("<td><input type='submit' name = 'action' value='FindData' class='formbutton' /> </td>");
		out.println("</tr>");
        out.println("</table>");
        out.println("</form>");	
		out.println("</center>");	
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
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Buy Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
	
	
}