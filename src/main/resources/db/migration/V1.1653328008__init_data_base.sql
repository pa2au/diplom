CREATE TABLE IF NOT EXISTS public.faculties
(
    id   serial            NOT NULL,
    name character varying NOT NULL,
    CONSTRAINT faculties_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.groups
(
    id         serial            NOT NULL,
    faculty_id integer           NOT NULL,
    name       character varying NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.verify_users
(
    id        serial            NOT NULL,
    user_id   integer           NOT NULL,
    token     character varying NOT NULL,
    is_active boolean           NOT NULL DEFAULT FALSE,
    CONSTRAINT verify_users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id                  serial            NOT NULL,
    first_name          character varying NOT NULL,
    last_name           character varying NOT NULL,
    patronymic          character varying,
    mobile_phone_number character varying NOT NULL,
    user_name           character varying NOT NULL,
    email               character varying NOT NULL,
    password            character varying NOT NULL,
    faculty_id          integer,
    group_id            integer,
    role                character varying NOT NULL,
    is_enabled          boolean           NOT NULL DEFAULT FALSE,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.week_days
(
    id       serial            NOT NULL,
    week_day character varying NOT NULL,
    name     character varying NOT NULL,
    CONSTRAINT week_days_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.times
(
    id            bigserial         NOT NULL,
    time          character varying NOT NULL,
    number_lesson integer           NOT NULL,
    CONSTRAINT times_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.disciplines
(
    id          serial            NOT NULL,
    name        character varying NOT NULL,
    description character varying NOT NULL,
    CONSTRAINT disciplines_pkey PRIMARY KEY (id)
);


ALTER TABLE IF EXISTS public.groups
    ADD CONSTRAINT "FK_faculties_groups" FOREIGN KEY (faculty_id)
        REFERENCES public.faculties (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT;

ALTER TABLE IF EXISTS public.users
    ADD CONSTRAINT "FK_groups_users" FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT;

ALTER TABLE IF EXISTS public.users
    ADD CONSTRAINT "FK_faculties_users" FOREIGN KEY (faculty_id)
        REFERENCES public.faculties (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE IF EXISTS public.verify_users
    ADD CONSTRAINT "FK_users_verify_users" FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;