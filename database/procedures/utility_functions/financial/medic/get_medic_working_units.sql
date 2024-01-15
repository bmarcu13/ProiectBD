DELIMITER //
CREATE PROCEDURE get_medic_working_units(_cnp_medic VARCHAR(13))
BEGIN

SELECT DISTINCT id_unitate_medicala FROM orar
WHERE orar.cnp_angajat = _cnp_medic;

END;
//
DELIMITER ;