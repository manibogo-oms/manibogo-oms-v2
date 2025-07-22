
CREATE TABLE IF NOT EXISTS `zip_code_region` (
    `zip_code` varchar(255) NOT NULL,
    `sido` varchar(255) NOT NULL,
    `sigungu` varchar(255) NOT NULL,
    PRIMARY KEY (`zip_code`)
);

CREATE TABLE IF NOT EXISTS `orders` (
    `order_number` VARCHAR(255) NOT NULL,
    `order_state` ENUM('PLACED', 'PURCHASED', 'DISPATCHED', 'SHIPPED', 'CONFIRMED', 'CANCELLED', 'REFUNDED') NOT NULL ,
    `sales_channel` ENUM('NAVER', 'SMART_STORE') NOT NULL,
    `product_number` varchar(255) NOT NULL,
    `amount` INTEGER NOT NULL CHECK (`amount` > 0),
    `final_price` BIGINT NOT NULL CHECK (`final_price` > 0 ),
    `customer_name` varchar(255) NOT NULL,
    `customer_tel` varchar(255) NOT NULL,
    `customer_message` varchar(255),
    `recipient_name` varchar(255) NOT NULL,
    `recipient_tel1` varchar(255) NOT NULL,
    `recipient_tel2` varchar(255),
    `recipient_addr1` varchar(255) NOT NULL,
    `recipient_addr2` varchar(255),
    `recipient_zip_code` varchar(255) NOT NULL,
    `shipping_bundle_number` VARCHAR(255) NOT NULL,
    `shipping_method` ENUM('DIRECT', 'PARCEL') NOT NULL,
    `shipping_charge_type` ENUM('COD', 'PREPAID') NOT NULL,
    `shipping_tracking_number` varchar(255),
    `shipping_company_name` varchar(255),
    `dispatch_deadline` DATE,
    `preferred_shipping_date` DATE,
    `admin_memo` VARCHAR(255),
    `purchase_memo` VARCHAR(255),
    `shipping_memo` VARCHAR(255),
    `placed_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`order_number`)
);

CREATE TABLE IF NOT EXISTS `order_product_option` (
    `order_number` VARCHAR(255) NOT NULL,
    `option_seq` INT(11) NOT NULL,
    `option_key` VARCHAR(255) NOT NULL,
    `option_value` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_number`, `option_seq`)
);

CREATE TABLE IF NOT EXISTS `product` (
    `product_number` VARCHAR(255) NOT NULL,
    `product_name` VARCHAR(255) NOT NULL,
    `priority` INT(11) NOT NULL,
    `is_enabled` BIT(1) NOT NULL,
    PRIMARY KEY (`product_number`)
);

CREATE TABLE IF NOT EXISTS `variant` (
    `product_number` VARCHAR(255) NOT NULL,
    `option_key` VARCHAR(255) NOT NULL,
    `option_value` VARCHAR(255) NOT NULL,
    `label` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`product_number`, `option_key`, `option_value`)
);

CREATE TABLE IF NOT EXISTS `order_log` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `order_number` VARCHAR(255) NOT NULL,
    `changed_at` DATETIME(6),
    `previous_state` ENUM('PLACED', 'PURCHASED', 'DISPATCHED', 'SHIPPED', 'CONFIRMED', 'CANCELLED', 'REFUNDED') NOT NULL,
    `new_state` ENUM('PLACED', 'PURCHASED', 'DISPATCHED', 'SHIPPED', 'CONFIRMED', 'CANCELLED', 'REFUNDED') NOT NULL,
    `changed_by` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `member` (
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('ROLE_ADMIN', 'ROLE_LOGISTICS') NOT NULL,
    `is_enabled` BIT(1),
    PRIMARY KEY (`username`)
);