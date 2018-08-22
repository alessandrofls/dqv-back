package com.dqv.dqv.control;

import java.util.ArrayList;
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
import com.dqv.dqv.bean.Especialistas;
import com.dqv.dqv.bean.Medico;
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.bean.TipoMedico;
import com.dqv.dqv.repository.RepoMedico;
import com.dqv.dqv.repository.RepoPessoa;

@RestController
@RequestMapping(path = "/medico")
@CrossOrigin
public class ControlMedico {
	@Autowired RepoMedico repoMedico;
	@Autowired private RepoPessoa repoPessoa;	

	@PostMapping(path = "/{idcoord}")
	public Medico save(@RequestBody Medico medico, @PathVariable("idcoord") Integer idcoord) {
		Pessoa coordenador = this.repoPessoa.findById(idcoord).get();
		if(coordenador.getCoordenador()) {
			medico.setResponsavel(coordenador);
			medico.setEspecialidade(Especialistas.MEDICO);
			this.repoMedico.save(medico);
		}
		return medico;
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
	
	@PostMapping(path = "/especialidade")
	public List<Medico> getMedicoTipo(@RequestBody Medico medico) {
		List<Medico> medicos = this.repoMedico.findAll();
		List<Medico> medicoTipo = new ArrayList<Medico>();
		
		for(int i=0;i<medicos.size();i++) {
			if(medicos.get(i).getTipo().equals(medico.getTipo())) {
				medicoTipo.add(medicos.get(i));
			}
		}
		return medicoTipo;
	}
}
