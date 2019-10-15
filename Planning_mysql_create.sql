CREATE TABLE `Plan` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Parent_id` INT(30),
	`Project_id` INT(30) NOT NULL,
	`Name` VARCHAR(255) NOT NULL,
	`Cur_version` INT(30) NOT NULL,
	`User_created` INT(30) NOT NULL,
	`Date_created` DATETIME NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Execution_Log` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Plan_id` INT(30) NOT NULL,
	`Version_id` INT(30),
	`State_id` INT(30),
	`Date_log` DATETIME NOT NULL,
	`Item_id` INT(30) NOT NULL,
	`Item_value` VARCHAR(255) NOT NULL,
	`User_executed` INT(30) NOT NULL,
	`Date_start` DATETIME,
	`Date_end` DATETIME,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Plan_Revision` (
	`Version_Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Plan_id` INT(30) NOT NULL,
	`Date_created` DATETIME NOT NULL,
	`Date_updated` DATETIME NOT NULL,
	`User_created` INT NOT NULL,
	`User_updated` INT(30) NOT NULL,
	`Update_reason` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`Version_Id`,`Plan_id`)
);

CREATE TABLE `Plan_Detail` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Plan_id` INT(30) NOT NULL,
	`Cur_version` INT(30),
	`Item_id` INT(30) NOT NULL,
	`Item_value` VARCHAR(255) NOT NULL,
	`User_assigned` INT(30) NOT NULL,
	`Date_start` DATETIME,
	`Date_end` DATETIME,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Plan_Item` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Parent_id` INT(30) NOT NULL,
	`Name` VARCHAR(255) NOT NULL,
	`Unit` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `User` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(255) NOT NULL,
	`Title` VARCHAR(255),
	`Department` VARCHAR(255),
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Goal` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Parent_id` INT(30) NOT NULL,
	`Target` INT(30) NOT NULL,
	`Description` VARCHAR(255),
	`Date_created` DATETIME,
	`User_created` INT(30),
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Project` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Code` VARCHAR(255) NOT NULL,
	`Name` VARCHAR(255),
	`Description` VARCHAR(255),
	`Manager_id` INT(30),
	`Goal_id` INT(30),
	`Date_start` DATETIME,
	`Date_end` DATETIME,
	`Date_created` DATETIME,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Detail_Revision` (
	`Plan_id` INT(30) NOT NULL,
	`Detail_id` INT(30) NOT NULL,
	`Old_version` INT(30) NOT NULL
);

CREATE TABLE `State` (
	`Id` INT(30) NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(255) NOT NULL,
	`Project_id` INT NOT NULL,
	`Is_initial` BOOLEAN,
	`Is_goal` BOOLEAN,
	`Content` VARCHAR(255) NOT NULL,
	`Result` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Constraint` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Plan_id` INT NOT NULL,
	`Constraint_rule` VARCHAR(255),
	PRIMARY KEY (`Id`)
);

ALTER TABLE `Plan` ADD CONSTRAINT `Plan_fk0` FOREIGN KEY (`Parent_id`) REFERENCES `Plan`(`Id`);

ALTER TABLE `Plan` ADD CONSTRAINT `Plan_fk1` FOREIGN KEY (`Project_id`) REFERENCES `Project`(`Id`);

ALTER TABLE `Plan` ADD CONSTRAINT `Plan_fk2` FOREIGN KEY (`User_created`) REFERENCES `User`(`Id`);

ALTER TABLE `Execution_Log` ADD CONSTRAINT `Execution_Log_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan`(`Id`);

ALTER TABLE `Execution_Log` ADD CONSTRAINT `Execution_Log_fk1` FOREIGN KEY (`Version_id`) REFERENCES `Plan_Revision`(`Version_Id`);

ALTER TABLE `Execution_Log` ADD CONSTRAINT `Execution_Log_fk2` FOREIGN KEY (`State_id`) REFERENCES `State`(`Id`);

ALTER TABLE `Execution_Log` ADD CONSTRAINT `Execution_Log_fk3` FOREIGN KEY (`Item_id`) REFERENCES `Plan_Item`(`Id`);

ALTER TABLE `Execution_Log` ADD CONSTRAINT `Execution_Log_fk4` FOREIGN KEY (`User_executed`) REFERENCES `User`(`Id`);

ALTER TABLE `Plan_Revision` ADD CONSTRAINT `Plan_Revision_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan`(`Id`);

ALTER TABLE `Plan_Revision` ADD CONSTRAINT `Plan_Revision_fk1` FOREIGN KEY (`User_created`) REFERENCES `User`(`Id`);

ALTER TABLE `Plan_Revision` ADD CONSTRAINT `Plan_Revision_fk2` FOREIGN KEY (`User_updated`) REFERENCES `User`(`Id`);

ALTER TABLE `Plan_Detail` ADD CONSTRAINT `Plan_Detail_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan`(`Id`);

ALTER TABLE `Plan_Detail` ADD CONSTRAINT `Plan_Detail_fk1` FOREIGN KEY (`Item_id`) REFERENCES `Plan_Item`(`Id`);

ALTER TABLE `Plan_Detail` ADD CONSTRAINT `Plan_Detail_fk2` FOREIGN KEY (`User_assigned`) REFERENCES `User`(`Id`);

ALTER TABLE `Plan_Item` ADD CONSTRAINT `Plan_Item_fk0` FOREIGN KEY (`Parent_id`) REFERENCES `Plan_Item`(`Id`);

ALTER TABLE `Goal` ADD CONSTRAINT `Goal_fk0` FOREIGN KEY (`Parent_id`) REFERENCES `Goal`(`Id`);

ALTER TABLE `Goal` ADD CONSTRAINT `Goal_fk1` FOREIGN KEY (`User_created`) REFERENCES `User`(`Id`);

ALTER TABLE `Project` ADD CONSTRAINT `Project_fk0` FOREIGN KEY (`Manager_id`) REFERENCES `User`(`Id`);

ALTER TABLE `Project` ADD CONSTRAINT `Project_fk1` FOREIGN KEY (`Goal_id`) REFERENCES `Goal`(`Id`);

ALTER TABLE `Detail_Revision` ADD CONSTRAINT `Detail_Revision_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan`(`Id`);

ALTER TABLE `Detail_Revision` ADD CONSTRAINT `Detail_Revision_fk1` FOREIGN KEY (`Detail_id`) REFERENCES `Plan_Detail`(`Id`);

ALTER TABLE `State` ADD CONSTRAINT `State_fk0` FOREIGN KEY (`Project_id`) REFERENCES `Project`(`Id`);

ALTER TABLE `Constraint` ADD CONSTRAINT `Constraint_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan`(`Id`);

