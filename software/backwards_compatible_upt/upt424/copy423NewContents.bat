SET TEMP_HOME_42=C:\Vijay\projects\security\software\backwards_compatible_upt\build\upt423

mkdir webapp
mkdir webapp\public_html
mkdir webapp\public_html\WEB-INF
mkdir webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\acegi-security-1.0.3.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\antlr-2.7.6.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\asm-1.5.3.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\asm-attrs-1.5.3.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\c3p0-0.9.0.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\cglib-2.1_3.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\clm-4.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\commons-collections-2.1.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\commons-dbcp-1.2.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\commons-lang-1.0.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\commons-logging-1.0.4.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\csmapi-4.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\dom4j-1.6.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\dwr-2.0.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\ehcache-1.2.4.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\ejb3-persistence-1.0.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\hibernate-3.2.0.ga.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\hibernate-annotations-1.0.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\jdom-1.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\jta-1.0.1B.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\log4j-1.2.9.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\mysql-connector-java-3.1.11.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\servlet-2.3.1.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\spring-1.2.8.jar webapp\public_html\WEB-INF\lib
copy %TEMP_HOME_42%\webapp\public_html\WEB-INF\lib\struts-1.1.jar webapp\public_html\WEB-INF\lib
mkdir webapp\src
copy %TEMP_HOME_42%\webapp\src\ApplicationSecurityConfig.xsd webapp\src
copy %TEMP_HOME_42%\webapp\src\csmupt42.csm.new.hibernate.cfg.xml webapp\src
mkdir webapp\src\gov
mkdir webapp\src\gov\nih
mkdir webapp\src\gov\nih\nci
mkdir webapp\src\gov\nih\nci\security
mkdir webapp\src\gov\nih\nci\security\upt
mkdir webapp\src\gov\nih\nci\security\upt\actions

copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\actions\HomeAction.java webapp\src\gov\nih\nci\security\upt\actions
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\actions\LoginAction.java webapp\src\gov\nih\nci\security\upt\actions
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\actions\LogoutAction.java webapp\src\gov\nih\nci\security\upt\actions
mkdir webapp\src\gov\nih\nci\security\upt\constants
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\constants\DisplayConstants.java webapp\src\gov\nih\nci\security\upt\constants

mkdir webapp\src\gov\nih\nci\security\upt\util 
mkdir webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\AuthorizationInformation.java webapp\src\gov\nih\nci\security\upt\util\properties
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\BackwardsCompatibilityInformation.java webapp\src\gov\nih\nci\security\upt\util\properties
mkdir webapp\src\gov\nih\nci\security\upt\util\properties\exceptions
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\exceptions\UPTBackwardsCompatibilityException.java webapp\src\gov\nih\nci\security\upt\util\properties\exceptions
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\exceptions\UPTConfigurationException.java webapp\src\gov\nih\nci\security\upt\util\properties\exceptions
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\FileHelper.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\ObjectFactory.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\UPTApplication.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\UPTProperties.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_42%\webapp\src\gov\nih\nci\security\upt\util\properties\UPTPropertiesTest.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_42%\webapp\src\upt-beans.xml webapp\src
