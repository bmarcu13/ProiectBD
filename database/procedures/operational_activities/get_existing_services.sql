DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_existing_services`(in p_id_programare int)
BEGIN
	SELECT sm.denumire_serviciu_medical, sm.id_serviciu_medical
    FROM serviciu_medical AS sm
    INNER JOIN programare_serviciu_medical AS psm 
    ON psm.id_serviciu_medical = sm.id_serviciu_medical
    AND psm.id_programare = p_id_programare;
END
//
DELIMITER;