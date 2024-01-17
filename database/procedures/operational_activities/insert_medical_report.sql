DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_medical_report`(
	in p_id_programare int,
    in p_nume_medic_recomandare varchar(20),
    in p_prenume_medic_recomandare varchar(20),
	in p_nume_asistent varchar(20),
    in p_prenume_asistent varchar(20),
    in p_simptome varchar(255),
    in p_diagnostic varchar(255),
    in p_recomandari varchar(255)
)
BEGIN
	DECLARE var_cnp_medic_recomandare VARCHAR(13);
    DECLARE var_cnp_asistent VARCHAR(13);
    
    IF p_nume_medic_recomandare != NULL AND p_prenume_medic_recomandare != NULL THEN
		SELECT cnp_angajat INTO var_cnp_medic_recomandare 
        FROM angajat
        INNER JOIN medic 
        ON medic.cnp_medic = angajat.cnp_angajat
        WHERE angajat.nume = p_nume_medic_recomandare AND angajat.prenume = p_prenume_medic_recomandare;
    END IF;
    
	IF p_nume_asistent != NULL AND p_prenume_asistent != NULL THEN
		SELECT cnp_angajat INTO var_cnp_asistent
        FROM angajat
        INNER JOIN asistent 
        ON asistent.cnp_asistent = angajat.cnp_angajat
        WHERE angajat.nume = p_nume_asistent AND angajat.prenume = p_prenume_asistent;
    END IF;
    
    INSERT INTO raport_medical VALUES(
		p_id_programare, 
        var_cnp_medic_recomandare, 
        var_cnp_asistent,
        p_simptome,
        p_diagnostic,
        p_recomandari,
        1
	);
    
END
//
DELIMITER;