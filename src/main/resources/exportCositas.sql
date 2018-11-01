--------------------------------------------------------
-- Archivo creado  - mi�rcoles-octubre-31-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence CAPACITACION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SAFE_USER"."CAPACITACION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
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
--  DDL for Table ASISTENCIAS
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."ASISTENCIAS" 
   (	"ASISTENCIA_ID" NUMBER, 
	"TRABAJADOR_FK" NUMBER, 
	"CAPACITACION_FK" NUMBER, 
	"FIRMA" VARCHAR2(400 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table CAPACITACIONES
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."CAPACITACIONES" 
   (	"CAPACITACION_ID" NUMBER, 
	"EXAMINADOR_FK" NUMBER, 
	"SUPERVISOR_FK" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"EMPRESA_FK" NUMBER, 
	"FECHA_REALIZACION" DATE, 
	"DESCRIPCION" VARCHAR2(2000 BYTE), 
	"ASISTENTES_MINIMOS" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
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
--  DDL for Table INFORMES_DETALLES
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."INFORMES_DETALLES" 
   (	"INFORME_DETALLE_ID" NUMBER, 
	"PREVENSIONISTA_FK" NUMBER, 
	"TECNICO_FK" NUMBER, 
	"NOMBRE" VARCHAR2(20 BYTE), 
	"SUPERVISOR_FK" NUMBER, 
	"FECHA_REALIZACION" DATE, 
	"CONFIRMACION_PREVENCIONISTA" NUMBER(1,0) DEFAULT 0, 
	"FECHA_APROBACION" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table INFORMES_INSTALACION
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."INFORMES_INSTALACION" 
   (	"INFORME_INSTALACION_ID" NUMBER, 
	"INFORME_DETALLE_FK" NUMBER, 
	"INSTALACION_FK" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table INFORMES_TRABAJADOR
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."INFORMES_TRABAJADOR" 
   (	"INFORME_TRABAJADOR_ID" NUMBER, 
	"INFORME_DETALLE_FK" NUMBER, 
	"TRABAJADOR_FK" NUMBER
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
--  DDL for Table TRABAJADORES
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."TRABAJADORES" 
   (	"TRABAJADOR_ID" NUMBER, 
	"EMPRESA_FK" NUMBER, 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"APELLIDO_PATERNO" VARCHAR2(50 BYTE), 
	"APELLIDO_MATERNO" VARCHAR2(50 BYTE), 
	"EMAIL" VARCHAR2(255 BYTE), 
	"RUN" VARCHAR2(15 BYTE)
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
--------------------------------------------------------
--  DDL for Table VISITAS_MEDICAS
--------------------------------------------------------

  CREATE TABLE "SAFE_USER"."VISITAS_MEDICAS" 
   (	"VISITA_MEDICA_ID" NUMBER, 
	"MEDICO_FK" NUMBER, 
	"SUPERVISOR_FK" NUMBER, 
	"CONFIRMACION_MEDICO" NUMBER(1,0), 
	"EMPRESA_FK" NUMBER, 
	"FECHA_REALIZACION" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SAFE_USER.ASISTENCIAS
SET DEFINE OFF;
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('1','1','1',null);
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('2','2','1',null);
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('3','3','1',null);
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('4','4','1',null);
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('5','1','2',null);
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('6','2','2',null);
Insert into SAFE_USER.ASISTENCIAS (ASISTENCIA_ID,TRABAJADOR_FK,CAPACITACION_FK,FIRMA) values ('7','45','2',null);
REM INSERTING into SAFE_USER.CAPACITACIONES
SET DEFINE OFF;
Insert into SAFE_USER.CAPACITACIONES (CAPACITACION_ID,EXAMINADOR_FK,SUPERVISOR_FK,NOMBRE,EMPRESA_FK,FECHA_REALIZACION,DESCRIPCION,ASISTENTES_MINIMOS) values ('1','4','35','Evaluaci�n de Riesgo Psicosocial','1',to_date('12/12/18','DD/MM/RR'),'El curso le permitir� conocer el adecuado proceso de evaluaci�n e intervenci�n de los riesgos psicosociales en las organizaciones, conforme al protocolo de vigilancia del Ministerio de Salud de Chile.
<br /><br />
Para ello, el curso se encuentra estructura en tres m�dulos: <br /><br />
 
M�dulo 1:  Factores de riesgos psicosociales laborales e introducci�n a herramientas de medici�n.<br />
M�dulo 2: �C�mo medir los factores de riesgo en mi lugar de trabajo?<br />
M�dulo 3: Dise�o y aplicaci�n de medidas de intervenciones en el lugar de trabajo','2');
Insert into SAFE_USER.CAPACITACIONES (CAPACITACION_ID,EXAMINADOR_FK,SUPERVISOR_FK,NOMBRE,EMPRESA_FK,FECHA_REALIZACION,DESCRIPCION,ASISTENTES_MINIMOS) values ('2','4','35','Formulaci�n de planes de negocios y Acceso a financiamiento','1',to_date('12/12/18','DD/MM/RR'),'Este curso busca dar a conocer los principales elementos de un plan de negocios y las diferentes alternativas de financiamiento de una empresa. Est� dirigido a mujeres emprendedoras que necesiten de cofinanciamiento para sus proyectos.<br />
El curso se centrar� en la estructura del Plan de Trabajo del Instrumento Capital Abeja de Sercotec.<br />
<li> Conceptos para comprender qu� es un plan de negocios y cu�l es su utilidad.</li> 
<li> Estructura de un plan de negocios.</li> 
<li> Consejos para completar un plan de negocios.</li> 
<li>  Necesidades de financiamiento y su naturaleza.</li> 
<li> Alternativas de financiamiento.</li> ','25');
REM INSERTING into SAFE_USER.EMPRESAS
SET DEFINE OFF;
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('1','Mc Donnald','Avenida siempre viva 28','+56 93 224 5366','contacto@mcdonnald.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('36','Energy XXI Gulf Coast, Inc.','673 Miller Parkway','+58 473 916 2681','gdemicoz@psu.edu');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('37','Continental Building Products, Inc.','02104 Amoth Pass','+1 678 778 2347','ljobling10@homestead.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('38','Autohome Inc.','69 Mesta Place','+81 290 370 2298','krotter11@google.com.hk');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('39','Arcadia Biosciences, Inc.','4183 Bay Lane','+63 603 480 2708','bwapple12@cmu.edu');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('40','First Trust High Income ETF','4187 Hovde Court','+358 658 670 3273','hfrede13@webeden.co.uk');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('41','Nuveen Select Tax Free Income Portfolio II','10 Almo Way','+7 751 622 2731','bmckane14@icq.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('42','Iconix Brand Group, Inc.','05 Ronald Regan Road','+86 621 918 0753','tdonan15@infoseek.co.jp');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('43','Nuveen Virginia Quality Municipal Income Fund','96274 Armistice Point','+30 500 674 7227','sdallinder16@cam.ac.uk');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('44','Celsius Holdings, Inc.','393 Di Loreto Junction','+86 518 844 2077','mpawle17@furl.net');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('45','Eaton Vance Short Diversified Income Fund','5 Clyde Gallagher Plaza','+86 281 583 1139','hrowter18@sbwire.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('46','Jensyn Acquistion Corp.','8 Debra Way','+63 389 452 8094','arottenbury19@de.vu');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('47','CME Group Inc.','34216 Shelley Hill','+7 759 382 7474','manand1a@seattletimes.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('48','Newtek Business Services Corp.','12 Southridge Alley','+1 727 587 6998','neberts1b@hatena.ne.jp');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('49','Sprouts Farmers Market, Inc.','815 Bartillon Pass','+81 127 816 8112','tmilius1c@europa.eu');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('50','Vishay Precision Group, Inc.','25 Bartelt Center','+86 402 238 2971','kjanas1d@oakley.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('2','Nuveen Missouri Quality Municipal Income Fund','9669 Hollow Ridge Park','+236 166 775 3396','rbrevitt1@issuu.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('3','Ikonics Corporation','62724 Kedzie Drive','+62 894 545 6509','lgratrix2@census.gov');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('4','ENDRA Life Sciences Inc.','8 Sherman Road','+86 466 372 1958','afrigout3@google.nl');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('5','Tenet Healthcare Corporation','02 Evergreen Plaza','+242 956 689 3417','rbrauninger4@imgur.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('6','Tempur Sealy International, Inc.','07692 Red Cloud Court','+58 903 348 9548','lboucher5@rakuten.co.jp');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('7','EQT Corporation','2950 Bunker Hill Road','+86 888 600 4370','hdimont6@wiley.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('8','Rennova Health, Inc.','36472 Loomis Avenue','+261 761 931 4270','cbulbeck7@redcross.org');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('9','Ascena Retail Group, Inc.','8585 Kedzie Terrace','+62 577 624 1771','bstreetfield8@auda.org.au');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('10','Amec Plc Ord','75 Debs Alley','+591 703 281 0085','aandrick9@etsy.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('11','Snyder&#39;s-Lance, Inc.','8449 Green Ridge Way','+51 950 359 4495','tbarrowclougha@unesco.org');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('12','Celsius Holdings, Inc.','376 Rigney Crossing','+98 759 490 6277','bskryneb@virginia.edu');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('13','Peak Resorts, Inc.','40131 Donald Court','+81 790 175 3988','jperuttoc@typepad.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('14','Korn/Ferry International','0 Manley Drive','+371 386 635 5742','jspennockd@dot.gov');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('15','RE/MAX Holdings, Inc.','76715 Hanson Terrace','+62 157 770 7391','cgirogettie@biglobe.ne.jp');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('16','Federal Agricultural Mortgage Corporation','638 Bayside Parkway','+55 727 833 4489','oarpef@rakuten.co.jp');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('17','NetSol Technologies Inc.','791 Autumn Leaf Plaza','+63 149 897 9908','mjoireg@mediafire.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('18','Stericycle, Inc.','2440 Packers Junction','+55 229 123 7686','mschwanth@telegraph.co.uk');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('19','PowerShares QQQ Trust, Series 1','761 Goodland Lane','+1 415 551 5607','ccabrarai@thetimes.co.uk');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('20','Cotiviti Holdings, Inc.','34 Mendota Avenue','+855 483 383 8796','rmccombej@dot.gov');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('21','Silicon Motion Technology Corporation','932 Lakewood Street','+56 603 470 6096','lpatronk@hp.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('22','Nuven Mortgage Opportunity Term Fund 2','97 Rieder Street','+967 305 723 6687','bmaclleesel@pinterest.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('23','Genesis Healthcare, Inc.','272 Morningstar Road','+374 510 195 7474','arichardm@narod.ru');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('24','Ameris Bancorp','618 Garrison Center','+970 629 689 4711','jbodsworthn@lycos.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('25','Oxford Lane Capital Corp.','78 Aberg Terrace','+48 291 628 1001','omcgaffeyo@seattletimes.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('26','Otter Tail Corporation','5 Manufacturers Hill','+62 860 853 1097','abimsonp@wikipedia.org');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('27','Ionis Pharmaceuticals, Inc.','58 Riverside Street','+86 146 803 5152','cendeanq@cnbc.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('28','Live Ventures Incorporated','40693 Merry Circle','+48 218 741 2642','kdavidger@meetup.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('29','Public Storage','0 Morrow Junction','+66 148 198 9255','kmayhos@nydailynews.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('30','Jaguar Animal Health, Inc.','7 Donald Circle','+86 124 605 1634','jsteadmant@columbia.edu');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('31','ENSCO plc','3 Summer Ridge Junction','+86 639 755 6952','ceastmentu@jugem.jp');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('32','Novo Nordisk A/S','48 Boyd Road','+62 504 896 2839','lrockliffev@tumblr.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('33','PetroChina Company Limited','5 Duke Junction','+86 368 547 7120','cwillerstonew@cafepress.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('34','Tilly&#39;s, Inc.','1 Browning Parkway','+63 894 546 0205','lyusupovx@vinaora.com');
Insert into SAFE_USER.EMPRESAS (EMPRESA_ID,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('35','PowerShares DWA Financial Momentum Portfolio','73 Randy Point','+7 391 616 9675','sericssony@51.la');
REM INSERTING into SAFE_USER.INFORMES_DETALLES
SET DEFINE OFF;
Insert into SAFE_USER.INFORMES_DETALLES (INFORME_DETALLE_ID,PREVENSIONISTA_FK,TECNICO_FK,NOMBRE,SUPERVISOR_FK,FECHA_REALIZACION,CONFIRMACION_PREVENCIONISTA,FECHA_APROBACION) values ('1','41','11','Grua orquilla rota','35',to_date('12/12/18','DD/MM/RR'),'1',to_date('12/01/18','DD/MM/RR'));
REM INSERTING into SAFE_USER.INFORMES_INSTALACION
SET DEFINE OFF;
Insert into SAFE_USER.INFORMES_INSTALACION (INFORME_INSTALACION_ID,INFORME_DETALLE_FK,INSTALACION_FK) values ('1','1','1');
REM INSERTING into SAFE_USER.INFORMES_TRABAJADOR
SET DEFINE OFF;
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
Insert into SAFE_USER.PROFILES (PROFILE_ID,DISPLAY_NAME,NATURAL_KEY) values ('7','Supervisor','SUPERVISOR');
REM INSERTING into SAFE_USER.TRABAJADORES
SET DEFINE OFF;
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('1','1','Patricio','Rey','Alvarez','patricio.rey@macdonald','11635475-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('2','1','Ermenejildo','Ferni','Alvarez','ermenejildo.ferni@macdonald.com','11635475-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('3','1','Mallory','Lancaster','Santana','lectus.ante.dictum@lacusNulla.ca','45333663-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('4','1','Edan','Noel','Whitley','ipsum@eunulla.ca','15017525-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('5','1','Irene','Barker','Mclaughlin','vulputate.posuere@duiquis.co.uk','47122189-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('6','1','Kermit','Green','Holman','et.magnis@Namtempordiam.net','20550776-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('7','1','Zachary','Brooks','Knight','tincidunt.tempus@luctus.org','9892452-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('8','1','Dawn','Ferguson','Oconnor','tempus.mauris.erat@vitaenibhDonec.ca','5895626-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('9','1','Jorden','Black','Ray','Aliquam.erat.volutpat@hendreritconsectetuer.net','24706592-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('10','1','Gavin','Campbell','Erickson','lorem.ut@pellentesqueeget.co.uk','26917808-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('11','1','Zeph','Lambert','Ware','nisi.magna.sed@mollis.edu','17135929-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('12','1','Deanna','Steele','Mercer','a.nunc.In@eleifend.edu','15370627-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('13','1','Hasad','Miller','Mcdonald','dui@molestiearcuSed.net','23598778-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('14','1','Astra','Riley','Carver','dignissim.lacus@lectus.com','14486389-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('15','1','Xenos','Myers','Perry','nec.ante.blandit@Pellentesquetincidunttempus.edu','50607765-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('16','1','Ray','Macias','Lara','Vestibulum.ante@portaelita.ca','16288844-7');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('17','1','Irma','Duke','Chaney','litora.torquent.per@Cras.edu','27454262-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('18','1','Uta','Allen','Cook','risus.quis.diam@magnaSedeu.co.uk','43082872-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('19','1','Coby','Weber','Roberts','pharetra@massa.ca','38561182-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('20','1','Moana','Preston','Alford','lectus.pede@Nunc.edu','49841062-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('21','1','Ivory','Webb','Puckett','velit.Pellentesque.ultricies@sollicitudin.co.uk','27147579-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('22','1','Chandler','Munoz','Fisher','egestas@mattisInteger.ca','35777784-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('23','1','Sacha','Dawson','Fields','a.mi@vehiculaaliquetlibero.edu','5024523-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('24','1','Yuli','Blackwell','Gregory','mattis@feugiat.edu','11376475-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('25','1','Yuli','Diaz','Atkinson','Cras.interdum.Nunc@amet.com','24876821-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('26','1','Brady','Cooper','Cervantes','faucibus@nasceturridiculusmus.com','10794030-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('27','1','Buffy','Greer','Dunn','vel.faucibus.id@purusac.net','49726384-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('28','1','Slade','Finley','Hendricks','Proin@ornareelitelit.net','31729925-7');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('29','1','Louis','Malone','Small','libero@sem.ca','29596814-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('30','1','Cameron','Haynes','Fischer','a.magna@Morbineque.com','7347574-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('31','1','Clementine','Mercer','Robinson','erat.Vivamus.nisi@hendrerit.edu','7323170-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('32','1','Nayda','Ryan','Tate','Proin.ultrices@acfeugiat.ca','25641053-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('33','1','Sybill','Cannon','Blevins','eu@pedesagittis.ca','50861490-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('34','1','Brielle','Mullins','Chavez','nec@odioEtiamligula.net','31200396-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('35','1','Ava','Bailey','Horne','pulvinar@liberoDonec.net','35631770-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('36','1','Allen','Reynolds','Beck','Nunc.lectus@vitaerisusDuis.com','32467277-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('37','1','Aquila','Duran','Mooney','sagittis.semper@auctorvelitAliquam.com','48188919-7');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('38','1','Miranda','Harvey','Griffith','dignissim.magna.a@montesnasceturridiculus.co.uk','32394288-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('39','1','Jared','Osborn','Bradford','ullamcorper@gravidaAliquamtincidunt.ca','49058200-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('40','1','Amity','Monroe','Valdez','purus.Nullam.scelerisque@acrisusMorbi.net','14653146-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('41','1','Melanie','Cantrell','Jimenez','sagittis.Nullam.vitae@amagnaLorem.ca','49852996-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('42','1','Tucker','Walter','Guzman','auctor.Mauris@ategestasa.org','32702164-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('43','1','Fulton','Shannon','Kramer','Vestibulum@rhoncus.edu','14797076-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('44','1','Ivan','Andrews','Cantu','vel.nisl@DonecestNunc.edu','6037247-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('45','1','Idona','Levine','Martin','sagittis.placerat.Cras@amet.com','37800896-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('46','1','Steven','Cantrell','Goodman','fermentum.convallis@ligulaAeneaneuismod.net','35705313-7');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('47','1','Raven','Graves','Green','lorem@vehiculaPellentesquetincidunt.co.uk','46844368-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('48','1','Angela','Alvarez','Fry','sed.sem@arcuNunc.co.uk','42746960-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('49','1','Timon','Burnett','Mcgee','non.sollicitudin.a@cubilia.com','29318017-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('50','1','Yael','Dominguez','Compton','quis@velit.org','10901079-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('51','1','Kessie','Pierce','Farrell','diam.vel.arcu@tinciduntorciquis.com','21942546-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('52','1','Hayley','Buck','Campbell','mus@nectempusscelerisque.edu','17186448-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('53','1','Duncan','Park','Kelly','ornare@natoque.co.uk','10812455-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('54','1','Roth','Henson','Keith','enim.commodo@semperduilectus.co.uk','15570797-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('55','1','Eaton','Osborne','Wallace','justo.nec.ante@imperdietornareIn.org','10379745-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('56','1','Mohammad','Workman','Garcia','gravida.Aliquam.tincidunt@magnaPraesent.net','6750523-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('57','1','Sigourney','Lambert','Trujillo','Aenean@ornarelectus.co.uk','29760420-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('58','1','Gail','Lindsey','Richmond','mollis.dui@Proinmi.net','37734905-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('59','1','Hadley','Hutchinson','Green','nonummy.ipsum.non@blanditat.ca','45056024-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('60','1','Demetrius','Cantu','Leon','lectus@egetvenenatisa.co.uk','43076352-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('61','1','Nigel','Holmes','Hahn','porttitor.tellus.non@malesuadamalesuadaInteger.com','38453775-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('62','1','Felix','Dunlap','Cameron','a@disparturient.org','46458520-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('63','1','MacKensie','Molina','Bauer','tempor.est@interdum.co.uk','5633440-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('64','1','Ali','Gonzalez','Davidson','Nulla.semper.tellus@pharetra.ca','47649328-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('65','1','Azalia','Morse','Yang','Sed@sedtortorInteger.net','18012923-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('66','1','Kelly','Trujillo','Hickman','tellus.justo.sit@liberoMorbiaccumsan.ca','17662128-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('67','1','Tiger','Frederick','Baird','Cras@pedeac.com','48299427-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('68','1','Sybill','Salas','Key','turpis@eudoloregestas.edu','29811946-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('69','1','Deborah','Fuller','Davis','sodales.nisi.magna@odio.co.uk','15275158-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('70','1','Madaline','Curtis','King','faucibus@luctuslobortisClass.net','48746956-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('71','1','Carly','Cantu','Hendrix','Suspendisse.aliquet@Inscelerisque.net','44174810-8');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('72','1','Colorado','Pickett','Hopper','nec@veliteget.net','33090435-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('73','1','Cailin','Dennis','Jacobs','eu.ultrices.sit@ligulaNullam.co.uk','7867344-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('74','1','Althea','Norris','Ball','tortor.Nunc.commodo@Etiamimperdietdictum.com','50157774-K');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('75','1','Aaron','Barnett','Wilkerson','nulla.In@elementumloremut.ca','14199520-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('76','1','Avram','Mcgee','Owen','non@Fuscefermentum.net','7391694-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('77','1','Oscar','Hendricks','Byrd','erat.vitae.risus@nonummyutmolestie.edu','25270090-0');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('78','1','Armando','Schwartz','Mullen','a.dui.Cras@Duissit.edu','39182846-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('79','1','Hiram','Downs','Goodwin','non@nuncac.co.uk','12840895-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('80','1','McKenzie','Barlow','Martin','felis.Donec@sedsemegestas.com','18830930-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('81','1','Holmes','Stokes','Buck','ultrices.mauris.ipsum@dapibusquamquis.com','9382269-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('82','1','Evangeline','Booth','Hanson','eros@sitamet.edu','42558186-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('83','1','Maile','Weber','Everett','dolor.dolor.tempus@euismodenim.com','31734731-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('84','1','Wynne','Vaughn','Nunez','urna@tempus.com','33141499-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('85','1','Herrod','Haney','Haley','erat@IncondimentumDonec.net','7449228-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('86','1','Morgan','Moody','Gilliam','Sed@luctus.org','29075886-6');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('87','1','Kane','White','Berg','Aliquam@elitCurabitur.ca','19432429-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('88','1','Jack','Humphrey','Vazquez','molestie.sodales.Mauris@massanon.co.uk','45282919-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('89','1','Seth','Ward','Willis','non@at.org','29675230-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('90','1','Ariel','Gallegos','Peters','non@nonenim.co.uk','14502119-7');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('91','1','Arsenio','Ashley','Nixon','Nunc@cursusIntegermollis.co.uk','27961282-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('92','1','Madaline','Willis','Montgomery','ante@Maecenasmi.net','25684313-7');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('93','1','Ignacia','Ford','Owen','placerat.Cras.dictum@tortor.com','50448264-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('94','1','Len','Day','Dillon','orci.lobortis.augue@Integervitaenibh.edu','42057030-9');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('95','1','Silas','Delgado','Combs','Vestibulum.ante.ipsum@magnatellus.org','43949067-5');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('96','1','Melvin','Wall','Meyer','bibendum@hendrerit.org','25970964-4');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('97','1','Hashim','Good','Joyner','Donec@Phasellus.co.uk','26563316-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('98','1','Elton','Stephens','Harding','nec.leo.Morbi@iaculis.org','37816993-3');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('99','1','Rama','Brock','Patton','accumsan@velit.co.uk','21287288-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('100','1','Duncan','Flores','Grimes','odio.auctor@natoquepenatibus.com','9097998-1');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('101','1','Nolan','Crane','Small','eu@tristiquenequevenenatis.org','31417387-2');
Insert into SAFE_USER.TRABAJADORES (TRABAJADOR_ID,EMPRESA_FK,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,EMAIL,RUN) values ('102','1','Gretchen','Love','Clay','ipsum.non.arcu@aliquetProin.ca','22609916-6');
REM INSERTING into SAFE_USER.USERS
SET DEFINE OFF;
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('1','admin_safe.tecnico@safe.cl','Alba Rosales R','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('2','no_perfil@safe.cl','Fito Paez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('3','medico@safe.cl','Patricia Rodriguez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('35','supervisor@safe.cl','Jorge Gonzales','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('5','supervisor2@safe.cl','Alfredo Sfeir','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('36','rojas_claudio_1988@hotmail.com','Claudio Esteban Rojas Rodriguez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('11','tecnico@safe.cl','Jorge Claudio Rojas Ulloa','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('37','admin_empresa@otra.cl','Claudio Rojas','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('41','prevencionista@safe.cl','Patricia Rodriguez','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','NN',to_date('01/01/16','DD/MM/RR'),'1');
Insert into SAFE_USER.USERS (USER_ID,EMAIL,DISPLAY_NAME,PASSWORD,LASTNAME,LASTPASSWORDRESETDATE,ENABLED) values ('4','examinador@safe.cl','Francisco','sha1:64000:18:/Lhl+PwH2oGjGL8I7+1YeH+6TBC2Iy5Q:5CZIRiFPRjxktR2uI1pcOonL','Arenas',null,'1');
REM INSERTING into SAFE_USER.USERS_PROFILES
SET DEFINE OFF;
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('1','1','1');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('2','1','2');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('3','4','6');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('4','3','3');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('5','35','7');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('6','5','7');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('7','37','5');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('8','41','4');
Insert into SAFE_USER.USERS_PROFILES (USER_PROFILE_ID,USERS_FK,PROFILES_FK) values ('9','11','1');
REM INSERTING into SAFE_USER.VISITAS_MEDICAS
SET DEFINE OFF;
Insert into SAFE_USER.VISITAS_MEDICAS (VISITA_MEDICA_ID,MEDICO_FK,SUPERVISOR_FK,CONFIRMACION_MEDICO,EMPRESA_FK,FECHA_REALIZACION) values ('1','3','35','0','1',to_date('25/12/18','DD/MM/RR'));
Insert into SAFE_USER.VISITAS_MEDICAS (VISITA_MEDICA_ID,MEDICO_FK,SUPERVISOR_FK,CONFIRMACION_MEDICO,EMPRESA_FK,FECHA_REALIZACION) values ('2','3','35','-1','1',to_date('25/11/18','DD/MM/RR'));
Insert into SAFE_USER.VISITAS_MEDICAS (VISITA_MEDICA_ID,MEDICO_FK,SUPERVISOR_FK,CONFIRMACION_MEDICO,EMPRESA_FK,FECHA_REALIZACION) values ('3','3','35','1','1',to_date('25/11/18','DD/MM/RR'));
--------------------------------------------------------
--  DDL for Index ASISTENCIAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."ASISTENCIAS_PK" ON "SAFE_USER"."ASISTENCIAS" ("ASISTENCIA_ID") 
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
--  DDL for Index PROFILE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."PROFILE_PK" ON "SAFE_USER"."PROFILES" ("PROFILE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index VISITAS_MEDICAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."VISITAS_MEDICAS_PK" ON "SAFE_USER"."VISITAS_MEDICAS" ("VISITA_MEDICA_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index INFORME_TRABAJADOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."INFORME_TRABAJADOR_PK" ON "SAFE_USER"."INFORMES_TRABAJADOR" ("INFORME_TRABAJADOR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index INFORMES_INSTALACION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."INFORMES_INSTALACION_PK" ON "SAFE_USER"."INFORMES_INSTALACION" ("INFORME_INSTALACION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index TRABAJADORES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."TRABAJADORES_PK" ON "SAFE_USER"."TRABAJADORES" ("TRABAJADOR_ID") 
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
--  DDL for Index USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."USER_PK" ON "SAFE_USER"."USERS" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index CAPACITACIONES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."CAPACITACIONES_PK" ON "SAFE_USER"."CAPACITACIONES" ("CAPACITACION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index INFORMES_DETALLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."INFORMES_DETALLES_PK" ON "SAFE_USER"."INFORMES_DETALLES" ("INFORME_DETALLE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index TABLE1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAFE_USER"."TABLE1_PK" ON "SAFE_USER"."INSTALACIONES" ("INSTALACION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
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
--  DDL for Procedure ASISTENCIAS_GET_ALL_BY_CAP_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."ASISTENCIAS_GET_ALL_BY_CAP_ID" (
    p_id in capacitaciones.capacitacion_id%type,
    o_cursor out SYS_REFCURSOR
)
as
begin
open o_cursor for
select capacitacion_fk, asistencia_id, trabajador_id, nombre, apellido_paterno, apellido_materno, email, run, firma, asistencias.capacitacion_fk from asistencias
inner join trabajadores on asistencias.trabajador_fk = trabajadores.trabajador_id
where capacitacion_fk = p_id;
end;

/*

asistencia_id
trabajador_id


*/

/
--------------------------------------------------------
--  DDL for Procedure CAPACITACION_BY_EMP_EXA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."CAPACITACION_BY_EMP_EXA" (
    p_empresa_fk IN capacitaciones.empresa_fk%TYPE, 
    p_examinador_fk IN capacitaciones.examinador_fk%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM capacitaciones where empresa_fk = p_empresa_fk AND examinador_fk = p_examinador_fk
      ORDER BY fecha_realizacion ASC; 
END;

/
--------------------------------------------------------
--  DDL for Procedure CAPACITACION_BY_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."CAPACITACION_BY_ID" (
    p_id IN capacitaciones.capacitacion_id%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM capacitaciones where capacitaciones.capacitacion_id = p_id; 
END;

/
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
--  DDL for Procedure INFORME_INSTALACION_BY_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."INFORME_INSTALACION_BY_ID" (
    p_id IN users.user_id%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
            SELECT *
                FROM informes_instalacion
                INNER JOIN informes_detalles ON informes_instalacion.informe_detalle_fk = informes_detalles.informe_detalle_id
            WHERE informes_instalacion.informe_instalacion_id = p_id;
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
--  DDL for Procedure TRABAJADORES_BY_EMPRESA_FK
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."TRABAJADORES_BY_EMPRESA_FK" (
    p_empresa_fk IN empresas.empresa_id%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM trabajadores where empresa_fk = p_empresa_fk; 
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
--  DDL for Procedure VISITAS_MED_BY_EMP_MED_CONF
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."VISITAS_MED_BY_EMP_MED_CONF" (
    p_empresa_fk IN visitas_medicas.empresa_fk%TYPE, 
    p_medico_fk IN visitas_medicas.medico_fk%TYPE, 
    p_confirmacion_medico IN visitas_medicas.confirmacion_medico%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM visitas_medicas where empresa_fk = p_empresa_fk AND medico_fk = p_medico_fk AND confirmacion_medico = p_confirmacion_medico
      ORDER BY fecha_realizacion ASC; 
END;

/
--------------------------------------------------------
--  DDL for Procedure VISITAS_MEDICAS_BY_EMPRESA_FK
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SAFE_USER"."VISITAS_MEDICAS_BY_EMPRESA_FK" (
    p_empresa_fk IN visitas_medicas.empresa_fk%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM visitas_medicas where empresa_fk = p_empresa_fk; 
END;

/
--------------------------------------------------------
--  Constraints for Table TRABAJADORES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."TRABAJADORES" ADD CONSTRAINT "TRABAJADORES_PK" PRIMARY KEY ("TRABAJADOR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."TRABAJADORES" MODIFY ("TRABAJADOR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table VISITAS_MEDICAS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" MODIFY ("FECHA_REALIZACION" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" MODIFY ("EMPRESA_FK" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" MODIFY ("CONFIRMACION_MEDICO" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" ADD CONSTRAINT "VISITAS_MEDICAS_PK" PRIMARY KEY ("VISITA_MEDICA_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" MODIFY ("VISITA_MEDICA_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CAPACITACIONES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."CAPACITACIONES" ADD CONSTRAINT "CAPACITACIONES_PK" PRIMARY KEY ("CAPACITACION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."CAPACITACIONES" MODIFY ("CAPACITACION_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("DISPLAY_NAME" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."USERS" ADD CONSTRAINT "USER_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."USERS" ADD CONSTRAINT "USER_EMAIL_UK" UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."USERS" MODIFY ("LASTNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INFORMES_DETALLES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" MODIFY ("CONFIRMACION_PREVENCIONISTA" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" ADD CONSTRAINT "INFORMES_DETALLES_PK" PRIMARY KEY ("INFORME_DETALLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" MODIFY ("INFORME_DETALLE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INFORMES_INSTALACION
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INFORMES_INSTALACION" ADD CONSTRAINT "INFORMES_INSTALACION_PK" PRIMARY KEY ("INFORME_INSTALACION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_INSTALACION" MODIFY ("INFORME_INSTALACION_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PROFILES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."PROFILES" MODIFY ("DISPLAY_NAME" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."PROFILES" MODIFY ("NATURAL_KEY" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."PROFILES" ADD CONSTRAINT "PROFILE_PK" PRIMARY KEY ("PROFILE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."PROFILES" ADD CONSTRAINT "PROFILE_NATURAL_KEY_PK" UNIQUE ("NATURAL_KEY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."PROFILES" ADD CONSTRAINT "PROFILE_DISPLAY_NAME_PK" UNIQUE ("DISPLAY_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ASISTENCIAS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."ASISTENCIAS" ADD CONSTRAINT "ASISTENCIAS_PK" PRIMARY KEY ("ASISTENCIA_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."ASISTENCIAS" MODIFY ("CAPACITACION_FK" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."ASISTENCIAS" MODIFY ("TRABAJADOR_FK" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."ASISTENCIAS" MODIFY ("ASISTENCIA_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INFORMES_TRABAJADOR
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INFORMES_TRABAJADOR" ADD CONSTRAINT "INFORME_TRABAJADOR_PK" PRIMARY KEY ("INFORME_TRABAJADOR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_TRABAJADOR" MODIFY ("INFORME_TRABAJADOR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPRESAS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("EMPRESA_ID" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("DIRECCION" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("TELEFONO" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."EMPRESAS" ADD CONSTRAINT "EMPRESAS_PK" PRIMARY KEY ("EMPRESA_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table INSTALACIONES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("INSTALACION_ID" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INSTALACIONES" ADD CONSTRAINT "TABLE1_PK" PRIMARY KEY ("INSTALACION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("EMPRESA_FK" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SAFE_USER"."INSTALACIONES" MODIFY ("CODIGO" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table ASISTENCIAS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."ASISTENCIAS" ADD CONSTRAINT "ASISTENCIAS_FK1" FOREIGN KEY ("TRABAJADOR_FK")
	  REFERENCES "SAFE_USER"."TRABAJADORES" ("TRABAJADOR_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."ASISTENCIAS" ADD CONSTRAINT "ASISTENCIAS_FK2" FOREIGN KEY ("CAPACITACION_FK")
	  REFERENCES "SAFE_USER"."CAPACITACIONES" ("CAPACITACION_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INFORMES_DETALLES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" ADD CONSTRAINT "INFORMES_DETALLES_FK1" FOREIGN KEY ("PREVENSIONISTA_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" ADD CONSTRAINT "INFORMES_DETALLES_FK2" FOREIGN KEY ("TECNICO_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_DETALLES" ADD CONSTRAINT "INFORMES_DETALLES_FK3" FOREIGN KEY ("SUPERVISOR_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INFORMES_INSTALACION
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INFORMES_INSTALACION" ADD CONSTRAINT "INFORMES_INSTALACION_FK1" FOREIGN KEY ("INSTALACION_FK")
	  REFERENCES "SAFE_USER"."INSTALACIONES" ("INSTALACION_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_INSTALACION" ADD CONSTRAINT "INFORMES_INSTALACION_FK2" FOREIGN KEY ("INFORME_DETALLE_FK")
	  REFERENCES "SAFE_USER"."INFORMES_DETALLES" ("INFORME_DETALLE_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INFORMES_TRABAJADOR
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INFORMES_TRABAJADOR" ADD CONSTRAINT "INFORMES_TRABAJADOR_FK1" FOREIGN KEY ("INFORME_DETALLE_FK")
	  REFERENCES "SAFE_USER"."INFORMES_DETALLES" ("INFORME_DETALLE_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."INFORMES_TRABAJADOR" ADD CONSTRAINT "INFORMES_TRABAJADOR_FK2" FOREIGN KEY ("TRABAJADOR_FK")
	  REFERENCES "SAFE_USER"."TRABAJADORES" ("TRABAJADOR_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INSTALACIONES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."INSTALACIONES" ADD CONSTRAINT "INSTALACIONES_FK1" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES "SAFE_USER"."EMPRESAS" ("EMPRESA_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TRABAJADORES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."TRABAJADORES" ADD CONSTRAINT "TRABAJADORES_FK1" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES "SAFE_USER"."EMPRESAS" ("EMPRESA_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERS_PROFILES
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."USERS_PROFILES" ADD CONSTRAINT "FK_USERS_PROFILES_PROFILES" FOREIGN KEY ("PROFILES_FK")
	  REFERENCES "SAFE_USER"."PROFILES" ("PROFILE_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."USERS_PROFILES" ADD CONSTRAINT "FK_USERS_PROFILES_USERS" FOREIGN KEY ("USERS_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table VISITAS_MEDICAS
--------------------------------------------------------

  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" ADD CONSTRAINT "VISITAS_MEDICAS_FK1" FOREIGN KEY ("EMPRESA_FK")
	  REFERENCES "SAFE_USER"."EMPRESAS" ("EMPRESA_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" ADD CONSTRAINT "VISITAS_MEDICAS_FK2" FOREIGN KEY ("MEDICO_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
  ALTER TABLE "SAFE_USER"."VISITAS_MEDICAS" ADD CONSTRAINT "VISITAS_MEDICAS_FK3" FOREIGN KEY ("SUPERVISOR_FK")
	  REFERENCES "SAFE_USER"."USERS" ("USER_ID") ENABLE;
