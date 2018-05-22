package com.prm.cursosp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursosp.domain.Pedido;
import com.prm.cursosp.repositories.PedidoRepository;
import com.prm.cursosp.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if (pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return pedido;
	}
}
