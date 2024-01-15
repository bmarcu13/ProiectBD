DELIMITER //
CREATE PROCEDURE get_all_medic_names()
BEGIN

SELECT get_medic_name(medic.cnp_medic) as nume FROM medic;

END;
//
DELIMITER ;