package org.dqms.war.android.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class MessageDisplayAPI
 */
@WebServlet("/MessageDisplayAPI")
public class MessageDisplayAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDisplayAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
   	 
            doPost(request,response);		 
    }
	synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getAttribute("mess");
		System.out.println("daslj"+request.getAttribute("mess"));
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		//out.println(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
