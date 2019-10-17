package com.dqv.dqv.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_idConsulta")
	private Consulta consulta;
	private Timestamp dataEmissao;
	private Timestamp validade;
	private String posologia;
	private String descrição;
	private String medicamento;
	private String principioAtivo;
	
	public Receita(Consulta consulta, Timestamp validade, String posologia, String descrição, String medicamento,
			String principioAtivo) {
		this.consulta = consulta;
		this.validade = validade;
		this.posologia = posologia;
		this.descrição = descrição;
		this.medicamento = medicamento;
		this.principioAtivo = principioAtivo;
		this.setDataEmissao(new Timestamp(System.currentTimeMillis()));
	}
	
	public Receita() {
		this.setDataEmissao(new Timestamp(System.currentTimeMillis()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Timestamp getValidade() {
		return validade;
	}

	public void setValidade(Timestamp validade) {
		this.validade = validade;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public Timestamp getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Timestamp dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
		
	
	
	
}
