DELIMITER //
CREATE FUNCTION count_specific_day_in_month(_dayName VARCHAR(8), _targetMonth DATE) RETURNS INT
READS SQL DATA
BEGIN
    DECLARE dayCount INT;
    DECLARE dayName VARCHAR(20);
    DECLARE endDate DATE;
    
    SET dayCount = 0;
    
    IF (_dayName = "Luni") THEN
		SET dayName = "Monday";
    ELSEIF (_dayName = "Marti") THEN
		SET dayName = "Tuesday";
	ELSEIF (_dayName = "Miercuri") THEN
		SET dayName = "Wednesday";
	ELSEIF (_dayName = "Joi") THEN
		SET dayName = "Thursday";
	ELSEIF (_dayName = "Vineri") THEN
		SET dayName = "Friday";
	ELSEIF (_dayName = "Sambata") THEN
		SET dayName = "Saturday";
	ELSEIF (_dayName = "Duminica") THEN
		SET dayName = "Sunday";
	END IF;

    SET endDate = LAST_DAY(_targetMonth);
    SET _targetMonth = DATE_FORMAT(_targetMonth, '%Y-%m-01');
    
    WHILE _targetMonth <= endDate DO
        IF DAYNAME(_targetMonth) = dayName THEN
            SET dayCount = dayCount + 1;
        END IF;
        SET _targetMonth = ADDDATE(_targetMonth, INTERVAL 1 DAY);
    END WHILE;
    
    RETURN dayCount;
END
//
DELIMITER ;
