DELIMITER //
CREATE FUNCTION get_employee_cnp(_name VARCHAR(20), _surname VARCHAR(20), _rank VARCHAR(45)) RETURNS VARCHAR(13)
READS SQL DATA
BEGIN

DECLARE employee_cnp VARCHAR(13);

SELECT angajat.cnp_angajat INTO employee_cnp FROM angajat
WHERE angajat.nume = _name AND angajat.prenume = _surname AND get_rank_name(angajat.id_functie) = _rank;

RETURN employee_cnp;

END;
//
DELIMITER ;