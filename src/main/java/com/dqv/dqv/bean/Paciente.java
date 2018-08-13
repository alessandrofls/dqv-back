package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Paciente extends Pessoa {
	@Id
	private Prontuario prontuario;
	public Paciente(String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco, Prontuario prontuario) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco);
		
		this.prontuario = prontuario;
		
	}
	public Prontuario getProntuario() {
		return prontuario;
	}
	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	
	
	

}
