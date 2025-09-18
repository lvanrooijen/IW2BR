CREATE SEQUENCE IF NOT EXISTS note_collections_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS note_collections(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    environment_id BIGINT REFERENCES public.environments(id)
);

CREATE SEQUENCE IF NOT EXISTS notes_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS notes(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    body varchar(255),
    note_collection_id BIGINT REFERENCES public.note_collections(id)
);