-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema policlinica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema policlinica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `policlinica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `policlinica` ;

-- -----------------------------------------------------
-- Table `policlinica`.`analiza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`analiza` (
  `id_analiza` INT NOT NULL AUTO_INCREMENT,
  `descriere_analiza` VARCHAR(255) NULL DEFAULT NULL,
  `limita_inferioara` INT NULL DEFAULT NULL,
  `limita_superioara` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_analiza`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`departament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`departament` (
  `id_departament` INT NOT NULL AUTO_INCREMENT,
  `denumire_departament` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_departament`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`functie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`functie` (
  `id_functie` INT NOT NULL AUTO_INCREMENT,
  `denumire_functie` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_functie`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`cont_clasa_permisiuni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`cont_clasa_permisiuni` (
  `id_cont_clasa_permisiuni` INT NOT NULL AUTO_INCREMENT,
  `parola` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_cont_clasa_permisiuni`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`utilizator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`utilizator` (
  `id_utilizator` INT NOT NULL AUTO_INCREMENT,
  `id_cont_clasa_permisiuni` INT NULL DEFAULT NULL,
  `parola` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_utilizator`),
  INDEX `fk_id_cont_clasa_permisiuni_idx` (`id_cont_clasa_permisiuni` ASC) VISIBLE,
  CONSTRAINT `fk_id_cont_clasa_permisiuni`
    FOREIGN KEY (`id_cont_clasa_permisiuni`)
    REFERENCES `policlinica`.`cont_clasa_permisiuni` (`id_cont_clasa_permisiuni`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`angajat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`angajat` (
  `cnp_angajat` VARCHAR(13) NOT NULL,
  `id_utilizator` INT NULL DEFAULT NULL,
  `id_departament` INT NULL DEFAULT NULL,
  `id_functie` INT NULL DEFAULT NULL,
  `nume` VARCHAR(20) NULL DEFAULT NULL,
  `prenume` VARCHAR(20) NULL DEFAULT NULL,
  `adresa` VARCHAR(45) NULL DEFAULT NULL,
  `numar_telefon` VARCHAR(12) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `iban` VARCHAR(34) NULL DEFAULT NULL,
  `nr_contract` INT NULL DEFAULT NULL,
  `data_angajare` DATE NULL DEFAULT NULL,
  `salariu_negociat` INT NULL DEFAULT NULL,
  `nr_ore` INT NULL DEFAULT NULL,
  PRIMARY KEY (`cnp_angajat`),
  INDEX `fk_id_utilizator_idx` (`id_utilizator` ASC) VISIBLE,
  INDEX `fk_id_departament_idx` (`id_departament` ASC) VISIBLE,
  INDEX `fk_id_functie_idx` (`id_functie` ASC) VISIBLE,
  CONSTRAINT `fk_id_departament`
    FOREIGN KEY (`id_departament`)
    REFERENCES `policlinica`.`departament` (`id_departament`),
  CONSTRAINT `fk_id_functie`
    FOREIGN KEY (`id_functie`)
    REFERENCES `policlinica`.`functie` (`id_functie`),
  CONSTRAINT `fk_id_utilizator`
    FOREIGN KEY (`id_utilizator`)
    REFERENCES `policlinica`.`utilizator` (`id_utilizator`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`tip_asistent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`tip_asistent` (
  `id_tip_asistent` INT NOT NULL AUTO_INCREMENT,
  `denumire_tip_asistent` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tip_asistent`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`asistent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`asistent` (
  `cnp_asistent` VARCHAR(13) NOT NULL,
  `id_tip_asistent` INT NULL DEFAULT NULL,
  `grad` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`cnp_asistent`),
  INDEX `fk_asistent_tip_asistent_id_tip_asistent_idx` (`id_tip_asistent` ASC) VISIBLE,
  CONSTRAINT `fk_asistent_tip_asistent_id_tip_asistent`
    FOREIGN KEY (`id_tip_asistent`)
    REFERENCES `policlinica`.`tip_asistent` (`id_tip_asistent`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`post_didactic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`post_didactic` (
  `id_post_didactic` INT NOT NULL AUTO_INCREMENT,
  `denumire_post_didactic` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_post_didactic`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`titlu_stiintific`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`titlu_stiintific` (
  `id_titlu_stiintific` INT NOT NULL AUTO_INCREMENT,
  `denumire_titlu_stiintific` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_titlu_stiintific`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`medic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`medic` (
  `cnp_medic` VARCHAR(13) NOT NULL,
  `id_post_didactic` INT NULL DEFAULT NULL,
  `id_titlu_stiintific` INT NULL DEFAULT NULL,
  `grad` VARCHAR(20) NULL DEFAULT NULL,
  `cod_parafa` VARCHAR(20) NULL DEFAULT NULL,
  `comision` INT NULL DEFAULT NULL,
  PRIMARY KEY (`cnp_medic`),
  INDEX `fk_medic_post_didactic_id_post_didactic_idx` (`id_post_didactic` ASC) VISIBLE,
  INDEX `fk_medic_titlu_stiintific_id_titlu_stiintific_idx` (`id_titlu_stiintific` ASC) VISIBLE,
  CONSTRAINT `fk_medic_angajat_cnp_medic`
    FOREIGN KEY (`cnp_medic`)
    REFERENCES `policlinica`.`angajat` (`cnp_angajat`),
  CONSTRAINT `fk_medic_post_didactic_id_post_didactic`
    FOREIGN KEY (`id_post_didactic`)
    REFERENCES `policlinica`.`post_didactic` (`id_post_didactic`),
  CONSTRAINT `fk_medic_titlu_stiintific_id_titlu_stiintific`
    FOREIGN KEY (`id_titlu_stiintific`)
    REFERENCES `policlinica`.`titlu_stiintific` (`id_titlu_stiintific`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`pacient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`pacient` (
  `cnp_pacient` VARCHAR(13) NOT NULL,
  `nume` VARCHAR(20) NULL DEFAULT NULL,
  `prenume` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`cnp_pacient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`programare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`programare` (
  `id_programare` INT NOT NULL AUTO_INCREMENT,
  `cnp_pacient` VARCHAR(13) NULL DEFAULT NULL,
  `cnp_medic` VARCHAR(13) NULL DEFAULT NULL,
  `pacient_inregistrat` TINYINT(1) NULL DEFAULT NULL,
  `data_programare` DATE NULL DEFAULT NULL,
  `ora_programare` TIME NULL DEFAULT NULL,
  `durata_programare` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_programare`),
  INDEX `fk_programare_medic_cnp_medic_idx` (`cnp_medic` ASC) VISIBLE,
  INDEX `fk_programare_pacient_cnp_pacient_idx` (`cnp_pacient` ASC) VISIBLE,
  CONSTRAINT `fk_programare_medic_cnp_medic`
    FOREIGN KEY (`cnp_medic`)
    REFERENCES `policlinica`.`medic` (`cnp_medic`),
  CONSTRAINT `fk_programare_pacient_cnp_pacient`
    FOREIGN KEY (`cnp_pacient`)
    REFERENCES `policlinica`.`pacient` (`cnp_pacient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`bon_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`bon_fiscal` (
  `id_programare` INT NOT NULL,
  `suma_platita` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_programare`),
  CONSTRAINT `fk_bon_fiscal_programare`
    FOREIGN KEY (`id_programare`)
    REFERENCES `policlinica`.`programare` (`id_programare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`specializare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`specializare` (
  `id_specializare` INT NOT NULL AUTO_INCREMENT,
  `denumire_specializare` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_specializare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`medic_specializare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`medic_specializare` (
  `cnp_medic` VARCHAR(13) NOT NULL,
  `id_specializare` INT NULL DEFAULT NULL,
  PRIMARY KEY (`cnp_medic`),
  INDEX `fk_medic_specializare_specializare_idx` (`id_specializare` ASC) VISIBLE,
  CONSTRAINT `fk_medic_specializare_medic`
    FOREIGN KEY (`cnp_medic`)
    REFERENCES `policlinica`.`medic` (`cnp_medic`),
  CONSTRAINT `fk_medic_specializare_specializare`
    FOREIGN KEY (`id_specializare`)
    REFERENCES `policlinica`.`specializare` (`id_specializare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`unitate_medicala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`unitate_medicala` (
  `id_unitate_medicala` INT NOT NULL AUTO_INCREMENT,
  `denumire_unitate_medicala` VARCHAR(45) NULL DEFAULT NULL,
  `adresa_unitate_medicala` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_unitate_medicala`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`orar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`orar` (
  `id_orar` INT NOT NULL AUTO_INCREMENT,
  `cnp_angajat` VARCHAR(13) NULL DEFAULT NULL,
  `id_unitate_medicala` INT NULL DEFAULT NULL,
  `is_generic` TINYINT(1) NULL DEFAULT NULL,
  `zi` VARCHAR(8) NULL DEFAULT NULL,
  `data` DATE NULL DEFAULT NULL,
  `ora_incepere` TIME NULL DEFAULT NULL,
  `ora_terminare` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_orar`),
  INDEX `fk_orar_angajat_cnp_angajat_idx` (`cnp_angajat` ASC) VISIBLE,
  INDEX `fk_orar_unitate_medicala_id_unitate_medicala_idx` (`id_unitate_medicala` ASC) VISIBLE,
  CONSTRAINT `fk_orar_angajat_cnp_angajat`
    FOREIGN KEY (`cnp_angajat`)
    REFERENCES `policlinica`.`angajat` (`cnp_angajat`),
  CONSTRAINT `fk_orar_unitate_medicala_id_unitate_medicala`
    FOREIGN KEY (`id_unitate_medicala`)
    REFERENCES `policlinica`.`unitate_medicala` (`id_unitate_medicala`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`program_policlinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`program_policlinica` (
  `id_unitate_medicala` INT NOT NULL,
  `zi` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id_unitate_medicala`, `zi`),
  CONSTRAINT `fk_id_unitate_medicala`
    FOREIGN KEY (`id_unitate_medicala`)
    REFERENCES `policlinica`.`unitate_medicala` (`id_unitate_medicala`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`serviciu_medical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`serviciu_medical` (
  `id_serviciu_medical` INT NOT NULL AUTO_INCREMENT,
  `id_specializare` INT NULL DEFAULT NULL,
  `denumire_serviciu_medical` VARCHAR(45) NULL DEFAULT NULL,
  `pret` INT NULL DEFAULT NULL,
  `durata` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_serviciu_medical`),
  INDEX `fk_serviciu_medical_specializare_id_specializare_idx` (`id_specializare` ASC) VISIBLE,
  CONSTRAINT `fk_serviciu_medical_specializare_id_specializare`
    FOREIGN KEY (`id_specializare`)
    REFERENCES `policlinica`.`specializare` (`id_specializare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`programare_serviciu_medical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`programare_serviciu_medical` (
  `id_programare` INT NOT NULL,
  `id_serviciu_medical` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_programare`),
  INDEX `fk_programare_serviciu_medical_serviciu_medical_idx` (`id_serviciu_medical` ASC) VISIBLE,
  CONSTRAINT `fk_programare_serviciu_medical_id_programare`
    FOREIGN KEY (`id_programare`)
    REFERENCES `policlinica`.`programare` (`id_programare`),
  CONSTRAINT `fk_programare_serviciu_medical_serviciu_medical`
    FOREIGN KEY (`id_serviciu_medical`)
    REFERENCES `policlinica`.`serviciu_medical` (`id_serviciu_medical`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`raport_medical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`raport_medical` (
  `id_programare` INT NOT NULL,
  `cnp_medic_recomandare` VARCHAR(13) NULL DEFAULT NULL,
  `cnp_asistent` VARCHAR(13) NULL DEFAULT NULL,
  `simptome` VARCHAR(255) NULL DEFAULT NULL,
  `diagnostic` VARCHAR(255) NULL DEFAULT NULL,
  `recomandari` VARCHAR(255) NULL DEFAULT NULL,
  `parafat` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_programare`),
  INDEX `fk_raport_medical_medic_cnp_medic_recomandare_idx` (`cnp_medic_recomandare` ASC) VISIBLE,
  INDEX `fk_raport_medical_asistent_cnp_asistent_idx` (`cnp_asistent` ASC) VISIBLE,
  CONSTRAINT `fk_raport_medical_asistent_cnp_asistent`
    FOREIGN KEY (`cnp_asistent`)
    REFERENCES `policlinica`.`asistent` (`cnp_asistent`),
  CONSTRAINT `fk_raport_medical_medic_cnp_medic_recomandare`
    FOREIGN KEY (`cnp_medic_recomandare`)
    REFERENCES `policlinica`.`medic` (`cnp_medic`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`rezultat_analiza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`rezultat_analiza` (
  `id_analiza` INT NOT NULL,
  `id_programare` INT NOT NULL,
  `valoare` INT NULL DEFAULT NULL,
  `pozitiv_negativ` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_analiza`, `id_programare`),
  INDEX `fk_rezultat_analiza_programare_id_programare_idx` (`id_programare` ASC) VISIBLE,
  CONSTRAINT `fk_rezultat_analiza_analiza_id_analiza`
    FOREIGN KEY (`id_analiza`)
    REFERENCES `policlinica`.`analiza` (`id_analiza`),
  CONSTRAINT `fk_rezultat_analiza_programare_id_programare`
    FOREIGN KEY (`id_programare`)
    REFERENCES `policlinica`.`programare` (`id_programare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `policlinica`.`rezultat_serviciu_medical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policlinica`.`rezultat_serviciu_medical` (
  `id_serviciu_medical` INT NOT NULL,
  `id_programare` INT NOT NULL,
  `rezultat` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_serviciu_medical`, `id_programare`),
  INDEX `fk_rezultat_serviciu_medical_programare_id_programare_idx` (`id_programare` ASC) VISIBLE,
  CONSTRAINT `fk_rezultat_serviciu_medical_id_serviciu_medical`
    FOREIGN KEY (`id_serviciu_medical`)
    REFERENCES `policlinica`.`serviciu_medical` (`id_serviciu_medical`),
  CONSTRAINT `fk_rezultat_serviciu_medical_programare_id_programare`
    FOREIGN KEY (`id_programare`)
    REFERENCES `policlinica`.`programare` (`id_programare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
