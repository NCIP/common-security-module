/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

DROP TABLE IF EXISTS CARD
;

DROP TABLE IF EXISTS HAND
;

DROP TABLE IF EXISTS DECK
;

DROP TABLE IF EXISTS SUIT
;

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


insert into DECK(ID,NAME)
values (1,"My Deck 1");

Insert into SUIT
   (ID, NAME, DECK_ID)
 Values
   (1, "Spade", 1);
Insert into SUIT
   (ID, NAME, DECK_ID)
 Values
   (2, "Flower", 1);
Insert into SUIT
   (ID, NAME, DECK_ID)
 Values
   (3, "Diamond", 1);
Insert into SUIT
   (ID, NAME, DECK_ID)
 Values
   (4, "Heart", 1);
COMMIT;



Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (53, "Joker", NULL, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (1, "Ace", 1, "My Ace");
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (2, "Two", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (3, "Three", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (4, "Four", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (5, "Five", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (6, "Six", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (7, "Seven", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (8, "Eight", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (9, "Nine", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (10, "Ten", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (11, "Jack", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (12, "Queen", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (13, "King", 1, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (14, "Ace", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (15, "Two", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (16, "Three", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (17, "Four", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (18, "Five", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (19, "Six", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (20, "Seven", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (21, "Eight", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (22, "Nine", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (23, "Ten", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (24, "Jack", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (25, "Queen", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (26, "King", 2, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (27, "Ace", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (28, "Two", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (29, "Three", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (30, "Four", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (31, "Five", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (32, "Six", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (33, "Seven", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (34, "Eight", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (35, "Nine", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (36, "Ten", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (37, "Jack", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (38, "Queen", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (39, "King", 3, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (40, "Ace", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (41, "Two", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (42, "Three", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (43, "Four", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (44, "Five", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (45, "Six", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (46, "Seven", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (47, "Eight", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (48, "Nine", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (49, "Ten", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (50, "Jack", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (51, "Queen", 4, NULL);
Insert into CARD
   (ID, NAME, SUIT_ID, IMAGE)
 Values
   (52, "King", 4, NULL);
COMMIT;


