
CREATE TABLE IF NOT EXISTS reviews (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       rating DOUBLE PRECISION CHECK (rating >= 0 AND rating <= 5),
                       company_id BIGINT NOT NULL
);
