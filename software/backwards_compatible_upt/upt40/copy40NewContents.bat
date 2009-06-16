SET TEMP_HOME_40=C:\Vijay\projects\security\software\backwards_compatible_upt\build\CSM_UPT_40_PROD_11022007\upt

mkdir webapp
mkdir webapp\src
copy %TEMP_HOME_40%\webapp\src\ApplicationSecurityConfig.xsd webapp\src
copy %TEMP_HOME_40%\webapp\src\csmupt40.csm.new.hibernate.cfg.xml webapp\src
mkdir webapp\src\gov
mkdir webapp\src\gov\nih
mkdir webapp\src\gov\nih\nci
mkdir webapp\src\gov\nih\nci\security
mkdir webapp\src\gov\nih\nci\security\upt
mkdir webapp\src\gov\nih\nci\security\upt\actions
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\actions\HomeAction.java webapp\src\gov\nih\nci\security\upt\actions
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\actions\LoginAction.java webapp\src\gov\nih\nci\security\upt\actions
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\actions\LogoutAction.java webapp\src\gov\nih\nci\security\upt\actions
mkdir webapp\src\gov\nih\nci\security\upt\constants
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\constants\DisplayConstants.java webapp\src\gov\nih\nci\security\upt\constants
mkdir webapp\src\gov\nih\nci\security\upt\util\
mkdir webapp\src\gov\nih\nci\security\upt\util\properties
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\AuthorizationInformation.java webapp\src\gov\nih\nci\security\upt\util\properties
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\BackwardsCompatibilityInformation.java webapp\src\gov\nih\nci\security\upt\util\properties
mkdir webapp\src\gov\nih\nci\security\upt\util\properties\exceptions
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\exceptions\UPTBackwardsCompatibilityException.java webapp\src\gov\nih\nci\security\upt\util\properties\exceptions
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\exceptions\UPTConfigurationException.java webapp\src\gov\nih\nci\security\upt\util\properties\exceptions
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\FileHelper.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\ObjectFactory.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\UPTApplication.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\UPTProperties.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_40%\webapp\src\gov\nih\nci\security\upt\util\properties\UPTPropertiesTest.java webapp\src\gov\nih\nci\security\upt\util\properties 
copy %TEMP_HOME_40%\webapp\src\upt-beans.xml webapp\src
