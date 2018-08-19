package com.dqv.dqv.bean;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
public class Medico extends Especialista{

	private String crm;
	private TipoMedico tipo;
	
	
	
	public Medico(String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco, int numeroDependentes, Timestamp dataAdmissao, String crm,
			TipoMedico tipo) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco, numeroDependentes, dataAdmissao);
		this.crm = crm;
		this.tipo = tipo;
	}
	
	public Medico() {
		super();
		this.setEspecialidade(Especialistas.MEDICO);
	}
	
	

	/*
	 * 	GETTERS AND SETTERS
	 */
	
	
	public String getCrm() {
		return crm;
	}


	public void setCrm(String crm) {
		this.crm = crm;
	}

	@Enumerated(EnumType.STRING)
	public TipoMedico getTipo() {
		return tipo;
	}


	public void setTipo(TipoMedico tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
