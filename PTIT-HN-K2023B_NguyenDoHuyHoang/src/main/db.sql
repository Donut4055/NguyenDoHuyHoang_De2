CREATE DATABASE hackathon01;
USE hackathon01;

CREATE TABLE departments (
                             department_id INT AUTO_INCREMENT PRIMARY KEY,
                             department_name VARCHAR(50) NOT NULL,
    description TEXT,
    status bit(1) DEFAULT 1
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL ,
    avatar_url VARCHAR(255),
    department_id INT,
    status bit(1) DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

DELIMITER //

CREATE PROCEDURE get_all_employees()

BEGIN
    SELECT e.employee_id, e.fullname, e.email, e.phone_number, e.avatar_url,
           d.department_name AS department_name, e.status, e.created_at
    FROM employees e
    LEFT JOIN departments d ON e.department_id = d.department_id
    WHERE e.status = 1
    ORDER BY e.created_at DESC;
END //


CREATE PROCEDURE get_all_departments()
BEGIN
    SELECT * FROM departments
             WHERE status = 1;
END //

CREATE PROCEDURE edit_employee(
    IN p_employee_id INT,
    IN p_fullname VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_phone_number VARCHAR(15),
    IN p_avatar_url VARCHAR(255),
    IN p_department_id INT
)
BEGIN
    UPDATE employees
    SET fullname = p_fullname,
        email = p_email,
        phone_number = p_phone_number,
        avatar_url = p_avatar_url,
        department_id = p_department_id
    WHERE employee_id = p_employee_id;
END //

CREATE PROCEDURE edit_department(
    IN p_department_id INT,
    IN p_name VARCHAR(50),
    IN p_description TEXT,
    IN p_status BIT(1)
)
BEGIN
    UPDATE departments
    SET department_name = p_name,
        description = p_description,
        status = p_status
    WHERE department_id = p_department_id;
END //

DELIMITER //
CREATE PROCEDURE add_department(
    IN p_name VARCHAR(50),
    IN p_description TEXT,
    IN p_status BIT(1)
)
BEGIN
    INSERT INTO departments (department_name, description, status)
    VALUES (p_name, p_description, p_status);
END //

CREATE PROCEDURE search_departments_ny_name(
    IN p_name VARCHAR(50)
)
BEGIN
    SELECT * FROM departments
    WHERE department_name LIKE CONCAT('%', p_name, '%')
          AND status = 1;
END //

CREATE PROCEDURE delete_department(
    IN p_department_id INT
)
BEGIN
    UPDATE departments
    SET status = 0
    WHERE department_id = p_department_id
      AND NOT EXISTS (SELECT 1 FROM employees WHERE department_id = p_department_id AND status = 1);
END //


CREATE PROCEDURE add_employee(
    IN p_fullname VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_phone_number VARCHAR(15),
    IN p_avatar_url VARCHAR(255),
    IN p_department_id INT
)
BEGIN
    INSERT INTO employees (fullname, email, phone_number, avatar_url, department_id)
    VALUES (p_fullname, p_email, p_phone_number, p_avatar_url, p_department_id);
END //

CREATE PROCEDURE search_employees_by_name(
    IN p_fullname VARCHAR(100)
)
BEGIN
    SELECT * FROM employees
    WHERE fullname LIKE CONCAT('%', p_fullname, '%')
          AND status = 1;
END //

CREATE PROCEDURE delete_employee(
    IN p_employee_id INT
)
BEGIN
    UPDATE employees
    SET status = 0
    WHERE employee_id = p_employee_id;
END //

DELIMITER //

DELIMITER //
CREATE PROCEDURE find_employee_by_id(
    IN p_employee_id INT
)
BEGIN
    SELECT e.employee_id, e.fullname, e.email, e.phone_number, e.avatar_url,
           d.department_name AS department_name, e.status, e.created_at
    FROM employees e
    LEFT JOIN departments d ON e.department_id = d.department_id
    WHERE e.employee_id = p_employee_id AND e.status = 1;
END //

CREATE PROCEDURE find_department_by_id(
    IN p_department_id INT
)
BEGIN
    SELECT * FROM departments
    WHERE department_id = p_department_id AND status = 1;
END //
DELIMITER //