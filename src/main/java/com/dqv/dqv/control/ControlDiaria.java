package com.dqv.dqv.control;

import java.sql.Timestamp;
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
@RequestMapping(path = "/diaria")
@CrossOrigin
public class ControlDiaria {
	@Autowired private RepoEspecialista repoEspecialista;
	@Autowired private RepoDiaria repoDiaria;
	@Autowired private RepoHorario repoHorario;
	
	@PostMapping(path = "/{idesp}")
	public Diaria save(@RequestBody Diaria diaria, @PathVariable("idesp") Integer idesp) {
		Float primeiroHorario = (float) (240/diaria.getTempoConsulta());
		Float segundoHorario = (float) (240/diaria.getTempoConsulta());
		LocalTime amAtual = LocalTime.of(8, 0);
		LocalTime pmAtual = LocalTime.of(14, 0);
		
		Especialista especialista = this.repoEspecialista.findById(idesp).get();
		diaria.setAprovado(false);;
		diaria.setValidado(false);
		diaria.setEspecialista(especialista);
		Diaria diariaSalva=this.repoDiaria.save(diaria);
		Horario h = new Horario();
		h.setDisponivel(true);
		
		for(int i=0;i<primeiroHorario.intValue();i++) {
			h = new Horario();
			h.setDisponivel(true);
			h.setHoraInico(amAtual);
			h.setDiaria(diariaSalva);
			amAtual=amAtual.plusMinutes(diaria.getTempoConsulta());
			h.setHoraFim(amAtual);
			this.repoHorario.save(h);
		}
		for(int i=0;i<segundoHorario.intValue();i++) {
			h = new Horario();
			h.setDisponivel(true);
			h.setHoraInico(pmAtual);
			h.setDiaria(diariaSalva);
			pmAtual=pmAtual.plusMinutes(diaria.getTempoConsulta());
			h.setHoraFim(pmAtual);
			this.repoHorario.save(h);
		}
			
		return diariaSalva;
	}
	
	@GetMapping
	public List<Diaria> getAll(){
		return this.repoDiaria.findAll();
	}
	
	@PutMapping
	public Diaria update(@RequestBody Diaria diaria) {
		this.repoDiaria.save(diaria);
		return this.repoDiaria.findById(diaria.getId()).get();
		
	}
	
	@DeleteMapping(path = "/{id}")
	public List <Diaria> deletarDiaria(@PathVariable("id") Integer id){
		this.repoDiaria.deleteById(id);
		return this.repoDiaria.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Diaria getDiariaById(@PathVariable("id") Integer id){
		return this.repoDiaria.findById(id).get();
	}
	
	@GetMapping(path = "/especialista/{idesp}")
	public List<Diaria> getDiariaByEspe(@PathVariable("idesp") Integer idesp){
		List <Diaria> diarias = this.repoDiaria.findAll();
		List <Diaria> diariasEsp = new ArrayList<Diaria>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for(int i=0;i<diarias.size();i++) {
			if(diarias.get(i).getEspecialista().getId()==idesp&&
			diarias.get(i).getDiaria().toLocalDateTime().getMonth()==now.toLocalDateTime().getMonth()) {
				diariasEsp.add(diarias.get(i));
			}
		}
		
		return diariasEsp;
	}
	
}
