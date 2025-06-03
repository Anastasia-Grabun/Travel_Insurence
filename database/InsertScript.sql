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
