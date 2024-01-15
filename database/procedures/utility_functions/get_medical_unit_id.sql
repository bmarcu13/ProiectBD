DELIMITER //
CREATE FUNCTION get_medical_unit_id(_employee_cnp VARCHAR(13)) RETURNS INT
READS SQL DATA
BEGIN

DECLARE medical_unit_id INT;

SELECT id_unitate_medicala INTO medical_unit_id FROM orar
WHERE orar.cnp_angajat = _employee_cnp
LIMIT 1;

RETURN medical_unit_id;

END;
//
DELIMITER ;