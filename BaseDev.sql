SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `BaseDevs` DEFAULT CHARACTER SET utf8 ;
USE `BaseDevs` ;

-- -----------------------------------------------------
-- Table `BaseDevs`.`Companies`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BaseDevs`.`Companies` (
  `idCompanies` INT NOT NULL AUTO_INCREMENT ,
  `Namecompany` VARCHAR(45) NULL ,
  PRIMARY KEY (`idCompanies`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BaseDevs`.`Customers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BaseDevs`.`Customers` (
  `idCustomers` INT NOT NULL AUTO_INCREMENT ,
  `name_customer` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idCustomers`, `name_customer`) ,
  UNIQUE INDEX `name_customer_UNIQUE` (`name_customer` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BaseDevs`.`Projects`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BaseDevs`.`Projects` (
  `idProjects` INT NOT NULL AUTO_INCREMENT ,
  `name_project` VARCHAR(45) NULL ,
  `id_company` INT NULL ,
  `id_customer` INT NULL ,
  `cost` INT NULL ,
  INDEX `company` (`id_company` ASC) ,
  INDEX `cust` (`id_customer` ASC) ,
  PRIMARY KEY (`idProjects`) ,
  CONSTRAINT `company`
    FOREIGN KEY (`id_company` )
    REFERENCES `BaseDevs`.`Companies` (`idCompanies` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `cust`
    FOREIGN KEY (`id_customer` )
    REFERENCES `BaseDevs`.`Customers` (`idCustomers` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
PACK_KEYS = DEFAULT
ROW_FORMAT = COMPACT;


-- -----------------------------------------------------
-- Table `BaseDevs`.`Developers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BaseDevs`.`Developers` (
  `idDevelopers` INT NOT NULL AUTO_INCREMENT ,
  `NameDevelopers` VARCHAR(45) NULL ,
  `SurnameDevelopers` VARCHAR(45) NULL ,
  `id_project` INT NULL ,
  `id_comp` INT NULL ,
  `salary` INT NULL ,
  PRIMARY KEY (`idDevelopers`) ,
  INDEX `proj` (`id_project` ASC) ,
  INDEX `comp` (`id_comp` ASC) ,
  CONSTRAINT `proj`
    FOREIGN KEY (`id_project` )
    REFERENCES `BaseDevs`.`Projects` (`idProjects` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `comp`
    FOREIGN KEY (`id_comp` )
    REFERENCES `BaseDevs`.`Companies` (`idCompanies` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Base of devs'
DELAY_KEY_WRITE = 1
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `BaseDevs`.`Skills`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BaseDevs`.`Skills` (
  `idSkills` INT NOT NULL AUTO_INCREMENT ,
  `Language` VARCHAR(45) NULL ,
  `idDevelopers` INT NULL ,
  PRIMARY KEY (`idSkills`) ,
  INDEX `id_dev` (`idDevelopers` ASC) ,
  CONSTRAINT `id_dev`
    FOREIGN KEY (`idDevelopers` )
    REFERENCES `BaseDevs`.`Developers` (`idDevelopers` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `BaseDevs`.`Companies`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `BaseDevs`;
INSERT INTO `BaseDevs`.`Companies` (`idCompanies`, `Namecompany`) VALUES ('1', 'Zond');
INSERT INTO `BaseDevs`.`Companies` (`idCompanies`, `Namecompany`) VALUES ('2', 'Luxoft');
INSERT INTO `BaseDevs`.`Companies` (`idCompanies`, `Namecompany`) VALUES ('3', 'Epum');

COMMIT;

-- -----------------------------------------------------
-- Data for table `BaseDevs`.`Customers`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `BaseDevs`;
INSERT INTO `BaseDevs`.`Customers` (`idCustomers`, `name_customer`) VALUES ('1', 'Petrel');
INSERT INTO `BaseDevs`.`Customers` (`idCustomers`, `name_customer`) VALUES ('2', 'Shlumberger');

COMMIT;

-- -----------------------------------------------------
-- Data for table `BaseDevs`.`Projects`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `BaseDevs`;
INSERT INTO `BaseDevs`.`Projects` (`idProjects`, `name_project`, `id_company`, `id_customer`, `cost`) VALUES ('1', 'Inversion', '1', '2', NULL);
INSERT INTO `BaseDevs`.`Projects` (`idProjects`, `name_project`, `id_company`, `id_customer`, `cost`) VALUES ('2', 'Seismic', '2', '1', NULL);
INSERT INTO `BaseDevs`.`Projects` (`idProjects`, `name_project`, `id_company`, `id_customer`, `cost`) VALUES ('3', 'Logging', '2', '2', NULL);
INSERT INTO `BaseDevs`.`Projects` (`idProjects`, `name_project`, `id_company`, `id_customer`, `cost`) VALUES ('4', 'Crashing', '3', '1', NULL);
INSERT INTO `BaseDevs`.`Projects` (`idProjects`, `name_project`, `id_company`, `id_customer`, `cost`) VALUES ('5', 'Washing', '3', '1', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `BaseDevs`.`Developers`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `BaseDevs`;
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('1', 'Alex', 'Kaminsky', '1', NULL, NULL);
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('2', 'Eugene', 'Kutepov', '5', NULL, NULL);
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('3', 'Dmitry', 'Fomush', '2', NULL, NULL);
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('5', 'Ruslan', 'Rozhk', '3', NULL, NULL);
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('4', 'Kolya', 'Petrov', '4', NULL, NULL);
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('6', 'Oleg', 'Kondratsky', '5', NULL, NULL);
INSERT INTO `BaseDevs`.`Developers` (`idDevelopers`, `NameDevelopers`, `SurnameDevelopers`, `id_project`, `id_comp`, `salary`) VALUES ('7', 'Alex', 'Kutniy', '3', NULL, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `BaseDevs`.`Skills`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `BaseDevs`;
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('1', 'Java', '1');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('2', 'C++', '1');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('3', 'Java', '2');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('4', 'PHP', '2');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('5', 'PHP', '3');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('6', 'C++', '3');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('7', 'C++', '4');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('8', 'C++', '6');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('9', 'Java', '6');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('10', 'Java', '5');
INSERT INTO `BaseDevs`.`Skills` (`idSkills`, `Language`, `idDevelopers`) VALUES ('11', 'Python', '7');

COMMIT;
