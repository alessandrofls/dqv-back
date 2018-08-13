package com.dqv.dqv.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.Receita;

@RestController
@RequestMapping(path = "/receitas")
@CrossOrigin
public class ReceitaController {
	
	/*@Autowired
	private ReceitaDAO receitas;
	
	@PostMapping
	public Receita save(@RequestBody Receita receita) {
		return this.receitas.save(receita);
	}
	
	@GetMapping
	public List<Receita> getAll(){
		return this.receitas.findAll();
	}
	
	@GetMapping(path="/{paciente}")
	public List<Receita> getPorPessoa(@PathVariable(value = "paciente") String paciente){
		return this.receitas.findByPaciente(paciente);
	}*/
}
