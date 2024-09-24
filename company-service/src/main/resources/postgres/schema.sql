-- Create the database if it doesn't exist
DO
$$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'company_service') THEN
      EXECUTE 'CREATE DATABASE "company_service";';
END IF;
END
$$;

-- Switch to the company_service database
\c company_service;

-- Create user and grant privileges if the user doesn't exist
DO
$$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'pc') THEN
      EXECUTE 'CREATE USER pc WITH PASSWORD ''pc'';';
END IF;
END
$$;

GRANT ALL PRIVILEGES ON DATABASE "company_service" TO pc;

-- Create the companies table if it doesn't exist
CREATE TABLE IF NOT EXISTS companies (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
  description TEXT,
  rating DECIMAL(3, 2)
  );
