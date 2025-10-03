-- customers (no FK column here)
INSERT INTO customer (first_name, last_name) VALUES ('Bob', 'Jones');
INSERT INTO customer (first_name, last_name) VALUES ('Anna', 'Smith');
INSERT INTO customer (first_name, last_name) VALUES ('Carlos', 'Jimenez');

-- accounts: FK customer_id is here (1: Bob, 2: Anna, 3: Carlos)
INSERT INTO account (account_number, account_type, date_opened, balance, customer_id)
VALUES ('AC1002', 'Checking', '2022-07-10', 10900.50, 2);

INSERT INTO account (account_number, account_type, date_opened, balance, customer_id)
VALUES ('AC1001', 'Savings', '2021-11-15', 125.95, 1);

INSERT INTO account (account_number, account_type, date_opened, balance, customer_id)
VALUES ('AC1003', 'Savings', '2022-07-11', 15000.00, 3);
