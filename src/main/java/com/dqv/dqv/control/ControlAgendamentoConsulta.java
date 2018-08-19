package com.dqv.dqv.control;

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

import com.dqv.dqv.bean.AgendamentoConsulta;
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.bean.Servidor;
import com.dqv.dqv.repository.RepoAgendamentoCons;
import com.dqv.dqv.repository.RepoPessoa;
import com.dqv.dqv.repository.RepoServidor;

@RestController
@RequestMapping(path = "/agendamento")
@CrossOrigin
public class ControlAgendamentoConsulta {
	@Autowired private RepoAgendamentoCons repoAgendamentoCons;
	@Autowired private RepoPessoa repoPessoa;
	@Autowired private RepoServidor repoServidor;
	
	
	@GetMapping(path = "pessoa/{id}")
	public List<AgendamentoConsulta> listarAgendamentosPessoa(@PathVariable("id") Integer id){
		Optional<Pessoa> p = repoPessoa.findById(id);
		return p.get().getAgendamentoConsultaPaciente();
	}
	@GetMapping(path = "servidor/{id}")
	public List<AgendamentoConsulta> listarAgendamentosServidor(@PathVariable("id") Integer id){
		Optional<Servidor> s = repoServidor.findById(id);
		return s.get().getAgendamentoConsultaPaciente();
	}
	
	@PostMapping
	public AgendamentoConsulta addConsulta(@RequestBody AgendamentoConsulta Agendamento) {
		return this.repoAgendamentoCons.save(Agendamento);
	}
	
	@GetMapping(path = "/{id}")
	public AgendamentoConsulta getAgendamentoConsultaById(@PathVariable("id") Integer id){
		return this.repoAgendamentoCons.findById(id).get();
	}
}
