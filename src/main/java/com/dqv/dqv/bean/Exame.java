package com.dqv.dqv.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tipo;
	private Consulta consulta;
	private Prontuario prontuario;
	
	
	public Exame(String tipo, Consulta consulta, Prontuario prontuario) {
		this.tipo = tipo;
		this.consulta = consulta;
		this.prontuario = prontuario;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Consulta getConsulta() {
		return consulta;
	}


	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}


	public Prontuario getProntuario() {
		return prontuario;
	}


	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	
	
	

}
