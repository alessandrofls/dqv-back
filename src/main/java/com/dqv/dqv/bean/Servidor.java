package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Servidor extends Funcionario {
	@Id
	private Situacao situacao;
	private Cargo cargo;
	
	public Servidor(String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco, int numeroDependentes, Timestamp dataAdmissao, Situacao situacao,
			Cargo cargo) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco, numeroDependentes,
				dataAdmissao);
		this.situacao = situacao;
		this.cargo = cargo;
	}

	
	@Enumerated(EnumType.STRING)
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
	
	

}
