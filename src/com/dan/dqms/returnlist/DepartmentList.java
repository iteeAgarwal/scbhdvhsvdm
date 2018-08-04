package com.dan.dqms.returnlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dqms.db.Department;
import org.dqms.util.Print;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dan.dqms.dbmanager.DBManager;

public class DepartmentList {

	Session session = DBManager.getConfiuration();
	Transaction t = session.beginTransaction();
	public int getDepIdByName(String depname)
	{
		int dep_id=0;
		Session session = DBManager.getConfiuration();
		Transaction t = session.beginTransaction();
		
		List<Department> deptList = new ArrayList<Department>();
	    Query qry = session.createQuery("from Department p where p.depart_name='"+depname+"' ");

	    try{
	    
		List l = qry.list();
	    Iterator it = l.iterator();

		while (it.hasNext()) {

			Object obc = (Object) it.next();

			Department deptBean = (Department) obc;

			dep_id=	deptBean.getDepart_id();
	        
		}
	    }
	    catch(Exception e)
	    {
	    	
	    	Print.logException("Exception in DepartmentList class ", e);
	    }
	    t.commit();
	    session.close();
	    DBManager.closeFactory();
		return dep_id;
	}
	public List<Department> getDeptList() {
		
		List<Department> deptList = new ArrayList<Department>();
        Query qry = session.createQuery("from Department p");

        try{
        
		List l = qry.list();
        Iterator it = l.iterator();

		while (it.hasNext()) {

			Object obc = (Object) it.next();

			Department deptBean = (Department) obc;

			deptBean.setDepart_id(deptBean.getDepart_id());

			deptBean.setDepart_name(deptBean.getDepart_name());

			deptList.add(deptBean);

		}
        }
        catch(Exception e)
        {
        	
        	Print.logException("Exception in DepartmentList class ", e);
        }

		return deptList;

	}
	
	
	public List<Department> getDepart(String deptID) {
		
		List<Department> deptList = new ArrayList<Department>();
        Query qry = session.createQuery("from Department p where depart_id = '"+deptID+"' ");

		try{
        List l = qry.list();
        Iterator it = l.iterator();

		while (it.hasNext()) {

			Object obc = (Object) it.next();

			Department deptBean = (Department) obc;

			deptBean.setDepart_id(deptBean.getDepart_id());

			deptBean.setDepart_name(deptBean.getDepart_name());

			deptList.add(deptBean);

		}
		}
		catch(Exception e)
		{
			Print.logException("Exception in DepartmentList class ", e);
		}

		return deptList;

	}

}
