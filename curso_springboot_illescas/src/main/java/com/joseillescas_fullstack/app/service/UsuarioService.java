package com.joseillescas_fullstack.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.joseillescas_fullstack.app.entity.Usuario;

public interface UsuarioService {
	
	public Iterable<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);

	public Optional<Usuario> findByd(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void deleteById(Long id);

}
