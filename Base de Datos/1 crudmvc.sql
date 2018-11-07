create database crudmvc;

use crudmvc;

create or replace table clientes (
	id int not null auto_increment,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    email varchar(255) not null,
    create_at date not null,
	estado int not null,
    primary key (id)
);

/* Populate tables */
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, estado) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', 1);

commit;
