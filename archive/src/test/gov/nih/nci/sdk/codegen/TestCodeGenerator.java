/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.gov.nih.nci.sdk.codegen;

import java.lang.reflect.*;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestCodeGenerator {
	
	private Class applicationServiceInterface;
	
	public TestCodeGenerator(Class applicationServiceInterface){
		this.applicationServiceInterface=applicationServiceInterface;
	}
	
	public void generate(){
		Method[] m = applicationServiceInterface.getMethods();
		
		for(int i=0;i<m.length;i++){
			Method m1 = m[i];
		System.out.println(m1.getName());
		
		Class rType = m1.getReturnType();
		System.out.println("Renturn type:"+rType.getName());
		
	      Class[] exs = m1.getExceptionTypes();
	       for(int j=0;j<exs.length;j++){
	       	 Class cl = exs[j];
	       	 System.out.println(cl.getName());
	       }
		}
		printMethods(applicationServiceInterface);
	}
	
	public  void printMethods(Class cl)
	   {  Method[] methods = cl.getDeclaredMethods();

	      for (int i = 0; i < methods.length; i++)
	      {  Method m = methods[i];
	         Class retType = m.getReturnType();
	         Class[] paramTypes = m.getParameterTypes();
	         String name = m.getName();
	         System.out.print(Modifier.toString(m.getModifiers()));
	         System.out.print(" " + retType.getName() + " " + name 
	            + "(");
	         for (int j = 0; j < paramTypes.length; j++)
	         {  if (j > 0) System.out.print(", ");
	            System.out.print(paramTypes[j].getName());
	         }
	         System.out.println(");");
	      }
	   }


	public static void main(String[] args) {
		
		TestCodeGenerator tcg = new TestCodeGenerator(TestApplicationService.class);
		tcg.generate();
	}
}
