package com.example.demo.controllers;

import com.example.demo.models.Carro;
import com.example.demo.services.CarroService;
import com.example.demo.services.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private ChoferService choferService;

    // Ruta Fija
    @GetMapping
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroService.obtenerTodos());
        return "carros-lista"; // Apunta a carros-lista.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("carro", new Carro());
        model.addAttribute("choferes", choferService.obtenerTodos());
        return "carros-form"; // Apunta a carros-form.html
    }

    @PostMapping("/guardar")
    public String guardarCarro(@ModelAttribute Carro carro, Model model) {
        try {
            carroService.guardarCarro(carro);
            return "redirect:/carros";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("carro", carro);
            model.addAttribute("choferes", choferService.obtenerTodos());
            return "carros-form";
        }
    }

    // Ruta Dinámica
    @GetMapping("/editar/{id}")
    public String editarCarro(@PathVariable Long id, Model model) {
        Carro carro = carroService.obtenerPorId(id).orElse(null);
        model.addAttribute("carro", carro);
        model.addAttribute("choferes", choferService.obtenerTodos());
        return "carros-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCarro(@PathVariable Long id) {
        carroService.eliminarCarro(id);
        return "redirect:/carros";
    }
}
