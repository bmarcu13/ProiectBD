DELIMITER //
CREATE FUNCTION get_user_cnp(_user_email VARCHAR(45)) RETURNS VARCHAR(13)
READS SQL DATA
BEGIN

DECLARE user_cnp VARCHAR(13);

SELECT cnp_angajat INTO user_cnp FROM angajat
WHERE angajat.email = _user_email;

RETURN user_cnp;

END;
//
DELIMITER ;