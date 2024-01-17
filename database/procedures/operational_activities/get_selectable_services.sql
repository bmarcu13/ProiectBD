DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_selectable_services`(in p_cnp_doctor varchar(13))
BEGIN
	SELECT s.denumire_serviciu_medical, s.id_serviciu_medical
    FROM serviciu_medical as s
    INNER JOIN medic_specializare 
    ON s.id_specializare = medic_specializare.id_specializare AND cnp_medic = p_cnp_doctor;
END
//
DELIMITER;