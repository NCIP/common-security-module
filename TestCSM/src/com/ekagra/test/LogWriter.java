/*
 * Created on Mar 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.test;


import java.util.*;
import java.util.logging.*;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


public class LogWriter{

  static private LogWriter instance;
  private static String baseDir ="C:/CSMTest/logs/";
  private LogWriter() {
    init();
  }

  static synchronized public LogWriter getInstance()
   {

     if (instance == null)
      {

       instance = new LogWriter();
      }

      return instance;
   }

   private void init(){
     try{
     logger = Logger.getLogger("com.ekagra.test");
     cal.setTime(new Date());
     
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
     String fileName = formatter.format(new Date())+"csmLog.txt";
//     String fileName = cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DATE)+"Log.xml";
     FileHandler filehandler = new FileHandler(baseDir+fileName);
     //filehandler.setFormatter(new XMLFormatter());// comment out this line for no XML
     filehandler.setFormatter(new SimpleFormatter());
     logger.addHandler(filehandler);
     logger.setLevel(Level.ALL);
     }catch(Exception ex){
       ex.printStackTrace();
     }
   }
   

   public void log(Level level, String msg){
     logger.log(level,msg);
   }
   public synchronized void  log(Level level, String msg, Object param1) {
     
     logger.log(level, msg,param1);
   }
 private static final long HR = 24*60*60*1000;
 private Calendar cal = new GregorianCalendar();
 private Logger logger = null;
 public static Level ALL = Level.ALL;
 public static Level CONFIG = Level.CONFIG;
 public static Level FINE = Level.FINE;
 public static Level FINER = Level.FINER;
 public static Level FINEST = Level.FINEST;
 public static Level INFO = Level.INFO;
 public static Level OFF = Level.OFF;
 public static Level SEVERE = Level.SEVERE;
 public static Level WARNING = Level.WARNING;

 public static void main(String[] args)
 {
   LogWriter lg = LogWriter.getInstance();
 }
}
