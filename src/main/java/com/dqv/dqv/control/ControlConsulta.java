package com.dqv.dqv.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.Consulta;
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.repository.RepoConsulta;
import com.dqv.dqv.repository.RepoPessoa;

@RestController
@RequestMapping(path = "/consultas")
@CrossOrigin
public class ControlConsulta {
	
	@Autowired private RepoConsulta repoConsulta;
	@Autowired private RepoPessoa repoPessoa;
	
	@GetMapping(path = "pessoa/{id}")
	public List<Consulta> listarConsultas(@PathVariable("id") Integer id){
		ArrayList<Consulta> consultas = new ArrayList<>(); 
		Optional<Pessoa> p = repoPessoa.findById(id);
		for(int i=0;i<p.get().getAgendamentoConsultaPaciente().size();i++) {
			 consultas.add(p.get().getAgendamentoConsultaPaciente().get(i).getConsulta());  
		}
		return consultas;
	}
	
	@PostMapping
	public Consulta addConsulta(@RequestBody Consulta consulta) {
		return this.repoConsulta.save(consulta);
	}
	
	@GetMapping(path = "/{id}")
	public Consulta getConsultaById(@PathVariable("id") Integer id){
		return this.repoConsulta.findById(id).get();
	}

}
