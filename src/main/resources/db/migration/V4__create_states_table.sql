CREATE TABLE states (
    abbreviation VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT states_pk PRIMARY KEY (abbreviation)
);

insert into states (abbreviation, name) values ('AC', 'Acre');
insert into states (abbreviation, name) values ('AL', 'Alagoas');
insert into states (abbreviation, name) values ('AP', 'Amapá');
insert into states (abbreviation, name) values ('AM', 'Amazonas');
insert into states (abbreviation, name) values ('BA', 'Bahia');
insert into states (abbreviation, name) values ('CE', 'Ceará');
insert into states (abbreviation, name) values ('DF', 'Distrito Federal');
insert into states (abbreviation, name) values ('ES', 'Espírito Santo');
insert into states (abbreviation, name) values ('GO', 'Goiás');
insert into states (abbreviation, name) values ('MA', 'Maranhão');
insert into states (abbreviation, name) values ('MT', 'Mato Grosso');
insert into states (abbreviation, name) values ('MS', 'Mato Grosso do Sul');
insert into states (abbreviation, name) values ('MG', 'Minas Gerais');
insert into states (abbreviation, name) values ('PA', 'Pará');
insert into states (abbreviation, name) values ('PB', 'Paraíba');
insert into states (abbreviation, name) values ('PR', 'Paraná');
insert into states (abbreviation, name) values ('PE', 'Pernambuco');
insert into states (abbreviation, name) values ('PI', 'Piauí');
insert into states (abbreviation, name) values ('RJ', 'Rio de Janeiro');
insert into states (abbreviation, name) values ('RN', 'Rio Grande do Norte');
insert into states (abbreviation, name) values ('RS', 'Rio Grande do Sul');
insert into states (abbreviation, name) values ('RO', 'Rondônia');
insert into states (abbreviation, name) values ('RR', 'Roraima');
insert into states (abbreviation, name) values ('SC', 'Santa Catarina');
insert into states (abbreviation, name) values ('SP', 'São Paulo');
insert into states (abbreviation, name) values ('SE', 'Sergipe');
insert into states (abbreviation, name) values ('TO', 'Tocantins');
