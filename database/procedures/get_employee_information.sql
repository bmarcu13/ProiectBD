DELIMITER //
CREATE PROCEDURE get_employee_information(_employee_cnp VARCHAR(13))
BEGIN

SELECT cnp_angajat, get_employee_department(id_departament) as departament, get_rank_name(id_functie) as functie, nume,
prenume, adresa, numar_telefon, email, iban, nr_contract, data_angajare, salariu_negociat, nr_ore FROM angajat
WHERE cnp_angajat = _employee_cnp;

END;
//
DELIMITER ;