INSERT INTO `policlinica`.`angajat` (`cnp_angajat`, `id_departament`, `id_functie`, `nume`, `prenume`, `adresa`, `numar_telefon`, `email`, `iban`, `data_angajare`, `salariu_negociat`, `nr_ore`) VALUES ('1980123456789', '3', '5', 'Popescu', 'Ion', 'Str. Florilor, nr. 10, București', '0722123456', 'ion.popescu@email.com', 'RO12RZBR1234567890123456', '2022-01-15', '5000', '40');

INSERT INTO angajat (cnp_angajat, id_utilizator, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore)
VALUES
    ('4567890123456', NULL, 3, 4, 'Mihai', 'Ana', 'Aleea Florilor nr. 20', '0745678901', 'ana.mihai@example.com', 'RO09BTRL0001234567890123', 1004, '2023-04-10', 5200, 42),
    ('5678901234567', NULL, 3, 5, 'Constantin', 'Dragos', 'Bulevardul Libertatii nr. 15', '0756789012', 'dragos.constantin@example.com', 'RO09CECE0001234567890123', 1005, '2023-05-05', 4900, 37),
    ('6789012345678', NULL, 3, 4, 'Dumitru', 'Miruna', 'Strada Independentei nr. 8', '0767890123', 'miruna.dumitru@example.com', 'RO09RZBR0001234567890123', 1006, '2023-06-20', 5300, 40);


INSERT INTO policlinica.angajat (cnp_angajat, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore) VALUES ('1234567890123', '2', '4', 'Popescu', 'Ana', 'Str. Florilor, nr. 10, București', '0712345678', 'ana.popescu@email.com', 'RO1234567890123456789012', '98765', '2022-03-15', '5000', '40');
INSERT INTO policlinica.angajat (cnp_angajat, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore) VALUES ('2345678901234', '1', '3', 'Ionescu', 'Ion', 'Str. Libertății, Nr. 25, Cluj-Napoca', '0723456789', 'ion.ionescu@email.com', 'RO9876543210987654321098', '54321', '2021-08-20', '7500', '38');
INSERT INTO policlinica.angajat (cnp_angajat, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore) VALUES ('3456789012345', '3', '4', 'Radu', 'Maria', 'Str. Mihai Viteazu, Nr. 5, Timișoara', '0734567890', 'maria.radu@email.com', 'RO5678901234567890123456', '12345', '2023-01-10', '6758', '38');

INSERT INTO angajati (cnp_angajat, id_utilizator, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore)
VALUES
    ('1231231231231', NULL, 3, 4, 'Simion', 'Andreea', 'Strada Soarelui nr. 25', '0700000000', 'andreea.simion@example.com', 'RO09BTRL0000000000000001', 1011, '2023-07-01', 5200, 42),
    ('2342342342342', NULL, 3, 5, 'Georgescu', 'Cristina', 'Bulevardul Luna nr. 15', '0700000001', 'cristina.georgescu@example.com', 'RO09CECE0000000000000002', 1012, '2023-08-02', 4900, 37),
    ('3453453453453', NULL, 3, 4, 'Ionescu', 'Gabriel', 'Aleea Stelelor nr. 8', '0700000002', 'gabriel.ionescu@example.com', 'RO09RZBR0000000000000003', 1013, '2023-09-03', 5300, 40),
    ('4564564564564', NULL, 3, 5, 'Popovici', 'Maria', 'Strada Florilor nr. 21', '0700000003', 'maria.popovici@example.com', 'RO09BTRL0000000000000004', 1014, '2023-10-04', 5200, 42),
    ('5675675675675', NULL, 3, 4, 'Barbu', 'Alexandru', 'Bulevardul Soarelui nr. 12', '0700000004', 'alexandru.barbu@example.com', 'RO09CECE0000000000000005', 1015, '2023-11-05', 4900, 37),
    ('6786786786786', NULL, 3, 5, 'Dumitrache', 'Catalin', 'Aleea Luna nr. 9', '0700000005', 'catalin.dumitrache@example.com', 'RO09RZBR0000000000000006', 1016, '2023-12-06', 5300, 40),
    ('7897897897897', NULL, 3, 4, 'Antonescu', 'Elena', 'Strada Jupiter nr. 18', '0700000006', 'elena.antonescu@example.com', 'RO09BTRL0000000000000007', 1017, '2024-01-07', 5200, 42),
    ('8908908908908', NULL, 3, 5, 'Florea', 'Mihai', 'Bulevardul Mercur nr. 14', '0700000007', 'mihai.florea@example.com', 'RO09CECE0000000000000008', 1018, '2024-02-08', 4900, 37),
    ('9019019019019', NULL, 3, 4, 'Stanescu', 'Larisa', 'Aleea Venus nr. 7', '0700000008', 'larisa.stanescu@example.com', 'RO09RZBR0000000000000009', 1019, '2024-03-09', 5300, 40),
    ('0120120120120', NULL, 3, 5, 'Munteanu', 'George', 'Strada Saturn nr. 16', '0700000009', 'george.munteanu@example.com', 'RO09BTRL0000000000000010', 1020, '2024-04-10', 5200, 42);
    
    -- Generare de date pentru tabelul angajati
INSERT INTO angajat (cnp_angajat, id_utilizator, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore)
VALUES
    ('1900101123456', NULL, 1, 1, 'Ionescu', 'Ana', 'Strada Victoriei nr. 20', '0712345678', 'ana.ionescu@example.com', 'RO09INGB0001234567890123', 1004, '2023-04-10', 5200, 42),
    ('2900102123456', NULL, 1, 3, 'Vasilescu', 'Mihai', 'Bulevardul Libertatii nr. 5', '0723456789', 'mihai.vasilescu@example.com', 'RO09BRDE0001234567890123', 1005, '2023-05-15', 4900, 39),
    ('3900103123456', NULL, 1, 1, 'Georgescu', 'Alex', 'Aleea Florilor nr. 12', '0734567890', 'alex.georgescu@example.com', 'RO09RNCB0001234567890123', 1006, '2023-06-20', 5300, 41);


-- Generare suplimentară de date pentru tabelul angajati
INSERT INTO angajat (cnp_angajat, id_utilizator, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore)
VALUES
    ('4900104123456', NULL, 1, 1, 'Constantinescu', 'Elena', 'Bulevardul Unirii nr. 30', '0745678901', 'elena.constantinescu@example.com', 'RO09INGB0001234567890123', 1007, '2023-07-25', 5100, 40),
    ('5900105123456', NULL, 1, 3, 'Stancu', 'Daniel', 'Calea Dorobantilor nr. 8', '0756789012', 'daniel.stancu@example.com', 'RO09BRDE0001234567890123', 1008, '2023-08-30', 4800, 38),
    ('6900106123456', NULL, 1, 1, 'Dumitrescu', 'Andreea', 'Aleea Mihai Viteazu nr. 7', '0767890123', 'andreea.dumitrescu@example.com', 'RO09RNCB0001234567890123', 1009, '2023-09-05', 5200, 42),
    ('7900107123456', NULL, 1, 3, 'Dragomir', 'Gabriel', 'Strada Independentei nr. 15', '0778901234', 'gabriel.dragomir@example.com', 'RO09INGB0001234567890123', 1010, '2023-10-10', 4900, 39),
    ('8900108123456', NULL, 1, 1, 'Mihalache', 'Mihaela', 'Bulevardul Timisoara nr. 25', '0789012345', 'mihaela.mihalache@example.com', 'RO09BRDE0001234567890123', 1011, '2023-11-15', 5300, 41),
    ('9900109123456', NULL, 1, 3, 'Florea', 'Victor', 'Aleea Aviatorilor nr. 11', '0790123456', 'victor.florea@example.com', 'RO09RNCB0001234567890123', 1012, '2023-12-20', 5000, 40),
    ('0900110123456', NULL, 1, 1, 'Stanescu', 'Ana-Maria', 'Strada Ion Creanga nr. 18', '0801234567', 'anamaria.stanescu@example.com', 'RO09INGB0001234567890123', 1013, '2024-01-25', 5400, 43);

INSERT INTO angajat (cnp_angajat, id_utilizator, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore)
VALUES
    ('1930506321234', NULL, 2, 2, 'Popescu', 'Ion', 'Strada Exemplu nr. 1', '0712345678', 'ion.popescu@example.com', 'RO09INGB0001234567890123', 1001, '2023-01-15', 5000, 40),
    ('2881219361234', NULL, 2, 2, 'Ionescu', 'Maria', 'Bulevardul Principal nr. 10', '0723456789', 'maria.ionescu@example.com', 'RO09BRDE0001234567890123', 1002, '2023-02-20', 5500, 45),
    ('6930928111234', NULL, 2, 2, 'Popa', 'Andrei', 'Aleea Secundara nr. 5', '0734567890', 'andrei.popa@example.com', 'RO09RNCB0001234567890123', 1003, '2023-03-25', 4800, 38),
    ('2950211121234', NULL, 2, 2, 'Mihai', 'Ana', 'Strada Florilor nr. 20', '0745678901', 'ana.mihai@example.com', 'RO09BTRL0001234567890123', 1004, '2023-04-30', 5200, 42),
    ('6940602121234', NULL, 2, 2, 'Georgescu', 'George', 'Bulevardul Libertatii nr. 15', '0756789012', 'george.georgescu@example.com', 'RO09BACX0001234567890123', 1005, '2023-06-05', 4900, 37);
    
    -- Generate 5 rows of dummy data
INSERT INTO angajat (cnp_angajat, id_utilizator, id_departament, id_functie, nume, prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore)
VALUES
    ('1970101234567', NULL, 3, 5, 'Ionescu', 'Ana', 'Strada Florilor nr. 12', '0712345678', 'ana.ionescu@example.com', 'RO09INGB0001234567890123', 1001, '2023-01-15', 5000, 40),
    ('2880212345678', NULL, 3, 5, 'Popescu', 'Mihai', 'Bulevardul Libertatii nr. 10', '0723456789', 'mihai.popescu@example.com', 'RO09BRDE0001234567890123', 1002, '2023-02-20', 5500, 45),
    ('3900301234567', NULL, 3, 5, 'Georgescu', 'Andreea', 'Aleea Florilor nr. 5', '0734567890', 'andreea.georgescu@example.com', 'RO09RNCB0001234567890123', 1003, '2023-03-25', 4800, 38),
    ('4900412345678', NULL, 3, 5, 'Dumitru', 'Maria', 'Strada Independentei nr. 8', '0745678901', 'maria.dumitru@example.com', 'RO09BTRL0001234567890123', 1004, '2023-04-30', 5100, 42),
    ('5900501234567', NULL, 3, 5, 'Stoica', 'Gabriel', 'Bulevardul Soarelui nr. 12', '0756789012', 'gabriel.stoica@example.com', 'RO09CECE0001234567890123', 1005, '2023-05-15', 4900, 37);




    
    