package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AgendamentoConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp horarioMarcado;
	private Boolean cancelada;
	private Boolean viaTelefone;
	private Paciente paciente;
	private Consulta consulta;
	private Servidor servidor;
	
	public AgendamentoConsulta(Boolean viaTelefone, Paciente paciente, Consulta consulta, Servidor servidor) {
		this.horarioMarcado = new Timestamp(System.currentTimeMillis());
		this.cancelada = false;
		this.viaTelefone = viaTelefone;
		this.paciente = paciente;
		this.consulta = consulta;
		this.servidor = servidor;
	}
	
	
	public AgendamentoConsulta(Integer id, Boolean viaTelefone, Paciente paciente, Consulta consulta) {
		this.horarioMarcado = new Timestamp(System.currentTimeMillis());
		this.cancelada = false;
		this.viaTelefone = viaTelefone;
		this.paciente = paciente;
		this.consulta = consulta;
	}

	public Timestamp getHorarioMarcado() {
		return horarioMarcado;
	}

	public void setHorarioMarcado(Timestamp horarioMarcado) {
		this.horarioMarcado = horarioMarcado;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public Boolean getViaTelefone() {
		return viaTelefone;
	}

	public void setViaTelefone(Boolean viaTelefone) {
		this.viaTelefone = viaTelefone;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	
	
	
	
}
