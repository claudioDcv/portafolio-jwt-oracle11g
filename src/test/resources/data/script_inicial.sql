--------------------------------------------------------
-- Archivo creado  - viernes-octubre-26-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence EMPRESA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SAFE_USER"."EMPRESA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence INSTALACION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SAFE_USER"."INSTALACION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PROFILE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SAFE_USER"."PROFILE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USER_PROFILE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SAFE_USER"."USER_PROFILE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SAFE_USER"."USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table EMPRESAS
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."EMPRESAS" 
   (	"EMPRESA_ID" NUMBER, 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"DIRECCION" VARCHAR2(100 BYTE), 
	"TELEFONO" VARCHAR2(100 BYTE), 
	"EMAIL" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table INSTALACIONES
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."INSTALACIONES" 
   (	"INSTALACION_ID" NUMBER, 
	"EMPRESA_FK" NUMBER, 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"CODIGO" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table PROFILES
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."PROFILES" 
   (	"PROFILE_ID" NUMBER, 
	"DISPLAY_NAME" VARCHAR2(50 BYTE), 
	"NATURAL_KEY" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."USERS" 
   (	"USER_ID" NUMBER, 
	"EMAIL" VARCHAR2(256 BYTE), 
	"DISPLAY_NAME" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"LASTNAME" VARCHAR2(50 BYTE), 
	"LASTPASSWORDRESETDATE" DATE, 
	"ENABLED" CHAR(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table USERS_PROFILES
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."USERS_PROFILES" 
   (	"USER_PROFILE_ID" NUMBER, 
	"USERS_FK" NUMBER, 
	"PROFILES_FK" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SAFE_USER.EMPRESAS
SET DEFINE OFF;
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('1','Mc Donnald','Avenida siempre viva 28','+56932245366','contacto@mcdonnald.com');
REM INSERTING into SAFE_USER.INSTALACIONES
SET DEFINE OFF;
Insert into SAFE_USER.INSTALACIONES (INSTALACION_ID,EMPRESA_FK,NOMBRE,CODIGO) values ('1','1','Grua Orquilla ','gru_or_01');
Insert into SAFE_USER.INSTALACIONES (INSTALACION_ID,EMPRESA_FK,NOMBRE,CODIGO) values ('2','1','Compresor 01','comp_01');
Insert into SAFE_USER.INSTALACIONES (INSTALACION_ID,EMPRESA_FK,NOMBRE,CODIGO) values ('3','1','Compresor 02','comp_02');
Insert into SAFE_USER.INSTALACIONES (INSTALACION_ID,EMPRESA_FK,NOMBRE,CODIGO) values ('4','1','Compresor 03','comp_03');
REM INSERTING into SAFE_USER.PROFILES
SET DEFINE OFF;
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('1','Tecnico','TECNICO');
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('2','Admin Safe','ADMIN_SAFE');
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('3','Medico','MEDICO');
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('4','Prevencionista','PREVENCIONISTA');
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('5','Trabajador','ADMIN_EMPRESA');
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('6','Examinador','EXAMINADOR');
REM INSERTING into SAFE_USER.USERS
SET DEFINE OFF;
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('1','admin_safe.tecnico@safe.cl','Alba Rosales R','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('2','no_perfil@safe.cl','Fito Paez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('3','medico@safe.cl','Patricia Rodriguez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('35','jorge.gonzales@gmail.com','Jorge Gonzales','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('5','alfredo.sfeir@gmail.com','Alfredo Sfeir','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('36','rojas_claudio_1988@hotmail.com','Claudio Esteban Rojas Rodriguez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('11','jorge_claudio@gmail.com','Jorge Claudio Rojas Ulloa','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('37','jru66@hotmail.com','Claudio Rojas','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('41','test2@gmail.com','Patricia Rodriguez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('4','examinador@safe.cl','Francisco','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','Arenas',null,'1');
REM INSERTING into SAFE_USER.USERS_PROFILES
SET DEFINE OFF;
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('1','1','1');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('2','1','2');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('3','4','6');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('4','3','3');
--------------------------------------------------------
--  DDL for Index PROFILE_DISPLAY_NAME_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."PROFILE_DISPLAY_NAME_PK" ON "SAFE_USER"."PROFILES" ("DISPLAY_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index USER_EMAIL_UK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."USER_EMAIL_UK" ON "SAFE_USER"."USERS" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PROFILE_NATURAL_KEY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."PROFILE_NATURAL_KEY_PK" ON "SAFE_USER"."PROFILES" ("NATURAL_KEY") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."USER_PK" ON "SAFE_USER"."USERS" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PROFILE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."PROFILE_PK" ON "SAFE_USER"."PROFILES" ("PROFILE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index TABLE1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."TABLE1_PK" ON "SAFE_USER"."INSTALACIONES" ("INSTALACION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index INSTALACIONES_INDEX1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."INSTALACIONES_INDEX1" ON "SAFE_USER"."INSTALACIONES" ("CODIGO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index EMPRESAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."EMPRESAS_PK" ON "SAFE_USER"."EMPRESAS" ("EMPRESA_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Procedure EMPRESAS_GET_ALL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."EMPRESAS_GET_ALL" (
o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM empresas; 
END;

/
--------------------------------------------------------
--  DDL for Procedure EMPRESAS_INSERT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."EMPRESAS_INSERT" (
	   p_nombre in empresas.nombre%type,
       p_direccion in empresas.direccion%type,
       p_telefono in empresas.telefono%type,
       p_email in empresas.email%type,
       o_ID OUT empresas.empresa_id%TYPE)
as
seq_id number;
BEGIN

    seq_id := SAFE_USER.empresa_seq.NEXTVAL;
    INSERT INTO empresas ("EMPRESA_ID", "NOMBRE", "DIRECCION", "TELEFONO", "EMAIL") 
    VALUES (seq_id, p_nombre, p_direccion, p_telefono, p_email);

    COMMIT;

    o_id := seq_id;
END;

/
--------------------------------------------------------
--  DDL for Procedure INSTALACIONES_GET_ALL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."INSTALACIONES_GET_ALL" (
o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM instalaciones; 
END;

/
--------------------------------------------------------
--  DDL for Procedure PROFILE_BY_USER_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."PROFILE_BY_USER_ID" (
    p_id in users_profiles.users_fk%TYPE,
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
    SELECT profile_id, display_name, natural_key
    FROM profiles
    WHERE profile_id IN (
        SELECT users_profiles.profiles_fk
        FROM users_profiles
        WHERE users_profiles.users_fk = p_id
    ); 
END;

/
--------------------------------------------------------
--  DDL for Procedure PROFILES_GET_ALL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."PROFILES_GET_ALL" (
o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM profiles; 
END;

/
--------------------------------------------------------
--  DDL for Procedure PROFILE_USER_BY_USER_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."PROFILE_USER_BY_USER_ID" (
    p_id in users_profiles.users_fk%TYPE,
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
    SELECT
    profiles.profile_id,
    profiles.natural_key,
    profiles.display_name,
    users.user_id,
    users.email,
    users.display_name as username
    FROM profiles
        INNER JOIN users_profiles
    ON profiles.profile_id = users_profiles.profiles_fk
        AND users_profiles.users_fk = p_id
    INNER JOIN users
ON users_profiles.users_fk = users.user_id;
END;

/
--------------------------------------------------------
--  DDL for Procedure USERS_BY_EMAIL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."USERS_BY_EMAIL" (
    p_email IN users.email%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM users where email = p_email; 
END;

/
--------------------------------------------------------
--  DDL for Procedure USERS_BY_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."USERS_BY_ID" (
    p_id IN users.user_id%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM users where user_id = p_id; 
END;

/
--------------------------------------------------------
--  DDL for Procedure USERS_DELETE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."USERS_DELETE" (
    p_id IN users.user_id%TYPE, 
    o_delete_id OUT users.user_id%TYPE)
AS
result_users number;
BEGIN
    DELETE FROM users WHERE user_id = p_id;
    COMMIT;
    
    select count(*) into result_users from users where users.user_id = p_id;
    
    if result_users = 0 then
        o_delete_id := p_id;
    else
        o_delete_id := 0;
    end if;
END;

/
--------------------------------------------------------
--  DDL for Procedure USERS_GET_ALL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."USERS_GET_ALL" (
o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM users; 
END;

/
--------------------------------------------------------
--  DDL for Procedure USERS_INSERT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."USERS_INSERT" (
	   p_EMAIL IN users.EMAIL%TYPE,
	   p_DISPLAY_NAME IN users.DISPLAY_NAME%TYPE,
	   p_PASSWORD IN users.PASSWORD%TYPE,
       o_USER_ID OUT users.user_id%TYPE)
as
seq_id number;
BEGIN

    seq_id := SAFE_USER.USERS_SEQ.NEXTVAL;
    INSERT INTO USERS ("USER_ID", "EMAIL", "DISPLAY_NAME", "PASSWORD") 
    VALUES (seq_id, p_email, p_display_name, p_PASSWORD);

    COMMIT;
    
    o_user_id := seq_id;
END;

/
--------------------------------------------------------
--  DDL for Procedure USERS_UPDATE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."USERS_UPDATE" (
    p_display_name IN users.display_name%TYPE,
    p_email IN users.email%TYPE,
    p_user_id IN users.user_id%TYPE,
    o_user_id OUT users.user_id%TYPE
)
AS
var_email_has_exist NUMBER;
BEGIN
    
    SELECT count(*) into var_email_has_exist FROM users where email = p_email and users.user_id != p_user_id;
    
    if (var_email_has_exist = 0) then
        UPDATE users SET display_name=p_display_name, email=p_email WHERE user_id=p_user_id;
        o_user_id := p_user_id;
        COMMIT;
    else
        o_user_id := 0;
    end if;
END;

/
--------------------------------------------------------
--  Constraints for Table EMPRESAS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."EMPRESAS" ADD CONSTRAINT "EMPRESAS_PK" PRIMARY KEY ("EMPRESA_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("TELEFONO" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("DIRECCION" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("EMPRESA_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("LASTNAME" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."USERS" ADD CONSTRAINT "USER_EMAIL_UK" UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."USERS" ADD CONSTRAINT "USER_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("DISPLAY_NAME" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PROFILES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."PROFILES" ADD CONSTRAINT "PROFILE_DISPLAY_NAME_PK" UNIQUE ("DISPLAY_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."PROFILES" ADD CONSTRAINT "PROFILE_NATURAL_KEY_PK" UNIQUE ("NATURAL_KEY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."PROFILES" ADD CONSTRAINT "PROFILE_PK" PRIMARY KEY ("PROFILE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."PROFILES" MODIFY ("NATURAL_KEY" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."PROFILES" MODIFY ("DISPLAY_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INSTALACIONES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("CODIGO" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("EMPRESA_FK" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INSTALACIONES" ADD CONSTRAINT "TABLE1_PK" PRIMARY KEY ("INSTALACION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("INSTALACION_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table INSTALACIONES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INSTALACIONES" ADD CONSTRAINT "INSTALACIONES_FK1" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES "SAFE_USER"."EMPRESAS" ("EMPRESA_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERS_PROFILES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."USERS_PROFILES" ADD CONSTRAINT "FK_USERS_PROFILES_PROFILES" FOREIGN KEY ("PROFILES_FK")
	  REFERENCES "SAFE_USER"."PROFILES" ("PROFILE_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."USERS_PROFILES" ADD CONSTRAINT "FK_USERS_PROFILES_USERS" FOREIGN KEY ("USERS_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
