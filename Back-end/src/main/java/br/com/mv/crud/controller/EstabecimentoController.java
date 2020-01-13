package br.com.mv.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.crud.entity.Estabelecimento;
import br.com.mv.crud.repository.EstabelecimentoRepository;
import br.com.mv.crud.service.EstabelecimentoService;

@RestController
@RequestMapping(value="estabelecimento/")
public class EstabecimentoController {
	
	@Autowired
	private EstabelecimentoRepository _estabelecimentoRepository;
	
	@Autowired
	private EstabelecimentoService service;
	
	@PostMapping(value="/", produces="application/json")
	public ResponseEntity<Estabelecimento> save(@RequestBody Estabelecimento estabelecimento){
		
		
		Estabelecimento estabelecimentoSalvo = _estabelecimentoRepository.save(estabelecimento);
		return ResponseEntity.ok(estabelecimentoSalvo);		
	}	
	
	@GetMapping(value="/")
	public ResponseEntity <Iterable<Estabelecimento>> getAll(){
		
		Iterable<Estabelecimento> estabelecimento = _estabelecimentoRepository.findAll();
		return ResponseEntity.ok(estabelecimento);		
	}
	
	@GetMapping(value="/{idEstabelecimento}", produces = "application/json")
	public ResponseEntity<Estabelecimento> getById(@PathVariable Long idEstabelecimento) {
		
		Optional<Estabelecimento> estabelecimento = _estabelecimentoRepository.findById(idEstabelecimento);
		
		if (estabelecimento.isPresent()) {
			return new ResponseEntity<Estabelecimento>(estabelecimento.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PutMapping(value="/",  produces = "application/json")
	public ResponseEntity<Estabelecimento> update(@RequestBody Estabelecimento estabelecimento) {
		
		Optional<Estabelecimento> oldEstabelecimento = _estabelecimentoRepository.findById(estabelecimento.getId());
		
		if (oldEstabelecimento.isPresent()) {
			Estabelecimento estabelecimentoAtualizado = _estabelecimentoRepository.save(estabelecimento);
			return ResponseEntity.ok(estabelecimentoAtualizado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@DeleteMapping(value="/{idEstabelecimento}")
	public ResponseEntity<Object> delete(@PathVariable Long idEstabelecimento){

		Optional<Estabelecimento> estabelecimento = _estabelecimentoRepository.findById(idEstabelecimento);
		if (estabelecimento.isPresent()) {
			_estabelecimentoRepository.delete(estabelecimento.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	} 

}
