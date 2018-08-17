package com.dqv.dqv.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.Receita;
import com.dqv.dqv.repository.RepoReceita;

@RestController
@RequestMapping(path = "/receitas")
@CrossOrigin
public class ControlReceita {
	
	@Autowired private RepoReceita repoReceita;
	
	@GetMapping(path ="/id")
	
	

	@PostMapping
	public Receita save(Receita receita) {
		return this.repoReceita.save(receita);
	}

}
