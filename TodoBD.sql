-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TodoBD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TodoBD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TodoBD` DEFAULT CHARACTER SET utf8 ;
USE `TodoBD` ;

-- -----------------------------------------------------
-- Table `TodoBD`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TodoBD`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TodoBD`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TodoBD`.`Task` (
  `idTask` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idTask`, `User_idUser`),
  UNIQUE INDEX `idTask_UNIQUE` (`idTask` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) VISIBLE,
  INDEX `fk_Task_User_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Task_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `TodoBD`.`User` (idUser)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
