CREATE TABLE IF NOT EXISTS classifiers (
    id BIGINT PRIMARY KEY,
    title VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS classifier_values (
    id BIGINT PRIMARY KEY,
    classifier_id BIGINT NOT NULL,
    ic VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(500) NOT NULL,
    FOREIGN KEY (classifier_id) REFERENCES classifiers(id)
);

CREATE TABLE IF NOT EXISTS country_default_day_rate(
    id BIGINT PRIMARY KEY,
    country_ic VARCHAR(200) NOT NULL UNIQUE,
    default_day_rate DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS age_coefficient(
    id BIGSERIAL PRIMARY KEY,
    age_from INT NOT NULL,
    age_to INT NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL
);


