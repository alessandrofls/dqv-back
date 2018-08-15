package com.dqv.dqv.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.repository.RepoPessoa;

@RestController
@RequestMapping(path = "/pessoa")
@CrossOrigin
public class ControlPessoa {
	
	@Autowired RepoPessoa repoPessoa;
	
	@PostMapping
	public Pessoa save(@RequestBody Pessoa pessoa) {
		return this.repoPessoa.save(pessoa);
	}
	
	@GetMapping
	public List<Pessoa> getAll(){
		return this.repoPessoa.findAll();
	}
	
//	@PutMapping
//	public Pessoa update(@RequestBody Pessoa pessoa) {
//		return this.repoPessoa.update(pessoa);
//	}
	
	@DeleteMapping(path = "/{id}")
	public List <Pessoa> deletarPessoa(@PathVariable("id") Integer id){
		this.repoPessoa.deleteById(id);
		return this.repoPessoa.findAll();
	}
	

}
