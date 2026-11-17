package com.denisi.primerapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.denisi.primerapi.model.Usuario;
import com.denisi.primerapi.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET: Listar usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    // GET: Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.createUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    // PUT: Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);

        return updatedUsuario != null
                ? ResponseEntity.ok(updatedUsuario)
                : ResponseEntity.notFound().build();
    }

    // DELETE: Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Usuario>> searchUsuarios(@RequestParam String query) {
        List<Usuario> usuarios = usuarioService.searchUsuarios(query);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/edad/mayor")
    public ResponseEntity<List<Usuario>> getUsuariosEdadMayor(
            @RequestParam int edad) {

        return ResponseEntity.ok(
                usuarioService.searchByEdadMayor(edad));
    }
}
