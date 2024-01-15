DELIMITER //
CREATE PROCEDURE get_employee_vacations(_employee_cnp VARCHAR(13), _month DATE)
BEGIN

SELECT data_incepere, data_terminare FROM perioada_concediu
WHERE perioada_concediu.cnp_angajat = _employee_cnp AND ((YEAR(_month) = YEAR(data_incepere) AND MONTH(_month) = MONTH(data_incepere)) OR
(YEAR(_month) = YEAR(data_terminare) AND MONTH(_month) = MONTH(data_terminare)));

END;
//
DELIMITER ;