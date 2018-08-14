package com.dqv.dqv.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Especialista extends Funcionario{

	private Especialistas especialidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_coordenador")
	private Pessoa coordenador;

	@OneToMany(mappedBy = "especialista")
	private List<Diaria> diarias = new ArrayList<Diaria>(); 	
	
	
	
	public Especialista(String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco, int numeroDependentes, Timestamp dataAdmissao) {
		super(nome, rg, cpf, telefone, sexo, estadoCivil, dataNascimento, endereco, numeroDependentes, dataAdmissao);
	}

	@Enumerated(EnumType.STRING)
	public Especialistas getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialistas especialidade) {
		this.especialidade = especialidade;
	}

	public List<Diaria> getDiarias() {
		return diarias;
	}

	public void setDiarias(List<Diaria> diarias) {
		this.diarias = diarias;
	}


		
	
	
}
