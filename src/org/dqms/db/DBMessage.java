package org.dqms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class DBMessage 
{
static Connection con=null;  
	
	public static Connection getConn()
	{
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dqms","root","rootroot");  
			System.out.println(con);
		}
		catch(Exception e)
		{ 
		 	System.out.println(e);
		 } 
		return con;
			}
	public static int insert(String message,int docId)
	{
		// TODO Auto-generated method stub
		int p=0,room_no;
		try
		{
		  Connection con=getConn();
		  room_no=find_room_no(docId);
		  System.out.println("Message:-"+message+"RoomNO:-"+room_no);
		  PreparedStatement ps=con.prepareStatement("insert into message(mess,room_no) values(?,?)");
		  ps.setString(1,message);
		  ps.setInt(2, room_no);
		   p=ps.executeUpdate();
		   System.out.println("value of p"+p);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	 
		return p;
	}
	
	public static int delete(String mess,int docId)
	{
		// TODO Auto-generated method stub
		int p=0,j=0,room_no;
		System.out.println("Message For Deletion"+mess);
		try
		{
			 Connection con=getConn();
			 room_no=find_room_no(docId);
			 PreparedStatement ps=con.prepareStatement("delete from message where room_no=? && mess=?");
			 ps.setInt(1, room_no);
			 ps.setString(2, mess);
			 p=ps.executeUpdate();
			 System.out.println("value of p"+p);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		if(p>0)
		{
			j--;
		}
		return j;
	}
	/*First Find Room_id from user_detail then find room_number using room_id from room_detail*/
	public static int find_room_no(int user_id)
	{   int room_id=0,room_no=0;
	    try
	    {
	    	Connection con=getConn();
	    	PreparedStatement ps=con.prepareStatement("select  room_id from user_Details where user_id=?");
	    	ps.setInt(1, user_id);
	    	ResultSet rs=ps.executeQuery();
	    	while(rs.next())
	    	{   room_id=rs.getInt(1);
	    		System.out.println(room_id);
	    	}
	    	System.out.println(room_id);
	    	/*Now get Room No From room_id*/
	    	PreparedStatement ps1=con.prepareStatement("select room_no from room_details where room_id=?");
	    	ps1.setInt(1, room_id);
	    	ResultSet rs1=ps1.executeQuery();
	    	while(rs1.next())
	    	{
	    		room_no=rs1.getInt(1);
	    	 	System.out.println("RoomNo"+room_no);
	    	}
	    	System.out.println("Room_no"+room_no);
	    }
	    catch(Exception ex)
	    {
	    	System.out.println(ex);
	    }
		return room_no;
		
	}
	public static JSONArray display(String message, int docId) {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		System.out.println(message);
		int j=0;
	     String[] strmessage=message.split("\n");
	     System.out.println("String messgae into array");
		int roomno;
	   try
		{
			 Connection con=getConn();
			 roomno=find_room_no(docId);
			PreparedStatement ps2=con.prepareStatement("update message set mFlag=?  where mess=? && room_no=?");
			 ps2.setBoolean(1, true);
			for(int i=0;i<strmessage.length;i++)
			 {
			 ps2.setString(2, strmessage[i]);
			 System.out.println("Message for boolean Checked-"+strmessage[i]);
			 }
			 ps2.setInt(3, roomno);
             j=ps2.executeUpdate();
             System.out.println(j);
			 PreparedStatement ps1=con.prepareStatement("select mess from message where mess = ? && room_no = ?");
			 for(int i=0;i<strmessage.length;i++)
			 {
			 ps1.setString(1, strmessage[i]);
				System.out.println("Message for checked:-"+strmessage[i]);
			 }
			 ps1.setInt(2, roomno);
			 ResultSet rs=ps1.executeQuery();
			 System.out.println("rs"+rs);
			 while(rs.next())
			 {    
				  JSONObject obj1=new JSONObject();
				  for(int i=0;i<strmessage.length;i++)
				  {
				 obj1.put("Mess"+i, rs.getString("mess"));
				  }
				 System.out.println("Message Object"+obj1);
				 arr.put(obj1);
		   }
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		} 
	   System.out.println("JsonArray String is"+arr);
	   return arr;
	}
}

