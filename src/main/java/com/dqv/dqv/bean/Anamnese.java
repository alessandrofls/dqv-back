package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anamnese {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Timestamp dataCriacao;
	
	

	public Anamnese() {
		this.dataCriacao = new Timestamp(System.currentTimeMillis());
	}

	public Anamnese(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
