package com.dqv.dqv.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doenca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Anamnese anamnese;
	private String descricao;
	private TipoDoenca tipoDoenca;
	private String codigoOms;
	
	public Doenca(Anamnese anamnese, String descricao, TipoDoenca tipoDoenca, String codigoOms) {
		this.anamnese = anamnese;
		this.descricao = descricao;
		this.tipoDoenca = tipoDoenca;
		this.setCodigoOms(codigoOms);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Anamnese getAnamnese() {
		return anamnese;
	}
	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoDoenca getTipoDoenca() {
		return tipoDoenca;
	}
	public void setTipoDoenca(TipoDoenca tipoDoenca) {
		this.tipoDoenca = tipoDoenca;
	}

	public String getCodigoOms() {
		return codigoOms;
	}

	public void setCodigoOms(String codigoOms) {
		this.codigoOms = codigoOms;
	}
	
	
	
}
