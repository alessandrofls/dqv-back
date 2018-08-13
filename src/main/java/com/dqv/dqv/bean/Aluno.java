package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno extends Pessoa {
	@Id
	private Curso curso;
	private String periodoInicial;
	private String PeriodoFinalEstimado;
	
	public Aluno( String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco, Curso curso, String periodoInicial, String periodoFinalEstimado) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco);
		this.curso = curso;
		this.periodoInicial = periodoInicial;
		PeriodoFinalEstimado = periodoFinalEstimado;
	}
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public String getPeriodoFinalEstimado() {
		return PeriodoFinalEstimado;
	}
	public void setPeriodoFinalEstimado(String periodoFinalEstimado) {
		PeriodoFinalEstimado = periodoFinalEstimado;
	}

	
	

}
