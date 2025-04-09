CREATE DATABASE parking;
use parking;


CREATE TABLE vehicles (
    vehicle_id VARCHAR(20) NOT NULL,
    user_id INT NOT NULL,
    type VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
    PRIMARY KEY (vehicle_id)
);

CREATE TABLE parking_lots (
    parking_id VARCHAR(10) NOT NULL,
    fullname VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    location VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
    capacity INT NULL,
    status VARCHAR(50) NULL,
    PRIMARY KEY (parking_id)
);

CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    role_id INT NOT NULL,
    fullname VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    address VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(50) NULL,
    password VARCHAR(100) NULL,
    image VARCHAR(200) NULL,
    qr_code VARCHAR(255),
    PRIMARY KEY (user_id)
);

CREATE TABLE parking_sessions (
    parking_sessionID INT NOT NULL AUTO_INCREMENT,
    parking_id VARCHAR(10) NOT NULL,
    vehicle_id VARCHAR(20) NOT NULL,
    entry_time DATETIME NULL,
    exit_time DATETIME NULL,
    fee INT NULL,
    total INT NULL,
    pic_entry VARCHAR(200) NULL,
    pic_exit VARCHAR(200) NULL,
    status VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
    PRIMARY KEY (parking_sessionID)
);

CREATE TABLE parking_sessions_visitor (
    parking_visitorID INT NOT NULL AUTO_INCREMENT,
    parking_id VARCHAR(10) NOT NULL,
    vehicle_visitorID VARCHAR(20) NOT NULL,
    pic_entry VARCHAR(200) NULL,
    pic_exit VARCHAR(100) NULL,
    entry_time DATETIME NULL,
    exit_time DATETIME NULL,
    fee INT NULL,
    total INT NULL,
    status VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
    PRIMARY KEY (parking_visitorID)
);

CREATE TABLE roles (
    role_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (role_id)
);

-- Thêm khóa ngoại
ALTER TABLE parking_sessions 
ADD CONSTRAINT fk_parking_sessions_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles (vehicle_id);

ALTER TABLE parking_sessions 
ADD CONSTRAINT fk_parking_sessions_parking FOREIGN KEY (parking_id) REFERENCES parking_lots (parking_id);

ALTER TABLE parking_sessions_visitor 
ADD CONSTRAINT fk_parking_sessions_visitor_parking FOREIGN KEY (parking_id) REFERENCES parking_lots (parking_id);

ALTER TABLE vehicles 
ADD CONSTRAINT fk_vehicles_user FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE users 
ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles (role_id);