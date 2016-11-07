
CREATE USER faas WITH PASSWORD 'faas';

CREATE DATABASE faasdb;
GRANT ALL PRIVILEGES ON DATABASE faasdb to faas;

  DROP TABLE faas_events;
  CREATE TABLE faas_events ( id serial not null primary key,
    event_type varchar(50),
    event_source varchar(50),
    event_time timestamp, 
    data json
  );
CREATE INDEX faas_events_idindx ON faas_events (id);

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public 
TO faas;
 GRANT USAGE, SELECT ON SEQUENCE faas_events_id_seq TO faas;