/*
Navicat Oracle Data Transfer
Oracle Client Version : 12.1.0.1.0

Source Server         : XE
Source Server Version : 100200
Source Host           : localhost:1521
Source Schema         : KHARJ

Target Server Type    : ORACLE
Target Server Version : 100200
File Encoding         : 65001

Date: 2014-06-20 07:22:25
*/


-- ----------------------------
-- Table structure for "KHARJ"."CITIES"
-- ----------------------------
DROP TABLE "KHARJ"."CITIES";
CREATE TABLE "KHARJ"."CITIES" (
"ID" NUMBER(10) NOT NULL ,
"ADDRESS" VARCHAR2(255 CHAR) NULL ,
"NAME" VARCHAR2(255 CHAR) NOT NULL ,
"PHONE" VARCHAR2(255 CHAR) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of CITIES
-- ----------------------------
INSERT INTO "KHARJ"."CITIES" VALUES ('1', null, 'Kyiv', '044 1234567');
INSERT INTO "KHARJ"."CITIES" VALUES ('2', null, 'Kharkiv', '057 1234567');
INSERT INTO "KHARJ"."CITIES" VALUES ('3', null, 'Odessa', '001 1234567');
INSERT INTO "KHARJ"."CITIES" VALUES ('4', null, 'Poltava', '002 1234567');

-- ----------------------------
-- Table structure for "KHARJ"."CLIENTS"
-- ----------------------------
DROP TABLE "KHARJ"."CLIENTS";
CREATE TABLE "KHARJ"."CLIENTS" (
"ID" NUMBER(10) NOT NULL ,
"ADDRESS" VARCHAR2(255 CHAR) NULL ,
"EMAIL" VARCHAR2(255 CHAR) NULL ,
"FIRST_NAME" VARCHAR2(255 CHAR) NULL ,
"LAST_NAME" VARCHAR2(255 CHAR) NULL ,
"MIDDLE_NAME" VARCHAR2(255 CHAR) NULL ,
"PHONE" VARCHAR2(255 CHAR) NULL ,
"REGISTRATION_DATE" DATE NULL ,
"CITY" NUMBER(10) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of CLIENTS
-- ----------------------------
INSERT INTO "KHARJ"."CLIENTS" VALUES ('5', 'Kharkiv addr', '1995kenny@gmail.com', 'Evgen', 'Kharchenko', null, '+380939415776', TO_DATE('2014-06-18 02:46:36', 'YYYY-MM-DD HH24:MI:SS'), '2');
INSERT INTO "KHARJ"."CLIENTS" VALUES ('6', 'Kharkiv addr 2', 'igormail', 'Igor', 'Zhylin', null, '1234', TO_DATE('2014-06-18 02:46:36', 'YYYY-MM-DD HH24:MI:SS'), '2');
INSERT INTO "KHARJ"."CLIENTS" VALUES ('7', 'Kyiv addr', 'ivanmail', 'Ivan', 'Dulin', null, '123456', TO_DATE('2014-06-18 02:46:36', 'YYYY-MM-DD HH24:MI:SS'), '1');

-- ----------------------------
-- Table structure for "KHARJ"."PARCEL_TYPES"
-- ----------------------------
DROP TABLE "KHARJ"."PARCEL_TYPES";
CREATE TABLE "KHARJ"."PARCEL_TYPES" (
"ID" NUMBER(10) NOT NULL ,
"MAX_WEIGHT" FLOAT NOT NULL ,
"MIN_WEIGHT" FLOAT NOT NULL ,
"NAME" VARCHAR2(255 CHAR) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of PARCEL_TYPES
-- ----------------------------
INSERT INTO "KHARJ"."PARCEL_TYPES" VALUES ('18', '50', '1', 'Packet');
INSERT INTO "KHARJ"."PARCEL_TYPES" VALUES ('19', '500', '5', 'Box');
INSERT INTO "KHARJ"."PARCEL_TYPES" VALUES ('20', '3000', '500', 'Container');

-- ----------------------------
-- Table structure for "KHARJ"."PARCELS"
-- ----------------------------
DROP TABLE "KHARJ"."PARCELS";
CREATE TABLE "KHARJ"."PARCELS" (
"ID" NUMBER(10) NOT NULL ,
"ACCEPT_DATE" DATE NULL ,
"DESCRIPTION" VARCHAR2(255 CHAR) NULL ,
"PRICE" FLOAT NULL ,
"WEIGHT" FLOAT NULL ,
"CITY_FROM" NUMBER(10) NULL ,
"CITY_TO" NUMBER(10) NULL ,
"CLIENT_FROM" NUMBER(10) NULL ,
"CLIENT_TO" NUMBER(10) NULL ,
"PARCEL_TYPE" NUMBER(10) NULL ,
"ROUTE" NUMBER(10) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of PARCELS
-- ----------------------------
INSERT INTO "KHARJ"."PARCELS" VALUES ('66', TO_DATE('2014-06-20 05:07:36', 'YYYY-MM-DD HH24:MI:SS'), null, null, '0', null, null, null, null, null, null);
INSERT INTO "KHARJ"."PARCELS" VALUES ('35', TO_DATE('2014-06-18 02:46:36', 'YYYY-MM-DD HH24:MI:SS'), 'happy packet', null, '2.5', '1', '2', '5', '7', '18', null);
INSERT INTO "KHARJ"."PARCELS" VALUES ('36', TO_DATE('2014-06-18 02:46:36', 'YYYY-MM-DD HH24:MI:SS'), 'mega box', null, '8', '1', '2', '7', '6', '19', null);

-- ----------------------------
-- Table structure for "KHARJ"."RATES"
-- ----------------------------
DROP TABLE "KHARJ"."RATES";
CREATE TABLE "KHARJ"."RATES" (
"ID" NUMBER(10) NOT NULL ,
"PRICE_MIN" FLOAT NOT NULL ,
"PRICE_PER_KG" FLOAT NOT NULL ,
"CITY_FROM" NUMBER(10) NOT NULL ,
"CITY_TO" NUMBER(10) NOT NULL ,
"PARCEL_TYPE" NUMBER(10) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of RATES
-- ----------------------------
INSERT INTO "KHARJ"."RATES" VALUES ('26', '5', '3', '1', '2', '18');
INSERT INTO "KHARJ"."RATES" VALUES ('27', '6', '2.5', '1', '2', '19');
INSERT INTO "KHARJ"."RATES" VALUES ('28', '3000', '1.8', '1', '2', '20');
INSERT INTO "KHARJ"."RATES" VALUES ('29', '5', '3', '1', '3', '18');
INSERT INTO "KHARJ"."RATES" VALUES ('30', '6', '2.5', '1', '3', '19');
INSERT INTO "KHARJ"."RATES" VALUES ('31', '3000', '1.8', '1', '3', '20');
INSERT INTO "KHARJ"."RATES" VALUES ('32', '7', '3', '2', '3', '18');
INSERT INTO "KHARJ"."RATES" VALUES ('33', '9', '2.5', '2', '3', '19');
INSERT INTO "KHARJ"."RATES" VALUES ('34', '4500', '1.8', '2', '3', '20');

-- ----------------------------
-- Table structure for "KHARJ"."ROUTE_MAPS"
-- ----------------------------
DROP TABLE "KHARJ"."ROUTE_MAPS";
CREATE TABLE "KHARJ"."ROUTE_MAPS" (
"ID" NUMBER(10) NOT NULL ,
"NUM" NUMBER(10) NOT NULL ,
"STOP_DATE" DATE NULL ,
"CITY" NUMBER(10) NOT NULL ,
"ROUTE" NUMBER(10) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of ROUTE_MAPS
-- ----------------------------
INSERT INTO "KHARJ"."ROUTE_MAPS" VALUES ('13', '0', TO_DATE('2014-05-17 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), '2', '11');
INSERT INTO "KHARJ"."ROUTE_MAPS" VALUES ('14', '1', TO_DATE('2014-05-17 14:04:50', 'YYYY-MM-DD HH24:MI:SS'), '4', '11');
INSERT INTO "KHARJ"."ROUTE_MAPS" VALUES ('15', '2', TO_DATE('2014-05-17 16:30:04', 'YYYY-MM-DD HH24:MI:SS'), '1', '11');
INSERT INTO "KHARJ"."ROUTE_MAPS" VALUES ('16', '0', TO_DATE('2014-05-16 23:50:00', 'YYYY-MM-DD HH24:MI:SS'), '1', '12');
INSERT INTO "KHARJ"."ROUTE_MAPS" VALUES ('17', '1', TO_DATE('2014-05-17 15:06:39', 'YYYY-MM-DD HH24:MI:SS'), '3', '12');

-- ----------------------------
-- Table structure for "KHARJ"."ROUTES"
-- ----------------------------
DROP TABLE "KHARJ"."ROUTES";
CREATE TABLE "KHARJ"."ROUTES" (
"ID" NUMBER(10) NOT NULL ,
"VEHICLE" NUMBER(10) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of ROUTES
-- ----------------------------
INSERT INTO "KHARJ"."ROUTES" VALUES ('11', '8');
INSERT INTO "KHARJ"."ROUTES" VALUES ('12', '10');

-- ----------------------------
-- Table structure for "KHARJ"."SCHEDULES"
-- ----------------------------
DROP TABLE "KHARJ"."SCHEDULES";
CREATE TABLE "KHARJ"."SCHEDULES" (
"ID" NUMBER(10) NOT NULL ,
"CLOSE_TIME" TIMESTAMP(6)  NULL ,
"DAY_OF_WEEK" NUMBER(10) NULL ,
"OPEN_TIME" TIMESTAMP(6)  NULL ,
"CITY" NUMBER(10) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SCHEDULES
-- ----------------------------
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('37', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('38', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('39', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('40', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('41', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('42', TO_TIMESTAMP(' 1970-01-01 18:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '5', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('43', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('44', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('45', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('46', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('47', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('48', TO_TIMESTAMP(' 1970-01-01 18:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '5', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('49', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('50', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('51', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('52', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('53', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('54', TO_TIMESTAMP(' 1970-01-01 18:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '5', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('55', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('56', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('57', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '2', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('58', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '3', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('59', TO_TIMESTAMP(' 1970-01-01 20:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4');
INSERT INTO "KHARJ"."SCHEDULES" VALUES ('60', TO_TIMESTAMP(' 1970-01-01 18:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '5', TO_TIMESTAMP(' 1970-01-01 09:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '4');

-- ----------------------------
-- Table structure for "KHARJ"."USERS"
-- ----------------------------
DROP TABLE "KHARJ"."USERS";
CREATE TABLE "KHARJ"."USERS" (
"ID" NUMBER(10) NOT NULL ,
"IS_ADMIN" NUMBER(1) NULL ,
"LOGIN" VARCHAR2(255 CHAR) NULL ,
"PASSWORD" VARCHAR2(255 CHAR) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of USERS
-- ----------------------------
INSERT INTO "KHARJ"."USERS" VALUES ('61', '1', 'admin', 'admin');
INSERT INTO "KHARJ"."USERS" VALUES ('62', '0', 'user', 'user');
INSERT INTO "KHARJ"."USERS" VALUES ('63', '0', 'babka', '123');
INSERT INTO "KHARJ"."USERS" VALUES ('64', '0', 'babka3', '123213');
INSERT INTO "KHARJ"."USERS" VALUES ('65', '0', 'bob', 'loo');

-- ----------------------------
-- Table structure for "KHARJ"."VEHICLE_MAXLOADS"
-- ----------------------------
DROP TABLE "KHARJ"."VEHICLE_MAXLOADS";
CREATE TABLE "KHARJ"."VEHICLE_MAXLOADS" (
"ID" NUMBER(10) NOT NULL ,
"MAX_COUNT" NUMBER(10) NULL ,
"MAX_WEIGHT" FLOAT NOT NULL ,
"PARCEL_TYPE" NUMBER(10) NOT NULL ,
"VEHICLE" NUMBER(10) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of VEHICLE_MAXLOADS
-- ----------------------------
INSERT INTO "KHARJ"."VEHICLE_MAXLOADS" VALUES ('21', '2', '6000', '20', '10');
INSERT INTO "KHARJ"."VEHICLE_MAXLOADS" VALUES ('22', '200', '2000', '18', '8');
INSERT INTO "KHARJ"."VEHICLE_MAXLOADS" VALUES ('23', '50', '200', '19', '8');
INSERT INTO "KHARJ"."VEHICLE_MAXLOADS" VALUES ('24', '100', '2000', '18', '9');
INSERT INTO "KHARJ"."VEHICLE_MAXLOADS" VALUES ('25', '20', '1000', '19', '9');

-- ----------------------------
-- Table structure for "KHARJ"."VEHICLES"
-- ----------------------------
DROP TABLE "KHARJ"."VEHICLES";
CREATE TABLE "KHARJ"."VEHICLES" (
"ID" NUMBER(10) NOT NULL ,
"MAX_LOAD" FLOAT NOT NULL ,
"MODEL" VARCHAR2(255 CHAR) NULL ,
"REGISTRATION_NUMBER" VARCHAR2(255 CHAR) NULL ,
"HOME_CITY" NUMBER(10) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of VEHICLES
-- ----------------------------
INSERT INTO "KHARJ"."VEHICLES" VALUES ('8', '4000', 'KAMAZ', 'AX1454AH', '2');
INSERT INTO "KHARJ"."VEHICLES" VALUES ('9', '3000', 'Mercedes', 'II7777BP', '1');
INSERT INTO "KHARJ"."VEHICLES" VALUES ('10', '6000', 'VOLVO', 'AB1234AA', '1');

-- ----------------------------
-- Procedure structure for "KHARJ"."TEXTSCHEDULE"
-- ----------------------------
CREATE OR REPLACE PROCEDURE "KHARJ"."TEXTSCHEDULE"(CITY_ID IN NUMBER)
IS

BEGIN

	SELECT name FROM cities
	WHERE id = 1;


END TEXTSCHEDULE;
/

-- ----------------------------
-- Sequence structure for "KHARJ"."CITIES_SEQ"
-- ----------------------------
DROP SEQUENCE "KHARJ"."CITIES_SEQ";
CREATE SEQUENCE "KHARJ"."CITIES_SEQ"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for "KHARJ"."CLIENTS_SEQ"
-- ----------------------------
DROP SEQUENCE "KHARJ"."CLIENTS_SEQ";
CREATE SEQUENCE "KHARJ"."CLIENTS_SEQ"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for "KHARJ"."HIBERNATE_SEQUENCE"
-- ----------------------------
DROP SEQUENCE "KHARJ"."HIBERNATE_SEQUENCE";
CREATE SEQUENCE "KHARJ"."HIBERNATE_SEQUENCE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 999999999999999999999999999
 START WITH 81
 CACHE 20;

-- ----------------------------
-- Sequence structure for "KHARJ"."PARCELS_SEQ"
-- ----------------------------
DROP SEQUENCE "KHARJ"."PARCELS_SEQ";
CREATE SEQUENCE "KHARJ"."PARCELS_SEQ"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for "KHARJ"."ROUTES_SEQ"
-- ----------------------------
DROP SEQUENCE "KHARJ"."ROUTES_SEQ";
CREATE SEQUENCE "KHARJ"."ROUTES_SEQ"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for "KHARJ"."VEHICLES_SEQ"
-- ----------------------------
DROP SEQUENCE "KHARJ"."VEHICLES_SEQ";
CREATE SEQUENCE "KHARJ"."VEHICLES_SEQ"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 999999999999999999999999999
 START WITH 21
 CACHE 20;

-- ----------------------------
-- Indexes structure for table CITIES
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."CITIES"
-- ----------------------------
ALTER TABLE "KHARJ"."CITIES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."CITIES" ADD CHECK ("NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."CITIES"
-- ----------------------------
ALTER TABLE "KHARJ"."CITIES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CLIENTS
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."CLIENTS"
-- ----------------------------
ALTER TABLE "KHARJ"."CLIENTS" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."CLIENTS"
-- ----------------------------
ALTER TABLE "KHARJ"."CLIENTS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table PARCEL_TYPES
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."PARCEL_TYPES"
-- ----------------------------
ALTER TABLE "KHARJ"."PARCEL_TYPES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."PARCEL_TYPES" ADD CHECK ("MAX_WEIGHT" IS NOT NULL);
ALTER TABLE "KHARJ"."PARCEL_TYPES" ADD CHECK ("MIN_WEIGHT" IS NOT NULL);
ALTER TABLE "KHARJ"."PARCEL_TYPES" ADD CHECK ("NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."PARCEL_TYPES"
-- ----------------------------
ALTER TABLE "KHARJ"."PARCEL_TYPES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table PARCELS
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."PARCELS"
-- ----------------------------
ALTER TABLE "KHARJ"."PARCELS" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."PARCELS"
-- ----------------------------
ALTER TABLE "KHARJ"."PARCELS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table RATES
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."RATES"
-- ----------------------------
ALTER TABLE "KHARJ"."RATES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."RATES" ADD CHECK ("PRICE_MIN" IS NOT NULL);
ALTER TABLE "KHARJ"."RATES" ADD CHECK ("PRICE_PER_KG" IS NOT NULL);
ALTER TABLE "KHARJ"."RATES" ADD CHECK ("CITY_FROM" IS NOT NULL);
ALTER TABLE "KHARJ"."RATES" ADD CHECK ("CITY_TO" IS NOT NULL);
ALTER TABLE "KHARJ"."RATES" ADD CHECK ("PARCEL_TYPE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."RATES"
-- ----------------------------
ALTER TABLE "KHARJ"."RATES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table ROUTE_MAPS
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."ROUTE_MAPS"
-- ----------------------------
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD CHECK ("NUM" IS NOT NULL);
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD CHECK ("CITY" IS NOT NULL);
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD CHECK ("ROUTE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."ROUTE_MAPS"
-- ----------------------------
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table ROUTES
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."ROUTES"
-- ----------------------------
ALTER TABLE "KHARJ"."ROUTES" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."ROUTES"
-- ----------------------------
ALTER TABLE "KHARJ"."ROUTES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SCHEDULES
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."SCHEDULES"
-- ----------------------------
ALTER TABLE "KHARJ"."SCHEDULES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."SCHEDULES" ADD CHECK ("CITY" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."SCHEDULES"
-- ----------------------------
ALTER TABLE "KHARJ"."SCHEDULES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table USERS
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."USERS"
-- ----------------------------
ALTER TABLE "KHARJ"."USERS" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."USERS"
-- ----------------------------
ALTER TABLE "KHARJ"."USERS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table VEHICLE_MAXLOADS
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."VEHICLE_MAXLOADS"
-- ----------------------------
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD CHECK ("MAX_WEIGHT" IS NOT NULL);
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD CHECK ("PARCEL_TYPE" IS NOT NULL);
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD CHECK ("VEHICLE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."VEHICLE_MAXLOADS"
-- ----------------------------
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table VEHICLES
-- ----------------------------

-- ----------------------------
-- Checks structure for table "KHARJ"."VEHICLES"
-- ----------------------------
ALTER TABLE "KHARJ"."VEHICLES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "KHARJ"."VEHICLES" ADD CHECK ("MAX_LOAD" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table "KHARJ"."VEHICLES"
-- ----------------------------
ALTER TABLE "KHARJ"."VEHICLES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."CLIENTS"
-- ----------------------------
ALTER TABLE "KHARJ"."CLIENTS" ADD FOREIGN KEY ("CITY") REFERENCES "KHARJ"."CITIES" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."PARCELS"
-- ----------------------------
ALTER TABLE "KHARJ"."PARCELS" ADD FOREIGN KEY ("PARCEL_TYPE") REFERENCES "KHARJ"."PARCEL_TYPES" ("ID");
ALTER TABLE "KHARJ"."PARCELS" ADD FOREIGN KEY ("CLIENT_TO") REFERENCES "KHARJ"."CLIENTS" ("ID");
ALTER TABLE "KHARJ"."PARCELS" ADD FOREIGN KEY ("ROUTE") REFERENCES "KHARJ"."ROUTES" ("ID");
ALTER TABLE "KHARJ"."PARCELS" ADD FOREIGN KEY ("CITY_TO") REFERENCES "KHARJ"."CITIES" ("ID");
ALTER TABLE "KHARJ"."PARCELS" ADD FOREIGN KEY ("CITY_FROM") REFERENCES "KHARJ"."CITIES" ("ID");
ALTER TABLE "KHARJ"."PARCELS" ADD FOREIGN KEY ("CLIENT_FROM") REFERENCES "KHARJ"."CLIENTS" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."RATES"
-- ----------------------------
ALTER TABLE "KHARJ"."RATES" ADD FOREIGN KEY ("CITY_TO") REFERENCES "KHARJ"."CITIES" ("ID");
ALTER TABLE "KHARJ"."RATES" ADD FOREIGN KEY ("CITY_FROM") REFERENCES "KHARJ"."CITIES" ("ID");
ALTER TABLE "KHARJ"."RATES" ADD FOREIGN KEY ("PARCEL_TYPE") REFERENCES "KHARJ"."PARCEL_TYPES" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."ROUTE_MAPS"
-- ----------------------------
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD FOREIGN KEY ("ROUTE") REFERENCES "KHARJ"."ROUTES" ("ID");
ALTER TABLE "KHARJ"."ROUTE_MAPS" ADD FOREIGN KEY ("CITY") REFERENCES "KHARJ"."CITIES" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."ROUTES"
-- ----------------------------
ALTER TABLE "KHARJ"."ROUTES" ADD FOREIGN KEY ("VEHICLE") REFERENCES "KHARJ"."VEHICLES" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."SCHEDULES"
-- ----------------------------
ALTER TABLE "KHARJ"."SCHEDULES" ADD FOREIGN KEY ("CITY") REFERENCES "KHARJ"."CITIES" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."VEHICLE_MAXLOADS"
-- ----------------------------
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD FOREIGN KEY ("PARCEL_TYPE") REFERENCES "KHARJ"."PARCEL_TYPES" ("ID");
ALTER TABLE "KHARJ"."VEHICLE_MAXLOADS" ADD FOREIGN KEY ("VEHICLE") REFERENCES "KHARJ"."VEHICLES" ("ID");

-- ----------------------------
-- Foreign Key structure for table "KHARJ"."VEHICLES"
-- ----------------------------
ALTER TABLE "KHARJ"."VEHICLES" ADD FOREIGN KEY ("HOME_CITY") REFERENCES "KHARJ"."CITIES" ("ID");
