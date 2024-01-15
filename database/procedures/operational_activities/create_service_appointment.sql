CREATE DEFINER=`root`@`localhost` PROCEDURE `create_service_appointment`(
	in p_id_programare INT,
    in p_id_serviciu_medical INT
)
BEGIN
	INSERT INTO programare_serviciu_medical
    VALUES(
		p_id_programare,
        p_id_serviciu_medical
    );
END