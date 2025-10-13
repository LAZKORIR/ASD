-- Departments (as given)
INSERT INTO department(department_no, name) VALUES
                                                (31288741190182539912, 'Sales'),
                                                (29274582650152771644, 'Marketing'),
                                                (29274599650152771609, 'Engineering');

-- Employees (as given)
INSERT INTO employee(employee_no, first_name, last_name, date_of_birth, date_of_employment, biweekly_salary, department_no) VALUES
                                                                                                                                ('000-11-1234','Michael','Philips','1995-12-31','2021-10-15',2750.50,29274599650152771609), -- Eng (3)
                                                                                                                                ('000-61-0987','Anna','Smith','1981-09-17','2022-08-21',2500.00,29274582650152771644),      -- Marketing (2)
                                                                                                                                ('000-99-1766','John','Hammonds','2001-07-15','2025-01-23',1560.75,31288741190182539912),   -- Sales (1)
                                                                                                                                ('000-41-1768','Barbara','Jones','2000-11-18','2025-03-13',1650.55,29274582650152771644);   -- Marketing (2)

-- Heads (as given; note Sales HOD not in Sales department)
UPDATE department SET head_employee_no = '000-61-0987' WHERE department_no = 31288741190182539912; -- Sales -> Anna
UPDATE department SET head_employee_no = NULL          WHERE department_no = 29274582650152771644; -- Marketing -> null
UPDATE department SET head_employee_no = '000-11-1234' WHERE department_no = 29274599650152771609; -- Eng -> Michael
