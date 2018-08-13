package com.dqv.dqv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dqv.dqv.bean.Paciente;

public interface RepoPaciente extends JpaRepository<Paciente, Integer>{

}
