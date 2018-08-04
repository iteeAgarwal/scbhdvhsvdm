package org.dqms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dqms.config.SystemSetting;
import org.dqms.util.Print;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TokenListDB
{  /*static int token_no;*/
	private static Connection connection() {
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName(SystemSetting.DB_DRIVER);
			con = DriverManager.getConnection(SystemSetting.DB_URL,
					SystemSetting.DB_USER, SystemSetting.DB_PASSWORD);

		} catch (SQLException e) {
			Print.logInfo("Exception in connection method TokenGeneratorDynamic class"
					, e);
			System.out.println("Exception in connection method and TokenList Dynamic class");
		} catch (Exception e) {
			Print.logInfo("Exception in  connection method TokenGeneratorDynamic class", e);
           
		}
		return con;

	}
  synchronized	public static JSONArray find(int u_id,String tFlag)
	{
		// TODO Auto-generated method stub
		Connection con = connection();
		PreparedStatement ps=null;
		 JSONObject obj=new JSONObject();
		JSONArray array=new JSONArray();
		int rows=0;
    /* 0 for fresh List*/
		if(tFlag.equals("0"))
		{
		try {
			ps=con.prepareStatement("select token_no from token_Details where status = ?");
			ps.setInt(1, 0);
			ResultSet  rs=ps.executeQuery();
		while(rs.next())
		{
			JSONObject json=new JSONObject();
				   System.out.println(rs.getInt("token_no"));
				   json.put("tokenno",rs.getInt("token_no"));
				System.out.println(json);	
				array.put(json);
			}
					
	
		}
		catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(array);
		}
		/* 1 for Skip List */
		else if(tFlag.equals("1") )
		{
			try {
				ps=con.prepareStatement("select token_no from token_Details where status = ?");
				ps.setInt(1, 2);
				ResultSet  rs=ps.executeQuery();
				while(rs.next())
				{
				   JSONObject json1=new JSONObject();
                  System.out.println(rs.getInt("token_no"));
				   json1.put("token_no",rs.getInt("token_no"));
				   System.out.println(json1);
				   array.put(json1);
				}
				
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(array);
		}
		else
		{
			System.out.print("Please input correct tFlag value 0 or 1");
		}
		return array;
	}

}
