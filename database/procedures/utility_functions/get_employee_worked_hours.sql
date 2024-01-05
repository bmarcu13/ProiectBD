DELIMITER //
CREATE FUNCTION get_employee_worked_hours(_cnp_angajat VARCHAR(13), _month DATE) RETURNS TIME
READS SQL DATA
BEGIN

DECLARE workedTime TIME;
DECLARE timeTakenOff TIME;
DECLARE specificWorkedTime TIME;

DECLARE firstDay DATE;
DECLARE lastDay DATE;

SELECT DATE_FORMAT(_month, '%Y-%m-01') INTO firstDay;
SELECT LAST_DAY(_month) INTO lastDay;

SELECT coalesce(sum(sec_to_time(time_to_sec(timediff(ora_terminare, ora_incepere)) * count_specific_day_in_month(orar.zi, _month))), '00:00:00') INTO workedTime FROM orar
WHERE orar.cnp_angajat = _cnp_angajat AND orar.is_generic = 1;

SELECT coalesce(sum(timediff(ora_terminare, ora_incepere)), '00:00:00') INTO specificWorkedTime FROM orar
WHERE orar.cnp_angajat = _cnp_angajat AND MONTH(orar.data) = MONTH(_month) AND orar.is_generic = 0;

-- aici sa scad numai cat are in orar, nu o zi intreaga
SELECT coalesce(sum(sec_to_time((datediff(LEAST(data_terminare, lastDay), GREATEST(data_incepere, firstDay)) * 8 * 60 * 60))), '00:00:00') INTO timeTakenOff FROM perioada_concediu
WHERE perioada_concediu.cnp_angajat = _cnp_angajat
AND (MONTH(perioada_concediu.data_incepere) = MONTH(_month) OR MONTH(perioada_concediu.data_terminare) = MONTH(_month));

RETURN timediff(addtime(workedTime, specificWorkedTime), timeTakenOff);

END;
//
DELIMITER ;