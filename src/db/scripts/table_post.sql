create table Post (
    id serial primary key,
    id_post int,
    name varchar (250),
    description text,
    url varchar (250) unique,
    created_date date
    )