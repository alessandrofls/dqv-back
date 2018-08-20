package com.dqv.dqv.bean;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AgendamentoConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp horaMarcado;
	
	@ManyToOne
	@JoinColumn(name = "fk_paciente")
	private Pessoa paciente;
	
	@ManyToOne
	@JoinColumn(name = "fk_servidor")
	private Servidor servidor;
	@OneToOne
	@JoinColumn(name = "fk_consulta")
	private Consulta consulta;
	public AgendamentoConsulta() {
		this.setHoraMarcado(new Timestamp(System.currentTimeMillis()));
	}
	public AgendamentoConsulta(Pessoa paciente, Servidor servidor, Consulta consulta) {
		super();
		this.paciente = paciente;
		this.servidor = servidor;
		this.consulta = consulta;
		this.setHoraMarcado(new Timestamp(System.currentTimeMillis()));
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pessoa getPaciente() {
		return paciente;
	}
	public void setPaciente(Pessoa paciente) {
		this.paciente = paciente;
	}
	public Servidor getServidor() {
		return servidor;
	}
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public Timestamp getHoraMarcado() {
		return horaMarcado;
	}
	public void setHoraMarcado(Timestamp horaMarcado) {
		this.horaMarcado = horaMarcado;
	}
	
	

	/*
	 * 	GETTERS AND SETTERS
	 */
	
	
	
	
	
}
