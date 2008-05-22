/*
 * Created on Jun 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.prototype.dao;

import gov.nih.nci.sdk.prototype.domainobjects.*;

import java.util.HashSet;
import java.util.List;








import net.sf.hibernate.Criteria;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Example;


/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HibernateDAO {
  private  SessionFactory sf;

  
  public HibernateDAO(){
  	 
  	this.sf=ApplicationSessionFactory.getSessionFactory();
  }
  
  public Object createObject(Object obj){
  	Session s = null;
	Transaction t = null;
	try {
		s = sf.openSession();
		t = s.beginTransaction();
		s.save(obj);
		t.commit();
	} catch (Exception ex) {
		ex.printStackTrace();
		try {
			t.rollback();
		} catch (Exception ex3){
			ex3.printStackTrace();
		}
		
	}  finally {
		try {
			s.close();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
	} 
	
  	return obj;
  }
  public Object updateObject(Object obj) {
  	
  	Session s = null;
	Transaction t = null;
	try {
		s = sf.openSession();
		t = s.beginTransaction();
		s.update(obj);
		t.commit();
	} catch (Exception ex) {
		ex.printStackTrace();
		try {
			t.rollback();
		} catch (Exception ex3){
			ex3.printStackTrace();
		}
		
	}  finally {
		try {
			s.close();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
	} 
	
  	return obj;
  }
  public void removeObject(Object obj) {
  	Session s = null;
	Transaction t = null;
	try {
		s = sf.openSession();
		t = s.beginTransaction();
		s.delete(obj);
		t.commit();
	} catch (Exception ex) {
		ex.printStackTrace();
		try {
			t.rollback();
		} catch (Exception ex3){
			ex3.printStackTrace();
			}
		
	}  finally {
		try {
			s.close();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
	} 
	
  	
  	
  }
  
  public List getObjects(Object obj) {
  	Session s = null;
  	List list = null;
  	try {
		s = sf.openSession();
		Criteria c = s.createCriteria(obj.getClass());
		c.add(Example.create(obj));
		 list = c.list();
	} catch (Exception ex){
		ex.printStackTrace();
		
	}finally {
		try {
			s.close();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
	} 
	
  	return list;
  }
  
  public static void main(String[] args){
  	HibernateDAO hd= new HibernateDAO();
  	/**
  	 Item item = new Item();
  	 item.setName("Bat");
  	 Item item1 = (Item)hd.createObject(item);
  	 System.out.println(item1.getId());
  	 */
  	
  	/**
  	 Customer ct = new Customer();
  	 ct.setName("vinay");
  	 
  	 Customer vinay = (Customer)hd.createObject(ct);
  	System.out.println(vinay.getId());
  	
  	Account ac = new Account();
     ac.setCustomer(vinay);
     
     Account vinayAc = (Account)hd.createObject(ac);
     System.out.println(vinayAc.getId());
     */
  	
  	 Customer vinay = new Customer();
  	 vinay.setName("vinay");
  	 List l = hd.getObjects(vinay);
  	 vinay = (Customer)l.get(0);
  	 Order o1 = new Order();
  	 o1.setCustomer(vinay);
  	 Item bat = new Item();
  	 bat.setName("Bat");
  	 
  	 List q = hd.getObjects(bat);
  	 HashSet hs = new HashSet(q);
  	 o1.setItems(hs);
  	 
  	   hd.createObject(o1);
  	   
  	   System.out.println(o1.getId());
  	 try{
  	ApplicationSessionFactory.getSessionFactory().close();
  	 }catch(Exception ex){
  	 	ex.printStackTrace();
  	 }
  }
}
