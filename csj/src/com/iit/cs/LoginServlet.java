package com.iit.cs;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet implements Serializable {
	
	LoginBean loginBean = new LoginBean();
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		String role = request.getParameter("role");
		
		
		loginBean.setUserName(username);
		loginBean.setPassWord(password);
		loginBean.setRole(role);
    
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
		
		HashMap<String,LoginBean> hlb = new HashMap<String,LoginBean>();	
		PrintWriter out = response.getWriter();
		//Code for checking whether user exists or not 
		
		MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
		
		int userExists = dataStoreUtilities.userExists(loginBean.getUserName());
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


		out.println("<li><a href=\"index.html\">CONTACT</a></li>");
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

		
			
		if(userExists == 1)
		{
			hlb  = (HashMap)dataStoreUtilities.getLoginDetails(loginBean.getUserName());
			if(hlb.get(loginBean.getUserName()).getRole().equals(loginBean.getRole())  && hlb.get(loginBean.getUserName()).getPassWord().equals(loginBean.getPassWord()))
			{
				HttpSession session = request.getSession();
				session.setAttribute("userName",loginBean.getUserName());

				if(hlb.get(loginBean.getUserName()).getRole().equals("storemanager"))
				{
					response.sendRedirect("storeManager.html");
				}
				else if(hlb.get(loginBean.getUserName()).getRole().equals("salesman"))
				{
					response.sendRedirect("salesman.html");
				}
				else
				{
					response.sendRedirect("customer.html");
				}
			}
			else
			{
				out.println("Loign Failed");
			}

			
		}
		else
		{
			 
			out.println("The UserName "+loginBean.getUserName()+" does not exists");
			
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

		
/*		ServletContext sc = request.getServletContext();
		String fileName= "C:\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\XMLFiles\\UserDetails.txt";
		
		try{
			
			FileInputStream fileInputStream     = new FileInputStream(new File(fileName));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);		
			
			hmb = (HashMap)objectInputStream.readObject();
			}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("File not read !!!!");
		}
*/	
		
	
		
		
	}

	
}
