DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_investigation_result`(
	in p_id_analiza INT,
    in p_id_programare INT, 
    in p_valoare INT,
    in p_pozitiv_negativ TINYINT
)
BEGIN
	INSERT INTO rezultat_analiza VALUES (p_id_analiza, p_id_programare, p_valoare, p_pozitiv_negativ)
    ON DUPLICATE KEY UPDATE 
		valoare = p_valoare,
        pozitiv_negativ = p_pozitiv_negativ;
END
//
DELIMITER;