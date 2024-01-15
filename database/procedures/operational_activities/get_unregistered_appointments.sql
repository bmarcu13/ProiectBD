CREATE DEFINER=`root`@`localhost` PROCEDURE `get_unregistered_appointments`()
BEGIN
	SELECT programare.id_programare, pacient.nume, pacient.prenume, programare.ora_programare
    FROM programare
    INNER JOIN pacient ON pacient.cnp_pacient = programare.cnp_pacient
    WHERE programare.pacient_inregistrat = 0
		AND programare.data_programare = curdate();
END