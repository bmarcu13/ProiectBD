INSERT INTO `analiza` (descriere_analiza, limita_inferioara, limita_superioara) VALUES
 ('Numar leucocite',4000,11000),
 ('Numar eritrocite',4000000,6000000),
 ('Concentratie hemoglobina',12,16),
 ('Numar trombocite',150000,450000),
 ('Hematocrit',39,50),
 ('Test HIV',NULL,NULL),
 ('Test hepatita B',NULL,NULL),
 ('Test hepatita C',NULL,NULL),
 ('Test helicobacter',NULL,NULL),
 ('Test COVID-19',NULL,NULL),
 ('Numar limfocite',20,55);
 
 INSERT INTO `departament` (denumire_departament) VALUES 
 ('Resurse Umane'),
 ('Financiar-Contabil'),
 ('Medical');
 
 INSERT INTO `functie` (denumire_functie) VALUES 
 ('Inspector resurse umane'),
 ('Expert financiar-contabil'),
 ('Receptioner'),
 ('Asistent medical'),
 ('Medic');
 
 INSERT INTO `post_didactic` (denumire_post_didactic) VALUES 
 ('Preparator'),
 ('Asistent'),
 ('Lector'),
 ('Conferentiar'),
 ('Profesor');
 
 INSERT INTO `specializare` (denumire_specializare) VALUES 
 ('Cardiologie'),
 ('Medicina primara'),
 ('Ortopedie'),
 ('Neurologie'),
 ('Chirurgie generala'),
 ('Radiologie'),
 ('Oftalmologie');

 
 INSERT INTO `serviciu_medical` (id_specializare, denumire_serviciu_medical, pret, durata) VALUES 
 (1,'ECG',70,'00:20:00'),
 (1,'Ecocardiografie',350,'00:12:00'),
 (1,'Cateterism cardiac',280,'01:00:00'),
 (2,'Vaccinari si imunizari',30,'00:05:00'),
 (2,'Consultatie periodica',20,'00:15:00'),
 (4,'EEG',600,'01:20:00'),
 (4,'IRM sistem nervos',400,'02:13:00'),
 (4,'Consultatii tulburari neurologice',180,'01:50:00'),
 (3,'Consultatii pentru dureri musculare',130,'00:30:00'),
 (3,'Tratament fracturi si luxatii',100,'02:08:00'),
 (3,'Artroplastie',235,'05:07:00'),
 (5,'Chirurgie de apendicectomie',250,'02:40:00'),
 (5,'Chirurgie de tiroidă',180,'03:18:00'),
 (6,'Radiografie standard',155,'01:05:00'),
 (6,'Tomografie computerizată',78,'00:42:00'),
 (6,'IRM tesuturi moi',396,'02:13:00'),
 (7,'Evaluare vedere',47,'00:17:00'),
 (7,'Prescriere ochelari',28,'00:10:00'),
 (7,'Chirurgie refractiva',792,'02:37:00');
 
 INSERT INTO `tip_asistent` (denumire_tip_asistent) VALUES 
 ('Generalist'),
 ('Laborator'),
 ('Radiologie');
 
 INSERT INTO `titlu_stiintific` (denumire_titlu_stiintific) VALUES 
 ('Doctorand'),
 ('Doctor');

