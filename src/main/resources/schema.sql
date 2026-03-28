CREATE TABLE tasks (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       status VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);