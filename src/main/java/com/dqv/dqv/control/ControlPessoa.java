package com.dqv.dqv.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping(path = "/login")
	public Pessoa login(@RequestBody Pessoa pessoa) {
		if(this.repoPessoa.existsByEmail(pessoa.getEmail())&&(this.repoPessoa.findByEmail(pessoa.getEmail()).getPass().equals(pessoa.getPass()))){
			return this.repoPessoa.findByEmail(pessoa.getEmail());
		}
		else{
			return new Pessoa();
		}
	}
	
	@GetMapping
	public List<Pessoa> getAll(){
		return this.repoPessoa.findAll();
	}
	
	@PutMapping
	public Pessoa update(@RequestBody Pessoa pessoa) {
		this.repoPessoa.save(pessoa);
		return this.repoPessoa.findById(pessoa.getId()).get();
		
	}
	
	@DeleteMapping(path = "/{id}")
	public List <Pessoa> deletarPessoa(@PathVariable("id") Integer id){
		this.repoPessoa.deleteById(id);
		return this.repoPessoa.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Pessoa getPessoaById(@PathVariable("id") Integer id){
		return this.repoPessoa.findById(id).get();
	}

}
