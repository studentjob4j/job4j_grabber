create table Post {
    id serial primary key,
    name varchar (250),
    description text,
    url varchar (250) unique,
    created_date date
}