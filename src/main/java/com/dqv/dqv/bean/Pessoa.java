package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Integer id;
	private String email;
	private String pass;
	private String nome;
	private String rg;
	private String cpf;
	private String telefone;
	private Sexo sexo;
	private EstadoCivil estadoCivil;
	private Timestamp dataNascimento;
	private Timestamp dataIncio;
	private Timestamp dataFim;
	private Boolean coordenador;
	
	
	@OneToOne
	@JoinColumn(name = "fk_id")
	private Endereco endereco;
	
//	@OneToMany(mappedBy = "responsavel")
//	private List<Especialista> especialistas = new ArrayList<Especialista>();
	
	
 	
	public Pessoa() {
		
	}
		
	
	public Pessoa(String nome, String rg, String cpf, String telefone, Sexo sexo, EstadoCivil estadoCivil,
			Timestamp dataNascimento, Endereco endereco) {
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}
	
	/*
	 * 	GETTERS AND SETTERS
	 */


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}


	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public Timestamp getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Timestamp dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Timestamp getDataIncio() {
		return dataIncio;
	}


	public void setDataIncio(Timestamp dataIncio) {
		this.dataIncio = dataIncio;
	}


	public Timestamp getDataFim() {
		return dataFim;
	}


	public void setDataFim(Timestamp dataFim) {
		this.dataFim = dataFim;
	}


	public Boolean getCoordenador() {
		return coordenador;
	}


	public void setCoordenador(Boolean coordenador) {
		this.coordenador = coordenador;
	}

	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


//	public List<Especialista> getEspecialistas() {
//		return especialistas;
//	}
//
//
//	public void setEspecialistas(List<Especialista> especialistas) {
//		this.especialistas = especialistas;
//	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
