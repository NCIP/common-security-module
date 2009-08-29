
DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS HAND;
DROP TABLE IF EXISTS DECK;
DROP TABLE IF EXISTS SUIT;

create table if not exists integers(i int unsigned not null);
create table if not exists numbers(i int unsigned not null);


CREATE TABLE DECK ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID)
)Type=InnoDB
;
CREATE TABLE SUIT ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	DECK_ID BIGINT, 
	PRIMARY KEY(ID)
)Type=InnoDB
;
CREATE TABLE CARD ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	SUIT_ID BIGINT, 
	IMAGE VARCHAR(50),
	PRIMARY KEY(ID)
)Type=InnoDB
;
CREATE TABLE HAND ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID)
)Type=InnoDB
;

ALTER TABLE SUIT ADD CONSTRAINT FK_SUIT_DECK
FOREIGN KEY (DECK_ID) REFERENCES DECK(ID)
ON DELETE NO ACTION
;
ALTER TABLE CARD ADD CONSTRAINT FK_CARD_SUIT
FOREIGN KEY (SUIT_ID) REFERENCES SUIT(ID)
ON DELETE NO ACTION
;

insert into integers(i) values (0), (1), (2), (3), (4), (5), (6), (7), (8), (9);

insert into numbers
select (tenthousands.i * 1000) +(thousands.i * 1000) + (hundreds.i * 100) + (tens.i * 10) + units.i as iii
from integers as units
    cross join integers as tens
    cross join integers as hundreds
    cross join integers as thousands
		cross join integers as tenthousands;
		
insert into Card (name) select concat('Card',  i) from numbers where i < 40000;
insert into Suit (name) select concat('Suit',  i) from numbers where i < 10;
insert into Deck (name) select concat('Deck',  i) from numbers where i < 10;


insert into csm_user (user_id,login_name,first_name,last_name) select i+2, concat('User',  i),'a','b' from numbers where i < 10;
insert into csm_group (group_id,group_name,application_id) select i+1, concat('Group',  i),2 from numbers where i < 10;

insert into csm_protection_element(protection_element_id,protection_element_name,object_id,attribute,application_id,attribute_value) 
	select id+2,'pe_name', 'test.gov.nih.nci.security.instancelevel.domainobjects.Card','id',2,id from Card;
	
insert into csm_protection_group(protection_group_id,protection_group_name,application_id,LARGE_ELEMENT_COUNT_FLAG) 
	select protection_element_id,concat('PE(',protection_element_id,') group'),2,0 from csm_protection_element where object_id = 'test.gov.nih.nci.security.instancelevel.domainobjects.Card' order by protection_element_id;

insert into csm_pg_pe (protection_group_id,protection_element_id) 
	select protection_element_id,protection_element_id from csm_protection_element where object_id = 'test.gov.nih.nci.security.instancelevel.domainobjects.Card' order by protection_element_id;

insert into csm_role(role_name,application_id,active_flag) value ('READ',2,1);

insert into csm_user_group_role_pg (user_id,protection_group_id, role_id ) 
	select u.user_id,pg.protection_group_id, 1 from csm_user u, csm_protection_group pg ORDER By rand();

insert into csm_user_group_role_pg (group_id,protection_group_id, role_id ) 
	select g.group_id,pg.protection_group_id, 1 from csm_group g, csm_protection_group pg ORDER By rand();

COMMIT;

