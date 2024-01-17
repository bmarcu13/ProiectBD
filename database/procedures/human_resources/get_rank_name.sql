DELIMITER //
CREATE FUNCTION get_rank_name(_rank_id INT) RETURNS VARCHAR(45)
READS SQL DATA
BEGIN

DECLARE rank_name VARCHAR(45);

SELECT denumire_functie INTO rank_name FROM functie
WHERE functie.id_functie = _rank_id;

RETURN rank_name;

END;
//
DELIMITER ;