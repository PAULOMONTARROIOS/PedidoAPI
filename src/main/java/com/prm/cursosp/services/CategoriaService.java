package com.prm.cursosp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursosp.domain.Categoria;
import com.prm.cursosp.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria buscar(int id) {
		Categoria obj = categoriaRepository.findOne(id);
		return obj;
	}
	
}
