-- Create the companies table if it doesn't exist
CREATE TABLE IF NOT EXISTS companies (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
  description TEXT,
  rating DECIMAL(3, 2)
  );
