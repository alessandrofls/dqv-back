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
import com.dqv.dqv.repository.RepoEspecialista;
import com.dqv.dqv.repository.RepoPessoa;

@RestController
@RequestMapping(path = "/especialista")
@CrossOrigin
public class ControlEspecialista {
	@Autowired RepoEspecialista repoEspecialista;
	@Autowired private RepoPessoa repoPessoa;
	
	@PostMapping
	public Especialista save(@RequestBody Especialista especialista) {
		return this.repoEspecialista.save(especialista);
	}
	
	@GetMapping
	public List<Especialista> getAll(){
		return this.repoEspecialista.findAll();
	}
	
	@PutMapping
	public Especialista update(@RequestBody Especialista especialista) {
		this.repoEspecialista.save(especialista);
		return this.repoEspecialista.findById(especialista.getId()).get();
		
	}
	
	@DeleteMapping(path = "/{id}")
	public List <Especialista> deletarEspecialista(@PathVariable("id") Integer id){
		this.repoEspecialista.deleteById(id);
		return this.repoEspecialista.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Especialista getEspecialistaById(@PathVariable("id") Integer id){
		return this.repoEspecialista.findById(id).get();
	}
	
	@GetMapping(path = "setcoordenador/{id}/{idcoord}")
	public List<Especialista> setCoordenadorById(@PathVariable("id") Integer id, @PathVariable("idcoord") Integer idcoord){
		Pessoa coordenador = this.repoPessoa.findById(idcoord).get();
		Especialista especialista = this.repoEspecialista.findById(id).get();
		
		if(coordenador.getCoordenador()) {
			especialista.setResponsavel(coordenador);
			coordenador.addEspecialista(especialista);
			this.repoEspecialista.save(especialista);
			this.repoPessoa.save(coordenador);
		}
		return coordenador.getEspecialistas();
	}
}
