DELIMITER //
CREATE FUNCTION get_medic_cnp_by_name(_employee_name VARCHAR(20), _employee_surname VARCHAR(20)) RETURNS VARCHAR(13)
READS SQL DATA
BEGIN

DECLARE employee_cnp VARCHAR(13);

SELECT cnp_angajat INTO employee_cnp FROM angajat
WHERE nume = _employee_name AND prenume = _employee_surname;

RETURN employee_cnp;

END;
//
DELIMITER ;