CREATE DEFINER=`root`@`localhost` PROCEDURE `get_account_credentials`(in param_email VARCHAR(45), 
    in param_password VARCHAR(255), 
    out param_response INT,
    out param_acc_id VARCHAR(45),
    out param_acc_password VARCHAR(255))
BEGIN
	DECLARE var_password VARCHAR(255);
    DECLARE var_acc_id VARCHAR(45);
    DECLARE var_acc_password VARCHAR(255);
    
	SELECT parola, id_cont_clasa_permisiuni
    INTO var_password, var_acc_id 
    FROM utilizator 
    WHERE email_utilizator = param_email;
    
    IF (ROW_COUNT() = 0) THEN
		set param_response = 404;
        set param_acc_id = NULL;
        set param_acc_password = NULL;
	ELSE 
		IF var_password = param_password THEN
			SELECT parola
            INTO var_acc_password 
            FROM cont_clasa_permisiuni 
            WHERE var_acc_id = id_cont_clasa_permisiuni;
            
            SET param_acc_id = var_acc_id;
            SET param_acc_password = var_acc_password;
            SET param_response = 200;
		ELSE 
			set param_response = 403;
			set param_acc_id = NULL;
			set param_acc_password = NULL;
		END IF;
	END IF;
END