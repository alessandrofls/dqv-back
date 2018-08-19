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

import com.dqv.dqv.bean.Especialista;
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.bean.Servidor;
import com.dqv.dqv.repository.RepoPessoa;
import com.dqv.dqv.repository.RepoServidor;


@RestController
@RequestMapping(path = "/servidor")
@CrossOrigin
public class ControlServidor {
	@Autowired RepoServidor repoServidor;
	
	@PostMapping
		public Servidor save(@RequestBody Servidor servidor) {
		return this.repoServidor.save(servidor);
	}
	
	@GetMapping
	public List<Servidor> getAll(){
		return this.repoServidor.findAll();
	}
	
	@PutMapping
	public Servidor update(@RequestBody Servidor servidor) {
		return this.repoServidor.save(servidor);
	}
	
	@DeleteMapping(path = "/{id}")
	public List <Servidor> deletarServidor(@PathVariable("id") Integer id){
		this.repoServidor.deleteById(id);
		return this.repoServidor.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Servidor getServidorById(@PathVariable("id") Integer id){
		return this.repoServidor.findById(id).get();
	}
}
