DELIMITER //
CREATE FUNCTION `get_medical_unit_income`(_month DATE, _medical_unit_id INT) RETURNS INT
READS SQL DATA
BEGIN

DECLARE income INT;

SELECT SUM(get_procedure_price(programare_serviciu_medical.id_serviciu_medical)) INTO income FROM programare
INNER JOIN programare_serviciu_medical ON programare.id_programare = programare_serviciu_medical.id_programare
WHERE YEAR(programare.data_programare) = YEAR(_month) AND MONTH(programare.data_programare) = MONTH(_month) AND get_medical_unit_id(programare.cnp_medic) = _medical_unit_id;

RETURN income;

END
//
DELIMITER ;