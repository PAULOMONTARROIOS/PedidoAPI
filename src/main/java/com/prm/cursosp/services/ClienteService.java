package com.prm.cursosp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursosp.domain.Cliente;
import com.prm.cursosp.repositories.ClienteRepository;
import com.prm.cursosp.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado, Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}

}
