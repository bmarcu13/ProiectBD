DELIMITER //
CREATE FUNCTION get_patient_name(param_cnp_pacient VARCHAR(13)) RETURNS VARCHAR(41)
READS SQL DATA
BEGIN

DECLARE full_name VARCHAR(41);

SELECT CONCAT(nume, ' ', prenume) INTO full_name FROM pacient
WHERE pacient.cnp_pacient = param_cnp_pacient;

RETURN full_name;

END
//
DELIMITER ;