DELIMITER //
CREATE FUNCTION get_employee_name(_employee_cnp VARCHAR(13)) RETURNS VARCHAR(41)
READS SQL DATA
BEGIN

DECLARE employee_name VARCHAR(41);

SELECT CONCAT(nume, ' ', prenume) INTO employee_name FROM angajat
WHERE angajat.cnp_angajat = _employee_cnp;

RETURN employee_name;

END;
//
DELIMITER ;