package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class HomeAction extends Action
{

	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		sendMail();
		HttpSession session = request.getSession();
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		/*
		 * clear the junk in the session here
		 */
		
	
		
		session.removeAttribute(DisplayConstants.LOGIN_WORKFLOW);
		
		return mapping.findForward(ForwardConstants.FORWARD_HOME);
	}

	public void sendMail() throws java.rmi.RemoteException {
		String msg_text = "Test Msg Text";
		javax.naming.InitialContext ctx = null;
		javax.mail.Session mail_session = null;
		try {
			ctx = new javax.naming.InitialContext();
			mail_session = (javax.mail.Session) ctx.lookup("java:/Mail");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MimeMessage msg = new MimeMessage(mail_session);
	    try {
	    	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("parmarv@mail.nih.gov"));
		    msg.setFrom(new InternetAddress("alice@mail.eedge.com"));
		    msg.setSubject("Important message from eEdge.com");
		    msg.setText(msg_text);
		    Transport.send(msg);
		    /*Store store = mail_session.getStore();
  		    store.connect();
			Folder f;
			f = store.getFolder("Sent");
			if (!f.exists()) f.create(Folder.HOLDS_MESSAGES);
    		f.appendMessages(new Message[] {msg});*/
			

		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 
		  

		  
		  


	    }


}
