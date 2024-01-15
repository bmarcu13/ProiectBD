DELIMITER //
CREATE FUNCTION get_medical_unit_expenses(_month DATE, _medical_unit_id INT) RETURNS INT
READS SQL DATA
BEGIN

DECLARE medical_unit_expenses INT;

SELECT SUM(get_employee_earnings(angajat.cnp_angajat, _month) - get_commission(angajat.cnp_angajat)/100 * get_medic_paid_services_profit(angajat.cnp_angajat, _month, _medical_unit_id))
INTO medical_unit_expenses FROM angajat
WHERE get_medical_unit_id(angajat.cnp_angajat) = _medical_unit_id;

IF medical_unit_expenses IS NULL THEN
	RETURN 0;
ELSE
	RETURN medical_unit_expenses;
END IF;

END;
//
DELIMITER ;