package com.example.demo.controllers;

import com.example.demo.models.Chofer;
import com.example.demo.repositories.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/choferes")
public class ChoferController {

    @Autowired
    private ChoferRepository choferRepository;

    @GetMapping
    public String listarChoferes(Model model) {
        model.addAttribute("choferes", choferRepository.findAll());
        return "choferes-lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("chofer", new Chofer());
        return "choferes-form";
    }

    @PostMapping("/guardar")
    public String guardarChofer(@ModelAttribute Chofer chofer) {
        choferRepository.save(chofer);
        return "redirect:/choferes";
    }

    @GetMapping("/editar/{id}")
    public String editarChofer(@PathVariable Long id, Model model) {
        Chofer chofer = choferRepository.findById(id).orElse(null);
        model.addAttribute("chofer", chofer);
        return "choferes-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarChofer(@PathVariable Long id) {
        choferRepository.deleteById(id);
        return "redirect:/choferes";
    }
}
