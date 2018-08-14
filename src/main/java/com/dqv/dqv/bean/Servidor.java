package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
public class Servidor extends Funcionario {

	

	private Situacao situacao;
	
	@OneToOne(mappedBy = "servidor")
	private AgendamentoConsulta agendamentoConsulta;
	
	
	public Servidor(String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco, int numeroDependentes, Timestamp dataAdmissao) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco, numeroDependentes, dataAdmissao);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 	GETTERS AND SETTERS
	 */
	
	@Enumerated(EnumType.STRING)
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}


	

}
