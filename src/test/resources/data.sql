--RISK_TYPE
INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'TRAVEL_MEDICAL', 'travel policy medical risk' FROM classifiers cl WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'TRAVEL_CANCELLATION', 'travel policy cancellation risk' FROM classifiers cl WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'TRAVEL_LOSS_BAGGAGE', 'travel policy lost baggage risk' FROM classifiers cl WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'TRAVEL_THIRD_PARTY_LIABILITY', 'travel policy third-party liability risk' FROM classifiers cl WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'TRAVEL_EVACUATION', 'travel policy evacuation risk' FROM classifiers cl WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'TRAVEL_SPORT_ACTIVITIES', 'travel policy sport activities risk' FROM classifiers cl WHERE cl.title = 'RISK_TYPE';

---Country
INSERT INTO classifiers (title, description)
VALUES ('COUNTRY', 'country classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'LATVIA', 'country Latvia' FROM classifiers cl WHERE cl.title = 'COUNTRY';
INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'SPAIN', 'country Spain' FROM classifiers cl WHERE cl.title = 'COUNTRY';
INSERT INTO classifier_values (classifier_id, ic, description)
SELECT cl.id, 'JAPAN', 'country Japan' FROM classifiers cl WHERE cl.title = 'COUNTRY';


