DELIMITER //

CREATE PROCEDURE get_employee(IN param_nume VARCHAR(20), IN param_prenume VARCHAR(20), IN param_denumire_functie VARCHAR(45))
BEGIN

SELECT *
FROM (
	SELECT cnp_angajat FROM angajat
	INNER JOIN functie ON angajat.id_functie = functie.id_functie AND functie.denumire_functie = param_denumire_functie
	WHERE angajat.nume = param_nume AND angajat.prenume = param_prenume
) as angajati_returnati
INNER JOIN orar ON angajati_returnati.cnp_angajat = orar.cnp_angajat;

END
//
DELIMITER ;