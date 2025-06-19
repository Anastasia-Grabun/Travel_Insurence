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

CREATE TABLE IF NOT EXISTS medical_risk_limit_level(
    id BIGSERIAL PRIMARY KEY,
    medical_risk_limit_level_ic VARCHAR(200) NOT NULL UNIQUE,
    coefficient DECIMAL(10,2) NOT NULL
);

CREATE TABLE persons (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    person_code VARCHAR(200) NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    CONSTRAINT unique_person_identity UNIQUE (first_name, last_name, person_code)
);

CREATE TABLE IF NOT EXISTS agreements (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    date_from TIMESTAMP NOT NULL,
    date_to TIMESTAMP NOT NULL,
    country VARCHAR(100) NOT NULL,
    premium DECIMAL(10,2) NOT NULL
);

CREATE TABLE selected_risks (
    id BIGSERIAL PRIMARY KEY,
    agreement_id BIGINT NOT NULL REFERENCES agreements(id),
    risk_ic VARCHAR(100) NOT NULL,
    CONSTRAINT unique_selected_risks UNIQUE (agreement_id, risk_ic)
);

CREATE TABLE agreement_persons (
    id BIGSERIAL PRIMARY KEY,
    agreement_id BIGINT NOT NULL REFERENCES agreements(id),,
    person_id BIGINT NOT NULL REFERENCES persons(id),
    medical_risk_limit_level VARCHAR(100) NOT NULL
);

CREATE TABLE agreement_person_risks (
    id BIGSERIAL PRIMARY KEY,
    agreement_person_id BIGINT NOT NULL REFERENCES agreement_persons(id),
    risk_ic VARCHAR(100) NOT NULL,
    premium DECIMAL(10,2) NOT NULL,
    CONSTRAINT unique_agreement_person_risks UNIQUE (agreement_person_id, risk_ic)
);




