package com.joseillescas_fullstack.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.joseillescas_fullstack.app.entity.Usuario;
import com.joseillescas_fullstack.app.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	//Crear un nuevo usuario
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	//Leer un usuario
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable Long id){
		Optional<Usuario> oUsuario = usuarioService.findByd(id);
		
		if(!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();	
			}
		return ResponseEntity.ok(oUsuario);
	}
	
	//Editar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody Usuario usuarioDetails, @PathVariable Long id){
		Optional<Usuario> usuario = usuarioService.findByd(id);
		
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		
		usuario.get().setNombre(usuarioDetails.getNombre());
		usuario.get().setClave(usuarioDetails.getClave());
		usuario.get().setEmail(usuarioDetails.getEmail());
		usuario.get().setEstado(usuarioDetails.getEstado());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));
	}
	
	//Eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id){
		
		if(!usuarioService.findByd(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	
	//Listar Usuarios
	@GetMapping
	public List <Usuario> readAll(){
		
		List<Usuario> usuarios = StreamSupport
				.stream(usuarioService.findAll().spliterator(),false)
				.collect(Collectors.toList());
		return usuarios;
	}
}
