CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_holiday`(in cnp_angajat VARCHAR(13))
BEGIN
	select data_incepere, data_terminare 
    from perioada_concediu
    where perioada_concediu.cnp_angajat = cnp_angajat;
END