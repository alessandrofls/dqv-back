package com.dqv.dqv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dqv.dqv.bean.Pessoa;

public interface RepoPessoa extends JpaRepository<Pessoa, Integer>{
	public Pessoa findByEmail(String email);
	public boolean existsByEmail(String email);
	public Pessoa findByCpf(String cpf);
}
