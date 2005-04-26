package gov.nih.nci.logging.dao;

import gov.nih.nci.logging.Constants;
import gov.nih.nci.logging.struts.QueryForm;
import gov.nih.nci.logging.util.Utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.util.Properties;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.sql.DataSource;

/**
 * DAO class for executing queries for log
 * messages based on the parameters in the
 * QueryForm.  
 * 
 * @author Brian Husted
 */
public class SummaryDaoJdbc implements Constants {




	/**
	 * Returns a list of summaries from the parameters 
	 * in the QueryForm.  
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List findSummaries(QueryForm query) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = JdbcConnectionHandler.createConnection();
			stmt = conn.createStatement();

            if( Utils.hasValue( query.getMaxRecordCount() )&&
              Integer.parseInt(query.getMaxRecordCount()) > 0 ) {
				stmt.setMaxRows(
                   Integer.parseInt(
                      query.getMaxRecordCount()) );
			}
			 rs = stmt.executeQuery(generateSQL(query));

			return convertResultSet( rs );

		} finally {
			try { rs.close();   } catch( Exception e){}
			try { stmt.close(); } catch( Exception e){}
			try { conn.close(); } catch( Exception e){}

		}

	}

	private String generateSQL(QueryForm q) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT L.OID,L.APPLICATION,L.SERVER,L.PRIORITY,"
         + "L.THREAD,L.CREATED_ON, L.MSG, L.NDC, L.THROWABLE FROM LOGTAB L WHERE " );

        if( Utils.hasValue( q.getStartDate() ) ) {
		    String sDate =
              Utils.formatDateForQuery( q.getStartDate(),
                q.getStartTime(), true );

			sql.append("L.CREATED_ON > " +
				sDate );
		} else {
		    //defualt to now if the the start date is not set
		    //this should never happen since the front-end application
		    //ensures a defualt value.
		    sql.append("L.CREATED_ON > " + System.currentTimeMillis() );
		}


		if( Utils.hasValue( q.getEndDate() ) ){
		    String eDate =
              Utils.formatDateForQuery( q.getEndDate(),
                q.getEndTime(), false );
        	sql.append(" AND L.CREATED_ON < " +
				eDate );
		}



        if ( Utils.hasValue( q.getApplication() ) ){
            sql.append(" AND L.APPLICATION ='" +
                q.getApplication() +"'" );

        }

        if ( Utils.hasValue( q.getLogLevel() ) ){
            sql.append(" AND L.PRIORITY = '" +
                q.getLogLevel() +"'" );

        }

        if ( Utils.hasValue( q.getServer() ) ){
            sql.append(" AND L.SERVER = '" +
                q.getServer() +"'" );

        }
        if ( Utils.hasValue( q.getThreadName() )){
            sql.append(" AND L.THREAD like '%" +
                Utils.clean( q.getThreadName() ) +"%'" );

        }

        if ( Utils.hasValue( q.getMsgBody() )){
            sql.append(" AND L.MSG like '%" +
                Utils.clean( q.getMsgBody() ) +"%'" );

        }

        if ( Utils.hasValue( q.getNdc() ) ){
             sql.append(" AND L.NDC like '%" +
                Utils.clean( q.getNdc() ) +"%'" );


        }

		        if ( Utils.hasValue( q.getThrowable() ) ){
		             sql.append(" AND L.THROWABLE like '%" +
		                Utils.clean( q.getThrowable() ) +"%'" );


        }


		sql.append(" order by L.OID" );

		String sqlString = sql.toString();
		return sqlString;
	}

	private List convertResultSet( ResultSet rs ){
	   List l = new ArrayList();
	   try{

          while ( rs.next() ){
            Summary s = new Summary();
            s.setOid( rs.getString( OID ) );
            s.setApplication( rs.getString( APPLICATION ) );
            s.setServer( rs.getString( SERVER ) );
            s.setPriority( rs.getString( PRIORITY ) );
            s.setThread( rs.getString( THREAD ) );
            s.setCreatedOn( rs.getString( CREATED_ON ) );
            s.setMsgBody(rs.getString( MSG ));
            s.setNdc(rs.getString( NDC ));
            s.setThrowable(rs.getString( THROWABLE ) );
            l.add( s );

          }



	   }catch( Exception ex ){
	      ex.printStackTrace();
	   }
       return l;
	}



}