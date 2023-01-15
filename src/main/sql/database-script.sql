CREATE SCHEMA IF NOT EXISTS `space_trip` DEFAULT CHARACTER SET utf8 ;
USE `space_trip` ;

-- -----------------------------------------------------
-- Table `space_trip`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`order_status` (
    `idStatus` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`idStatus`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `space_trip`.`planet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`planet` (
    `id_planet` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_planet`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 9
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `space_trip`.`trip_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`trip_status` (
    `id_trip_status` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_trip_status`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `space_trip`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`role` (
    `id_role` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id_role`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `space_trip`.`user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`user_status` (
    `id_user_status` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_user_status`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `space_trip`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`user` (
    `id_user` INT NOT NULL AUTO_INCREMENT,
    `lastname` VARCHAR(45) NOT NULL,
    `firstname` VARCHAR(45) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(13) NOT NULL,
    `fk_role` INT NOT NULL,
    `fk_user_status` INT NOT NULL,
    PRIMARY KEY (`id_user`),
    INDEX `fk_user_role` (`fk_role` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
    INDEX `fk_user_user_status1_idx` (`fk_user_status` ASC) VISIBLE,
    CONSTRAINT `fk_user_role`
    FOREIGN KEY (`fk_role`)
    REFERENCES `space_trip`.`role` (`id_role`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_user_user_status1`
    FOREIGN KEY (`fk_user_status`)
    REFERENCES `space_trip`.`user_status` (`id_user_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `space_trip`.`trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`trip` (
    `id_trip` INT NOT NULL AUTO_INCREMENT,
    `created` TIMESTAMP NOT NULL,
    `started` TIMESTAMP NOT NULL,
    `price` FLOAT NOT NULL,
    `count_vacancies` INT NOT NULL,
    `fk_planet` INT NOT NULL,
    `fk_trip_status` INT NOT NULL,
    `fk_operator` INT NOT NULL,
    PRIMARY KEY (`id_trip`),
    INDEX `fk_trip_planet` (`fk_planet` ASC) VISIBLE,
    INDEX `fk_trip_trip_status1_idx` (`fk_trip_status` ASC) VISIBLE,
    INDEX `fk_trip_user1_idx` (`fk_operator` ASC) VISIBLE,
    CONSTRAINT `fk_trip_planet`
    FOREIGN KEY (`fk_planet`)
    REFERENCES `space_trip`.`planet` (`id_planet`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_trip_trip_status1`
    FOREIGN KEY (`fk_trip_status`)
    REFERENCES `space_trip`.`trip_status` (`id_trip_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_trip_user1`
    FOREIGN KEY (`fk_operator`)
    REFERENCES `space_trip`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `space_trip`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `space_trip`.`order` (
    `id_order` INT NOT NULL AUTO_INCREMENT,
    `ordered_seats` INT NOT NULL DEFAULT '1',
    `payment_amount` FLOAT NOT NULL,
    `processed` TIMESTAMP NULL,
    `fk_trip` INT NOT NULL,
    `fk_order_status` INT NOT NULL,
    `fk_user` INT NOT NULL,
    PRIMARY KEY (`id_order`),
    INDEX `fk_order_trip` (`fk_trip` ASC) VISIBLE,
    INDEX `fk_order_status` (`fk_order_status` ASC) VISIBLE,
    INDEX `fk_order_user1_idx` (`fk_user` ASC) VISIBLE,
    CONSTRAINT `fk_order_status`
    FOREIGN KEY (`fk_order_status`)
    REFERENCES `space_trip`.`order_status` (`idStatus`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_order_trip`
    FOREIGN KEY (`fk_trip`)
    REFERENCES `space_trip`.`trip` (`id_trip`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`fk_user`)
    REFERENCES `space_trip`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb3;