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
        // Podríamos agregar una regla para que la licencia sea única
        choferRepository.save(chofer);
    }

    public void eliminarChofer(Long id) {
        choferRepository.deleteById(id);
    }

}
