DELIMITER //
CREATE FUNCTION get_employee_rank(_employee_cnp VARCHAR(13)) RETURNS VARCHAR(45)
READS SQL DATA
BEGIN

DECLARE employee_rank VARCHAR(45);

SELECT get_rank_name(angajat.id_functie) INTO employee_rank FROM angajat
WHERE angajat.cnp_angajat = _employee_cnp;

RETURN employee_rank;

END;
//
DELIMITER ;