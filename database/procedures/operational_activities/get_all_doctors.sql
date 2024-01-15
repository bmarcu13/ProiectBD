DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_doctors`()
BEGIN
	SELECT medic.cnp_medic, angajat.prenume, angajat.nume
    FROM medic
    INNER JOIN angajat on medic.cnp_medic = angajat.cnp_angajat;
END
//
DELIMITER ;