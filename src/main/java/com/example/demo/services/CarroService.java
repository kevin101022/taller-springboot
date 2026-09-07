package com.example.demo.services;

import com.example.demo.models.Carro;
import com.example.demo.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Chofer;
import com.example.demo.services.ChoferService;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ChoferService choferService;

    public List<Carro> obtenerTodos() {
        return carroRepository.findAll();
    }

    public Optional<Carro> obtenerPorId(Long id) {
        return carroRepository.findById(id);
    }

    public void guardarCarro(Carro carro) {
        // Regla de Negocio: No debe haber placas repetidas
        if (carro.getId() == null) {
            if (carroRepository.existsByPlaca(carro.getPlaca())) {
                throw new IllegalArgumentException("Ya existe un carro con la placa " + carro.getPlaca());
            }
        } else {
            Optional<Carro> carroDb = carroRepository.findById(carro.getId());
            if (carroDb.isPresent() && !carroDb.get().getPlaca().equals(carro.getPlaca())) {
                 if (carroRepository.existsByPlaca(carro.getPlaca())) {
                     throw new IllegalArgumentException("Ya existe un carro con la placa " + carro.getPlaca());
                 }
            }
        }

        // Regla de Negocio: Un chofer no puede tener más de 3 carros
        if (carro.getChofer() != null && carro.getChofer().getId() != null) {
            // Solo validamos si es un carro NUEVO o si se cambió de chofer
            boolean checkChofer = true;
            if (carro.getId() != null) {
                 Carro carroDb = carroRepository.findById(carro.getId()).orElse(null);
                 if (carroDb != null && carroDb.getChofer().getId().equals(carro.getChofer().getId())) {
                     checkChofer = false; // Mismo chofer, no es un carro extra para él
                 }
            }
            if (checkChofer) {
                Optional<Chofer> choferDb = choferService.obtenerPorId(carro.getChofer().getId());
                if (choferDb.isPresent() && choferDb.get().getCarros() != null && choferDb.get().getCarros().size() >= 3) {
                    throw new IllegalArgumentException("El chofer seleccionado ya tiene el límite máximo de 3 carros asignados.");
                }
            }
        }

        carroRepository.save(carro);
    }

    public void eliminarCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
