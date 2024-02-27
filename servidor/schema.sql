DROP TABLE IF EXISTS categories_preferences;
DROP TABLE IF EXISTS users_promotions;
DROP TABLE IF EXISTS promotions;
DROP TABLE IF EXISTS issues;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS auctions;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS wallets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    role_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY(role_id)
);

-- tabla usuarios
CREATE TABLE users (
	user_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    born_date DATE,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT(NOW()),
    role_id BIGINT DEFAULT(1),
    PRIMARY KEY(user_id),
    FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

-- tabla carteras
CREATE TABLE wallets (
	wallet_id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    amount FLOAT DEFAULT(0),
    PRIMARY KEY(wallet_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

-- tabla categorias
CREATE TABLE categories(
	category_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    PRIMARY KEY(category_id)
);

-- tabla subastas
CREATE TABLE auctions (
	auction_id BIGINT NOT NULL AUTO_INCREMENT,
    auction_name VARCHAR(50) NOT NULL,
    auction_description VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    starts_at TIMESTAMP NOT NULL,
    ends_at TIMESTAMP NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    initial_price FLOAT NOT NULL,
    PRIMARY KEY(auction_id),
    FOREIGN KEY(category_id) REFERENCES categories(category_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

-- tabla pujas
CREATE TABLE bids (
	bid_id BIGINT NOT NULL AUTO_INCREMENT,
    auction_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    amount FLOAT NOT NULL,
    PRIMARY KEY(bid_id),
    FOREIGN KEY(auction_id) REFERENCES auctions(auction_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

-- tabla notificaciones
CREATE TABLE notifications (
	notification_id BIGINT NOT NULL AUTO_INCREMENT,
	category_id BIGINT NOT NULL,
    subject VARCHAR(50) NOT NULL,
    message VARCHAR(255) NOT NULL,
    send_on TIMESTAMP NOT NULL,
    PRIMARY KEY(notification_id),
    FOREIGN KEY(category_id) REFERENCES categories(category_id)
);

-- tabla incidencias
CREATE TABLE issues (
	issue_id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    subject VARCHAR(50) NOT NULL,
    message VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT(NOW()),
    is_solved BOOLEAN NOT NULL DEFAULT(false),
    PRIMARY KEY(issue_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

-- tabla codigos promocionales
CREATE TABLE promotions (
	promotion_id BIGINT NOT NULL AUTO_INCREMENT,
    code VARCHAR(20) NOT NULL,
    ends_at TIMESTAMP NOT NULL,
    amount FLOAT NOT NULL,
    PRIMARY KEY(promotion_id)
);

-- tabla de uso de codigos promocionales por usuarios
CREATE TABLE users_promotions (
	promotion_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY(promotion_id, user_id),
    FOREIGN KEY(promotion_id) REFERENCES promotions(promotion_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

-- tabla de preferencias de categorias por usuario
CREATE TABLE categories_preferences(
	user_id bigint NOT NULL,
    category_id bigint NOT NULL,
    PRIMARY KEY(user_id, category_id),
    foreign key(user_id) references users(user_id),
    foreign key(category_id) references categories(category_id)
);

-- trigger
drop trigger if exists update_wallet;

create trigger update_wallet after insert on users_promotions
for each row
begin
    update wallets set amount = amount + (select amount from promotions where promotion_id = new.promotion_id) where user_id = new.user_id;
end;

drop trigger if exists create_wallet;

create trigger create_wallet after insert on users
for each row
begin
    insert into wallets(user_id, amount) values (new.user_id, 0);
end;
