-- Script SGDQV

SET NAMES 'utf8';
create schema DQV;
use DQV;

create table pessoa(
	cpf char(11),
    rg char(7) unique not null,
    nome varchar(25) not null,   
    data_nascimento date not null,
    sexo enum('MASCULINO','FEMININO'),
    estado_civil enum('SOLTEIRO','CASADO','DIVORCIADO','VIUVO', 'SEPARADO'),
	cep char(8) not null,
    logradouro varchar(60),
    numero int,
	telefone char(11),
    primary key(cpf)
);


create table usuario(
	cpf char(11) unique,
    login varchar(20) not null unique,
    senha varchar(20) not null,
    primary key(cpf),
    foreign key(cpf) references pessoa(cpf) on delete cascade
);


create table curso(
	codigo int auto_increment,
    nome varchar(50) not null unique,
    primary key(codigo)
);

create table aluno(
	cpf char(11),
    periodo_inicial date,
    confirmado boolean default false,
	periodo_final_estimado date,
    codigo_curso int not null,
    primary key (cpf),
    foreign key(cpf) references pessoa(cpf) on delete cascade,
    foreign key(codigo_curso) references curso(codigo)
);

create table coordenador(
	cpf char(11),
	dt_inicio date not null,
	dt_fim date,
    primary key(cpf),
    foreign key(cpf) references pessoa(cpf) on delete cascade,
);

create table funcionario(
	cpf char(11),
    primary key(cpf),
    foreign key(cpf) references pessoa(cpf) on delete cascade
);

create table dependente(
	cpf char(11),
    cpf_funcionario char(11) not null,
    parentesco enum('FILHO', 'FILHA'),
    idade int,
    primary key(cpf),
    foreign key(cpf) references pessoa(cpf) on delete cascade,
    foreign key(cpf_funcionario) 
    references funcionario(cpf) on delete cascade    
);


create table cargo(
	 codigo int auto_increment,
     descricao varchar(20) not null unique,
     primary key(codigo)
);

create table servidor(
	cpf char(11),
    situacao enum('ATIVO','INATIVO') not null,
    codigo_cargo int not null,
    primary key(cpf),
    foreign key(cpf) references funcionario(cpf) on delete cascade,
    foreign key(codigo_cargo) references cargo(codigo)
);

create table especialista(
	cpf char(11),
    tipo enum('ASSISTENTE_SOCIAL'
				,'PSICOLOGO',
                'NUTRICIONISTA',
                'ORTODONTISTA',
                'MEDICO') not null,
    primary key(cpf),
    foreign key(cpf) references funcionario(cpf) on delete cascade  
);

create table medico(
	cpf char(11),
    tipo enum('CARDIOLOGISTA',
			  'GINECOLOGISTA',
              'MEDICO_DO_TRABALHO') not null,
	crm char(8) not null unique,
    primary key(cpf),
    foreign key(cpf) references especialista(cpf) on delete cascade 
);


create table diaria(
    seq int,
	dia date not null,
	tempo_consulta int not null,
	especialista char(11) not null,
	qtd_total_consultas int,
    valido boolean,
	coordenador char(11),
	
	primary key(seq),
    foreign key(especialista) references especialista(cpf),
	foreign key(coordenador) references coordenador(cpf)
);

create table horario(
	codigo int auto_increment,
	diaria int not null,
    hr_inicial time,
    hr_final time,
	disponivel boolean,
    primary key(codigo),
	foreign key(diaria) references diaria(seq)
);

create table consulta(
	codigo int auto_increment,
    status_consulta enum('REALIZADA','PENDENTE') not null,
    avaliacao int null, #apenas irá variar entre 0-10
    obs varchar(30),
	horario int not null,
    dt_realizada date,
    cpf_especialista_realiza  char(11) not null,
    primary key(codigo),
    foreign key (cpf_especialista_realiza) references especialista(cpf) on delete cascade,
	foreign key (horario) references horario(codigo) on delete cascade
);

create table procedimento_realizado(
	 codigo_consulta int,
     procedimento varchar(50),
     primary key(codigo_consulta, procedimento),
     foreign key(codigo_consulta) references consulta(codigo) on delete cascade 
);


create table anamnese(
	codigo int auto_increment,
    dt_criacao date,
    primary key(codigo)
);


create table antecedente_pessoal(
	codigo int auto_increment,
    descricao varchar(50),
    tipo enum('ETILISMO',
				'FEBRE_REUMATICA',
				'CATAPORA',
                'PAPEIRA',
                'SARAMPO',
                'HEPATITE',
                'TUBERCULOSE',
                'CONVULSOES',
                'DENGUE',
                'TABAGISMO') not null,
	codigo_anamnese int not null,
    primary key(codigo),
    foreign key(codigo_anamnese) references anamnese(codigo) 
);


create table doenca(
	codigo_oms int,
    nome varchar(20),
    tipo_doenca varchar(20),
    codigo_cat_doenca int not null,
    cod_anamnese int not null,
    primary key(codigo_oms,nome),
	foreign key(codigo_cat_doenca) references categoria_doenca(codigo),
    foreign key(cod_anamnese) references anamnese(codigo)  
);


create table prontuario(
	num int auto_increment,
    dt_ult_att date,
    codigo_anamnese int not null unique,
    primary key(num),
    foreign key(codigo_anamnese) references anamnese(codigo)   
);

create table paciente(
	cpf char(11),
	pendencia boolean default false,
    num_prontuario int not null unique,
    cpf_servidor_cadastrante char(11),
	data_fim_pend date,
    primary key(cpf),
    foreign key(cpf) references pessoa(cpf) on delete cascade,
    foreign key(num_prontuario) references prontuario(num),
    foreign key(cpf_servidor_cadastrante) references servidor(cpf) on delete set null
);

create table receita(
	codigo_consulta int,
    seq int auto_increment,
    data_emitida date,
    dt_validade date not null,
    descricao varchar(50) not null,
    posologia varchar(50),
    principio_ativo varchar(50),
    primary key(seq, codigo_consulta),
    foreign key(codigo_consulta)
    references consulta(codigo)  on delete cascade
);



create table exame(
	codigo int auto_increment,
    tipo enum('GINECOLOGIA', 'CARDIOLOGIA', 'MEDICINA_DO_TRABALHO') not null,
    dt_realizada date,
    num_prontuario int not null,
    codigo_consulta int not null,
    primary key(codigo),
    foreign key(codigo_consulta) references consulta(codigo) on delete cascade,
    foreign key(num_prontuario) references prontuario(num) on delete cascade
);

    codigo_consulta int,
    seq_receita int,
    codigo_medicamento int,
    primary key(codigo_consulta, seq_receita, codigo_medicamento),
    foreign key(codigo_consulta) references consulta(codigo) on delete cascade,
    foreign key(codigo_medicamento) references medicamento(codigo) on delete cascade,
    foreign key(seq_receita) references receita(seq)
);

create table agendamento_consulta(
    dt_marcacao date,
    hora time not null,
    cpf_servidor char(11),
    cpf_paciente char(11),
    codigo_consulta int,
    via_telefone boolean,
    cancelada boolean,
    primary key(cpf_paciente, codigo_consulta, dt_marcacao),
    foreign key(cpf_servidor) references servidor(cpf) on delete set null,
    foreign key(cpf_paciente) references paciente(cpf) on delete cascade,
    foreign key(codigo_consulta) references consulta(codigo) on delete cascade
);
















-------------------------------------------------TRIGGERS----------------------------------------------------

-- trigger para atualizar a idade do dependente quando inserido
delimiter //
create trigger att_idade_dependente
before insert on dependente
for each row
begin
	declare dt_nasc date;
    declare idade int default 0;
    select data_nascimento into dt_nasc from pessoa where cpf = new.cpf;
    if(month(dt_nasc) < month(now())) then
		set idade = year(now()) - year(dt_nasc);
	elseif (month(dt_nasc) = month(now())) then
		if (day(dt_nasc) <= day(now())) then
			set idade = year(now()) - year(dt_nasc);
		else
			set idade = year(now()) - year(dt_nasc) - 1;
		end if;
	else
		set idade = year(now()) - year(dt_nasc) - 1;
	end if;
    
    set new.idade = idade;
end
//


-- trigger para atualizar o atributo "duracao_N_meses" quando inserido

delimiter |

create trigger tempo_contrato
before insert on contrato
for each row
begin
	if new.dt_fim is not null then
			set new.duracao_n_meses = datediff(new.dt_fim, new.dt_inicio) / 30;
	end if;														
end |


-- trigger para achar um novo numero de sequencia para diária e definir a quantidade de consultas diárias quando inserida

delimiter |
create trigger seq_diaria_e_qtd_total_consultas
before insert on diaria
for each row
begin
	declare minutos int;
	declare tempo_inicial time;
	declare tempo_final time;
    set new.seq = (select count(*) from diaria where codigo_jornada = new.codigo_jornada) + 1;
	set tempo_inicial = (select horario.hr_inicial from horario 
					where codigo = new.codigo_horario);
	set tempo_final = (select horario.hr_final from horario 
					where codigo = new.codigo_horario);
    
    set minutos = time_to_sec(timediff(tempo_final,tempo_inicial))/60;
	set new.qtd_total_consultas = (minutos/new.tempo_consulta);
    set new.qtd_atual = 0;
end |


-- Trigger para a coluna seq em item_de_consumo

delimiter |
create trigger seq_item_consumo
before insert on item_de_consumo
for each row
begin
	set new.seq = (select count(*) from item_de_consumo where codigo_produto = new.codigo_produto)+1; 
end |

-- Trigger para a coluna seq em sugestao

delimiter |
create trigger seq_item_sugestao
before insert on sugestao
for each row
begin
	set new.seq = (select count(*) from sugestao where cpf_sugestor = new.cpf_sugestor)+1; 
end |

-- Trigger para definir a quantidade de consultas diárias quando atualizada

create trigger qtd_total_consultas_2
before update on diaria
for each row
begin
	declare minutos int;
	declare tempo_inicial time;
	declare tempo_final time;
	if new.codigo_horario != old.codigo_horario or new.tempo_consulta != old.tempo_consulta then
		set tempo_inicial = (select horario.hr_inicial from horario 
						where codigo = new.codigo_horario);
		set tempo_final = (select horario.hr_final from horario 
						where codigo = new.codigo_horario);
		
		set minutos = time_to_sec(timediff(tempo_final,tempo_inicial))/60;
		set new.qtd_total_consultas = (minutos/new.tempo_consulta);
		set new.qtd_atual = 0;
	end if;
end |

delimiter ;

------------------------------------------------------------Stored Procedure---------------------------------------------------

-- Stored Procedure para criar todos os criar todos os dentes de um paciente.

delimiter //
create procedure odontograma_default (in odonto_code int)
begin
	#como para criar um paciente precisa de um prontuario
    #e pra criar um prontuario precisa criar um odontograma,
    #ele já estará criado, logo, precisamos apenas inserir
    #os itens da arcada.
	insert into item_arcada(codigo_odontograma, numero, situacao, obs) values
    (odonto_code, 'ONZE', 'BOM', null),
    (odonto_code, 'DOZE', 'BOM', null),
    (odonto_code, 'TREZE', 'BOM', null),
    (odonto_code, 'CATORZE', 'BOM', null),
    (odonto_code, 'QUINZE', 'BOM', null),
    (odonto_code, 'DEZESSEIS', 'BOM', null),
    (odonto_code, 'DEZESSETE', 'BOM', null),
    (odonto_code, 'DEZOITO', 'BOM', null),
    (odonto_code, 'VINTE_E_UM', 'BOM', null),
    (odonto_code, 'VINTE_E_DOIS', 'BOM', null),
    (odonto_code, 'VINTE_E_TRES', 'BOM', null),
    (odonto_code, 'VINTE_E_QUATRO', 'BOM', null),
    (odonto_code, 'VINTE_E_CINCO', 'BOM', null),
    (odonto_code, 'VINTE_E_SEIS', 'BOM', null),
    (odonto_code, 'VINTE_E_SETE', 'BOM', null),
    (odonto_code, 'VINTE_E_OITO', 'BOM', null),
    (odonto_code, 'TRINTA_E_UM', 'BOM', null),
    (odonto_code, 'TRINTA_E_DOIS', 'BOM', null),
    (odonto_code, 'TRINTA_E_TRES', 'BOM', null),
    (odonto_code, 'TRINTA_E_QUATRO', 'BOM', null),
    (odonto_code, 'TRINTA_E_CINCO', 'BOM', null),
    (odonto_code, 'TRINTA_E_SEIS', 'BOM', null),
    (odonto_code, 'TRINTA_E_SETE', 'BOM', null),
    (odonto_code, 'TRINTA_E_OITO', 'BOM', null),
    (odonto_code, 'QUARENTA_E_UM', 'BOM', null),
    (odonto_code, 'QUARENTA_E_DOIS', 'BOM', null),
    (odonto_code, 'QUARENTA_E_TRES', 'BOM', null),
    (odonto_code, 'QUARENTA_E_QUATRO', 'BOM', null),
    (odonto_code, 'QUARENTA_E_CINCO', 'BOM', null),
    (odonto_code, 'QUARENTA_E_SEIS', 'BOM', null),
    (odonto_code, 'QUARENTA_E_SETE', 'BOM', null),
    (odonto_code, 'QUARENTA_E_OITO', 'BOM', null);
end
//

-- Atualizar situação de um item de consumo

delimiter // 
#usuario fornece o codigo do produto e o sequencial do item de consumo, junto com a situacao a ser atualizada
create procedure atualizarSituacaoIC(codigo_ic_prod int ,  seq_ic int, situacao tinyint)
begin
	
    if (situacao < 3)then
    	update item_de_consumo 
        set status_item_consumo = situacao 
        where item_de_consumo.codigo_produto = codigo_ic_prod and item_de_consumo.seq = seq_ic;
    end if;
    
end
//

---Criar uma doença para paciente
--Para criar um antecedente pessoal também é necessário fornecer uma descrição e o tipo (Catapora, Convulsões...)

delimiter //
create procedure criarAntecedentePressoal(cpf_pessoa varchar(11), descricao_ap varchar(50), tipo_ap int, codigo_cat_ap int)
begin
	#Declarando a variavel para código anamnese
    declare cod_an int default 0;
    #declarando uma variavel para antecedente pessoal
    declare cod_ap int default 0;
    declare crs cursor for select codigo_anamnese from paciente join prontuario join anamnese on paciente.num_prontuario = prontuario.num
	and prontuario.codigo_anamnese = anamnese.codigo where paciente.cpf = cpf_pessoa;
    
    open crs;
    fetch crs into cod_an;
    set cod_ap = rand(hour(now())) * 10000000; # a hora do dia serve pra gerar uma semente automática
	
    if(tipo_ap > 0 and tipo_ap < 11) then
		insert into antecendente_pessoal( id, descricao, tipo, codigo_anamnese, codigo_cat_desc) values
		(cod_ap, descricao_ap, tipo_ap, cod_an, codigo_cat_ap);
    end if;    
    
end
//

-- criar um exame
delimiter //
#OBS: Para a criação do exame também é necessário que seja fornecido a consulta.alter
#Pois um mesmo cpf pode estar associado a diferentes consultas
create procedure criarExame(cpf_paciente varchar(11), tipo_exame tinyint, cod_consulta int(11) )
begin
	
    declare cod_exame int default 0;
    declare cod_pront int default 0;
    declare crs cursor for select num_prontuario from paciente where paciente.cpf = cpf_paciente;
    
    open crs;
    fetch crs into cod_pront;
    
    #Verifica se o tipo está dentro do intervalo do enum    
    if(tipo_exame < 4 and tipo_exame > 0)then
		insert into exame(tipo, num_prontuario, codigo_consulta) values
        (tipo_exame, cod_pront,cod_consulta);        
    end if;

end
//

#-- para deixar apenas uma jornada de algum especialista como atual
delimiter |
create procedure att_jornada_atual(in cpf_espec varchar(11), in codigo_jornada int)
begin
	update jornada_de_trabalho set jornada_atual = false where cpf_especialista = cpf_espec and codigo != codigo_jornada;
end|

#-- para atualizar o atributo 'qtd_horas_semanais' da jornada de trabalho
delimiter |
create procedure att_qtd_horas_jornada(in jornCode int)
begin
	declare qtd_horas int;
    set qtd_horas = (select sum(time_to_sec(timediff(hr_final,hr_inicial))/3600) 
								from jornada_de_trabalho 
									join diaria 
                                    join horario
                                    on jornada_de_trabalho.codigo = diaria.codigo_jornada
                                    and diaria.codigo_horario = horario.codigo
                                    where jornada_de_trabalho.codigo = jornCode);
    update jornada_de_trabalho
    set qtd_horas_semanais = qtd_horas
	where jornada_de_trabalho.codigo = jornCode;
end|

delimiter // 
#-- para atualizar a quantidade atual do produto, incrementar ou decrementar
create procedure att_qtd_atual_produto(codigo_produto int ,  qtd int, modo_atualizacao tinyint)
begin
	if( modo_atualizacao = true ) then
		update produto set qtd_atual = qtd_atual + qtd where codigo = codigo_produto;
	else
		update produto set qtd_atual = qtd_atual - qtd where codigo = codigo_produto;
	end if;
end
//
----------------------------------------------------------Functions------------------------------------------------------------
--função para ver se o paciente é assiduo
delimiter //
create function  paciente_assiduo (cpfPaciente char(11)) returns boolean
deterministic
begin
	declare done int default 0;
	declare assiduo boolean default False;
    declare semana_atual int;
    declare cont_semana1 int default 0;
    declare cont_semana2 int default 0;
    declare cont_semana3 int default 0;
    declare cursorDatas cursor for select WEEKOFYEAR(consulta.dt_realizada) as semana
									from pessoa
										join paciente
										join agendamento_consulta
                                        join consulta
										on pessoa.cpf = paciente.cpf and agendamento_consulta.cpf_paciente = paciente.cpf and agendamento_consulta.codigo_consulta = consulta.codigo
									where pessoa.cpf = cpfPaciente and (weekofyear(now()) -4) <= consulta.dt_realizada
									order by semana asc;
	declare continue handler for not found set done = 1;
    
    open cursorDatas;
    repeat
		fetch cursorDatas into semana_atual;
        if semana_atual = (weekofyear(now()) -1) then
			set cont_semana1 = cont_semana1 +1;
		end if;
		if semana_atual = (weekofyear(now()) -2) then
			set cont_semana2 = cont_semana2 +1; 
		end if;
		if semana_atual = (weekofyear(now()) -3) then
			set cont_semana3 = cont_semana3 +1; 
		end if;
    until done
    end repeat;
    close cursorDatas;
    
    if (cont_semana1 > 1) and (cont_semana2 > 1) and (cont_semana3 > 1) then
		set assiduo = True;
	end if;
    return assiduo;
end
//


--funcao para verificar se o paciente possui todos os dentes

delimiter |
create function verificar_dentes(cpf_paciente varchar(11))
returns boolean
deterministic
begin
	declare cod_odonto integer;
	declare count_dentes integer;
    
    set cod_odonto = (select codigo_odontograma 
    from prontuario join paciente on num = num_prontuario
    where paciente.cpf = cpf_paciente);
    
    set count_dentes = (select count(*) from item_arcada 
    where codigo_odontograma = cod_odonto and situacao = 'EXTRAIDO');
    if count_dentes = 0 then return true;
    else return false;
    end if;
end|


--função para verificar disponibilidade de especialista

delimiter //
create function disponibilidade_especialista(cpf_entrada char(11), data_entrada date) returns tinyint
deterministic
begin
	#O select verifica se a data de entrada está dentro do intervalo da jornada de trabalho no médico
    declare retorno tinyint default 0;
    declare teste 	tinyint default 0;
    declare disp cursor for select diaria.disponivel from especialista join jornada_de_trabalho join diaria on
	especialista.cpf = jornada_de_trabalho.cpf_especialista and diaria.codigo_jornada = jornada_de_trabalho.codigo
	where diaria.disponivel = 1 and cpf_especialista = cpf_entrada and jornada_de_trabalho.dt_inicial <= data_entrada and jornada_de_trabalho.dt_final >= data_entrada;
    declare continue handler for not found set  teste = 0; 
    #Caso a data colocada não estiver no intervalo de disponibilidade o select não retornará nada portanto
    #Existe a necesidade de se declarar um handler
    
    open disp;
    
    fetch disp into teste;
	
    #Primeiro é verificado se o ano da entrada é o mesmo do ano corrente
    #Para não se fazer consultas de anos muito antigos ou muito no futuro
	if year(data_entrada) = year(now()) then
		if teste = 1 then
			set retorno = 1;
		else
			set retorno = 0;
		end if;
    end if;
    
    return retorno;
    
end
//

--Retorna a quantidade de horas da jornada atual de um especialista
delimiter //
create function  qtd_hrs_espec(cpfEspec char(11)) returns time
deterministic
begin
	declare total_horas time default 0;
	declare qtd_hora_atual time default 0;
    declare finalHorarios int default 0;
    declare cod_horario_atual int default 0;
    declare hora_inicial time;
    declare hora_final time;
    #consulta ta ok
    declare cursor_horario cursor for select horario.codigo
										from especialista
										join jornada_de_trabalho
										join diaria
                                        join horario
										on especialista.cpf = jornada_de_trabalho.cpf_especialista 
											and jornada_de_trabalho.codigo = diaria.codigo_jornada
                                            and horario.codigo = diaria.codigo_horario
									where especialista.cpf = cpfEspec and jornada_de_trabalho.jornada_atual = 1;
	declare continue handler for not found set finalHorarios = 1;
    
    open cursor_horario;
    repeat
		fetch cursor_horario into cod_horario_atual;
        select hr_final, hr_inicial into hora_final, hora_inicial from horario where codigo = cod_horario_atual;
        set qtd_hora_atual = TIMEDIFF(hora_final, hora_inicial);
        set total_horas = total_horas + qtd_hora_atual;
    until finalHorarios
    end repeat;
    close cursor_horario;
    
    #gambiarra para tirar um bug que ele sempre duplica a quantidade de horas da ultima diaria
    set total_horas = total_horas - qtd_hora_atual;
    
    return total_horas;
end
//

#--------------------------------------------------------------Views-----------------------------------------------------------

# VIEW PRODUTOS CONSUMIDOS 

CREATE VIEW produtos_consumidos (codigo_produto, descrição_produto, unidade, tipo, dt_entrada, razao_social, cnpj, cpf_especialista, nome_especialista) AS
SELECT produto.codigo, produto.descricao, produto.unidade, categoria_produto.tipo, item_de_consumo.dt_entrada, fornecedor.razao_social, fornecedor.cnpj, especialista.cpf, pessoa.nome
FROM produto 
JOIN item_de_consumo
JOIN categoria_produto
JOIN fornecedor
JOIN pessoa
JOIN especialista
ON produto.codigo = item_de_consumo.codigo_produto
AND produto.cod_categoria = categoria_produto.codigo
AND produto.cnpj_fornecedor = fornecedor.cnpj
AND item_de_consumo.cpf_especialista = especialista.cpf
AND pessoa.cpf = especialista.cpf
where item_de_consumo.status_item_consumo = 'INDISPONIVEL';


# VIEW CONSULTAS 

CREATE OR REPLACE VIEW vw_consultas AS
SELECT consulta.codigo AS Codigo_Consulta, 
		A.cpf_paciente AS CPF_Paciente, 
		P.nome AS Nome_Paciente,
		A.hora AS Hora, 
		consulta.status_consulta AS Status, 
		consulta.dt_realizada AS Data_Realizada, 
        consulta.cpf_especialista_realiza AS CPF_Especialista, 
        E.nome AS Especialista, 
		consulta.avaliacao AS Avaliacao,
        consulta.obs AS Observacao, 
		A.cpf_servidor AS CPF_Servidor, 
        S.nome AS Servidor, 
		receita_medicamento.seq_receita AS Codigo_Receita, 
        receita.data_emitida AS Data_Receita, 
		receita.dt_validade AS Validade_Receita,
        receita_medicamento.codigo_medicamento AS Codigo_Medicamento, 
        medicamento.principio_ativo AS Principio_Ativo, 
		medicamento.descricao AS Descricao_Medicamento,
        EX.codigo AS Codigo_Exame, 
		LAB.nome AS Nome_Laboratorio,  
		LAB.tipo AS Tipo      
        FROM consulta
			JOIN agendamento_consulta A ON consulta.codigo = A.codigo_consulta
			LEFT JOIN pessoa S ON A.cpf_servidor = S.cpf
            JOIN pessoa P ON A.cpf_paciente = P.cpf
            JOIN pessoa E ON consulta.cpf_especialista_realiza = E.cpf
			LEFT JOIN receita_medicamento ON consulta.codigo = receita_medicamento.codigo_consulta
            LEFT JOIN receita ON receita.seq = receita_medicamento.seq_receita
            LEFT JOIN medicamento ON medicamento.codigo = receita_medicamento.codigo_medicamento
            LEFT JOIN exame EX ON EX.codigo_consulta = consulta.codigo
            LEFT JOIN laboratorio LAB ON LAB.codigo = EX.codigo_laboratorio
			WHERE consulta.status_consulta = 'REALIZADA' AND A.cancelada != 1;


# view para odontograma

create view view_odontograma( cpf_paciente,nome_paciente,
							codigo_odontograma, observacao_odontograma, numero_item_arcada, situacao_item_arcada, obs_item_arcada)
as select paciente.cpf, pessoa.nome , odontograma.codigo, odontograma.obs , item_arcada.numero, item_arcada.situacao, item_arcada.obs  
from pessoa join paciente join prontuario join odontograma join item_arcada on  
pessoa.cpf = paciente.cpf and paciente.num_prontuario = prontuario.num and 
prontuario.codigo_odontograma = odontograma.codigo and odontograma.codigo = item_arcada.codigo_odontograma;


# views prontuário

CREATE VIEW Prontuario_pac (cpf, nome, num_prontuario, dt_ult_att_pront, cod_ana, dt_cri_anam, odont_cod, dt_ult_att_odont, odont_obs) AS
SELECT paciente.cpf, pessoa.nome, prontuario.num, prontuario.dt_ult_att, anamnese.codigo, anamnese.dt_criacao, odontograma.codigo, odontograma.dt_ult_att, odontograma.obs 
from paciente
join pessoa
join prontuario 
join anamnese
join odontograma
on paciente.num_prontuario = prontuario.num
and paciente.cpf = pessoa.cpf
and prontuario.codigo_anamnese = anamnese.codigo
and prontuario.codigo_odontograma = odontograma.codigo;


CREATE VIEW antecedente_pac (cpf, cod_ant, desc_ant, tipo_ant, desc_cat) AS
SELECT paciente.cpf, antecedente_pessoal.codigo, antecedente_pessoal.descricao, antecedente_pessoal.tipo, categoria_antecedente.descendente
from paciente
join prontuario 
join anamnese
join antecedente_pessoal
join categoria_antecedente
on paciente.num_prontuario = prontuario.num
and prontuario.codigo_anamnese = anamnese.codigo
and anamnese.codigo = antecedente_pessoal.codigo_anamnese
and antecedente_pessoal.codigo_cat_desc = categoria_antecedente.codigo;

CREATE VIEW doenca_pac (cpf, cod_doen_oms, nome_doen, tipo_doen, desc_cat) AS
SELECT paciente.cpf, doenca.codigo_oms, doenca.nome, doenca.tipo_doenca, categoria_doenca.descricao
from paciente
join prontuario 
join anamnese
join doenca
join categoria_doenca
on paciente.num_prontuario = prontuario.num
and prontuario.codigo_anamnese = anamnese.codigo
and doenca.cod_anamnese = anamnese.codigo
and doenca.codigo_cat_doenca = categoria_doenca.codigo;


CREATE VIEW arcada_pac (cpf, numero, situacao, obs ) AS
SELECT paciente.cpf, item_arcada.numero, item_arcada.situacao, item_arcada.obs
from paciente
join prontuario 
join odontograma
join item_arcada
on paciente.num_prontuario = prontuario.num
and prontuario.codigo_odontograma = odontograma.codigo
and odontograma.codigo = item_arcada.codigo_odontograma;

/*
#-------------------------------------------------Grant e Users--------------------------------------------------------------
#---Criação de usuários
#Pra alterar a senha execute
#
#set password for nome_usuario =  password(‘nova_senha’)
#
#cria usuario de nome ‘X’ identificado pela senha ‘y’

create user 'paciente' identified by '123456';

create user 'coordenador' identified by '123456';

create user 'especialista' identified by '123456';

create user 'medico' identified by '123456';

create user 'dependente' identified by '123456';

create user 'aluno' identified by '123456';

create user 'funcionario' identified by '123456';

grant all privileges on DQV.* to coordenador;

#Grants funcionario
grant insert, update, select on DQV.paciente to funcionario;
grant insert, update, select on DQV.pessoa to funcionario;
grant insert, update, select on DQV.especialista to funcionario;
grant insert, update, select on DQV.medico to funcionario;
grant insert, update, select on DQV.paciente to funcionario;
grant insert, update, select on DQV.terceirizado to funcionario;
grant insert, update, select on DQV.dependente to funcionario;
grant insert, update, select on DQV.servidor to funcionario;
grant insert, update, select on DQV.cargo to funcionario;
grant insert, update, select on DQV.agendamento_consulta to funcionario;
grant insert, update, select on DQV.consulta to funcionario;
				#CargaHoraria
grant insert, update, select on DQV.jornada_de_trabalho to funcionario;
grant insert, update, select on DQV.calendario to funcionario;
grant insert, update, select on DQV.horario to funcionario;
grant insert, update, select on DQV.diaria to funcionario;
				#Os cadastros do prontuario do paciente
grant insert, update, select on DQV.consulta to funcionario;
grant insert, update, select on DQV.anamnese to funcionario;
grant insert, update, select on DQV.odontograma to funcionario;
grant insert, update, select on DQV.item_arcada to funcionario;
grant insert, update, select on DQV.arcada_dentaria_ref to funcionario;
grant insert, update, select on DQV.doenca to funcionario;
grant insert, update, select on DQV.categoria_doenca to funcionario;
grant insert, update, select on DQV.antecedente_pessoal to funcionario;
grant insert, update, select on DQV.categoria_antecedente to funcionario;
	#estoque
grant select , insert, update on DQV.item_de_consumo to funcionario;
grant select , insert, update on DQV.produto to funcionario;
grant select , insert, update on DQV.categoria_produto to funcionario;
grant select , insert, update on DQV.fornecedor to funcionario;
grant select , insert, update on DQV.estoque to funcionario;
	#EXame
grant select , insert, update on DQV.exame to funcionario;
grant select , insert, update on DQV.laboratorio to funcionario;


#Grants paciente
grant select on DQV.consulta to dependente;
grant select on DQV.anamnese to dependente;
grant select on DQV.odontograma to dependente;
grant select on DQV.item_arcada to dependente;
grant select on DQV.arcada_dentaria_ref to dependente;
grant select on DQV.doenca to dependente;
grant select on DQV.categoria_doenca to dependente;
grant select on DQV.antecedente_pessoal to dependente;
grant select on DQV.categoria_antecedente to dependente;


#Grants paciente
grant select on DQV.consulta to paciente;
grant select on DQV.anamnese to paciente;
grant select on DQV.odontograma to paciente;
grant select on DQV.item_arcada to paciente;
grant select on DQV.arcada_dentaria_ref to paciente;
grant select on DQV.doenca to paciente;
grant select on DQV.categoria_doenca to paciente;
grant select on DQV.antecedente_pessoal to paciente;
grant select on DQV.categoria_antecedente to paciente;

#Grants aluno
grant insert , update, select on DQV.curso to aluno;
grant insert , update, select on DQV.pessoa to aluno;
grant select , insert, update on DQV.telefone to aluno;
grant select , insert, update on DQV.sugestao to aluno;


#Grants Médico
grant select , insert, update on DQV.receita to medico;
grant select , insert, update on DQV.medicamento to medico;
grant select , insert, update on DQV.receita_medicamento to medico;
grant select , insert, update on DQV.procedimento_realizado to medico;
grant select , insert, update on DQV.procedimento_realizado to medico;
	#Prontuario do paciente
grant select on DQV.prontuario to medico;
grant select on DQV.anamnese to medico;
grant select on DQV.antecedente_pessoal to medico;
grant select on DQV.categoria_antecedente to medico;
grant select on DQV.doenca to medico;
grant select on DQV.categoria_doenca to medico;
	#estoque
grant select , insert, update on DQV.item_de_consumo to medico;
grant select on DQV.produto to medico;
grant select on DQV.categoria_produto to medico;
grant select on DQV.fornecedor to medico;
grant select on DQV.estoque to medico;
	#EXame
grant select , insert, update on DQV.exame to medico;
grant select on DQV.laboratorio to medico;

#Grant especialista
grant select , insert, update on DQV.procedimento_realizado to especialista;
#Grants paciente
grant select on DQV.consulta to especialista;
grant select on DQV.anamnese to especialista;
			#Dentista também é um especialista
grant select on DQV.odontograma to especialista;
grant select on DQV.item_arcada to especialista;
grant select on DQV.arcada_dentaria_ref to especialista;
grant select on DQV.doenca to especialista;
grant select on DQV.categoria_doenca to especialista;
grant select on DQV.antecedente_pessoal to especialista;
grant select on DQV.categoria_antecedente to especialista;
#Prontuario do paciente
grant select on DQV.prontuario to especialista;
grant select on DQV.anamnese to especialista;
grant select on DQV.antecedente_pessoal to especialista;
grant select on DQV.categoria_antecedente to especialista;
grant select on DQV.doenca to especialista;
grant select on DQV.categoria_doenca to especialista;
*/

