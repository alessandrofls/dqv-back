package com.dqv.dqv.control;

import java.time.LocalTime;
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

import com.dqv.dqv.bean.Diaria;
import com.dqv.dqv.bean.Especialista;
import com.dqv.dqv.bean.Horario;
import com.dqv.dqv.repository.RepoDiaria;
import com.dqv.dqv.repository.RepoEspecialista;
import com.dqv.dqv.repository.RepoHorario;

@RestController
@RequestMapping(path = "/horario")
@CrossOrigin
public class ControlHorario {
	@Autowired private RepoEspecialista repoEspecialista;
	@Autowired private RepoDiaria repoDiaria;
	@Autowired private RepoHorario repoHorario;
	
	@GetMapping
	public List<Horario> getAll(){
		return this.repoHorario.findAll();
	}
	
	@PutMapping
	public Horario update(@RequestBody Horario horario) {
		this.repoHorario.save(horario);
		return this.repoHorario.findById(horario.getId()).get();
		
	}
	
	@DeleteMapping(path = "/{id}")
	public List <Horario> deletarHorario(@PathVariable("id") Integer id){
		this.repoHorario.deleteById(id);
		return this.repoHorario.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Horario getHorarioById(@PathVariable("id") Integer id){
		return this.repoHorario.findById(id).get();
	}
	
	@GetMapping(path = "/diariaespecialista/{idDiaria}")
	public List<Horario> getHoraDiariaByEspe(@PathVariable("idDiaria") Integer idDiaria){
		Diaria diaria = this.repoDiaria.findById(idDiaria).get();
		List <Horario> horarios = this.repoHorario.findAll();
		List <Horario> horariosEsp = new ArrayList<Horario>();
		
		for(int i=0;i<horarios.size();i++) {
			if(horarios.get(i).getDiaria().getId()==diaria.getId()) {
				horariosEsp.add(horarios.get(i));
			}
		}
		
		return horariosEsp;
	}
	
	@GetMapping(path = "/especialista/{idesp}")
	public List<Horario> getHorariosEspe(@PathVariable("idesp") Integer idesp){
		List <Horario> horarios = this.repoHorario.findAll();
		List <Horario> horariosEsp = new ArrayList<Horario>();
		
		for(int i=0;i<horarios.size();i++) {
			if((horarios.get(i).getDiaria().getEspecialista().getId()==idesp)&&(horarios.get(i).isDisponivel())) {
				horariosEsp.add(horarios.get(i));
			}
		}
		return horariosEsp;
	}
}
