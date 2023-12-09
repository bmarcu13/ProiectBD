INSERT INTO program_policlinica (id_unitate_medicala, zi, ora_deschidere, ora_inchidere)
VALUES 
-- Program pentru Spitalul Municipal (id_unitate_medicala: 1)
(1, 'Luni', '08:00:00', '18:00:00'),
(1, 'Marti', '08:00:00', '18:00:00'),
(1, 'Miercuri', '08:00:00', '18:00:00'),
(1, 'Joi', '08:00:00', '18:00:00'),
(1, 'Vineri', '08:00:00', '16:30:00'),
(1, 'Sambata', '09:00:00', '13:00:00'),
(1, 'Duminica', NULL, NULL),

-- Program pentru Centrul Medical "Floare de Lotus" (id_unitate_medicala: 2)
(2, 'Luni', '09:00:00', '19:00:00'),
(2, 'Marti', '09:00:00', '19:00:00'),
(2, 'Miercuri', '09:00:00', '19:00:00'),
(2, 'Joi', '09:00:00', '19:00:00'),
(2, 'Vineri', '09:00:00', '17:30:00'),
(2, 'Sambata', '10:00:00', '14:00:00'),
(2, 'Duminica', NULL, NULL),

-- Program pentru Clinica "Speranta" (id_unitate_medicala: 3)
(3, 'Luni', '10:00:00', '20:00:00'),
(3, 'Marti', '10:00:00', '20:00:00'),
(3, 'Miercuri', '10:00:00', '20:00:00'),
(3, 'Joi', '10:00:00', '20:00:00'),
(3, 'Vineri', '10:00:00', '18:30:00'),
(3, 'Sambata', '11:00:00', '15:00:00'),
(3, 'Duminica', NULL, NULL),

-- Program pentru Policlinica "Noua Generatie" (id_unitate_medicala: 4)
(4, 'Luni', '08:30:00', '17:30:00'),
(4, 'Marti', '08:30:00', '17:30:00'),
(4, 'Miercuri', '08:30:00', '17:30:00'),
(4, 'Joi', '08:30:00', '17:30:00'),
(4, 'Vineri', '08:30:00', '16:00:00'),
(4, 'Sambata', '09:30:00', '13:30:00'),
(4, 'Duminica', NULL, NULL),

-- Program pentru Centrul de Recuperare Medicala (id_unitate_medicala: 5)
(5, 'Luni', '07:00:00', '16:30:00'),
(5, 'Marti', '07:00:00', '16:30:00'),
(5, 'Miercuri', '07:00:00', '16:30:00'),
(5, 'Joi', '07:00:00', '16:30:00'),
(5, 'Vineri', '07:00:00', '15:30:00'),
(5, 'Sambata', '08:00:00', '12:00:00'),
(5, 'Duminica', NULL, NULL);