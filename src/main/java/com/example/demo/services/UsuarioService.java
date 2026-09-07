package com.example.demo.services;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void guardarUsuario(Usuario usuario) {
        // Regla de Negocio: Correo electrónico único
        if (usuario.getId() == null || !usuarioRepository.findById(usuario.getId()).get().getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new IllegalArgumentException("Ya existe un usuario con el correo " + usuario.getEmail());
            }
        }
        
        // Regla de Negocio: Formato de correo válido (básico)
        if (!usuario.getEmail().contains("@")) {
             throw new IllegalArgumentException("El correo electrónico no es válido.");
        }

        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
