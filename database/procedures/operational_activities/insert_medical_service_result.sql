DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_medical_service_result`(
	in p_id_serviciu_medical int,
    in p_id_programare int,
    in p_rezultat varchar(255)
)
BEGIN
	INSERT INTO rezultat_serviciu_medical VALUES(
		p_id_serviciu_medical,
        p_id_programare,
		p_rezultat
    );
END
//
DELIMITER;