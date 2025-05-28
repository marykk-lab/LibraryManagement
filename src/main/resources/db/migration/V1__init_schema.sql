CREATE TABLE IF NOT EXISTS users (
                                     user_id SERIAL PRIMARY KEY,
                                     username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255),
    city VARCHAR(255),
    role VARCHAR(255) NOT NULL
    );

INSERT INTO users (username, password, email, phone, city, role)
VALUES (
           'admin',
           '$2a$10$Ln7J5N7IdWKGOy0kt60EpeSkNSR07eL7EfBIV/Sb6vsLIrcsrwWzS',
           'admin@admin.com',
           NULL,
           NULL,
           'ADMIN'
       )
    ON CONFLICT (username) DO NOTHING;