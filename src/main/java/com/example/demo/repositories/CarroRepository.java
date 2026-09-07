package com.example.demo.repositories;

import com.example.demo.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByPlaca(String placa);
}
