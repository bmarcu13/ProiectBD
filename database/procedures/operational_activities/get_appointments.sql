CREATE DEFINER=`root`@`localhost` PROCEDURE `get_appointments`(
	in p_data DATE, 
    in p_cnp_medic VARCHAR(13)
)
BEGIN
	SELECT programare.id_programare, pacient.nume, pacient.prenume, programare.ora_programare, programare.pacient_inregistrat
    FROM programare
    INNER JOIN pacient ON pacient.cnp_pacient = programare.cnp_pacient
    WHERE programare.data_programare = p_data 
    AND programare.cnp_medic = p_cnp_medic
    AND 
    (SELECT COUNT(*) 
    FROM raport_medical AS rm
    WHERE rm.id_programare = programare.id_programare)
    = 0;
END