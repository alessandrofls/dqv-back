package com.dqv.dqv.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.AgendamentoConsulta;
import com.dqv.dqv.bean.Consulta;
import com.dqv.dqv.bean.Especialista;
import com.dqv.dqv.bean.Horario;
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.bean.Servidor;
import com.dqv.dqv.repository.RepoAgendamentoCons;
import com.dqv.dqv.repository.RepoConsulta;
import com.dqv.dqv.repository.RepoEspecialista;
import com.dqv.dqv.repository.RepoHorario;
import com.dqv.dqv.repository.RepoPessoa;
import com.dqv.dqv.repository.RepoServidor;

@RestController
@RequestMapping(path = "/consulta")
@CrossOrigin
public class ControlConsulta {
	
	@Autowired private RepoAgendamentoCons repoAgendamentoCons;
	@Autowired private RepoPessoa repoPessoa;
	@Autowired private RepoConsulta repoConsulta;
	@Autowired private RepoEspecialista repoEspecialista;
	
	@GetMapping(path = "/pessoa/{id}")
	public List<Consulta> listarConsultasPessoa(@PathVariable("id") Integer id){
		Pessoa p = repoPessoa.findById(id).get();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<Consulta> consultasPessoa = new ArrayList<Consulta>();
		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getPaciente().getId()==p.getId()) {
				consultasPessoa.add(agendamentos.get(i).getConsulta());
			}
		}
		return consultasPessoa;
	}
	
	@GetMapping(path = "/especialista/{id}")
	public List<Consulta> listarConsultasEspecialista(@PathVariable("id") Integer id){
		Especialista e = repoEspecialista.findById(id).get();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<Consulta> consultasEspecialista = new ArrayList<Consulta>();
		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getConsulta().getEspecialista().getId()==e.getId()) {
				consultasEspecialista.add(agendamentos.get(i).getConsulta());
			}
		}
		return consultasEspecialista;
	}
	
	@GetMapping(path = "/{id}")
	public Consulta getAgendamentoConsultaById(@PathVariable("id") Integer id){

		return this.repoConsulta.findById(id).get();
	}
	
	@PutMapping
	public Consulta update(@RequestBody Consulta consulta) {
		this.repoConsulta.save(consulta);
		return this.repoConsulta.findById(consulta.getId()).get();
		
	}

}
