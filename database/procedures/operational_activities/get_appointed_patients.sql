DELIMITER //
CREATE PROCEDURE get_appointed_patients(IN param_cnp_medic VARCHAR(13))
BEGIN

SELECT (nume, prenume) FROM (
SELECT * from pacient
INNER JOIN programare ON pacient.cnp_pacient = programare.cnp_pacient
AND programare.data_programare = CURRENT_DATE
AND programare.cnp_medic = param_cnp_medic) AS pacienti_returnati;


-- o alta forma de-a scrie procedura, doar in caz ca nu merge prima cand o testez
-- SELECT * FROM (
-- SELECT cnp_pacient FROM programare
-- WHERE programare.cnp_medic = param_cnp_medic AND programare.data_programare = CURRENT_DATE
-- ) as pacienti_la_medic_azi
-- INNER JOIN pacient ON pacienti_de_azi.cnp_pacient = pacient.cnp_pacient;

END;
//
DELIMITER ;