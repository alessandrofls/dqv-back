package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Especialista extends Funcionario{
	@Id
	private Especialistas especialidade;
	private Coordenador coordenador;

	
	
	public Especialista(String nome, String rg, String cpf, String telefone, Sexo sexo,
			EstadoCivil estadoCivil, Timestamp dataNascimento, Endereco endereco, int numeroDependentes,
			Timestamp dataAdmissao, Especialistas especialidade, Coordenador coordenador) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco, numeroDependentes,
				dataAdmissao);
		this.especialidade = especialidade;
		this.coordenador = coordenador;
	}

	@Enumerated(EnumType.STRING)
	public Especialistas getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialistas especialidade) {
		this.especialidade = especialidade;
	}


	public Coordenador getCoordenador() {
		return coordenador;
	}


	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}
	
	
	
	
	
	
	
}
