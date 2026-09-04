package main;

import controller.Controlador;


public class Tiendaparking {
    public static void main(String[] args) {
        // Main delega todo el flujo al Controlador
        Controlador controlador = new Controlador();
        controlador.iniciar();
    }
}
