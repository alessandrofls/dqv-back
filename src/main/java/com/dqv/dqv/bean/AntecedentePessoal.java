package com.dqv.dqv.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AntecedentePessoal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String tipo;
	private Anamnese anamnese;
	
	public AntecedentePessoal(String descricao, String tipo, Anamnese anamnese) {
		this.descricao = descricao;
		this.tipo = tipo;
		this.anamnese = anamnese;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Anamnese getAnamnese() {
		return anamnese;
	}


	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}
	
	
	
}
