package com.dqv.dqv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dqv.dqv.bean.Pessoa;

public interface RepoPessoa extends JpaRepository<Pessoa, Integer>{
	

}
