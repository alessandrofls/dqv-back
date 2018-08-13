package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Dependente  extends Pessoa{
	@Id
	private Funcionario responsavel;
	private Parentesco parentesco;

	public Dependente(String nome, String rg, String cpf, String telefone, Sexo sexo,
			EstadoCivil estadoCivil, Timestamp dataNascimento, Endereco endereco, Parentesco parentesco, Funcionario responsavel) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco);
		this.parentesco = parentesco;
		this.responsavel = responsavel;
	}
	
	@Enumerated (EnumType.STRING)
	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}
	
	
	
	
	
	
}
