package com.denisi.primerapi.repository;

import com.denisi.primerapi.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(String nombre, String email);

    List<Usuario> findByEdadGreaterThan(int edad);

}
