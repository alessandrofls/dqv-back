package com.dqv.dqv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.dqv.dqv.bean.Pessoa;

@NoRepositoryBean
public interface BaseRepoPessoa <T extends Pessoa> extends JpaRepository<T, Integer> {
	public T findByCpf(String cpf);
	public T buscarId(Integer id);
}
