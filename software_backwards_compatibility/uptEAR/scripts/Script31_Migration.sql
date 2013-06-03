/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

﻿

USE csm_dev_bkwrdscmptbl_3_1;


ALTER TABLE csm_application ADD COLUMN   DATABASE_URL VARCHAR(100);
ALTER TABLE csm_application ADD COLUMN 	DATABASE_USER_NAME VARCHAR(100);
ALTER TABLE csm_application ADD COLUMN 	DATABASE_PASSWORD VARCHAR(100);
ALTER TABLE csm_application ADD COLUMN 	DATABASE_DIALECT VARCHAR(100);
ALTER TABLE csm_application ADD COLUMN 	DATABASE_DRIVER VARCHAR(100);
