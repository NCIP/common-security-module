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
	
	private void modifyFile(File f){
		
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
	         		stbr.append("/**");
	         		stbr.append("\n");
	         		stbr.append("* <!-- LICENSE_TEXT_START -->");
	         		stbr.append("\n");
     				stbr.append("* <!-- LICENSE_TEXT_END -->");
     				stbr.append("\n");
					stbr.append("*/");
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
	
	
	public static void main(String[] args){
		LicenseTagInsertor lit = new LicenseTagInsertor();
		lit.insertLicenseTag("C:/CSMTest/src");
	}

}


