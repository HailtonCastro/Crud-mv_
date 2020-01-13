package br.com.mv.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mv.crud.repository.EstabelecimentoRepository;

@Service	
public class EstabelecimentoService {
	
	@Autowired 
	EstabelecimentoRepository repository;

}
