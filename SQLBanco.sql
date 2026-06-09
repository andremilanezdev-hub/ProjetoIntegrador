-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema controle_visitantes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `controle_visitantes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `controle_visitantes` ;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`departamentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`agendamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`agendamentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departamento_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `data_agendamento` DATETIME NOT NULL,
  `data_fim` DATETIME NULL DEFAULT NULL,
  `motivo` VARCHAR(255) NULL DEFAULT NULL,
  `status` ENUM('AGENDADO', 'CONFIRMADO', 'CANCELADO') NULL DEFAULT 'AGENDADO',
  PRIMARY KEY (`id`),
  INDEX `departamento_id` (`departamento_id` ASC),
  INDEX `usuario_id` (`usuario_id` ASC),
  CONSTRAINT `agendamentos_ibfk_1`
    FOREIGN KEY (`departamento_id`)
    REFERENCES `controle_visitantes`.`departamentos` (`id`),
  CONSTRAINT `agendamentos_ibfk_2`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `controle_visitantes`.`usuarios` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`visitantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`visitantes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `telefone` VARCHAR(20) NULL DEFAULT NULL,
  `empresa` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf` (`cpf` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`agendamentos_visitantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`agendamentos_visitantes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `agendamento_id` INT NOT NULL,
  `visitante_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `agendamento_id` (`agendamento_id` ASC),
  INDEX `visitante_id` (`visitante_id` ASC),
  CONSTRAINT `agendamentos_visitantes_ibfk_1`
    FOREIGN KEY (`agendamento_id`)
    REFERENCES `controle_visitantes`.`agendamentos` (`id`),
  CONSTRAINT `agendamentos_visitantes_ibfk_2`
    FOREIGN KEY (`visitante_id`)
    REFERENCES `controle_visitantes`.`visitantes` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`visitas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`visitas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departamento_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `data_entrada` DATETIME NOT NULL,
  `data_saida` DATETIME NULL DEFAULT NULL,
  `motivo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_visita_departamento` (`departamento_id` ASC),
  INDEX `fk_visita_usuario` (`usuario_id` ASC),
  CONSTRAINT `fk_visita_departamento`
    FOREIGN KEY (`departamento_id`)
    REFERENCES `controle_visitantes`.`departamentos` (`id`),
  CONSTRAINT `fk_visita_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `controle_visitantes`.`usuarios` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `controle_visitantes`.`visitantes_visitas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_visitantes`.`visitantes_visitas` (
  `idvisitantes_visitas` INT NOT NULL AUTO_INCREMENT,
  `visitantes_id` INT NOT NULL,
  `visitas_id` INT NOT NULL,
  PRIMARY KEY (`idvisitantes_visitas`, `visitantes_id`, `visitas_id`),
  INDEX `fk_visitantes_visitas_visitantes1_idx` (`visitantes_id` ASC),
  INDEX `fk_visitantes_visitas_visitas1_idx` (`visitas_id` ASC),
  CONSTRAINT `fk_visitantes_visitas_visitantes1`
    FOREIGN KEY (`visitantes_id`)
    REFERENCES `controle_visitantes`.`visitantes` (`id`),
  CONSTRAINT `fk_visitantes_visitas_visitas1`
    FOREIGN KEY (`visitas_id`)
    REFERENCES `controle_visitantes`.`visitas` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
