package com.prm.cursosp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prm.cursosp.domain.Cidade;
import com.prm.cursosp.domain.Cliente;
import com.prm.cursosp.domain.Endereco;
import com.prm.cursosp.domain.enums.TipoCliente;
import com.prm.cursosp.dto.ClienteDTO;
import com.prm.cursosp.dto.ClienteNewDTO;
import com.prm.cursosp.repositories.CidadeRepository;
import com.prm.cursosp.repositories.ClienteRepository;
import com.prm.cursosp.repositories.EnderecoRepository;
import com.prm.cursosp.services.exceptions.DataIntegrityException;
import com.prm.cursosp.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado, Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); // Busca o objeto no banco, caso não exista já lança uma exceção do método find
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void delete(Integer id) {
		Cliente obj = find(id);

		try {
			clienteRepository.delete(obj.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cliente que possue Pedidos associados");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = cidadeRepository.findOne(objDTO.getCidadeId());
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null){
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null){
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
