package com.dqv.dqv.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path = "/agendamento")
@CrossOrigin
public class ControlAgendamentoConsulta {
	@Autowired private RepoAgendamentoCons repoAgendamentoCons;
	@Autowired private RepoPessoa repoPessoa;
	@Autowired private RepoServidor repoServidor;
	@Autowired private RepoHorario repoHorario;
	@Autowired private RepoConsulta repoConsulta;
	@Autowired private RepoEspecialista repoEspecialista;
	
	
	
	@GetMapping(path = "pessoa/{id}")
	public List<AgendamentoConsulta> listarAgendamentosPessoa(@PathVariable("id") Integer id){
		Pessoa p = repoPessoa.findById(id).get();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<AgendamentoConsulta> agendamentosPessoa = new ArrayList<AgendamentoConsulta>();
		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getPaciente().getId()==p.getId()) {
				agendamentosPessoa.add(agendamentos.get(i));
			}
		}
		return agendamentosPessoa;
	}
	
	@GetMapping(path = "servidor/{id}")
	public List<AgendamentoConsulta> listarAgendamentosServidor(@PathVariable("id") Integer id){
		Servidor s = repoServidor.findById(id).get();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<AgendamentoConsulta> agendamentosServidor = new ArrayList<AgendamentoConsulta>();
		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getServidor().getId()==s.getId()) {
				agendamentosServidor.add(agendamentos.get(i));
			}
		}
		return agendamentosServidor;
	}
	
	@PostMapping(path = "/{idHora}")
	public AgendamentoConsulta addConsulta(@RequestBody AgendamentoConsulta agendamento,@PathVariable("idHora") Integer idHora) {
		Horario horario = repoHorario.findById(idHora).get();
		Consulta consulta = new Consulta();
		Consulta consultaSalva;
		consulta.setHorario(horario);
		consulta.setEspecialista(horario.getDiaria().getEspecialista());
		if(agendamento.getServidor()!=null) {
			consulta.setViaTelefone(true);
		}
		consultaSalva=this.repoConsulta.save(consulta);
		agendamento.setConsulta(consultaSalva);
		return this.repoAgendamentoCons.save(agendamento);
	}
	
	@GetMapping(path = "/{id}")
	public AgendamentoConsulta getAgendamentoConsultaById(@PathVariable("id") Integer id){

		return this.repoAgendamentoCons.findById(id).get();
	}
	

	@GetMapping(path = "/especialista/{id}")
	public List<AgendamentoConsulta> listarConsultasEspecialista(@PathVariable("id") Integer id){
		Especialista e = repoEspecialista.findById(id).get();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<AgendamentoConsulta> consultasEspecialista = new ArrayList<AgendamentoConsulta>();

		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getConsulta().getEspecialista().getId()==e.getId()) {
				consultasEspecialista.add(agendamentos.get(i));
			}
		}
		return consultasEspecialista;
	}
	
	@GetMapping
	public List<AgendamentoConsulta> getAgendamentos(){
		return this.repoAgendamentoCons.findAll();
	}
	
	
}
