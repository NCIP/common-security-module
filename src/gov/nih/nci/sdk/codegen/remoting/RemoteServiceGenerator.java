/*
 * Created on Jun 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.remoting;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * This class will generate the Remote interface depending on ApplicationService
 * interface.
 */
public class RemoteServiceGenerator {
	
	private Class applicationService;
	
	public String basePackage = "gov.nih.nci.application.remote";
	
	public RemoteServiceGenerator(Class applicationService){
		this.applicationService= applicationService;
	}
	
	public void generate(){
		
	}
	/**
	 * Since this is a interface so there will not be any implementation
	 *
	 */
	private void implementMethods(){
		;;
	}

	public static void main(String[] args) {
	}
}
