DELIMITER //
CREATE FUNCTION get_medic_paid_services_profit(_cnp_medic VARCHAR(13), _month DATE, _medical_unit_id INT) RETURNS INT
READS SQL DATA
BEGIN

DECLARE services_profit INT;

SELECT SUM(suma_platita) INTO services_profit FROM bon_fiscal
INNER JOIN programare ON bon_fiscal.id_programare = programare.id_programare AND programare.cnp_medic = _cnp_medic AND MONTH(programare.data_programare) = MONTH(_month) AND YEAR(programare.data_programare) = YEAR(_month)
WHERE bon_fiscal.id_unitate_medicala = _medical_unit_id;

IF services_profit IS NULL THEN
	RETURN 0;
ELSE
	RETURN services_profit;
END IF;

END;
//
DELIMITER ;