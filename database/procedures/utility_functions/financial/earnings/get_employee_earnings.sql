DELIMITER //
CREATE FUNCTION get_employee_earnings(_employee_cnp VARCHAR(13), _month DATE) RETURNS INT
READS SQL DATA
BEGIN

DECLARE salary INT;
DECLARE workedHours INT;
DECLARE requiredHours INT;

IF YEAR(_month) > YEAR(CURRENT_DATE) OR (YEAR(_month) = YEAR(CURRENT_DATE) AND MONTH(_month) > MONTH(CURRENT_DATE)) THEN
	RETURN 0;
END IF;

SET workedHours = time_to_sec(get_employee_worked_hours(_employee_cnp, _month));
SET requiredHours = get_required_nr_of_hours(_employee_cnp) * 60 * 60;

SET salary = get_employee_salary(_employee_cnp) * LEAST(workedHours, requiredHours)/requiredHours;

RETURN salary;

END;

//
DELIMITER ;