DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_available_investigations`()
BEGIN
	SELECT * FROM analiza;
END
//
DELIMITER;