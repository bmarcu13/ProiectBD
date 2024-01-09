DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `get_required_nr_of_hours`(_cnp_employee VARCHAR(13)) RETURNS int
    READS SQL DATA
BEGIN

DECLARE requiredHours INT;

SELECT nr_ore INTO requiredHours FROM angajat
WHERE angajat.cnp_angajat = _cnp_employee;

RETURN requiredHours;

END;
//
DELIMITER ;