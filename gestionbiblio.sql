/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de cr�ation :  14/06/2022 04:30:48                      */
/*==============================================================*/

Create database if not exists gestionbiblio;
use gestionbiblio;

drop table if exists CATEGORIE;

drop table if exists EMPRUNT;

drop table if exists LECTEUR;

drop table if exists OUVRAGE;

drop table if exists SANCTION;

drop table if exists SANCTIONER;

drop table if exists SPECIALITE;

drop table if exists LOGIN;

/*==============================================================*/
/* Table : CATEGORIE                                            */
/*==============================================================*/
create table CATEGORIE
(
   CODE_CATEGORIE       int not null,
   TYPE_LECTEUR         text,
   primary key (CODE_CATEGORIE)
);

/*==============================================================*/
/* Table : EMPRUNT                                              */
/*==============================================================*/
create table EMPRUNT
(
   NUM_OUV              int not null,
   NUM_LECTEUR          int not null,
   DATE_PRET            text,
   DATE_REMISE          text,
   primary key (NUM_OUV, NUM_LECTEUR)
);

/*==============================================================*/
/* Table : LECTEUR                                              */
/*==============================================================*/
create table LECTEUR
(
   NUM_LECTEUR          int auto_increment not null,
   CODE_CATEGORIE       int,
   NOM_LECTEUR          text,
   PRENOM_LECTEUR       text,
   DATE_NAIS            text,
   LIEU_NAIS            text,
   primary key (NUM_LECTEUR)
);

/*==============================================================*/
/* Table : OUVRAGE                                              */
/*==============================================================*/
create table OUVRAGE
(
   NUM_OUV              int auto_increment not null,
   CODE_SPECIALITE      int,
   TITRE_OUV            text,
   NOM_AUTEUR           text,
   DATE_ENT_OUV         text,
   DATE_EDITION         text,
   EXEMPLAIRE           text,
   primary key (NUM_OUV)
);

/*==============================================================*/
/* Table : SANCTION                                             */
/*==============================================================*/
create table SANCTION
(
   NUM_SANCTION         int not null,
   DESIGNATION_SANCTION text,
   primary key (NUM_SANCTION)
);

/*==============================================================*/
/* Table : SANCTIONER                                           */
/*==============================================================*/
create table SANCTIONER
(
   NUM_LECTEUR          int not null,
   NUM_SANCTION         int not null,
   DATE_SANCTION        text,
   primary key (NUM_LECTEUR, NUM_SANCTION)
);

/*==============================================================*/
/* Table : SPECIALITE                                           */
/*==============================================================*/
create table SPECIALITE
(
   CODE_SPECIALITE      int not null,
   DESIGNATION          text,
   primary key (CODE_SPECIALITE)
);

/*==============================================================*/
/* Table : LOGIN                                                */
/*==============================================================*/
create table LOGIN
(
   username          text,
   password          text
);


insert into LOGIN VALUES('bachir','passer123');


alter table EMPRUNT add constraint FK_EMPRUNT foreign key (NUM_LECTEUR)
      references LECTEUR (NUM_LECTEUR) on delete restrict on update restrict;

alter table EMPRUNT add constraint FK_EMPRUNT2 foreign key (NUM_OUV)
      references OUVRAGE (NUM_OUV) on delete restrict on update restrict;

alter table LECTEUR add constraint FK_AVOIR2 foreign key (CODE_CATEGORIE)
      references CATEGORIE (CODE_CATEGORIE) on delete restrict on update restrict;

alter table OUVRAGE add constraint FK_APPARTIENT3 foreign key (CODE_SPECIALITE)
      references SPECIALITE (CODE_SPECIALITE) on delete restrict on update restrict;

alter table SANCTIONER add constraint FK_SANCTIONER foreign key (NUM_SANCTION)
      references SANCTION (NUM_SANCTION) on delete restrict on update restrict;

alter table SANCTIONER add constraint FK_SANCTIONER2 foreign key (NUM_LECTEUR)
      references LECTEUR (NUM_LECTEUR) on delete restrict on update restrict;


insert into categorie values(1,"employé");
insert into categorie values (2,"professeur");
insert into categorie values (3,"Etudiant");

insert into lecteur(code_categorie,nom_lecteur,prenom_lecteur,date_nais,lieu_nais)
Values  ("1","houmed","bachir","10-08-1992","djibouti"),
	      ("3","mohamed","farid","10-08-1992","france"),
	      ("2","mohamed","ali","10-08-1992","éthiopie");

insert into specialite values(1,"Philosophie"),(2,"Histoire"),(3,"Français"),(4,"Mathématique"),
      (5,"Physique"),(6,"Chimie"),(7,"Svt");

insert into SANCTION VALUES(1,"sanctionné"),(2,"non sanctionné");

#select prenom_lecteur,nom_lecteur,type_lecteur,date_nais,lieu_nais 
#from lecteur 
#INNER JOIN categorie USING(code_categorie);

