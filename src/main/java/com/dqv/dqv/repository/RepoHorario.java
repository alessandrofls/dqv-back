package com.dqv.dqv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dqv.dqv.bean.Horario;

public interface RepoHorario extends JpaRepository<Horario, Integer>{
	
}
