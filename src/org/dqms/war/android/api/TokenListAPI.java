package org.dqms.war.android.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dqms.db.TokenListDB;
import org.json.JSONArray;
import org.json.JSONObject;
@WebServlet("/TokenList")
public class TokenListAPI extends HttpServlet
{
	public TokenListAPI()
	{
		super();
	}

	@Override
	synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String data =req.getParameter("TokenDetail");
		int u_id=0;
		String t_Flag = "";
		if(data==null)
		{
			System.out.println("Nothing");
		}
		else
		{
			JSONObject json=new JSONObject();
			try
			{
				json=new JSONObject(data);
				if(!json.isNull("user_id"))
				{
					u_id=Integer.parseInt(json.getString("user_id"));
				   System.out.println("User_id"+u_id);
				}
				if(!json.isNull("tFlag"))
				{
					t_Flag=json.getString("tFlag");
					/*o:Fresh and 1:Skip*/
					System.out.println("Flag Value "+t_Flag);
				} System.out.println("User Id"+u_id+"Token_Flag"+t_Flag);
				
	
				
			}
			
			catch(Exception ex)
			{
				System.out.println("Exception"+ex);
			}
		}
		System.out.println("User Id"+u_id+"Token_Flag"+t_Flag);
		/*0 For fresh List and 1 for Skip List*/
			if(t_Flag.equals("0") || t_Flag.equals("1"))
		{  
			System.out.println("User Id"+u_id+"Token_Flag"+t_Flag);
			JSONArray json1 = TokenListDB.find(u_id,t_Flag);
			System.out.println("Response"+json1);
			resp.setContentType("application/json");
			PrintWriter out=resp.getWriter();
			out.print(json1);
		}
		else
		{
			System.out.println("Wrong choice of Token_Flag");
		}
		
	}
}
