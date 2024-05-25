
insert into pessoa (nome, sexo) values('Janio', 2);

insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Goias', 'GO');

insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('Paraíso', 1);
insert into cidade (nome, id_estado) values('Goiânia', 2);

insert into usuario(username, senha) values('janio', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==');

insert into pessoafisica (id, cpf, id_usuario) values(1, '111.111.111-11', 1);

insert into psicologo (crp, id_pessoa_fisica) values('crp111', 1);

insert into consulta(nome, preco) values ('Consulta Normal', 100);
insert into consulta(nome, preco) values ('Pacote 1', 100);
insert into consulta(nome, preco) values ('Pacote 2', 190);
insert into consulta(nome, preco) values ('Pacote 3', 280);
insert into consulta(nome, preco) values ('Pacote 4', 370);
insert into consulta(nome, preco) values ('Pacote 5', 460);
insert into consulta(nome, preco) values ('Pacote 6', 550);

--insert into consultaavulsa(id, tipoConsulta) values (1, 2);

insert into paciente(nome) values ('Maria');
insert into paciente(nome) values ('Jeferson');
insert into paciente(nome) values ('Yzaack');
insert into paciente(nome) values ('Wesley');