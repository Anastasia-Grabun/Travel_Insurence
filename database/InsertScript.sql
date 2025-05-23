INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES
    (1, 'TRAVEL_MEDICAL', 'travel policy medical risk'),
    (1, 'TRAVEL_CANCELLATION', 'travel policy cancellation risk'),
    (1, 'TRAVEL_LOSS_BAGGAGE', 'travel policy lost baggage risk'),
    (1, 'TRAVEL_THIRD_PARTY_LIABILITY', 'travel policy third-party liability risk'),
    (1, 'TRAVEL_EVACUATION', 'travel policy evacuation risk'),
    (1, 'TRAVEL_SPORT_ACTIVITIES', 'travel policy sport activities risk');