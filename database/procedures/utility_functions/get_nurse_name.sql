DELIMITER //
CREATE FUNCTION get_nurse_name(param_nurse_cnp VARCHAR(13)) RETURNS VARCHAR(41)
READS SQL DATA
BEGIN

DECLARE full_name VARCHAR(41);

SELECT CONCAT(nume, ' ', prenume) INTO full_name FROM asistent
INNER JOIN angajat ON asistent.cnp_asistent = angajat.cnp_angajat
WHERE asistent.cnp_asistent = param_nurse_cnp;

RETURN full_name;

END;
//
DELIMITER ;