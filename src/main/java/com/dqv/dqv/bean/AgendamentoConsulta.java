package com.dqv.dqv.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AgendamentoConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name = "fk_paciente")
	private Pessoa paciente;
	@OneToOne
	@JoinColumn(name = "fk_servidor")
	private Servidor servidor;
	@OneToOne
	@JoinColumn(name = "fk_consulta")
	private Consulta consulta;
	
	

	/*
	 * 	GETTERS AND SETTERS
	 */
	
	
	
	
	
}
