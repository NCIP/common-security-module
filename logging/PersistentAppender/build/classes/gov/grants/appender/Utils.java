package gov.grants.appender;
import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;


/**
 *  Utility Class for Appender project.
 *  @author Brian
 *
 */
public class Utils {

    /**
     * Writes fatal errors to a log file on the system's current directory.
     * @param t
     */
    public static void writeMsgToTmpFile( Throwable t ){
            FileWriter writer = null;
             try{
             File f = new File ("jdbcappender" + System.currentTimeMillis() +".log" );
             writer = new FileWriter( f );
             writer.write( getErrorAndStack( t ) );
             writer.flush();


            } catch( Exception e ){
            } finally{
               try{ writer.close(); }catch( Exception e1 ){}
             }
       }



  /**
   * Utility method... I'll just put this here temporarily.
   * Add documentation, move in utility class/package?
   *
   * Allows client method to get the stack trace for an error
   * so that it can be parsed or passed to another method.
   *
   * NOTE: This method is currently static since I thought I might
   *   need to call it from constructors and etc. ...
   *
   * For now, let all exceptions propagate...
   *
   * NOTE: If speed becomes an issue then we might want to complete
   *   the implementation (below) that "caches" the writer objects
   *   so that we don't have to instantiate them every time.  However,
   *   if we use singleton object (ie static/class variables) then
   *   we need to consider synchronization issues.
   *   Anyway, hopefully we won't have that many errors and, when
   *   we do have an error, speed won't be an issue.  So for now
   *   I'll just leave this the way it is.
   */
  public static StringBuffer getStackTrace ( Throwable t ) {
	java.io.StringWriter stringWriter;

	stringWriter = new java.io.StringWriter();
	t.printStackTrace( new java.io.PrintWriter(stringWriter) );

	return stringWriter.getBuffer();
  }

  public static String getErrorAndStack( Throwable t ){
      if ( t == null ){ return null; }
      return t.getMessage() + System.getProperty( "line.separater" )
        + getStackTrace( t ).toString();

  }

  /**
   *
   */
  public static String getTrace ( Object throwable ) throws ClassCastException
  {
	return getTrace( (Throwable)throwable );
  }

  /**
   *
   */
  public static StringBuffer getTrace ( Object throwable, StringBuffer buffer )
							   throws ClassCastException
  {
	return getTrace( (Throwable)throwable, buffer );
  }

  /**
   *
   */
  public static String getTrace ( Throwable throwable ) {
	return getTrace( throwable, null ).toString();
  }

  /**
   * Allows client method to get the stack trace for an error
   * so that it can be parsed or passed to another method.
   *
   * Add documentation...
   *
   * NOTE: This method is currently static since I thought I might
   *   need to call it from constructors and etc. ...
   *
   * For now, let all exceptions propagate...
   *
   * NOTE: If speed becomes an issue then we might want to complete
   *   the implementation (below) that "caches" the writer objects
   *   so that we don't have to instantiate them every time.  However,
   *   if we use singleton object (ie static/class variables) then
   *   we need to consider synchronization issues.
   *   Anyway, hopefully we won't have that many errors and, when
   *   we do have an error, speed won't be an issue.  So for now
   *   I'll just leave this the way it is.
   *
   * Create subclass of PrintWriter that supports getBuffer()...
   *
   * Create getStack() method that parse the trace and returns a
   * a Stack object.
   *
   */
  public static StringBuffer getTrace ( Throwable throwable, StringBuffer buffer )
  {
	java.io.StringWriter  strWriter = null;
	java.io.PrintWriter   prtWriter = null;

	try {
	  strWriter = new java.io.StringWriter();
	  prtWriter = new java.io.PrintWriter( strWriter );

	  throwable.printStackTrace( prtWriter );

	  if ( buffer == null ) {
		return strWriter.getBuffer();
	  }

	  return buffer.append( strWriter.getBuffer() );

	} finally {

	  try { prtWriter.close(); } catch ( Exception e ) {}
	}

  }
  
  public static String getThrowable(LoggingEvent le) {
	ThrowableInformation tinfo = le.getThrowableInformation();
	Throwable t = null;
    try{
      t = tinfo.getThrowable();
    }catch( Exception ex ){}

    if ( t == null ){ return ""; }

    java.io.StringWriter stringWriter;
    stringWriter = new java.io.StringWriter();
    t.printStackTrace( new java.io.PrintWriter(stringWriter) );

    return stringWriter.getBuffer().toString();
}


}