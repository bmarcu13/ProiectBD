CREATE DEFINER=`root`@`localhost` PROCEDURE `get_existing_investigations`(
	in p_id_programare int
)
BEGIN
	SELECT analiza.id_analiza, analiza.descriere_analiza, valoare, pozitiv_negativ
    FROM rezultat_analiza
    INNER JOIN analiza ON rezultat_analiza.id_analiza = analiza.id_analiza
    WHERE id_programare = p_id_programare;
END