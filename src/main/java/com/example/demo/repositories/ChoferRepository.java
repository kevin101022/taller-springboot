package com.example.demo.repositories;

import com.example.demo.models.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoferRepository extends JpaRepository<Chofer, Long> {
}
