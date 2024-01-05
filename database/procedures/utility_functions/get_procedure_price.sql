DELIMITER //
CREATE FUNCTION get_procedure_price(_id_serviciu_medical INT) RETURNS INT
READS SQL DATA
BEGIN

DECLARE price INT;

SELECT pret INTO price FROM serviciu_medical
WHERE id_serviciu_medical = _id_serviciu_medical;

RETURN price;

END;
//
DELIMITER ;