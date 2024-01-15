DELIMITER //
CREATE FUNCTION get_medic_profit_on_one_unit(_cnp_medic VARCHAR(13), _month DATE, _medical_unit_id INT) RETURNS INT
READS SQL DATA
BEGIN

DECLARE medic_earnings INT;
DECLARE comision_medic INT;
DECLARE medic_profit_on_unit INT;

SELECT get_employee_earnings(_cnp_medic, _month) INTO medic_earnings;

SELECT get_commission(_cnp_medic) INTO comision_medic;
SELECT get_medic_paid_services_profit(_cnp_medic, _month, _medical_unit_id) INTO medic_profit_on_unit;

RETURN medic_profit_on_unit - medic_profit_on_unit * comision_medic / 100 - medic_earnings;

END;
//
DELIMITER ;