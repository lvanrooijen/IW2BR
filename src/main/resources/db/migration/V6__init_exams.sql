CREATE SEQUENCE IF NOT EXISTS exams_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS exams(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    is_finalised BOOLEAN,
    is_deleted BOOLEAN,
    created_at DATE,
    environment_id BIGINT REFERENCES public.environments(id)
);

CREATE SEQUENCE IF NOT EXISTS questions_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS questions(
    id BIGINT PRIMARY KEY,
    question VARCHAR(255),
    answer_amount INTEGER,
    deleted BOOLEAN,
    tag_id BIGINT REFERENCES public.tags(id),
    exam_id BIGINT REFERENCES public.exams(id)
);

CREATE SEQUENCE IF NOT EXISTS answers_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS answers(
     id BIGINT PRIMARY KEY,
     answer VARCHAR(255),
     is_correct BOOLEAN,
     question_id BIGINT REFERENCES public.questions(id)
);

