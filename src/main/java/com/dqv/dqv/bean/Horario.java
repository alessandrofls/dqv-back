package com.dqv.dqv.bean;


import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Horario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalTime horaInico;
	private LocalTime horaFim;
	private Boolean disponivel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_diaria")
	private Diaria diaria;
	
	@OneToOne(mappedBy = "horario")
	private Consulta consulta;
	
	public Horario() {
		
	}
	
	public Horario(LocalTime horaInico, LocalTime horaFim, boolean disponivel, Diaria diaria) {
		this.horaInico = horaInico;
		this.horaFim = horaFim;
		this.disponivel = disponivel;
		this.diaria = diaria;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalTime getHoraInico() {
		return horaInico;
	}
	public void setHoraInico(LocalTime horaInico) {
		this.horaInico = horaInico;
	}
	public LocalTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Diaria getDiaria() {
		return diaria;
	}

	public void setDiaria(Diaria diaria) {
		this.diaria = diaria;
	}
	
	
	
}
