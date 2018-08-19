package com.dqv.dqv.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.Especialista;
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.repository.RepoPessoa;

@RestController
@RequestMapping(path = "/coordenador")
@CrossOrigin
public class ControlCoordenador {
@Autowired RepoPessoa repoPessoa;
	
	
	@GetMapping
	public List<Pessoa> getAll(){
		List<Pessoa> pessoas = this.repoPessoa.findAll();
		List<Pessoa> coordenadores = new ArrayList<Pessoa>();
		for(int i=0; i<pessoas.size();i++) {
			if(pessoas.get(i).getCoordenador()) {
				coordenadores.add(pessoas.get(i));
			}
		}
		return coordenadores;
	}
	
//	@PutMapping
//	public Pessoa update(@RequestBody Pessoa pessoa) {
//		return this.repoPessoa.update(pessoa);
//	}
	
	@DeleteMapping(path = "/{id}")
	public List <Pessoa> deletarCoordenador(@PathVariable("id") Integer id){
		this.repoPessoa.deleteById(id);
		return this.repoPessoa.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Pessoa getCoordenadorById(@PathVariable("id") Integer id){
		return this.repoPessoa.findById(id).get();
	}
	
	@GetMapping(path = "/especialistas/{id}")
	public List<Especialista> getEspecialistasById(@PathVariable("id") Integer id){
		return this.repoPessoa.findById(id).get().getEspecialistas();
	}
}
