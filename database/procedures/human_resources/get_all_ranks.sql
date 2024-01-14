DELIMITER //
CREATE PROCEDURE get_all_ranks()
BEGIN

SELECT DISTINCT denumire_functie FROM functie;

END;
//
DELIMITER ;