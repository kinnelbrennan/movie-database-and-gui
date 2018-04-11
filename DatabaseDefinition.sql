-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema movieDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movieDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movieDB` DEFAULT CHARACTER SET utf8 ;
USE `movieDB` ;

-- -----------------------------------------------------
-- Table `movieDB`.`Actors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieDB`.`Actors` (
  `idActors` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(250) NOT NULL,
  `Birthday` DATE NULL DEFAULT NULL,
  `Birthplace` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idActors`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movieDB`.`Awards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieDB`.`Awards` (
  `idAwards` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(250) NOT NULL,
  `Category` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idAwards`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movieDB`.`Film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieDB`.`Film` (
  `idFilm` INT(11) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(250) NOT NULL,
  `Release_Date` DATE NULL DEFAULT NULL,
  `imdbRating` DECIMAL(3,0) NULL DEFAULT NULL,
  `Plot` LONGTEXT NULL DEFAULT NULL,
  `Length` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idFilm`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movieDB`.`Actor_Awards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieDB`.`Actor_Awards` (
  `idActor_Awards` INT(11) NOT NULL AUTO_INCREMENT,
  `Year` DATE NULL DEFAULT NULL,
  `idAward` INT(11) NULL DEFAULT NULL,
  `idActor` INT(11) NOT NULL,
  `idFilms` INT(11) NOT NULL,
  PRIMARY KEY (`idActor_Awards`),
  INDEX `idActor_idx` (`idActor` ASC),
  INDEX `idFilm_idx` (`idFilms` ASC),
  INDEX `idAward_idx` (`idAward` ASC),
  CONSTRAINT `idActor`
    FOREIGN KEY (`idActor`)
    REFERENCES `movieDB`.`Actors` (`idActors`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAward`
    FOREIGN KEY (`idAward`)
    REFERENCES `movieDB`.`Awards` (`idAwards`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idFilms`
    FOREIGN KEY (`idFilms`)
    REFERENCES `movieDB`.`Film` (`idFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movieDB`.`Film_Awards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieDB`.`Film_Awards` (
  `idFilm_Awards` INT(11) NOT NULL AUTO_INCREMENT,
  `Year` DATE NULL DEFAULT NULL,
  `idAwards` INT(11) NOT NULL,
  `id_Film` INT(11) NOT NULL,
  PRIMARY KEY (`idFilm_Awards`),
  INDEX `idAward_idx` (`idAwards` ASC),
  INDEX `idFilm_idx` (`id_Film` ASC),
  CONSTRAINT `idAwards`
    FOREIGN KEY (`idAwards`)
    REFERENCES `movieDB`.`Awards` (`idAwards`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_Film`
    FOREIGN KEY (`id_Film`)
    REFERENCES `movieDB`.`Film` (`idFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movieDB`.`Works_on`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieDB`.`Works_on` (
  `idWorks_on` INT(11) NOT NULL AUTO_INCREMENT,
  `Role` VARCHAR(250) NULL DEFAULT NULL,
  `star` TINYINT(4) NULL DEFAULT NULL,
  `idActors` INT(11) NOT NULL,
  `idFilm` INT(11) NOT NULL,
  PRIMARY KEY (`idWorks_on`),
  INDEX `idActors_idx` (`idActors` ASC),
  INDEX `idFilm_idx` (`idFilm` ASC),
  CONSTRAINT `idActors`
    FOREIGN KEY (`idActors`)
    REFERENCES `movieDB`.`Actors` (`idActors`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idFilm`
    FOREIGN KEY (`idFilm`)
    REFERENCES `movieDB`.`Film` (`idFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
