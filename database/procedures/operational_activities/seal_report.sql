DELIMITER //
CREATE PROCEDURE seal_report(IN param_id_programare INT)
BEGIN

DECLARE check_if_sealed TINYINT(1);

SELECT parafat INTO check_if_sealed FROM raport_medical
WHERE raport_medical.id_programare = param_id_programare;

IF check_if_sealed IS NULL OR check_if_sealed = 0 THEN
	UPDATE raport_medical
    SET parafat = 1
    WHERE raport_medical.id_programare = param_id_programare;
END IF;

END;
//
DELIMITER ;