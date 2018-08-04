package org.dqms.war.android.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
import org.json.JSONArray;
import org.json.JSONObject;
@WebServlet("/MessageDDAPI")
public class MessageDeleteDisplay extends HttpServlet
{
	synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		 
	         doPost(request,response);		 
	}
 synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
int i=0; 
JSONArray jsonarray = null;
String message1="",message="";
int docId=0;
String mFlag =null;
String data=request.getParameter("a");
System.out.println(data);
 if(data==null)
 {
	 System.out.println("nothing");
 }
 else
 {
	 JSONObject json=new JSONObject();
	 try
	 {
		 json = new JSONObject(data);
		 System.out.println("Hey"+json);
		 if(!json.isNull("message"))
		 {    
			 jsonarray=json.getJSONArray("message");
	         for(int j=0;j<jsonarray.length();j++){
	        	 message1=message1+"\n"+jsonarray.getString(j);
	         }
	         message=message1.trim();
			 System.out.println("message of String"+jsonarray+"\nString message before trim"+message1+"After trim"+message);
		 }
		 if(!json.isNull("mFlag"))
		 {
			 mFlag=json.getString("mFlag");
			 System.out.println("Flag value is"+mFlag);
		 }
		 if(!json.isNull("docId"))
		 {
			docId = json.getInt("docId");
			 System.out.println("DoctorId"+docId);
		 }
	 }
	 catch(Exception ex)
	 {
		 System.out.println("Exception"+ex);
	 }
 }

System.out.println("\nMessage Received"+message1);
	System.out.println("Value of mFlag"+mFlag);
 


/*if value=0 insert an data otherwise delete the data*/
	 
if(mFlag.equals("1"))
	{
		i=DBMessage.delete(message,docId);
		if(i<0)
		{
			System.out.println("Message Deletion");
			response.setContentType("text/Html");
			PrintWriter out1=response.getWriter();
			 		String htmlRespone1 = "DeletionSucess"; 
			 		out1.println( htmlRespone1);
		}
		else
		{
			response.setContentType("text/Html");
			PrintWriter out1=response.getWriter();
			 		String htmlRespone1 = "Error in Deletion"; 
			 		out1.println( htmlRespone1);
		}

 }
	else if(mFlag.equals("0"))
	{
		JSONArray arr1=DBMessage.display(message,docId);
	
		if(arr1!=null)
		{   System.out.println("Response get"+arr1);
		/*request.setAttribute("mess",arr1);*/
			response.setContentType("application/json");
			PrintWriter out1=response.getWriter();
			 		out1.println(arr1);
			/* RequestDispatcher rd = request.getRequestDispatcher("MessageDisplayAPI");
			 		rd.forward(request,response);*/
		/*response.sendRedirect("MessageDisplayAPI?="+arr1);*/
		}
		else
		{
			//Send Response for wrong mFag value
			response.setContentType("text/Html");
			PrintWriter out1=response.getWriter();
			 		String htmlRespone1 = "No message For Display"; 
			 		out1.println( htmlRespone1);
		}
		
	}

 }
 
 }
 

	
	



