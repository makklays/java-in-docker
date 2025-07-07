
-- V4__create_cards_table.sql
-- Migration #4: create table cards

-- Creating table 'cards' for saving main data of cards of users
CREATE TABLE cards
(
    id                bigint auto_increment primary key,
    user_id           bigint not null,

    card_number       VARCHAR(19) null,
    cardholder_name   VARCHAR(255) null,
    expiration_date   DATE null,
    cvv               VARCHAR(4) null,
    pin               VARCHAR(6) null,
    balance           DECIMAL(15,2) NOT NULL,
    credit_limit      DECIMAL(15,2) NOT NULL,
    currency          VARCHAR(3) null, # например, USD, EUR
    status            ENUM('ACTIVE', 'BLOCKED', 'EXPIRED') NOT NULL DEFAULT 'EXPIRED',

    bank_name         VARCHAR(255) null,
    type              ENUM('VISA', 'MASTERCARD', 'AMEX', 'MIR') NOT NULL DEFAULT 'VISA',
    issue_date        DATE null, # дата выпуска карты
    reward_points     bigint null, # бонусные баллы (если есть программа лояльности)
    interest_rate     DECIMAL(15,2) NOT NULL, # процентная ставка
    contactless       bit null, # поддержка бесконтактной оплаты

    created_at        datetime(6)  null,
    updated_at        datetime(6)  null
);

-- Adding index for fast searching by user_id
CREATE INDEX idx_cards_card_user_id ON cards(user_id);

-- Adding index for fast searching by card_number
CREATE INDEX idx_cards_card_number ON cards(card_number);

-- Adding index for fast searching by status
CREATE INDEX idx_cards_card_status ON cards(status);

