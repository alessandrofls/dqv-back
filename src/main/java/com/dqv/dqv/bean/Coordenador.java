package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coordenador extends Pessoa{
	@Id
	private Timestamp dataIncio;
	private Timestamp dataFim;
		
	
	public Coordenador(String nome, String rg, String cpf, String telefone, Sexo sexo,
			EstadoCivil estadoCivil, Timestamp dataNascimento, Endereco endereco, int numeroDependentes,
			Timestamp dataAdmissao, Timestamp dataIncio) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco);
		this.dataIncio = dataIncio;
		
	}


	public Timestamp getDataIncio() {
		return dataIncio;
	}


	public void setDataIncio(Timestamp dataIncio) {
		this.dataIncio = dataIncio;
	}


	public Timestamp getDataFim() {
		return dataFim;
	}


	public void setDataFim(Timestamp dataFim) {
		this.dataFim = dataFim;
	}
	
	
	
	
	
	
	
	
	
}
