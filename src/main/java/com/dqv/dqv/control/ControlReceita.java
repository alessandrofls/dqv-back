package com.dqv.dqv.control;

import java.util.ArrayList;
import java.util.List;

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
import com.dqv.dqv.bean.Pessoa;
import com.dqv.dqv.bean.Receita;
import com.dqv.dqv.repository.RepoAgendamentoCons;
import com.dqv.dqv.repository.RepoConsulta;
import com.dqv.dqv.repository.RepoEspecialista;
import com.dqv.dqv.repository.RepoPessoa;
import com.dqv.dqv.repository.RepoReceita;

@RestController
@RequestMapping(path = "/receita")
@CrossOrigin
public class ControlReceita {
	
	@Autowired private RepoReceita repoReceita;
	@Autowired private RepoConsulta repoConsulta;
	@Autowired private RepoPessoa repoPessoa;
	@Autowired private RepoAgendamentoCons repoAgendamentoCons;
	@Autowired private RepoEspecialista repoEspecialista;

	@PostMapping(path = "/{idCons}")
	public Receita save(@RequestBody Receita receita, @PathVariable("idCons") Integer idCons) {
		Consulta c = repoConsulta.findById(idCons).get();
		receita.setConsulta(c);
		return this.repoReceita.save(receita);
	}
	
	@GetMapping(path = "/pessoa/{id}")
	public List<Receita> listarReceitaPessoa(@PathVariable("id") Integer id){
		Pessoa p = repoPessoa.findById(id).get();
		List<Receita> receitas = this.repoReceita.findAll();
		List<Receita> receitasPessoa = new ArrayList<Receita>();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<AgendamentoConsulta> consultasPessoa = new ArrayList<AgendamentoConsulta>();
		
		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getPaciente().getId()==p.getId()) {
				consultasPessoa.add(agendamentos.get(i));
			}
		}
		
		for(int i=0;i<receitas.size();i++) {
			for(int j=0;j<consultasPessoa.size();j++) {
				if(consultasPessoa.get(j).getConsulta().getId()==receitas.get(i).getConsulta().getId()) {
					receitasPessoa.add(receitas.get(j));
				}
			}
		}
		return receitasPessoa;
	}
	
	@GetMapping(path = "/especialista/{id}")
	public List<Receita> listarConsultasEspecialista(@PathVariable("id") Integer id){
		Especialista e = repoEspecialista.findById(id).get();
		List<AgendamentoConsulta> agendamentos = this.repoAgendamentoCons.findAll();
		List<Consulta> consultasEspecialista = new ArrayList<Consulta>();
		List<Receita> receitas = this.repoReceita.findAll();
		List<Receita> receitasEspecialista = new ArrayList<Receita>();
		
		for(int i=0;i<agendamentos.size();i++) {
			if(agendamentos.get(i).getConsulta().getEspecialista().getId()==e.getId()) {
				consultasEspecialista.add(agendamentos.get(i).getConsulta());
			}
		}
		
		for(int i=0;i<receitas.size();i++) {
			for(int j=0;j<consultasEspecialista.size();j++) {
				if(consultasEspecialista.get(j).getId()==receitas.get(i).getConsulta().getId()) {
					receitasEspecialista.add(receitas.get(j));
				}
			}
		}
		return receitasEspecialista;
	}
	
	@GetMapping(path = "/{id}")
	public Consulta getAgendamentoConsultaById(@PathVariable("id") Integer id){

		return this.repoConsulta.findById(id).get();
	}
	
	

}
