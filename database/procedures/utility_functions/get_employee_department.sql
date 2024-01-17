DELIMITER //
CREATE FUNCTION get_employee_department(_department_id INT) RETURNS VARCHAR(45)
READS SQL DATA
BEGIN

DECLARE employee_department VARCHAR(45);

SELECT denumire_departament INTO employee_department FROM departament
WHERE departament.id_departament = _department_id;

RETURN employee_department;

END;
//
DELIMITER ;