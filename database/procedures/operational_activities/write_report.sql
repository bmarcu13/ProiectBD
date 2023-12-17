DELIMITER //
CREATE PROCEDURE write_report(
	IN param_id_programare INT,
    IN param_cnp_medic_recomandare VARCHAR(13),
    IN param_cnp_asistent VARCHAR(13),
	IN param_simptome VARCHAR(255),
    IN param_diagnostic VARCHAR(255),
    IN param_recomandari VARCHAR(255),
    IN param_parafat TINYINT(1)
)
BEGIN

INSERT INTO report (
        id_programare,
        cnp_medic_recomandare,
        cnp_asistent,
        simptome,
        diagnostic,
        recomandari,
        parafat
    ) VALUES (
        param_id_programare,
        param_cnp_medic_recomandare,
        param_cnp_asistent,
        param_simptome,
        param_diagnostic,
        param_recomandari,
        param_parafat
    );


END;
//
DELIMITER ;