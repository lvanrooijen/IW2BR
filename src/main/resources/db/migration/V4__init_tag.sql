CREATE SEQUENCE IF NOT EXISTS tags_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS tags(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    negative_flagged_count INTEGER,
    positive_flagged_count INTEGER,
    environment_id BIGINT REFERENCES public.environments(id)
);


ALTER TABLE notes ADD COLUMN tag_id BIGINT REFERENCES public.tags(id);