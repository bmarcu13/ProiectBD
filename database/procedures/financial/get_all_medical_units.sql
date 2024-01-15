CREATE DELIMITER //
CREATE PROCEDURE get_all_medical_units()
BEGIN

SELECT * FROM unitate_medicala;

END;
//
DELIMITER ;