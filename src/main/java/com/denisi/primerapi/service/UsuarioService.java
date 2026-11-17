package com.denisi.primerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.denisi.primerapi.model.Usuario;
import com.denisi.primerapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario updatedUsuario = existingUsuario.get();
            updatedUsuario.setNombre(usuario.getNombre());
            updatedUsuario.setEmail(usuario.getEmail());
            return usuarioRepository.save(updatedUsuario);
        }
        return null;
    }

    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Usuario> searchUsuarios(String query) {
        return usuarioRepository.findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }

    public List<Usuario> searchByEdadMayor(int edad) {
        return usuarioRepository.findByEdadGreaterThan(edad);
    }
}
