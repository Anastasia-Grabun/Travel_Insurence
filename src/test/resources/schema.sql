CREATE TABLE classifiers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE classifier_values (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    classifier_id BIGINT NOT NULL,
    ic VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(500) NOT NULL,
    FOREIGN KEY (classifier_id) REFERENCES classifiers(id)
);

CREATE TABLE country_default_day_rate(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country_ic VARCHAR(200) NOT NULL UNIQUE,
    default_day_rate DECIMAL(10,2) NOT NULL
);

