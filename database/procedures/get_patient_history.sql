DELIMITER //
CREATE PROCEDURE get_patient_history(IN param_cnp_pacient VARCHAR(13))
BEGIN

-- returns all medical reports of the patient, including the name of the patient, medic, nurse and the medic who recommended it
SELECT raport_medical.id_programare, simptome, diagnostic, recomandari, parafat,
get_patient_name(programare.cnp_pacient),
get_medic_name(programare.cnp_medic), get_nurse_name(raport_medical.cnp_asistent),
get_medic_name(raport_medical.cnp_medic_recomandare)
FROM raport_medical
INNER JOIN programare ON raport_medical.id_programare = programare.id_programare AND programare.cnp_pacient = param_cnp_pacient;

END;
//
DELIMITER ;