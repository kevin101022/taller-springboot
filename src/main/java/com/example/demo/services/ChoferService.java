package com.example.demo.services;

import com.example.demo.models.Chofer;
import com.example.demo.repositories.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoferService {

    @Autowired
    private ChoferRepository choferRepository;

    public List<Chofer> obtenerTodos() {
        return choferRepository.findAll();
    }

    public Optional<Chofer> obtenerPorId(Long id) {
        return choferRepository.findById(id);
    }

    public void guardarChofer(Chofer chofer) {
        // Regla de Negocio: El teléfono solo puede contener números
        if (chofer.getTelefono() != null && !chofer.getTelefono().matches("\\d+")) {
            throw new IllegalArgumentException("El número de teléfono solo puede contener números (sin letras, espacios ni símbolos).");
        }
        
        // Regla de Negocio: La licencia solo puede contener números
        if (chofer.getLicencia() != null && !chofer.getLicencia().matches("\\d+")) {
            throw new IllegalArgumentException("El número de licencia solo puede contener números.");
        }

        choferRepository.save(chofer);
    }

    public void eliminarChofer(Long id) {
        choferRepository.deleteById(id);
    }

}
