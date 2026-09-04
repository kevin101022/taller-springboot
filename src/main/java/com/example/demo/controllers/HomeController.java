package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarIndex() {
        // Retorna el nombre de la plantilla HTML que crearemos a continuación
        return "index";
    }
}
