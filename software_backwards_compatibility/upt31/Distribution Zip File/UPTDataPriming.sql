/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

insert into application(APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
values ("<<upt_context_name>>","UPT Super Admin Application",0,1,sysdate());

insert into user (LOGIN_NAME,FIRST_NAME,LAST_NAME,UPDATE_DATE)
values ("<<super_admin_login_id>>","<<super_admin_first_name >> ","<<super_admin_last_name >> ",sysdate());
 
insert into protection_element(PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values("<<upt_context_name>>","UPT Super Admin Application","<<upt_context_name>>",1,sysdate());

	 
insert into user_protection_element(PROTECTION_ELEMENT_ID,USER_ID,UPDATE_DATE)
values(1,1,sysdate());
