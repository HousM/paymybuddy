CREATE DATABASE paymybuddy CHARACTER SET utf8mb4;

USE paymybuddy;

CREATE TABLE user (
                id BIGINT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(70) NOT NULL,
                last_name VARCHAR(70) NOT NULL,
                email VARCHAR(70) NOT NULL,
                password VARCHAR(100) NOT NULL,
                phone VARCHAR(20) NOT NULL,
                PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE buddy_account (
                id BIGINT AUTO_INCREMENT NOT NULL,
                user_id BIGINT NOT NULL,
                balance DECIMAL(7,2) NOT NULL,
                PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE bank_account (
                id BIGINT AUTO_INCREMENT NOT NULL,
                user_id BIGINT NOT NULL,
                iban VARCHAR(34),
                bic VARCHAR(11),
                PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE transaction (
                id BIGINT AUTO_INCREMENT NOT NULL,
                type VARCHAR(9) NOT NULL,
                buddy_account_receiver_id BIGINT,
                buddy_account_owner_id BIGINT NOT NULL,
                bank_account_id BIGINT,
                date DATE NOT NULL,
                description VARCHAR(100) NOT NULL,
                amount DECIMAL(5,2) NOT NULL,
                fee DECIMAL(3,2) NOT NULL,
                PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE connect (
                owner_id BIGINT NOT NULL,
                buddy_id BIGINT NOT NULL,
                PRIMARY KEY (owner_id, buddy_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
