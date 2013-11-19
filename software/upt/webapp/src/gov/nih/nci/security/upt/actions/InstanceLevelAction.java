/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.actions;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module's User Provisioning Tool (UPT) Software License,
 *Version 3.0 Copyright 2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'UPT Software').  The UPT Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.
 *
 *This UPT Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the UPT Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the UPT Software; (ii) distribute and have distributed to
 *and by third parties the UPT Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */



import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.ApplicationForm;
import gov.nih.nci.security.upt.forms.InstanceLevelForm;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.util.ClassPathLoader;
import gov.nih.nci.security.upt.util.HibernateHelper;
import gov.nih.nci.security.upt.viewobjects.SearchResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 *
 */
public class InstanceLevelAction extends ActionSupport implements ServletContextAware
{
	private static final Logger logDB = Logger.getLogger(InstanceLevelAction.class);
	protected ServletContext servletContext;
	private InstanceLevelForm instanceLevelForm;
	private String operation;
	private String className;
	private String filterChain;
	private String targetClassName;
	private String targetClassAttributeName;
	private String targetClassAttributeType;
	private String targetClassAlias;
	private String targetClassAttributeAlias;
	private String generatedSQL;
	private String generatedSQLForUser;
	private String generatedSQLForGroup;
	

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFilterChain() {
		return filterChain;
	}

	public void setFilterChain(String filterChain) {
		this.filterChain = filterChain;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public String getTargetClassAttributeName() {
		return targetClassAttributeName;
	}

	public void setTargetClassAttributeName(String targetClassAttributeName) {
		this.targetClassAttributeName = targetClassAttributeName;
	}

	public String getTargetClassAttributeType() {
		return targetClassAttributeType;
	}

	public void setTargetClassAttributeType(String targetClassAttributeType) {
		this.targetClassAttributeType = targetClassAttributeType;
	}

	public String getTargetClassAlias() {
		return targetClassAlias;
	}

	public void setTargetClassAlias(String targetClassAlias) {
		this.targetClassAlias = targetClassAlias;
	}

	public String getTargetClassAttributeAlias() {
		return targetClassAttributeAlias;
	}

	public void setTargetClassAttributeAlias(String targetClassAttributeAlias) {
		this.targetClassAttributeAlias = targetClassAttributeAlias;
	}

	public String getGeneratedSQL() {
		return generatedSQL;
	}

	public void setGeneratedSQL(String generatedSQL) {
		this.generatedSQL = generatedSQL;
	}

	public String getGeneratedSQLForUser() {
		return generatedSQLForUser;
	}

	public void setGeneratedSQLForUser(String generatedSQLForUser) {
		this.generatedSQLForUser = generatedSQLForUser;
	}

	public String getGeneratedSQLForGroup() {
		return generatedSQLForGroup;
	}

	public void setGeneratedSQLForGroup(String generatedSQLForGroup) {
		this.generatedSQLForGroup = generatedSQLForGroup;
	}

	public InstanceLevelForm getInstanceLevelForm() {
		return instanceLevelForm;
	}

	public void setInstanceLevelForm(InstanceLevelForm instanceLevelForm) {
		this.instanceLevelForm = instanceLevelForm;
	}

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String execute() throws Exception
	{
		if(instanceLevelForm == null)
			instanceLevelForm = new InstanceLevelForm();
		updateForm();
		if(operation.equalsIgnoreCase("loadHome"))
				return loadHome();
		else if(operation.equalsIgnoreCase("loadAdd"))
				return loadAdd();
		else if(operation.equalsIgnoreCase("loadSearch"))
				return loadSearch();
		else if(operation.equalsIgnoreCase("LoadSearchResult"))
				return loadSearchResult();
		else if(operation.equalsIgnoreCase("create"))
				return create();
		else if(operation.equalsIgnoreCase("read"))
				return read();
		else if(operation.equalsIgnoreCase("update"))
			return update();
		else if(operation.equalsIgnoreCase("delete"))
			return delete();
		else if(operation.equalsIgnoreCase("search"))
			return search();
		else if(operation.equalsIgnoreCase("loadUpload"))
			return loadUpload();
		else if(operation.equalsIgnoreCase("upload"))
			return upload();
		else
			return "input";
			
	}	
	
	private void updateForm()
	{
		instanceLevelForm.setClassName(className);
		instanceLevelForm.setFilterChain(filterChain);
		instanceLevelForm.setGeneratedSQL(generatedSQL);
		instanceLevelForm.setGeneratedSQLForGroup(generatedSQLForGroup);
		instanceLevelForm.setGeneratedSQLForUser(generatedSQLForUser);
		instanceLevelForm.setTargetClassAlias(targetClassAlias);
		instanceLevelForm.setTargetClassAttributeAlias(targetClassAttributeAlias);
		instanceLevelForm.setTargetClassAttributeName(targetClassAttributeName);
		instanceLevelForm.setTargetClassAttributeType(targetClassAttributeType);
		instanceLevelForm.setTargetClassName(targetClassName);
	}
	
	public String loadHome() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|loadHome|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}

		session.removeAttribute(DisplayConstants.CURRENT_ACTION);
		session.removeAttribute(DisplayConstants.CURRENT_FORM);
		session.removeAttribute(DisplayConstants.SEARCH_RESULT);
		session.removeAttribute(DisplayConstants.CREATE_WORKFLOW);


		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|loadHome|Success|Load the Home Page||");

		return ForwardConstants.LOAD_HOME_SUCCESS;
	}


	public String loadUpload() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|loadUpload|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}

		instanceLevelForm.resetForm();

		session.setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.UPLOAD);
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		session.removeAttribute(DisplayConstants.SEARCH_RESULT);
		session.setAttribute(DisplayConstants.CREATE_WORKFLOW,DisplayConstants.CREATE_WORKFLOW);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|loadUpload|Success|Loading the Upload Page||");

		return ForwardConstants.LOAD_UPLOAD_SUCCESS;
	}


	public String loadAdd() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|loadAdd|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}

		if (session.getAttribute(DisplayConstants.HIBERNATE_SESSIONFACTORY) == null)
		{
			addActionError("Hibernate Session Factory not found. Upload the JAR file to instantiate the session factory" );
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|read|Failure|File not found ||");
			return ForwardConstants.LOAD_ADD_FAILURE;
		}

		instanceLevelForm.resetForm();
		session.setAttribute("classNames",(HibernateHelper.getAllClassNames((SessionFactory)request.getSession().getAttribute(DisplayConstants.HIBERNATE_SESSIONFACTORY))));


		session.setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.ADD);
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		session.removeAttribute(DisplayConstants.SEARCH_RESULT);
		session.setAttribute(DisplayConstants.CREATE_WORKFLOW,DisplayConstants.CREATE_WORKFLOW);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|loadAdd|Success|Loading the Upload Page||");

		return ForwardConstants.LOAD_ADD_SUCCESS;
	}

	public String loadSearch() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|loadSearch|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}

		instanceLevelForm.resetForm();

		session.setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.SEARCH);
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|loadSearch|Success|Loading the Search Page||");

		return ForwardConstants.LOAD_SEARCH_SUCCESS;
	}

	public String loadSearchResult() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|loadSearchResult|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}

		if(session.getAttribute(DisplayConstants.CREATE_WORKFLOW)!=null){
			session.removeAttribute(DisplayConstants.CREATE_WORKFLOW);
			session.removeAttribute(DisplayConstants.SEARCH_RESULT);
			session.removeAttribute(DisplayConstants.ORIGINAL_SEARCH_RESULT);
			return ForwardConstants.LOAD_HOME_SUCCESS;
		}else
		{
			if(session.getAttribute(DisplayConstants.ORIGINAL_SEARCH_RESULT) != null)
			{
				session.setAttribute(DisplayConstants.SEARCH_RESULT,session.getAttribute(DisplayConstants.ORIGINAL_SEARCH_RESULT));
				session.removeAttribute(DisplayConstants.ORIGINAL_SEARCH_RESULT);
			}
		}
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|loadSearchResult|Success|Loading the Search Result Page||");

		return ForwardConstants.LOAD_SEARCH_RESULT_SUCCESS;
	}

	/**
	* Added this method to handle pre-popup search results.
	*/

	public String upload() //throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|upload|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		UserInfoHelper.setUserInfo(((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), session.getId());
		//errors = form.validate(mapping, request);
		File formFileList[] = new File[2];

		File fileArray[] = new File[2];
		int fileArrayCount = 0;

		formFileList[0] = instanceLevelForm.getUploadedFile()[0];
		formFileList[1] = instanceLevelForm.getUploadedFile()[1];


		//Remove previuosly uploaded jars from ClassPathLoader.
		ClassPathLoader.releaseJarsFromClassPath(session);

		List fileList = new ArrayList();
		for (int i = 0 ; i < 2 ; i ++)
		{
			String fileName    = formFileList[i].getName();
			//int fileSize       = formFileList[i].getFileSize();
			if (fileName == null || fileName.length()  == 0)
			{
				if (i == 0 )
				{
					addActionError("File number " + Integer.toString(i+1) + " is required!" );
					if (logDB.isDebugEnabled())
						logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
							"|"+instanceLevelForm.getFormName()+"|read|Failure|File not found ||");
					return ForwardConstants.UPLOAD_FAILURE;
				}
			}
			else
			{
				System.out.println("instanceLevelForm.getUploadContentType " +fileName);
				System.out.println("instanceLevelForm.getUploadContentType " +instanceLevelForm.getUploadedFileContentType());
				if (!(
						((instanceLevelForm.getUploadedFileContentType()[i]).equals("application/x-jar")) || 
						((instanceLevelForm.getUploadedFileContentType()[i]).equals("application/x-zip-compressed")) || 
						((instanceLevelForm.getUploadedFileContentType()[i]).equals("application/octet-stream")) || 
						((instanceLevelForm.getUploadedFileContentType()[i]).equals("application/java-archive"))))
				{
					addActionError("The File number " + Integer.toString(i+1) + " is not a valid Java Archive (JAR) File" );
					if (logDB.isDebugEnabled())
						logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
							"|"+instanceLevelForm.getFormName()+"|read|Failure|Not a valid archive||");
					return ForwardConstants.UPLOAD_FAILURE;
				}

				try
					{
						File tempFile = File.createTempFile(fileName.substring(0,fileName.lastIndexOf('.')), ".jar" );
						System.out.println("tempFile " +tempFile);
						FileInputStream fileInputStream = new FileInputStream(instanceLevelForm.getUploadedFile()[i]);
						FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
						System.out.println("fileOutputStream " +fileOutputStream.toString());
						
						int read = 0;
						byte[] bytes = new byte[1024];
				 
						while ((read = fileInputStream.read(bytes)) != -1) {
							System.out.println("read " +read);
							fileOutputStream.write(bytes, 0, read);
						}
						
						ClassPathLoader.addFile(tempFile);
						fileList.add(tempFile.getPath());
	
						fileArray[fileArrayCount++] = tempFile;
						System.out.println("fileOutputStream " +fileOutputStream.toString());
					}
					catch (FileNotFoundException fileNotFoundException)
					{
						addActionError("Unable to locate the first uploaded file" );
						if (logDB.isDebugEnabled())
							logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
								"|"+instanceLevelForm.getFormName()+"|read|Failure|File not found |" + fileNotFoundException.getMessage() + "|");
						return ForwardConstants.UPLOAD_FAILURE;
					}
					catch (IOException e)
					{
						addActionError("Error in creating the file on the server side! " );
						if (logDB.isDebugEnabled())
							logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
								"|"+instanceLevelForm.getFormName()+"|read|Failure|Error reading the file|" + e.getMessage() + "|");
						return ForwardConstants.UPLOAD_FAILURE;
					}
			}
		}
		String hibernateFileName = instanceLevelForm.getHibernateFileName();
		System.out.println("hibernateFileName " +hibernateFileName);

		try
		{
			SessionFactory sessionFactory = HibernateHelper.loadSessionFactory(hibernateFileName, session);
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|create|Success|Obtaining a Session Factory from the Uploaded Jar ||");
			Session hibernateSession = sessionFactory.openSession();
			if (hibernateSession == null)
			{
				addActionError("Error in loading Hibernate Session Factory! " );
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+instanceLevelForm.getFormName()+"|read|Failure|Error in obtaining the Session||");
				return ForwardConstants.UPLOAD_FAILURE;
			}
			session.setAttribute(DisplayConstants.HIBERNATE_SESSIONFACTORY, sessionFactory);
			session.setAttribute(DisplayConstants.JAR_FILE_LIST, fileList);
			session.setAttribute(DisplayConstants.HIBERNATE_CONFIG_FILE_JAR,fileArray);
		}
		catch (CSConfigurationException e)
		{
			e.printStackTrace();
			addActionError(e.getMessage());
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|read|Failure|Error in obtaining the Session Factory|" + e.getMessage() + "|");
			return ForwardConstants.UPLOAD_FAILURE;
		}
/*
		if(!errors.isEmpty())
		{
			session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|create|Failure|Error validating the "+instanceLevelForm.getFormName()+" object|"
					+instanceLevelForm.toString()+"|");
			return ForwardConstants.UPLOAD_FAILURE;
		}
*/
		addActionMessage("Upload Successful");
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+instanceLevelForm.getFormName()+"|create|Success|Uploading the Jar & Loading the Hibernate |"
				+instanceLevelForm.toString()+"|");
		return ForwardConstants.UPLOAD_SUCCESS;
	}





	public String create() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|create|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		UserInfoHelper.setUserInfo(((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), session.getId());
		try
		{
			List<String> errors = instanceLevelForm.validate();
			if(errors != null)
			{
				for(String error : errors)
					addActionError(error);
				
				session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+instanceLevelForm.getFormName()+"|create|Failure|Error validating the "+instanceLevelForm.getFormName()+" object|"
						+"|");
				return "input";
			}
			UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
			instanceLevelForm.setRequest(request);
			instanceLevelForm.buildDBObject(userProvisioningManager);
			addActionMessage("Add Successful");
		}
		catch (CSException cse)
		{
			addActionError(cse.getMessage());
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|create|Failure|Error Adding the "+instanceLevelForm.getFormName()+" object|"
					+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+instanceLevelForm.getFormName()+"|create|Success|Adding a  new "+instanceLevelForm.getFormName()+" object|"
				+"|");
		return ForwardConstants.CREATE_SUCCESS;
	}


	public String read() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|read|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		if (instanceLevelForm.getPrimaryId() == null || instanceLevelForm.getPrimaryId().equalsIgnoreCase(""))
		{
			addActionError("A record needs to be selected first to view details" );
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|read|Failure|No Primary Id for "+instanceLevelForm.getFormName()+" object||");
			return ForwardConstants.READ_FAILURE;
		}
		try
		{
			UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
			instanceLevelForm.setRequest(request);
			instanceLevelForm.buildDisplayForm(userProvisioningManager);
		}
		catch (CSException cse)
		{
			addActionError(cse.getMessage());
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|read|Failure|Error Reading the "+instanceLevelForm.getFormName()+" object|"
					+instanceLevelForm.toString()+"|"+ cse.getMessage());
		}

		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+instanceLevelForm.getFormName()+"|read|Success|Success reading "+instanceLevelForm.getFormName()+" object|"
				+instanceLevelForm.toString()+"|");
		return ForwardConstants.READ_SUCCESS;

	}

	public String update() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|update|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		UserInfoHelper.setUserInfo(((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), session.getId());
		try
		{
			List<String> errors = instanceLevelForm.validate();
			if(errors != null)
			{
				for(String error : errors)
					addActionError(error);
				session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+instanceLevelForm.getFormName()+"|update|Failure|Error validating the "+instanceLevelForm.getFormName()+" object|"
						+instanceLevelForm.toString()+"|");
				return "input";
			}
			UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
			instanceLevelForm.setRequest(request);
			instanceLevelForm.buildDBObject(userProvisioningManager);
			addActionMessage("Update Successful");
		}
		catch (CSException cse)
		{
			addActionError(cse.getMessage());
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|update|Failure|Error Updating the "+instanceLevelForm.getFormName()+" object|"
					+instanceLevelForm.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+instanceLevelForm.getFormName()+"|update|Success|Updating existing "+instanceLevelForm.getFormName()+" object|"
				+instanceLevelForm.toString()+"|");
		return ForwardConstants.UPDATE_SUCCESS;
	}

	public String delete() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|delete|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		UserInfoHelper.setUserInfo(((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), session.getId());
		try
		{
			UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
			instanceLevelForm.setRequest(request);
			instanceLevelForm.removeDBObject(userProvisioningManager);
			addActionMessage("Delete Successful");
		}
		catch (CSException cse)
		{
			addActionError(cse.getMessage());
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|delete|Failure|Error Deleting the "+instanceLevelForm.getFormName()+" object|"
					+instanceLevelForm.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+instanceLevelForm.getFormName()+"|delete|Success|Success Deleting "+instanceLevelForm.getFormName()+" object|"
				+instanceLevelForm.toString()+"|");
		return ForwardConstants.DELETE_SUCCESS;
	}

	public String search() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+instanceLevelForm.getFormName()+"|search|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		UserInfoHelper.setUserInfo(((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), session.getId());
		try
		{
			UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
			instanceLevelForm.setRequest(request);
			SearchResult searchResult = instanceLevelForm.searchObjects(userProvisioningManager);
			if ( searchResult.getSearchResultObjects() == null || searchResult.getSearchResultObjects().isEmpty())
			{
				addActionError("The search criteria returned zero results");
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+instanceLevelForm.getFormName()+"|search|Failure|No Records found for "+instanceLevelForm.getFormName()+" object|"
						+instanceLevelForm.toString()+"|");
				instanceLevelForm.resetForm();
				return ForwardConstants.SEARCH_FAILURE;
			}
			if (searchResult.getSearchResultMessage() != null && !(searchResult.getSearchResultMessage().trim().equalsIgnoreCase("")))
			{
				addActionMessage(searchResult.getSearchResultMessage());
			}

			if(session.getAttribute(DisplayConstants.SEARCH_RESULT)!=null){

				String str = (String) session.getAttribute(DisplayConstants.CREATE_WORKFLOW);
				if(session.getAttribute(DisplayConstants.CREATE_WORKFLOW)==null){
						if(session.getAttribute(DisplayConstants.ORIGINAL_SEARCH_RESULT)==null){

							session.setAttribute(DisplayConstants.ORIGINAL_SEARCH_RESULT, session.getAttribute(DisplayConstants.SEARCH_RESULT) );
						}
				}
			}
			session.setAttribute(DisplayConstants.SEARCH_RESULT, searchResult);
		}
		catch (CSException cse)
		{
			addActionError(cse.getMessage());
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+instanceLevelForm.getFormName()+"|search|Failure|Error Searching the "+instanceLevelForm.getFormName()+" object|"
					+instanceLevelForm.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, instanceLevelForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+instanceLevelForm.getFormName()+"|search|Success|Success in searching "+instanceLevelForm.getFormName()+" object|"
				+instanceLevelForm.toString()+"|");
		return ForwardConstants.SEARCH_SUCCESS;
	}

}
