INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
-- Orar pentru CNP: 0120120120120
('0120120120120', 1, 1, 'Luni', NULL, '08:00:00', '18:00:00'),
('0120120120120', 1, 1, 'Miercuri', NULL, '08:00:00', '18:00:00'),
('0120120120120', 1, 1, 'Sambata', NULL, '09:00:00', '13:00:00'),

-- Orar pentru CNP: 0900110123456
('0900110123456', 2, 1, 'Marti', NULL, '09:00:00', '19:00:00'),
('0900110123456', 2, 1, 'Joi', NULL, '09:00:00', '19:00:00'),
('0900110123456', 2, 1, 'Sambata', NULL, '10:00:00', '14:00:00'),

-- Orar pentru CNP: 1231231231231
('1231231231231', 3, 1, 'Luni', NULL, '10:00:00', '20:00:00'),
('1231231231231', 3, 1, 'Miercuri', NULL, '10:00:00', '20:00:00'),
('1231231231231', 3, 1, 'Sambata', NULL, '11:00:00', '15:00:00'),

-- Orar pentru CNP: 1234567890123
('1234567890123', 4, 1, 'Luni', NULL, '08:30:00', '17:30:00'),
('1234567890123', 4, 1, 'Miercuri', NULL, '08:30:00', '17:30:00'),
('1234567890123', 4, 1, 'Sambata', NULL, '09:30:00', '13:30:00'),

-- Orar pentru CNP: 1900101123456
('1900101123456', 5, 1, 'Marti', NULL, '07:00:00', '16:30:00'),
('1900101123456', 5, 1, 'Joi', NULL, '07:00:00', '16:30:00'),
('1900101123456', 5, 1, 'Sambata', NULL, '08:00:00', '12:00:00');

-- AICI II AL DOILEA SET

INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
-- CNP: 2345678901234
('2345678901234', 1, 1, 'Luni', NULL, '08:00:00', '18:00:00'),
('2345678901234', 1, 1, 'Miercuri', NULL, '08:00:00', '18:00:00'),
('2345678901234', 1, 1, 'Vineri', NULL, '08:00:00', '16:30:00'),

-- CNP: 2880212345678
('2880212345678', 2, 1, 'Luni', NULL, '09:00:00', '19:00:00'),
('2880212345678', 2, 1, 'Marti', NULL, '09:00:00', '19:00:00'),
('2880212345678', 2, 1, 'Joi', NULL, '09:00:00', '19:00:00'),

-- CNP: 2881219361234
('2881219361234', 3, 1, 'Luni', NULL, '10:00:00', '20:00:00'),
('2881219361234', 3, 1, 'Marti', NULL, '10:00:00', '20:00:00'),
('2881219361234', 3, 1, 'Miercuri', NULL, '10:00:00', '20:00:00'),

-- CNP: 2900102123456
('2900102123456', 4, 1, 'Luni', NULL, '08:30:00', '17:30:00'),
('2900102123456', 4, 1, 'Marti', NULL, '08:30:00', '17:30:00'),
('2900102123456', 4, 1, 'Miercuri', NULL, '08:30:00', '17:30:00'),

-- CNP: 2950211121234
('2950211121234', 5, 1, 'Marti', NULL, '07:00:00', '16:30:00'),
('2950211121234', 5, 1, 'Joi', NULL, '07:00:00', '16:30:00'),
('2950211121234', 5, 1, 'Sambata', NULL, '08:00:00', '12:00:00'),

-- CNP: 3453453453453
('3453453453453', 1, 1, 'Marti', NULL, '08:00:00', '18:00:00'),
('3453453453453', 1, 1, 'Joi', NULL, '08:00:00', '18:00:00'),
('3453453453453', 1, 1, 'Sambata', NULL, '09:00:00', '13:00:00');

-- AL TREILEA SET

INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
-- Orar pentru cnp 3456789012345
('3456789012345', 1, 1, 'Luni', NULL, '08:00:00', '18:00:00'),
('3456789012345', 1, 1, 'Miercuri', NULL, '08:00:00', '18:00:00'),
('3456789012345', 1, 1, 'Vineri', NULL, '08:00:00', '16:30:00'),

-- Orar pentru cnp 3900103123456
('3900103123456', 2, 0, NULL, '2023-04-01', '09:00:00', '19:00:00'),
('3900103123456', 2, 0, NULL, '2023-04-03', '09:00:00', '19:00:00'),
('3900103123456', 2, 0, NULL, '2023-04-05', '09:00:00', '17:30:00'),

-- Orar pentru cnp 3900301234567
('3900301234567', 3, 1, 'Luni', NULL, '10:00:00', '20:00:00'),
('3900301234567', 3, 1, 'Miercuri', NULL, '10:00:00', '20:00:00'),
('3900301234567', 3, 1, 'Vineri', NULL, '10:00:00', '18:30:00'),

-- Orar pentru cnp 4564564564564
('4564564564564', 4, 0, NULL, '2023-04-02', '08:30:00', '17:30:00'),
('4564564564564', 4, 0, NULL, '2023-04-04', '08:30:00', '17:30:00'),
('4564564564564', 4, 0, NULL, '2023-04-06', '08:30:00', '16:00:00'),

-- Orar pentru cnp 4567890123456
('4567890123456', 5, 1, 'Luni', NULL, '07:00:00', '16:30:00'),
('4567890123456', 5, 1, 'Miercuri', NULL, '07:00:00', '16:30:00'),
('4567890123456', 5, 1, 'Vineri', NULL, '07:00:00', '15:30:00'),

-- Orar pentru cnp 4900104123456
('4900104123456', 1, 0, NULL, '2023-04-01', '09:00:00', '18:00:00'),
('4900104123456', 1, 0, NULL, '2023-04-03', '09:00:00', '18:00:00'),
('4900104123456', 1, 0, NULL, '2023-04-05', '09:00:00', '16:30:00');

-- AL PATRULEA SET

INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
-- Orar pentru CNP: 4900412345678
('4900412345678', 1, 1, 'Luni', NULL, '08:00:00', '16:30:00'),
('4900412345678', 1, 1, 'Marti', NULL, '08:00:00', '16:30:00'),
('4900412345678', 1, 1, 'Miercuri', NULL, '08:00:00', '16:30:00'),

-- Orar pentru CNP: 5675675675675
('5675675675675', 2, 1, 'Marti', NULL, '09:00:00', '17:30:00'),
('5675675675675', 2, 1, 'Joi', NULL, '09:00:00', '17:30:00'),
('5675675675675', 2, 1, 'Vineri', NULL, '09:00:00', '17:30:00'),

-- Orar pentru CNP: 5678901234567
('5678901234567', 3, 1, 'Luni', NULL, '10:00:00', '18:30:00'),
('5678901234567', 3, 1, 'Miercuri', NULL, '10:00:00', '18:30:00'),
('5678901234567', 3, 1, 'Vineri', NULL, '10:00:00', '18:30:00'),

-- Orar pentru CNP: 5900105123456
('5900105123456', 4, 1, 'Miercuri', NULL, '08:30:00', '17:30:00'),
('5900105123456', 4, 1, 'Joi', NULL, '08:30:00', '17:30:00'),
('5900105123456', 4, 1, 'Vineri', NULL, '08:30:00', '17:30:00'),

-- Orar pentru CNP: 5900501234567
('5900501234567', 5, 1, 'Luni', NULL, '07:00:00', '16:30:00'),
('5900501234567', 5, 1, 'Miercuri', NULL, '07:00:00', '16:30:00'),
('5900501234567', 5, 1, 'Vineri', NULL, '07:00:00', '16:30:00'),

-- Orar pentru CNP: 6786786786786
('6786786786786', 1, 1, 'Marti', NULL, '09:00:00', '18:00:00'),
('6786786786786', 1, 1, 'Joi', NULL, '09:00:00', '18:00:00'),
('6786786786786', 1, 1, 'Sambata', NULL, '09:00:00', '13:00:00');

-- AL CINCILEA SET

-- Pentru CNP-ul 6789012345678
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('6789012345678', 1, 1, 'Luni', NULL, '08:00:00', '18:00:00'),
('6789012345678', 1, 1, 'Miercuri', NULL, '08:00:00', '18:00:00'),
('6789012345678', 1, 1, 'Vineri', NULL, '08:00:00', '16:30:00');

-- Pentru CNP-ul 6900106123456
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('6900106123456', 2, 1, 'Marti', NULL, '09:00:00', '19:00:00'),
('6900106123456', 2, 1, 'Joi', NULL, '09:00:00', '19:00:00'),
('6900106123456', 2, 1, 'Sambata', NULL, '10:00:00', '14:00:00');

-- Pentru CNP-ul 6930928111234
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('6930928111234', 3, 1, 'Luni', NULL, '10:00:00', '20:00:00'),
('6930928111234', 3, 1, 'Miercuri', NULL, '10:00:00', '20:00:00'),
('6930928111234', 3, 1, 'Vineri', NULL, '10:00:00', '18:30:00');

-- Pentru CNP-ul 6940602121234
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('6940602121234', 4, 1, 'Luni', NULL, '08:30:00', '17:30:00'),
('6940602121234', 4, 1, 'Marti', NULL, '08:30:00', '17:30:00'),
('6940602121234', 4, 1, 'Joi', NULL, '08:30:00', '17:30:00');

-- Pentru CNP-ul 7897897897897
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('7897897897897', 5, 1, 'Luni', NULL, '07:00:00', '16:30:00'),
('7897897897897', 5, 1, 'Miercuri', NULL, '07:00:00', '16:30:00'),
('7897897897897', 5, 1, 'Vineri', NULL, '07:00:00', '15:30:00');

-- Pentru CNP-ul 7900107123456
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('7900107123456', 1, 1, 'Marti', NULL, '08:00:00', '18:00:00'),
('7900107123456', 1, 1, 'Joi', NULL, '08:00:00', '18:00:00'),
('7900107123456', 1, 1, 'Sambata', NULL, '09:00:00', '13:00:00');

-- AL SASELEA SET

-- Orar pentru CNP-ul 8900108123456
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('8900108123456', 1, 1, 'Luni', NULL, '08:00:00', '18:00:00'),
('8900108123456', 1, 1, 'Marti', NULL, '08:00:00', '18:00:00'),
('8900108123456', 1, 1, 'Miercuri', NULL, '08:00:00', '18:00:00');

-- Orar pentru CNP-ul 8908908908908
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('8908908908908', 2, 1, 'Joi', NULL, '09:00:00', '19:00:00'),
('8908908908908', 2, 1, 'Vineri', NULL, '09:00:00', '17:30:00'),
('8908908908908', 2, 1, 'Sambata', NULL, '10:00:00', '14:00:00');

-- Orar pentru CNP-ul 9019019019019
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('9019019019019', 3, 1, 'Luni', NULL, '10:00:00', '20:00:00'),
('9019019019019', 3, 1, 'Marti', NULL, '10:00:00', '20:00:00'),
('9019019019019', 3, 1, 'Miercuri', NULL, '10:00:00', '20:00:00');

-- Orar pentru CNP-ul 9900109123456
INSERT INTO orar (cnp_angajat, id_unitate_medicala, is_generic, zi, data, ora_incepere, ora_terminare)
VALUES 
('9900109123456', 4, 1, 'Joi', NULL, '08:30:00', '17:30:00'),
('9900109123456', 4, 1, 'Vineri', NULL, '08:30:00', '16:00:00'),
('9900109123456', 4, 1, 'Sambata', NULL, '09:30:00', '13:30:00');