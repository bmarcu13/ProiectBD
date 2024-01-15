DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_specializations_for_doctor`(in p_cnp_medic VARCHAR(13))
BEGIN
	SELECT serviciu_medical.id_serviciu_medical,
		serviciu_medical.denumire_serviciu_medical, 
		serviciu_medical.durata, 
        serviciu_medical.pret
    FROM medic_specializare 
    INNER JOIN specializare 
    ON specializare.id_specializare = medic_specializare.id_specializare
    INNER JOIN serviciu_medical
    ON serviciu_medical.id_specializare = specializare.id_specializare
    WHERE medic_specializare.cnp_medic = p_cnp_medic
    UNION 
    SELECT serviciu_medical.id_serviciu_medical, 
		serviciu_medical.denumire_serviciu_medical, 
        serviciu_medical.durata, 
        serviciu_medical.pret
	FROM serviciu_medical
	WHERE serviciu_medical.id_serviciu_medical = 20;
END
//
DELIMITER ;