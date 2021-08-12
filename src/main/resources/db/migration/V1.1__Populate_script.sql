INSERT INTO department(id, name) VALUES(1, "accounting");
INSERT INTO department(id, name) VALUES(2, "it");
INSERT INTO department(id, name) VALUES(3, "hr");

DELIMITER $$

CREATE PROCEDURE insert_accounting_test_data()
BEGIN
  DECLARE i INT DEFAULT 1;

  WHILE i < 10 DO
INSERT INTO employee (id , `name`, salary, employee_department) VALUES(i, CONCAT('accountName', i), 2000.50, 1);
SET i = i + 1;
END WHILE;
END$$

DELIMITER ;

CALL insert_accounting_test_data();
DROP PROCEDURE insert_accounting_test_data;

DELIMITER $$

CREATE PROCEDURE insert_it_test_data()
BEGIN
  DECLARE i INT DEFAULT 10;

  WHILE i < 1000 DO
INSERT INTO employee (id , `name`, salary, employee_department) VALUES(i, CONCAT('itName', i), 3000.50, 2);
SET i = i + 1;
END WHILE;
END$$

DELIMITER ;

CALL insert_it_test_data();
DROP PROCEDURE insert_it_test_data;

DELIMITER $$

CREATE PROCEDURE insert_hr_test_data()
BEGIN
  DECLARE i INT DEFAULT 1000;

  WHILE i < 1004 DO
INSERT INTO employee (id , `name`, salary, employee_department) VALUES(i, CONCAT('hrName', i), 1000.50, 3);
SET i = i + 1;
END WHILE;
END$$

DELIMITER ;

CALL insert_hr_test_data();
DROP PROCEDURE insert_hr_test_data;