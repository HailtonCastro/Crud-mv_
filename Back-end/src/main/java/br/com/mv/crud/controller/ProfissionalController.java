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


import br.com.mv.crud.entity.Profissional;
import br.com.mv.crud.repository.ProfissionalRepository;

@RestController
@RequestMapping(value="profissional/")
public class ProfissionalController {

	@Autowired
	private ProfissionalRepository _profissonalRepository;
	
	@PostMapping(value="/", produces="application/json")
	public ResponseEntity<Profissional> save(@RequestBody Profissional profissional){
		
		Profissional profissionalSalvo = _profissonalRepository.save(profissional);
		return ResponseEntity.ok(profissionalSalvo);		
	}
	
	@GetMapping(value="/",  produces = "application/json")
	public ResponseEntity<Iterable<Profissional>> getAll(){
		
		Iterable<Profissional> profissional = _profissonalRepository.findAll();
        return new ResponseEntity<Iterable<Profissional>>(profissional, HttpStatus.OK);           	
	}  
	
	@GetMapping(value="/{idProfissional}", produces = "application/json" )
	public ResponseEntity<Profissional> getById(@PathVariable Long idProfissional){
		
		Optional<Profissional> profissional = _profissonalRepository.findById(idProfissional);
		
		if (profissional.isPresent()) {
			return new ResponseEntity<Profissional>(profissional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}						
	}
	
	@PutMapping(value="/",  produces = "application/json")
	public ResponseEntity<Profissional> update(@RequestBody Profissional profissional) {
		
		Optional<Profissional> oldProfissional = _profissonalRepository.findById(profissional.getId());
		
		if (oldProfissional.isPresent()) {
			Profissional profissionalAtualizado = _profissonalRepository.save(profissional);
			return ResponseEntity.ok(profissionalAtualizado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}	
	
	@DeleteMapping(value="/{idProfissional}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable Long idProfissional){

		Optional<Profissional> profissional = _profissonalRepository.findById(idProfissional);
		if (profissional.isPresent()) {
			_profissonalRepository.deleteById(idProfissional);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	} 
}
