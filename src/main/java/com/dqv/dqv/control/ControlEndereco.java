package com.dqv.dqv.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dqv.dqv.bean.Endereco;
import com.dqv.dqv.repository.RepoEndereco;

@RestController
@RequestMapping(path = "/endereco")
@CrossOrigin
public class ControlEndereco {
	@Autowired private RepoEndereco repoEndereco;
	
	@PostMapping
	public Endereco save(@RequestBody Endereco e) {
		return this.repoEndereco.save(e);
	}
	
	@GetMapping
	public List<Endereco> listarEnderecos(Endereco e){
		Example<Endereco> example = Example.of(e);
		return this.repoEndereco.findAll(example);
	}
	
}
