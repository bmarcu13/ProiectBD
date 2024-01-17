CREATE DEFINER = CURRENT_USER TRIGGER `policlinica`.`programare_serviciu_medical_AFTER_INSERT` AFTER INSERT ON `programare_serviciu_medical` FOR EACH ROW
BEGIN
	DECLARE var_pret INT;
    
	SELECT sm.pret INTO var_pret
    FROM serviciu_medical AS sm
    WHERE NEW.id_serviciu_medical = sm.id_serviciu_medical;
    
    INSERT INTO bon_fiscal
    VALUES (NEW.id_programare, var_pret)
    ON DUPLICATE KEY
    UPDATE suma_platita = suma_platita + var_pret;
END
