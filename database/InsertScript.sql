-- classifier RISK_TYPE
INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT c.id, v.ic, v.description
FROM classifiers c,
     (VALUES
          ('TRAVEL_MEDICAL', 'travel policy medical risk'),
          ('TRAVEL_CANCELLATION', 'travel policy cancellation risk'),
          ('TRAVEL_LOSS_BAGGAGE', 'travel policy lost baggage risk'),
          ('TRAVEL_THIRD_PARTY_LIABILITY', 'travel policy third-party liability risk'),
          ('TRAVEL_EVACUATION', 'travel policy evacuation risk'),
          ('TRAVEL_SPORT_ACTIVITIES', 'travel policy sport activities risk')
     ) AS v(ic, description)
WHERE c.title = 'RISK_TYPE';

-- classifier COUNTRY
INSERT INTO classifiers (title, description)
VALUES ('COUNTRY', 'country classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT c.id, v.ic, v.description
FROM classifiers c,
     (VALUES
          ('LATVIA', 'country Latvia'),
          ('SPAIN', 'country Spain'),
          ('JAPAN', 'country Japan')
     ) AS v(ic, description)
WHERE c.title = 'COUNTRY';

---country_default_day_rate
INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('LATVIA', 1.00);

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('SPAIN', 2.50);

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('JAPAN', 3.50);

---age age_coefficient
INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(0, 5, 1.1);

INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(6, 10, 0.7);

INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(11, 17, 1.0);

INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(18, 40, 1.1);

INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(41, 65, 1.2);

INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(66, 150, 1.5);

---medical-limit
INSERT INTO classifiers(title, description)
VALUES('MEDICAL_RISK_LIMIT_LEVEL', 'Medical Risk limit level classifier');

INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'LEVEL_10000',
    'Medical Risk 10000 euro limit level'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'LEVEL_15000',
    'Medical Risk 15000 euro limit level'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'LEVEL_20000',
    'Medical Risk 20000 euro limit level'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';


INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'LEVEL_50000',
    'Medical Risk 50000 euro limit level'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
VALUES('LEVEL_10000', 1.0),
      ('LEVEL_15000', 1.2),
      ('LEVEL_20000', 1.5),
      ('LEVEL_50000', 2.0);


