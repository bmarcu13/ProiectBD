DELIMITER //
CREATE FUNCTION get_medical_unit_profit(_month DATE, _medical_unit_id INT) RETURNS INT
READS SQL DATA
BEGIN

DECLARE income INT;
DECLARE expenses INT;

SELECT get_medical_unit_income(_month, _medical_unit_id) INTO income;
SELECT get_medical_unit_expenses(_month, _medical_unit_id) INTO expenses;

IF income IS NULL THEN
	SET income = 0;
END IF;

IF expenses IS NULL THEN
	SET expenses = 0;
END IF;

RETURN income - expenses;

END;
//
DELIMITER ;