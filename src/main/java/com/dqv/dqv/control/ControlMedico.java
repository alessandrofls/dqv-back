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

import com.dqv.dqv.bean.Medico;
import com.dqv.dqv.repository.RepoMedico;

@RestController
@RequestMapping(path = "/medico")
@CrossOrigin
public class ControlMedico {
@Autowired RepoMedico repoMedico;
	
	@PostMapping
	public Medico save(@RequestBody Medico medico) {
		return this.repoMedico.save(medico);
	}
	
	@GetMapping
	public List<Medico> getAll(){
		return this.repoMedico.findAll();
	}
	
	@PutMapping
	public Medico update(@RequestBody Medico medico) {
		this.repoMedico.save(medico);
		return this.repoMedico.findById(medico.getId()).get();
		
	}
	
	@DeleteMapping(path = "/{id}")
	public List <Medico> deletarMedico(@PathVariable("id") Integer id){
		this.repoMedico.deleteById(id);
		return this.repoMedico.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Medico getMedicoById(@PathVariable("id") Integer id){
		return this.repoMedico.findById(id).get();
	}
}
