package com.prm.cursosp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.prm.cursosp.domain.Categoria;
import com.prm.cursosp.repositories.CategoriaRepository;
import com.prm.cursosp.services.exceptions.DataIntegrityException;
import com.prm.cursosp.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(int id) {
		Categoria obj = categoriaRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. Id: " + id + ", Tipo : " + Categoria.class.getName());
		}
		return obj;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null); // Garante que o objeto tem ID nulo e com isso vai se tratar de uma inserção
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId()); // Busca o objeto no banco, caso não exista já lança uma exceção do método find
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		Categoria obj = find(id);

		try {
			categoriaRepository.delete(obj.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possue produtos");
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
}
