package com.iit.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TrendingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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

		//CODE FOR TRENDING PRODUCT STARTS HERE 
  	    
  	    MongoDBDataStoreUtilities dataStoreUtilities = new MongoDBDataStoreUtilities();
  	    AggregationOutput aggregationOutput = null;
  	    aggregationOutput = dataStoreUtilities.trendingSearch();
  	    
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		out.println("<center><h1> Current Trending Product </h1></center>");
		
		for (DBObject dbObject : aggregationOutput.results()) {
			ArrayList ar = new ArrayList();
			BasicDBObject basicDBOject = (BasicDBObject) dbObject;
			BasicDBList productList = (BasicDBList) basicDBOject.get("Product");
			BasicDBList productReview = (BasicDBList) basicDBOject.get("Reviews");
			BasicDBList rating = (BasicDBList) basicDBOject.get("Rating");
			BasicDBList productPrice = (BasicDBList) basicDBOject.get("productPrice");
			String rating1 = rating.get(productCount).toString();
			int rate = Integer.parseInt(rating1);
			//output.println(rating1);
			out.println("\n");
			rowCount++;
			
			if(Integer.parseInt(rating.get(productCount).toString()) ==5){
			out.println("<center><table border = '1'><tr BGCOLOR='#FFFFFF'><th>City: <th>"+basicDBOject.getString("City")+"</tr>");
			
			int temp= 0;
			// Now print the products with the given review rating and limit the count to 5
			
			while (productCount < productList.size()) {
				
				ar.add(productList.get(productCount).toString());		
				
				
				
				productCount++;
			}
			String retvals[];
			retvals = getHighestFreq(ar);
			ar = null;
			// Reset product count
			out.println("<tr><td>Product Name: </td><td>"+ retvals[0]+"</td></tr>");
			out.println("<tr><td>Total No OF Reviews for <b>"+ retvals[0]+"</b>   </td><td>"+retvals[1] +"</td></tr>");
			out.println("<br>");
			}
			productCount = 0;
			//output.println(ar.toString());
		}
		
		// No data found
		if (rowCount == 0) {
			pageContent = "<h1>No Data Found</h1>";
			out.println(pageContent);
		}
		out.println("</table></center>");
  	    
  	    
	  	    
  	    //CODE FOR TRENDING PRODUCT ENDS HERE 
  	    
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
	
	protected String[] getHighestFreq(ArrayList list){
		String arr[] = new String[2];
		int max = 0;
      int curr = 0;
      String currKey =  null;
      Set<String> unique = new HashSet<String>(list);

          for (String key : unique) {
                curr = Collections.frequency(list, key);

               if(max < curr){
                 max = curr;
                 currKey = key;
                }
            }
		arr[0]=currKey;
		String a = String.valueOf(max);
		arr[1]=a;
		return arr;
	}


}