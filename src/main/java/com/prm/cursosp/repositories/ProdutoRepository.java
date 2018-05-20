package com.prm.cursosp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prm.cursosp.domain.Produto;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer>{
	
}
