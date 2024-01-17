DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_generic_timetable`(in cnp_angajat VARCHAR(13))
BEGIN
	select zi, ora_incepere, ora_terminare, unitate_medicala.denumire_unitate_medicala
    from orar
    inner join unitate_medicala on orar.id_unitate_medicala = unitate_medicala.id_unitate_medicala
    where orar.cnp_angajat = cnp_angajat and orar.is_generic = 1;
END

//
DELIMITER ;