package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;

@Entity
public class Funcionario extends Pessoa {

	private Timestamp dataAdmissao;
		
	public Funcionario(String nome, String rg, String cpf, String telefone, Sexo sexo,
			EstadoCivil estadoCivil, Timestamp dataNascimento, Endereco endereco, Timestamp dataAdmissao) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco);
		this.dataAdmissao = dataAdmissao;
	}
	
	public Funcionario() {
		super();
	}
	
	
	public Timestamp getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Timestamp dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	
}
