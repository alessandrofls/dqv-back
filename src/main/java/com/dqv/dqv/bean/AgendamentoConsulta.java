package com.dqv.dqv.bean;


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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_paciente")
	private Pessoa paciente;
	@OneToOne
	@JoinColumn(name = "fk_servidor")
	private Servidor servidor;
	@OneToOne
	@JoinColumn(name = "fk_consulta")
	private Consulta consulta;
	public AgendamentoConsulta(Pessoa paciente, Servidor servidor, Consulta consulta) {
		super();
		this.paciente = paciente;
		this.servidor = servidor;
		this.consulta = consulta;
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
	
	

	/*
	 * 	GETTERS AND SETTERS
	 */
	
	
	
	
	
}
