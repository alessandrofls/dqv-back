package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Diaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp diaria;
	private Integer qtdeTotalConsulta;
	private Integer tempoConsulta;
	private Boolean validado;
	private Boolean aprovado;
	
	@ManyToOne
	@JoinColumn(name = "fk_especialista")
	private Especialista especialista;
	
	
	public Diaria(Timestamp diaria, int qtdeTotalConsulta, int tempoConsulta) {
		this.diaria = diaria;
		this.qtdeTotalConsulta = qtdeTotalConsulta;
		this.tempoConsulta = tempoConsulta;
		this.validado = false;
		this.aprovado = false;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Timestamp getDiaria() {
		return diaria;
	}


	public void setDiaria(Timestamp diaria) {
		this.diaria = diaria;
	}


	public int getQtdeTotalConsulta() {
		return qtdeTotalConsulta;
	}


	public void setQtdeTotalConsulta(int qtdeTotalConsulta) {
		this.qtdeTotalConsulta = qtdeTotalConsulta;
	}


	public int getTempoConsulta() {
		return tempoConsulta;
	}


	public void setTempoConsulta(int tempoConsulta) {
		this.tempoConsulta = tempoConsulta;
	}


	public boolean isValidado() {
		return validado;
	}


	public void setValidado(boolean validado) {
		this.validado = validado;
	}


	public boolean isAprovado() {
		return aprovado;
	}


	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}
	
	
	
	
}
