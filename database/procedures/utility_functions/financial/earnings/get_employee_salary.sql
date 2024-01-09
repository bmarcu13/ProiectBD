DELIMITER //
CREATE FUNCTION get_employee_salary(_employee_cnp VARCHAR(13)) RETURNS INT
READS SQL DATA
BEGIN

DECLARE salary INT;

SELECT salariu_negociat INTO salary FROM angajat WHERE cnp_angajat = _employee_cnp;

RETURN salary;

END;
//
DELIMITER ;