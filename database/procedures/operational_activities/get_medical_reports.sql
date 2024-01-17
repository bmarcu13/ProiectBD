DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_medical_reports`(in p_cnp_pacient varchar(13))
BEGIN
	SELECT prgm.id_programare, prgm.data_programare, sm.simptome, sm.diagnostic, sm.recomandari
    FROM serviciu_medical AS sm
    INNER JOIN programare AS prgm
    ON sm.id_programare = prgm.id_programare
    WHERE prgm.cnp_pacient = p_cnp_pacient;
END
//
DELIMITER;