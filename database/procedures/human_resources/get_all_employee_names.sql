DELIMITER //
CREATE PROCEDURE get_all_employee_names()
BEGIN

SELECT DISTINCT CONCAT(nume, ' ', prenume) AS nume FROM angajat;

END;
//
DELIMITER ;