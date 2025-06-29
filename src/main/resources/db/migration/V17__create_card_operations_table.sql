
-- V17__create_card_operations_table.sql
-- Migration #17: create table card operations

-- Creating table 'card_operations' for saving main data of cards of users
CREATE TABLE card_operations
(
    id                bigint auto_increment primary key,
    user_id           bigint null,

    operation_date    DATE null, # дата операции
    operation_type    ENUM('PAYMENT', 'WITHDRAW', 'DEPOSIT', 'TRANSFER', 'SALARY_PAYMENT', 'FEE') NOT NULL DEFAULT 'PAYMENT',

    # payment - оплата покупок
    # withdraw - снятие наличных
    # transfer - перевод между счетами
    # deposit - пополнение карты
    # salary_payment - начисление зарплаты
    # fee - комиссия банка

    #amount           DOUBLE null, # для скорости, но не точности
    amount            DECIMAL(19, 2) NULL # для точности и работы с деньгами

    currency          VARCHAR(3) null, # например, USD, EUR
    description       text null,
    merchant_name     VARCHAR(255) null,
    status            ENUM('COMPLETED', 'PENDING', 'FAILED') NOT NULL DEFAULT 'FAILED',

    created_at        datetime(6)  null,
    updated_at        datetime(6)  null
);

-- Adding index for fast searching by user_id
CREATE INDEX idx_card_operations_user_id ON card_operations(user_id);

-- Adding index for fast searching by operation_type
CREATE INDEX idx_card_operations_operation_type ON card_operations(operation_type);

-- Adding index for fast searching by currency
CREATE INDEX idx_card_operations_currency ON card_operations(currency);

-- Adding index for fast searching by status
CREATE INDEX idx_card_operations_status ON card_operations(status);

