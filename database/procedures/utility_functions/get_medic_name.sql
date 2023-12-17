DELIMITER //
CREATE FUNCTION get_medic_name(param_cnp_medic VARCHAR(13)) RETURNS VARCHAR(41)
READS SQL DATA
BEGIN

DECLARE full_name VARCHAR(41);

SELECT CONCAT(nume, ' ', prenume) into full_name FROM medic
INNER JOIN angajat ON medic.cnp_medic = angajat.cnp_angajat
WHERE medic.cnp_medic = param_cnp_medic;

RETURN full_name;

END;
//
DELIMITER ;