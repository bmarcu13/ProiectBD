CREATE DEFINER=`root`@`localhost` PROCEDURE `get_user_cnp`(in p_user_email varchar(45))
BEGIN
	SELECT angajat.cnp_angajat 
    FROM angajat
    WHERE angajat.email = p_user_email;
END