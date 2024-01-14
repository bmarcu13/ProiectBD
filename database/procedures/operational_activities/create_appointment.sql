CREATE DEFINER=`root`@`localhost` PROCEDURE `create_appointment`(
	in p_cnp_pacient VARCHAR(13), 
    in p_nume_pacient VARCHAR(20),
    in p_prenume_pacient VARCHAR(20),
    in p_cnp_medic VARCHAR(13),
    in p_data DATE,
    in p_ora TIME,
    in p_durata TIME,
    out p_id INT
    )
BEGIN
	DECLARE var_resultsNr INT;
    
	SELECT COUNT(*) INTO var_resultsNr
    FROM programare
    WHERE p_data = programare.data_programare 
    AND p_cnp_medic = programare.cnp_medic
    AND (p_ora 
		between programare.ora_programare 
        and ADDTIME(programare.ora_programare, programare.durata_programare)
    OR ADDTIME(p_ora, p_durata)
		between programare.ora_programare
        and ADDTIME(programare.ora_programare, programare.durata_programare));
        
	IF var_resultsNr > 0 THEN 
		SET p_id = -1;
	ELSE 
		SELECT COUNT(*) INTO var_resultsNr
        FROM pacient
        WHERE p_cnp_pacient = pacient.cnp_pacient;
            
		IF var_resultsNr = 0 THEN
			INSERT INTO pacient 
            VALUES (
				p_cnp_pacient,
                p_nume_pacient,
                p_prenume_pacient
            );
        END IF;
    
		INSERT INTO programare (
			cnp_pacient, 
            cnp_medic, 
            pacient_inregistrat, 
            data_programare, 
            ora_programare,
            durata_programare
		)
        VALUES (
			p_cnp_pacient,
            p_cnp_medic,
            0,
            p_data,
            p_ora, 
            p_durata
        );
        
		SET p_id = last_insert_id();
    END IF;
END