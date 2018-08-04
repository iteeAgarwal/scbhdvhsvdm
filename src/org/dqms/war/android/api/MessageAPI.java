package org.dqms.war.android.api;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.dqms.db.DBMessage;
import org.json.JSONObject;
/**
 * Servlet implementation class Message
 */
@WebServlet("/MessageAPI")
public class MessageAPI extends HttpServlet 
{
	
	int i=0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		int docId=0; 
	 String message="";
		// TODO Auto-generated method stub
	String data=request.getParameter("a");
	System.out.println("DATA received"+data);
	if(data==null || data=="")
	{
		System.out.println("nothing");
	}
	else
	{
	
		 JSONObject json=new JSONObject();
		 System.out.println("JsonObject"+json);
		 try
		 { 
				 json = new JSONObject(data);
			 
			 System.out.println("Hey"+json);
			 if(!json.isNull("message"))
			 {
				 message=json.getString("message");
				 System.out.println("message"+message);
			 }
			 if(!json.isNull("docId"))
			 {
				docId = json.getInt("docId");
			 }
			
		 }
		 catch(Exception ex)
		 {
			 System.out.println("Exception"+ex);
		 }
	 
	 System.out.println("Message Received"+message);
	
		  i=DBMessage.insert(message,docId);
		  System.out.println("Value Of I"+i);
		if(i>0)
		{
			System.out.println("Message insert Successfully");
			response.setContentType("text/Html");
			PrintWriter out=response.getWriter();
			 		String htmlRespone = "Sucess"; 
			 		out.println( htmlRespone);
		}
		else
		{
		
					response.setContentType("text/Html");
					PrintWriter out1=response.getWriter();
					 		String htmlRespone1 = "Error"; 
					 		out1.println( htmlRespone1);
		}
		
		
			
	}
	}

}