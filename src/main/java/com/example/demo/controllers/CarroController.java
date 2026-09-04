package com.example.demo.controllers;

import com.example.demo.models.Carro;
import com.example.demo.repositories.CarroRepository;
import com.example.demo.repositories.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ChoferRepository choferRepository;

    // Ruta Fija
    @GetMapping
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroRepository.findAll());
        return "carros-lista"; // Apunta a carros-lista.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("carro", new Carro());
        model.addAttribute("choferes", choferRepository.findAll());
        return "carros-form"; // Apunta a carros-form.html
    }

    @PostMapping("/guardar")
    public String guardarCarro(@ModelAttribute Carro carro) {
        carroRepository.save(carro);
        return "redirect:/carros";
    }

    // Ruta Dinámica
    @GetMapping("/editar/{id}")
    public String editarCarro(@PathVariable Long id, Model model) {
        Carro carro = carroRepository.findById(id).orElse(null);
        model.addAttribute("carro", carro);
        model.addAttribute("choferes", choferRepository.findAll());
        return "carros-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCarro(@PathVariable Long id) {
        carroRepository.deleteById(id);
        return "redirect:/carros";
    }
}
