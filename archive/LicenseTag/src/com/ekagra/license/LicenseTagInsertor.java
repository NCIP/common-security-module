/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Mar 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.license;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LicenseTagInsertor {
	
	public void insertLicenseTag(String sourceRoot){
		ArrayList al = this.getAllFiles(new File(sourceRoot));
		for(int i=0;i<al.size();i++){
			File f = (File)al.get(i);
			this.modifyFile(f);
		}
		
		System.out.println("Total Files chnaged:"+al.size());
	}
	
	private ArrayList getAllFiles(File folder){
		ArrayList allFiles = new ArrayList();
		ArrayList allFolders = new ArrayList();
		boolean done = false;
		FileFilter fileFilter =(FileFilter) new JavaFileFilter();
		File[] files = folder.listFiles(fileFilter);
		while(!done){
					
			for(int i=0;i<files.length;i++){
				File ff = files[i];
				if(ff.isFile()){
					System.out.println("Adding:"+ff.getAbsolutePath());
					allFiles.add(ff);
				}else{
					allFolders.add(ff);
					done =false;
				}
			}
			if(allFolders.size()!=0){
				File fol = (File)allFolders.get(0);
				files = fol.listFiles(fileFilter);
				allFolders.remove(fol);
			}else{
				done = true;
			}
		}
		return allFiles;
	}
	
	private void modifyFileXX(File f){
		
		//System.out.println(f.getPath());
		StringBuffer stbr = new StringBuffer();
		try
	      {  
	         BufferedReader in
	            = new BufferedReader(new FileReader(f));
	         String s;
	         while ((s = in.readLine()) != null){
	         	stbr.append(s).append("\n");
	         	if(s.startsWith("package")){
	         		stbr.append("\n");
	         		stbr.append("/**\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *<!-- LICENSE_TEXT_START -->\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *The NCICB Common Security Module (CSM) Software License, Version 1.0 Copyright\n");
	         		stbr.append(" *2004-2005 Ekagra Software Technologies Limited ('Ekagra')\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *Copyright Notice.  The software subject to this notice and license includes both\n");
	         		stbr.append(" *human readable source code form and machine readable, binary, object code form\n");
	         		stbr.append(" *(the 'CSM Software').  The CSM Software was developed in conjunction with the\n");
	         		stbr.append(" *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To\n");
	         		stbr.append(" *the extent government employees are authors, any rights in such works shall be\n");
	         		stbr.append(" *subject to Title 17 of the United States Code, section 105.    \n");
	         		stbr.append(" *\n");
	         		stbr.append(" *This CSM Software License (the 'License') is between NCI and You.  'You (or\n");
	         		stbr.append(" *'Your') shall mean a person or an entity, and all other entities that control,\n");
	         		stbr.append(" *are controlled by, or are under common control with the entity.  'Control' for\n");
	         		stbr.append(" *purposes of this definition means (i) the direct or indirect power to cause the\n");
	         		stbr.append(" *direction or management of such entity, whether by contract or otherwise, or\n");
	         		stbr.append(" *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or\n");
	         		stbr.append(" *(iii) beneficial ownership of such entity.  \n");
	         		stbr.append(" *\n");
	         		stbr.append(" *This License is granted provided that You agree to the conditions described\n");
	         		stbr.append(" *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,\n");
	         		stbr.append(" *no-charge, irrevocable, transferable and royalty-free right and license in its\n");
	         		stbr.append(" *rights in the CSM Software to (i) use, install, access, operate, execute, copy,\n");
	         		stbr.append(" *modify, translate, market, publicly display, publicly perform, and prepare\n");
	         		stbr.append(" *derivative works of the CSM Software; (ii) distribute and have distributed to\n");
	         		stbr.append(" *and by third parties the CSM Software and any modifications and derivative works\n");
	         		stbr.append(" *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to\n");
	         		stbr.append(" *third parties, including the right to license such rights to further third\n");
	         		stbr.append(" *parties.  For sake of clarity, and not by way of limitation, NCI shall have no\n");
	         		stbr.append(" *right of accounting or right of payment from You or Your sublicensees for the\n");
	         		stbr.append(" *rights granted under this License.  This License is granted at no charge to You.\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *1.	Your redistributions of the source code for the Software must retain the\n");
	         		stbr.append(" *above copyright notice, this list of conditions and the disclaimer and\n");
	         		stbr.append(" *limitation of liability of Article 6 below.  Your redistributions in object code\n");
	         		stbr.append(" *form must reproduce the above copyright notice, this list of conditions and the\n");
	         		stbr.append(" *disclaimer of Article 6 in the documentation and/or other materials provided\n");
	         		stbr.append(" *with the distribution, if any.\n");
	         		stbr.append(" *2.	Your end-user documentation included with the redistribution, if any, must\n");
	         		stbr.append(" *include the following acknowledgment: 'This product includes software developed\n");
	         		stbr.append(" *by Ekagra and the National Cancer Institute.'  If You do not include such\n");
	         		stbr.append(" *end-user documentation, You shall include this acknowledgment in the Software\n");
	         		stbr.append(" *itself, wherever such third-party acknowledgments normally appear.\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra\n");
	         		stbr.append(" *Software Technologies Limited' and 'Ekagra' to endorse or promote products\n");
	         		stbr.append(" *derived from this Software.  This License does not authorize You to use any\n");
	         		stbr.append(" *trademarks, service marks, trade names, logos or product names of either NCI or\n");
	         		stbr.append(" *Ekagra, except as required to comply with the terms of this License.\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *4.	For sake of clarity, and not by way of limitation, You may incorporate this\n");
	         		stbr.append(" *Software into Your proprietary programs and into any third party proprietary\n");
	         		stbr.append(" *programs.  However, if You incorporate the Software into third party proprietary\n");
	         		stbr.append(" *programs, You agree that You are solely responsible for obtaining any permission\n");
	         		stbr.append(" *from such third parties required to incorporate the Software into such third\n");
	         		stbr.append(" *party proprietary programs and for informing Your sublicensees, including\n");
	         		stbr.append(" *without limitation Your end-users, of their obligation to secure any required\n");
	         		stbr.append(" *permissions from such third parties before incorporating the Software into such\n");
	         		stbr.append(" *third party proprietary software programs.  In the event that You fail to obtain\n");
	         		stbr.append(" *such permissions, You agree to indemnify NCI for any claims against NCI by such\n");
	         		stbr.append(" *third parties, except to the extent prohibited by law, resulting from Your\n");
	         		stbr.append(" *failure to obtain such permissions.\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *5.	For sake of clarity, and not by way of limitation, You may add Your own\n");
	         		stbr.append(" *copyright statement to Your modifications and to the derivative works, and You\n");
	         		stbr.append(" *may provide additional or different license terms and conditions in Your\n");
	         		stbr.append(" *sublicenses of modifications of the Software, or any derivative works of the\n");
	         		stbr.append(" *Software as a whole, provided Your use, reproduction, and distribution of the\n");
	         		stbr.append(" *Work otherwise complies with the conditions stated in this License.\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,\n");
	         		stbr.append(" *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,\n");
	         		stbr.append(" *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO\n");
	         		stbr.append(" *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE\n");
	         		stbr.append(" *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL\n");
	         		stbr.append(" *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR\n");
	         		stbr.append(" *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER\n");
	         		stbr.append(" *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR\n");
	         		stbr.append(" *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF\n");
	         		stbr.append(" *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
	         		stbr.append(" *\n");
	         		stbr.append(" *<!-- LICENSE_TEXT_END -->\n");
     				stbr.append(" *\n");
					stbr.append(" */\n");
					stbr.append("\n");

	         	}
	         }
	         	
	         System.out.println(stbr.toString());
	         in.close();
	         String path = f.getAbsolutePath();
	         f.delete();
	         FileWriter fWriter = new FileWriter(path);
	         fWriter.write(stbr.toString());
	         fWriter.close();
	      }
	      catch (IOException e)
	      {  
	      }
	}
	
	private void modifyFile(File f){
		
		//System.out.println(f.getPath());
		StringBuffer stbr = new StringBuffer();
		try
	      {  
	         BufferedReader in
	            = new BufferedReader(new FileReader(f));
	         String s;
	         while ((s = in.readLine()) != null){
	         	
	         	if(s.indexOf("Software License, Version 1.0")>-1){
	         		System.out.println("Line found:"+s);
	         		s=StringUtils.replaceInString(s,"Software License, Version 1.0","Software License, Version 3.0");
	         		System.out.println("Line changed:"+s);
	         	}
	         	stbr.append(s).append("\n");
	         }
	         	
	         //System.out.println(stbr.toString());
	         in.close();
	         String path = f.getAbsolutePath();
	         f.delete();
	         FileWriter fWriter = new FileWriter(path);
	         fWriter.write(stbr.toString());
	         fWriter.close();
	      }
	      catch (IOException e)
	      {  
	      }
	}
	
	
	public static void main(String[] args){
		LicenseTagInsertor lit = new LicenseTagInsertor();
		lit.insertLicenseTag("C:/eclipse/workspace/api/src");
	}

}


