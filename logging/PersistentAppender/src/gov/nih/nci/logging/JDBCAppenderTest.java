package gov.nih.nci.logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Category;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *  JUNIT test class for the JDBCAppender. 
 * 
 *  @author Brian Husted
 *
 */
public class JDBCAppenderTest extends junit.framework.TestCase implements Constants {
   protected static java.util.Random random = new java.util.Random();
   private final static char[] chars;

    static {
      chars = new char[52];
      for (int i = 0; i < 26; i ++) {
         chars[i] = (char) (97 + i);
         chars[i + 26] = (char) (65 + i);
      }
    }

    protected static String generateString()
    {
        int length = random.nextInt(66);
        char[] array = new char[length];
        for (int i = 0; i < length; i ++) {
           array[i] = chars[random.nextInt (chars.length)];
        }
        return new String (array);
    }
   static Category cat = Category.getInstance(JDBCAppenderTest.class.getName());

   public JDBCAppenderTest ( String testName ){
        super( testName );
   }

   public static void testAppend() throws Exception{
     DOMConfigurator.configure("c:/gg_test_jdbcLog.xml");

    // System.setProperty( "java.library.path", "/oracle/product/9i/bin" );
     long startTime = System.currentTimeMillis();
     for (int i = 0; i < 10000; i++) {

            cat.debug("DEBUG" );
			cat.info("INFO");
			cat.error("ERROR");
			cat.fatal("FATAL");
			
	 }
	 long endTime = System.currentTimeMillis();

	 System.out.println( "Total elapsed time was: " + (endTime - startTime) );

   }

   public static void main( String[] args ) throws Exception {
       JDBCAppenderTest.testAppend();
   }


}