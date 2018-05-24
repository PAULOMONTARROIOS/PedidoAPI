package com.prm.cursosp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prm.cursosp.domain.Categoria;
import com.prm.cursosp.domain.Produto;
import com.prm.cursosp.repositories.CategoriaRepository;
import com.prm.cursosp.repositories.ProdutoRepository;
import com.prm.cursosp.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Produto pedido = produtoRepository.findOne(id);
		if (pedido == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. Id: " + id + ", Tipo: " + Produto.class.getName());
		}
		return pedido;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepository.findAll(ids);
		
		return produtoRepository.search(nome, categorias, pageRequest);
	}

}
