DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `register_patient_for_appointment`(
	IN p_id_programare INT 
)
BEGIN
	UPDATE programare 
    SET programare.pacient_inregistrat = 1
    WHERE programare.id_programare = p_id_programare;
END
//
DELIMITER;