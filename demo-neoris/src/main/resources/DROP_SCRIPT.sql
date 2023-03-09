alter table cuentas drop foreign key FKl7qjco9qn0mai4bxjxee01vwr;
alter table movimientos drop foreign key FKdu4l5tx20ok9p4v5jjpgqs9e;
alter table movimientos drop foreign key FK4moe88hxuohcysas5h70mdc09;
drop table if exists clientes;
drop table if exists cuentas;
drop table if exists hibernate_sequences;
drop table if exists movimientos;
drop table if exists persona;
