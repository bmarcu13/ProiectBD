DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `get_employee_worked_hours`(_cnp_angajat VARCHAR(13), _month DATE) RETURNS time
    READS SQL DATA
BEGIN

DECLARE workedTime TIME;
DECLARE timeTakenOff TIME;
DECLARE specificWorkedTime TIME;

DECLARE tempWorkedTime INT;
DECLARE tempTimeTakenOff INT;
DECLARE tempSpecificWorkedTime INT;

DECLARE firstDay DATE;
DECLARE lastDay DATE;

DECLARE min_time TIME;
DECLARE temp TIME;

SET min_time = CAST('00:00:00' AS TIME);
SET temp = CAST('00:00:00' AS TIME);

SELECT DATE_FORMAT(_month, '%Y-%m-01') INTO firstDay;
SELECT LAST_DAY(_month) INTO lastDay;

SELECT coalesce(sum(time_to_sec(timediff(ora_terminare, ora_incepere)) * count_specific_day_in_month(orar.zi, _month)), 0) INTO tempWorkedTime FROM orar
WHERE orar.cnp_angajat = _cnp_angajat AND orar.is_generic = 1;

SELECT coalesce(sum(time_to_sec(timediff(ora_terminare, ora_incepere))), 0) INTO tempSpecificWorkedTime FROM orar
WHERE orar.cnp_angajat = _cnp_angajat AND MONTH(orar.data) = MONTH(_month) AND orar.is_generic = 0;

-- aici sa scad numai cat are in orar, nu o zi intreaga
SELECT coalesce(sum(datediff(LEAST(data_terminare, lastDay), GREATEST(data_incepere, firstDay)) * 8 * 60 * 60), 0) INTO tempTimeTakenOff FROM perioada_concediu
WHERE perioada_concediu.cnp_angajat = _cnp_angajat
AND (MONTH(perioada_concediu.data_incepere) = MONTH(_month) OR MONTH(perioada_concediu.data_terminare) = MONTH(_month));

SET workedTime = sec_to_time(tempWorkedTime);
SET specificWorkedTime = sec_to_time(tempSpecificWorkedTime);
SET timeTakenOff = sec_to_time(tempTimeTakenOff);

IF addtime(workedTime, specificWorkedTime) IS NOT NULL THEN
	SET temp = addtime(workedTime, specificWorkedTime);
END IF;

IF temp > timeTakenOff THEN
	RETURN timediff(addtime(workedTime, specificWorkedTime), timeTakenOff);
ELSE
	RETURN min_time;
END IF;

END
//
DELIMITER ;