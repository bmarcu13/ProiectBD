DELIMITER //
CREATE FUNCTION get_employee_earnings(_employee_cnp VARCHAR(13), _month DATE) RETURNS INT
READS SQL DATA
BEGIN

DECLARE salary INT;
DECLARE workedHours INT;
DECLARE requiredHours INT;

SET workedHours = time_to_sec(get_employee_worked_hours(_employee_cnp, _month));
SET requiredHours = get_required_nr_of_hours(_employee_cnp) * 60 * 60;

SET salary = get_employee_salary(_employee_cnp) * workedHours/requiredHours;

RETURN salary;

END;

//
DELIMITER ;