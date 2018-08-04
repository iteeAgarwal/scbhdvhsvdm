package com.dan.dqms.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dqms.db.Department;
import org.dqms.db.Roles;
import org.dqms.db.Room;
import org.dqms.db.User;
import org.dqms.util.Print;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dan.dqms.dbmanager.DBManager;
import com.dan.dqms.returnlist.DepartmentList;
import com.dan.dqms.returnlist.RoomsList;
import com.dan.dqms.returnlist.UserList;
import com.dan.dqms.returnlist.UserRoleList;

@WebServlet("/NewUserInsert")
public class NewUserInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession httpsession;
	public NewUserInsert() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		httpsession=request.getSession(false);
		
		long todayTime = System.currentTimeMillis() / 1000;

		boolean flag = false;

		UserList userList = new UserList();
		List<User> arrlist = userList.getAllUsers();

		Session session = DBManager.getConfiuration();
		Transaction tx = session.beginTransaction();
		
		
		

		String userRole = request.getParameter("userRole");

		String userID = request.getParameter("userID");

		String userName = request.getParameter("userName");

		String userPhNo = request.getParameter("userPhNo");

		String userEmail = request.getParameter("userEmail");

		String uName = request.getParameter("uName");

		String uPass = request.getParameter("uPass");

		String desig = request.getParameter("desig");

		String depart = request.getParameter("depart");
		
		if(depart==null){
			depart="0";
		}

		String roomNo = request.getParameter("rooms_list");
		
		int roomNoInt = 0;
		
		if(roomNo == null || roomNo == "")
		{
			roomNoInt =0;
		}
		else
		{
			roomNoInt = Integer.parseInt(roomNo);
		}
		
		
		
		
		String view = request.getParameter("view");
		int viewInt = 0;
		if(view == null)
		{
			viewInt =0;
		}
		else
		{
			viewInt = Integer.parseInt(view);
		}
		

		

		String roomSaveBtn = request.getParameter("userDetails");

		String userEdit = request.getParameter("userEdit");

		String roomcancelBtn = request.getParameter("roomcancel");

		if (roomSaveBtn != null) {

			try {

				if (arrlist.size() > 0) {
					for (User list : arrlist) {
						if (uName.equalsIgnoreCase(list.getUsername())) {
							flag = true;
							httpsession.setAttribute("msg",
									"User already exits.....!!");
							request.setAttribute("userList",
									userList.getAllUsers());
							break;
						}

					}
				}
				if (!flag) {
					User user = new User();

					user.setRole_id(Integer.parseInt(userRole));

					// user.setUser_id(Integer.parseInt(userID));

					user.setName(userName);

					user.setPhone_no(userPhNo);

					user.setEmail_id(userEmail);

					user.setUsername(uName);

					user.setPassword(uPass);

					user.setDesignation(desig);

					user.setDepart_id(Integer.parseInt(depart));

					user.setRoom_id(roomNoInt);

					user.setView1(viewInt);
					
					user.setCreation_date(todayTime);
					
					user.setLast_updated_on(todayTime);
					
					

					session.persist(user);

					tx.commit();
					session.close();
					httpsession.setAttribute("msg", "User save successfully.....!!");

				}

				response.sendRedirect("./NewUserList");
				/*request.getRequestDispatcher("./NewUserList").forward(request,
						response);*/

			} catch (Exception e) {
				
				Print.logException("Exception in new user insert  form", e);
			}

		}
		if (userEdit != null) {

			try {

				Query qry = session
						.createQuery("update User p set  p.role_id=?,name=?,phone_no=?,email_id=?,username=?,password=?,designation=?,depart_id=?,room_id=?,view=?,last_updated_on=? where p.user_id ='"
								+ userID + "'");
				qry.setParameter(0, Integer.parseInt(userRole));

				qry.setParameter(1, userName);

				qry.setParameter(2, userPhNo);

				qry.setParameter(3, userEmail);

				qry.setParameter(4, uName);

				qry.setParameter(5, uPass);

				qry.setParameter(6, desig);

				qry.setParameter(7, Integer.parseInt(depart));

				qry.setParameter(8, roomNoInt);
				
				qry.setParameter(9, viewInt);
				
				qry.setParameter(10, todayTime);
				
				

				int res = qry.executeUpdate();
				
				tx.commit();

				session.close();

				if (res > 0) {
					httpsession.setAttribute("msg", "User Edit successfully.....!!");
				} else {
					httpsession.setAttribute("msg",
							"User Edit Unsuccessfully.....!!");

				}

				response.sendRedirect("./NewUserList");
				/*
				request.getRequestDispatcher("./NewUserList").forward(request,
						response);*/

			} catch (Exception e) {
				Print.logException("Exception in  user update  form",e);
			}

		}

		if (roomcancelBtn != null) {
			request.setAttribute("userList", userList.getAllUsers());

			response.sendRedirect("./NewUserList");
			
			/*request.getRequestDispatcher("./NewUserList").forward(request,
					response);*/

		}

	}
}
