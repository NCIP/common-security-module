/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MethodSignature {
  private String statementWithSession;
  private String statementWithoutSession;
  private String name;
  private String origSignature;
  private String signatureWithSessionKey;
  private String retType;
  private String methodCallWithSession;
  private String methodCallWithoutSession;
  
  public void setStatementWithSession(String statementWithSession){
  	this.statementWithSession=statementWithSession;
  }
  public String getStatementWithSession(){
  	return statementWithSession;
  }
  public void setStatementWithoutSession(String statementWithoutSession){
  	this.statementWithoutSession=statementWithoutSession;
  }
  public String getStatementWithoutSession(){
  	return statementWithoutSession;
  }
  public void setName(String name){
  	this.name=name;
  }
  public String getName(){
  	return name;
  }
  public void setOrigSignature(String origSignature){
  	this.origSignature=origSignature;
  }
  public String getOrigSignature(){
  	return origSignature;
  }
  public void setSignatureWithSessionKey(String signatureWithSessionKey){
  	this.signatureWithSessionKey=signatureWithSessionKey;
  }
  public String getSignatureWithSessionKey(){
  	return signatureWithSessionKey;
  }
  public void setRetType(String retType){
  	this.retType=retType;
  }
  public String getRetType(){
  	return this.retType;
  }
  public void setMethodCallWithSession(String methodCallWithSession){
  	this.methodCallWithSession = methodCallWithSession;
  }
  public String getMethodCallWithSession(){
  	return this.methodCallWithSession;
  }
  public void setMethodCallWithoutSession(String methodCallWithoutSession){
  	this.methodCallWithoutSession= methodCallWithoutSession;
  }
  public String getMethodCallWithoutSession(){
  	return this.methodCallWithoutSession;
  }
}
