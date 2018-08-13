package com.dqv.dqv.bean;


import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalTime horaInico;
	private LocalTime horaFim;
	private Boolean disponivel;
	
	public Horario(LocalTime horaInico, LocalTime horaFim, boolean disponivel) {
		this.horaInico = horaInico;
		this.horaFim = horaFim;
		this.disponivel = disponivel;
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
	
	
	
}
