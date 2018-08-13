package com.dqv.dqv.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean status;
	private String observacao;
	private String avaliacao;
	private String procedimentoRealizado;
	private Horario horario;
	
	public Consulta(Horario horario) {
		this.status = false;
		this.horario = horario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String getProcedimentoRealizado() {
		return procedimentoRealizado;
	}
	public void setProcedimentoRealizado(String procedimentoRealizado) {
		this.procedimentoRealizado = procedimentoRealizado;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	};
	
	
	
	
}
