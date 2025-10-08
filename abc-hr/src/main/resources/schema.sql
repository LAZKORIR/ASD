DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
                            department_no    NUMERIC(38,0) PRIMARY KEY,
                            name             VARCHAR(100) NOT NULL,
                            head_employee_no VARCHAR(15)  -- may be NULL, FK added after employee exists
);

CREATE TABLE employee (
                          employee_no        VARCHAR(15) PRIMARY KEY,
                          first_name         VARCHAR(60) NOT NULL,
                          last_name          VARCHAR(60) NOT NULL,
                          date_of_birth      DATE        NOT NULL,
                          date_of_employment DATE        NOT NULL,
                          biweekly_salary    DECIMAL(12,2) NOT NULL CHECK (biweekly_salary >= 0),
                          department_no      NUMERIC(38,0) NOT NULL,
                          CONSTRAINT fk_emp_dept FOREIGN KEY (department_no) REFERENCES department(department_no)
);

-- Add HOD FK now that employee exists
ALTER TABLE department
    ADD CONSTRAINT fk_dept_hod FOREIGN KEY (head_employee_no)
        REFERENCES employee(employee_no);
