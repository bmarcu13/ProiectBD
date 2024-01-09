DELIMITER //

CREATE FUNCTION get_commission(_cnp_angajat VARCHAR(13)) RETURNS INT
READS SQL DATA
BEGIN

DECLARE comision_doctor INT;
SELECT comision INTO comision_doctor FROM medic WHERE cnp_medic = _cnp_angajat;

IF comision_doctor IS NULL THEN
	RETURN 0;
ELSE
	RETURN comision_doctor;
END IF;
    
END;
//
DELIMITER ;