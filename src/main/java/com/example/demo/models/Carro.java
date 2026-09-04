package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String marca;
    private String modelo;

    // Relación: Muchos carros pueden pertenecer a un chofer
    @ManyToOne
    @JoinColumn(name = "chofer_id")
    private Chofer chofer;

    public Carro() {}

    public Carro(String placa, String marca, String modelo, Chofer chofer) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.chofer = chofer;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Chofer getChofer() { return chofer; }
    public void setChofer(Chofer chofer) { this.chofer = chofer; }
}
