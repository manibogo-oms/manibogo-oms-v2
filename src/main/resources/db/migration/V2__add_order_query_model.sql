CREATE TABLE order_state_history
(
    order_number  VARCHAR(255) NOT NULL,
    placed_at     datetime NULL,
    purchased_at  datetime NULL,
    dispatched_at datetime NULL,
    shipped_at    datetime NULL,
    confirmed_at  datetime NULL,
    cancelled_at  datetime NULL,
    refunded_at   datetime NULL,
    CONSTRAINT PK_ORDER_STATE_HISTORY PRIMARY KEY (order_number)
);

ALTER TABLE orders
    ADD option_key1 VARCHAR(255) NULL;

ALTER TABLE orders
    ADD option_key2 VARCHAR(255) NULL;

ALTER TABLE orders
    ADD option_key3 VARCHAR(255) NULL;

ALTER TABLE orders
    ADD option_value1 VARCHAR(255) NULL;

ALTER TABLE orders
    ADD option_value2 VARCHAR(255) NULL;

ALTER TABLE orders
    ADD option_value3 VARCHAR(255) NULL;

DROP TABLE order_log;

DROP TABLE order_product_option;

ALTER TABLE orders DROP
COLUMN placed_at;