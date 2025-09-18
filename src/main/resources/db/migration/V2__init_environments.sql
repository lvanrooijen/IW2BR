CREATE SEQUENCE IF NOT EXISTS environments_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS environments(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    created_at DATE,
    owner UUID REFERENCES public.users(id)
);