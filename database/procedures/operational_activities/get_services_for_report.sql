DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_services_for_report`(in p_id_programare int)
BEGIN
	SELECT sm.denumire_serviciu_medical, rsm.rezultat
    FROM rezultat_serviciu_medical AS rsm
    INNER JOIN serviciu_medical AS sm
    ON sm.id_serviciu_medical = rsm.id_serviciu_medical
    WHERE rsm.id_programare = p_id_programare;
END
//
DELIMITER;