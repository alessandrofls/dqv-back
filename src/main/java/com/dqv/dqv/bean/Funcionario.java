package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Funcionario extends Pessoa {
	@Id
	private Integer numeroDependentes;
	private Timestamp dataAdmissao;
	
	
	public Funcionario(String nome, String rg, String cpf, String telefone, Sexo sexo,
			EstadoCivil estadoCivil, Timestamp dataNascimento, Endereco endereco, int numeroDependentes, Timestamp dataAdmissao) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco);
		this.numeroDependentes = 0;
		this.dataAdmissao = dataAdmissao;
	}
	
	public int getNumeroDependentes() {
		return numeroDependentes;
	}
	
	public Timestamp getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Timestamp dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public void addNumeroDependentes() {
		this.numeroDependentes++;
	}
	
	public void decreaseNumeroDependentes() {
		this.numeroDependentes--;
	}
	
	
	
	
}
