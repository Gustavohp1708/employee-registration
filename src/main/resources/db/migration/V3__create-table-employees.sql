create table employees (

    id bigint not null auto_increment,

    name varchar(255) not null,
    email varchar(255) not null unique,
    phone varchar(20) not null unique,
    role varchar(255) not null,
    department varchar(255) not null,
    active BOOLEAN not null,

    address_id bigint not null,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    primary key (id),

    constraint fk_employee_address
                       foreign key (address_id)
                       references addresses(id)               
);