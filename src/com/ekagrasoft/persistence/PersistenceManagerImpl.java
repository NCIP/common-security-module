/*
 * Created on May 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagrasoft.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ekagrasoft.dataobjects.*;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PersistenceManagerImpl implements PersistenceManager{

	/* (non-Javadoc)
	 * @see com.ekagrasoft.spring.PersistenceManager#getPersonById(java.lang.String)
	 */
	
	public Person getPersonById(String id) throws PersistenceException
	{
		Person person = null;
		
		Connection connection = getConnection();
		try {
			PreparedStatement prepareStatement;			
			prepareStatement = connection.prepareStatement("select id,name from person where id=?");
			prepareStatement.setString(1,id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next())
			{
				person = new Person();
				person.setId(resultSet.getString(1));
				person.setName(resultSet.getString(2));				
			}
			
		} catch (SQLException e) 
		{
			System.out.println("Error : " + e.getMessage());			
			e.printStackTrace();
			throw new PersistenceException("Could not get Person:"+e.getMessage());
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e1) {
				System.out.println("Error : " + e1.getMessage());			
				e1.printStackTrace();
			}
		}

		return person;
	}

	
	public Person createPerson(Person person) throws PersistenceException
	{
		
		Connection connection = getConnection();
		boolean result = false;
		try {
			PreparedStatement prepareStatement;			
			prepareStatement = connection.prepareStatement("insert into person (name) values (?) ");
			prepareStatement.setString(1,person.getName());
			result = prepareStatement.execute();
			prepareStatement = connection.prepareStatement("select * from person where name=?");
			prepareStatement.setString(1,person.getName());
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next())
			{
				person.setId(resultSet.getString(1));
			}
		} catch (SQLException e) 
		{
			System.out.println("Error : " + e.getMessage());			
			e.printStackTrace();
			throw new PersistenceException("Could not create Person:"+e.getMessage());
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e1) {
				System.out.println("Error : " + e1.getMessage());			
				e1.printStackTrace();
			}
		}
		return person;
	}

	public boolean deletePersonById(String id) throws PersistenceException
	{
		
		Connection connection = getConnection();
		boolean result = false;
		try {
			PreparedStatement prepareStatement;			
			prepareStatement = connection.prepareStatement("delete from person where id=?");
			prepareStatement.setString(1,id);
			prepareStatement.executeUpdate();
			prepareStatement.execute();
			result = true;
		} catch (SQLException e) 
		{
			System.out.println("Error : " + e.getMessage());			
			result = false;
			e.printStackTrace();
			throw new PersistenceException("Could not delete Person:"+e.getMessage());
		} finally
		{
			try {
				connection.close();
			} catch (SQLException e1) {
				System.out.println("Error : " + e1.getMessage());			
				e1.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection()
	{
		Connection connection = null;
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) 
		{
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}

		// Get the connection to the database
		try 
		{
			connection = DriverManager.getConnection("jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:cbdev9", "ncisecurity","ncisecurity");
		} catch (SQLException e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	public static void main(String[] args){
		PersistenceManagerImpl pm	   = new PersistenceManagerImpl();
		try{
		Person p = pm.getPersonById("3");
		}catch(PersistenceException ex){
			System.out.println("Exception raised");
			ex.printStackTrace();
		}
	}
	
	
}

