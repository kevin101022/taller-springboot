package com.example.demo.controllers;

import com.example.demo.models.Chofer;
import com.example.demo.services.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/choferes")
public class ChoferController {

    @Autowired
    private ChoferService choferService;

    @GetMapping
    public String listarChoferes(Model model) {
        model.addAttribute("choferes", choferService.obtenerTodos());
        return "choferes-lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("chofer", new Chofer());
        return "choferes-form";
    }

    @PostMapping("/guardar")
    public String guardarChofer(@ModelAttribute Chofer chofer, Model model) {
        try {
            choferService.guardarChofer(chofer);
            return "redirect:/choferes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("chofer", chofer);
            return "choferes-form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarChofer(@PathVariable Long id, Model model) {
        Chofer chofer = choferService.obtenerPorId(id).orElse(null);
        model.addAttribute("chofer", chofer);
        return "choferes-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarChofer(@PathVariable Long id) {
        choferService.eliminarChofer(id);
        return "redirect:/choferes";
    }
}
