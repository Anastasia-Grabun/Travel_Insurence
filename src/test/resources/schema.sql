CREATE TABLE classifiers (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX ix_classifiers_title ON classifiers(title);

CREATE TABLE classifier_values (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    classifier_id BIGINT NOT NULL,
    ic VARCHAR(200) NOT NULL,
    description VARCHAR(500) NOT NULL
);

ALTER TABLE classifier_values
    ADD CONSTRAINT fk_classifier
        FOREIGN KEY (classifier_id)
            REFERENCES classifiers(id);

CREATE UNIQUE INDEX ix_classifier_values_ic
    ON classifier_values(ic);
