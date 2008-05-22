/*
 * Created on Mar 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.test;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserSession implements Runnable{
	private final String name;
    private final int delay;
    public UserSession(String name, int delay) {
      this.name = name;
      this.delay = delay;
    }
    
    public void run() {
        System.out.println("Starting: " + name);
        try {
          Thread.sleep(delay);
          /**
           * Do your work here.
           */
          SessionCalls sc = new SessionCalls(name);
          sc.doWork();
        } catch (InterruptedException ignored) {
        }
        System.out.println("Done with: " + name);
      }


}
