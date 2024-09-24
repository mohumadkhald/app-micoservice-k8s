CREATE DATABASE IF NOT EXISTS app;
GRANT ALL PRIVILEGES ON app.* TO pc@localhost IDENTIFIED BY 'pc';

USE app;

CREATE TABLE IF NOT EXISTS jobs (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  title VARCHAR(255) NOT NULL,
                                  description TEXT,
                                  min_salary DECIMAL(10, 2) CHECK (min_salary >= 0),
                                  max_salary DECIMAL(10, 2) CHECK (max_salary >= 0),
                                  location VARCHAR(255) NOT NULL,
                                  status VARCHAR(50) CHECK (status IN ('open', 'closed', 'pending')),
                                  company_id BIGINT NOT NULL
);
