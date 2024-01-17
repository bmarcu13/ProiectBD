DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_ranks`(_name VARCHAR(20), _surname VARCHAR(20))
BEGIN

SELECT DISTINCT get_rank_name(id_functie) as denumire_functie FROM angajat
WHERE angajat.nume = _name AND angajat.prenume = _surname;

END
//
DELIMITER ;