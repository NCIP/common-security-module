/*
 * Created on Jul 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;



import gov.nih.nci.sdk.common.ApplicationException;
import gov.nih.nci.system.dao.impl.orm.ORMConnection;
import gov.nih.nci.system.servicelocator.ServiceLocator;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SdkDAO {

	static final Logger log = Logger.getLogger(SdkDAO.class.getName());

	public Object createObject(Object obj) throws ApplicationException{
		Session s = null;
		Transaction t = null;
		try{
		s = this.getSession(obj);
		}catch(Exception ex){
			
			throw new ApplicationException("Could not obtain session for this entity! Could not create object");
			
		}
		
		
		if(s==null){
			throw new ApplicationException("Could not obtain session for this entity! Could not create object");
			
		}
		
		try {
			
			t = s.beginTransaction();
			s.save(obj);
			t.commit();
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3){
				if (log.isDebugEnabled())
					log.debug("createObject|Failure|Error in Rolling Back Transaction|"+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log	.debug("createObject|Failure|Error in creating the "+ obj.getClass().getName()+ ex.getMessage());
			
			throw new ApplicationException("An error occured in creating the "+obj.getClass().getName() + "\n" + ex.getMessage());
		}  finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log.debug("createObject|Failure|Error in Closing Session |"	+ ex2.getMessage());
			}
		} 
		if (log.isDebugEnabled())
			log	.debug("createObject|Success|Successful in creating the "+ obj.getClass().getName());
	  	return obj;
		
		
	}
	public Object updateObject(Object obj) throws ApplicationException{
		Session s = null;
		Transaction t = null;
		try{
		s = this.getSession(obj);
		}catch(Exception ex){
			
			throw new ApplicationException("Could not obtain session for this entity! Could not update the object");
			
		}
		
		if(s==null){
			throw new ApplicationException("Could not obtain session for this entity! Could not update the object");
			
		}
		
		try {
			
			t = s.beginTransaction();
			s.update(obj);
			t.commit();
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3){
				if (log.isDebugEnabled())
					log.debug("updateObject|Failure|Error in Rolling Back Transaction|"+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log	.debug("updateObject|Failure|Error in updating the "+ obj.getClass().getName()+ ex.getMessage());
			
			throw new ApplicationException("An error occured in updating the "+obj.getClass().getName() + "\n" + ex.getMessage());
		}  finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log.debug("updateObject|Failure|Error in Closing Session |"	+ ex2.getMessage());
			}
		} 
		if (log.isDebugEnabled())
			log	.debug("updateObject|Success|Successful in updating the "+ obj.getClass().getName());
	  	return obj;
	}
	public void removeObject(Object obj) throws ApplicationException{
		Session s = null;
		Transaction t = null;
		try{
		s = this.getSession(obj);
		}catch(Exception ex){
			
			throw new ApplicationException("Could not obtain session for this entity! Could not remove the object");
			
		}
		
		if(s==null){
			throw new ApplicationException("Could not obtain session for this entity! Could not remove the object");
			
		}
		
		try {
			
			t = s.beginTransaction();
			s.delete(obj);
			t.commit();
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3){
				if (log.isDebugEnabled())
					log.debug("deleteObject|Failure|Error in Rolling Back Transaction|"+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log	.debug("deleteObject|Failure|Error in removing the "+ obj.getClass().getName()+ ex.getMessage());
			
			throw new ApplicationException("An error occured in removing the "+obj.getClass().getName() + "\n" + ex.getMessage());
		}  finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log.debug("deleteObject|Failure|Error in Closing Session |"	+ ex2.getMessage());
			}
		} 
		if (log.isDebugEnabled())
			log	.debug("deleteObject|Success|Successful in deleting the "+ obj.getClass().getName());
	}
	public List getObjects(Object obj) throws ApplicationException{
		Session s = null;
		
		try{
		s = this.getSession(obj);
		}catch(Exception ex){
			
			throw new ApplicationException("Could not obtain session for this entity! Could not get objects");
			
		}
		
		if(s==null){
			throw new ApplicationException("Could not obtain session for this entity! Could not get the objects");
			
		}
		List list = null;
	  	try {
			
			Criteria c = s.createCriteria(obj.getClass());
			c.add(Example.create(obj));
			 list = c.list();
		} catch (Exception ex){
			log.error(ex);
			if (log.isDebugEnabled())
				log	.debug("getObjects|Failure|Error in getting objects"+ ex.getMessage());
			
			throw new ApplicationException("An error occured in getting objects" + "\n" + ex.getMessage());
		}finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log.debug("getObjects|Failure|Error in Closing Session |"	+ ex2.getMessage());
			}
		} 
		if (log.isDebugEnabled())
			log	.debug("getObjects|Success|Successful in deleting the "+ obj.getClass().getName());
	  	return list;
	}
	
	private Session getSession(Object obj) throws Exception{
		ServiceLocator sl = new ServiceLocator();
		
		  int i = sl.getORMCounter(obj.getClass().getName());
		  
		  ORMConnection cn = ORMConnection.getInstance();
		  
		  Session sn = cn.openSession(i);
		 
		return sn;
	}
}
