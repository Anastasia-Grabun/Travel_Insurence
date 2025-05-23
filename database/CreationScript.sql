CREATE TABLE IF NOT EXISTS classifiers (
    id BIGINT PRIMARY KEY,
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

