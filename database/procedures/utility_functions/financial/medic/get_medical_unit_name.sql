DELIMITER //
CREATE FUNCTION get_medical_unit_name(_medical_unit_id INT) RETURNS VARCHAR(45)
READS SQL DATA
BEGIN

DECLARE nume_unitate_medicala VARCHAR(45);

SELECT denumire_unitate_medicala INTO nume_unitate_medicala FROM unitate_medicala
WHERE unitate_medicala.id_unitate_medicala = _medical_unit_id;

IF nume_unitate_medicala IS NULL THEN
	RETURN "";
ELSE
	RETURN nume_unitate_medicala;
END IF;

END;
//
DELIMITER ;