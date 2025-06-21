CREATE TABLE IF NOT EXISTS classifiers (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS classifier_values (
    id BIGSERIAL PRIMARY KEY,
    classifier_id BIGINT NOT NULL,
    ic VARCHAR(200) NOT NULL UNIQUE,
    description VARCHAR(500) NOT NULL,
    FOREIGN KEY (classifier_id) REFERENCES classifiers(id)
);

CREATE TABLE IF NOT EXISTS country_default_day_rate (
    id BIGSERIAL PRIMARY KEY,
    country_ic VARCHAR(200) NOT NULL UNIQUE,
    default_day_rate NUMERIC(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS age_coefficient (
    id BIGSERIAL PRIMARY KEY,
    age_from INT NOT NULL,
    age_to INT NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS medical_risk_limit_level (
    id BIGSERIAL PRIMARY KEY,
    medical_risk_limit_level_ic VARCHAR(200) NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS persons (
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

CREATE TABLE IF NOT EXISTS selected_risks (
    id BIGSERIAL PRIMARY KEY,
    agreement_id BIGINT NOT NULL,
    risk_ic VARCHAR(100) NOT NULL,
    CONSTRAINT unique_selected_risks UNIQUE (agreement_id, risk_ic),
    FOREIGN KEY (agreement_id) REFERENCES agreements(id)
);

CREATE TABLE IF NOT EXISTS agreement_persons (
    id BIGSERIAL PRIMARY KEY,
    agreement_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    medical_risk_limit_level VARCHAR(100),
    travel_cost DECIMAL(10,2),
    FOREIGN KEY (agreement_id) REFERENCES agreements(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

CREATE TABLE IF NOT EXISTS agreement_person_risks (
    id BIGSERIAL PRIMARY KEY,
    agreement_person_id BIGINT NOT NULL,
    risk_ic VARCHAR(100) NOT NULL,
    premium DECIMAL(10,2) NOT NULL,
    CONSTRAINT unique_agreement_person_risks UNIQUE (agreement_person_id, risk_ic),
    FOREIGN KEY (agreement_person_id) REFERENCES agreement_persons(id)
);

CREATE TABLE IF NOT EXISTS agreements_xml_export (
    id BIGSERIAL PRIMARY KEY,
    agreement_uuid VARCHAR(255) NOT NULL,
    already_exported BOOL NOT NULL,
    FOREIGN KEY (agreement_uuid) REFERENCES agreements(uuid)
);


