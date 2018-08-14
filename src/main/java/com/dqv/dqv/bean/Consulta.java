package com.dqv.dqv.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp horaMarcado;
	private Boolean viaTelefone;
	private Boolean cancelada;
	private Boolean status;
	private String observacao;
	private String avaliacao;
	private String procedimentoRealizado;
	
	@OneToOne
	@JoinColumn(name = "fk_horario")
	private Horario horario;
	
	@OneToMany(mappedBy = "consulta")
	private List<Receita> receitas = new ArrayList<Receita>();
	
	@OneToOne(mappedBy = "consulta")
	private AgendamentoConsulta agendamentoConsulta;

	public Consulta( Timestamp horaMarcado, Boolean viaTelefone, Boolean cancelada, Boolean status,
			String observacao, String avaliacao, String procedimentoRealizado, Horario horario, List<Receita> receitas,
			AgendamentoConsulta agendamentoConsulta) {
		this.horaMarcado = horaMarcado;
		this.viaTelefone = viaTelefone;
		this.cancelada = cancelada;
		this.status = status;
		this.observacao = observacao;
		this.avaliacao = avaliacao;
		this.procedimentoRealizado = procedimentoRealizado;
		this.horario = horario;
		this.receitas = receitas;
		this.agendamentoConsulta = agendamentoConsulta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getHoraMarcado() {
		return horaMarcado;
	}

	public void setHoraMarcado(Timestamp horaMarcado) {
		this.horaMarcado = horaMarcado;
	}

	public Boolean getViaTelefone() {
		return viaTelefone;
	}

	public void setViaTelefone(Boolean viaTelefone) {
		this.viaTelefone = viaTelefone;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
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
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public AgendamentoConsulta getAgendamentoConsulta() {
		return agendamentoConsulta;
	}

	public void setAgendamentoConsulta(AgendamentoConsulta agendamentoConsulta) {
		this.agendamentoConsulta = agendamentoConsulta;
	}
	
	
	
	

	
	
	
	
	
}
