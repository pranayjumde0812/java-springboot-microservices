use configdb;

CREATE TABLE PROPERTIES (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    APPLICATION VARCHAR(200),
    PROFILE VARCHAR(200),
    LABEL VARCHAR(200),
    PROP_KEY VARCHAR(200),
    PROP_VALUE VARCHAR(200),
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO PROPERTIES (application, profile, label, prop_key, prop_value) VALUES
('configdemo', 'prod', 'main', 'build.id', '101'),
('configdemo', 'prod', 'main', 'build.version', '1.2.3'),
('configdemo', 'prod', 'main', 'build.name', 'Database-Production-Build SERVER APP'),
('configdemo', 'prod', 'main', 'build.type', 'Database Production Build SERVER APP');

INSERT INTO PROPERTIES (application, profile, label, prop_key, prop_value) VALUES
('configdemo', 'dev', 'main', 'build.id', '102'),
('configdemo', 'dev', 'main', 'build.version', '1.22.30'),
('configdemo', 'dev', 'main', 'build.name', 'Database-Development-Build SERVER APP'),
('configdemo', 'dev', 'main', 'build.type', 'Database Development Build SERVER APP');