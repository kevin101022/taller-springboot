package views;

import java.util.Scanner;

public class Viaje_vista {

    public void mostrarValidando() {
        System.out.println("\n--- VALIDANDO FICHA DE VIAJE ---");
    }

    public void mostrarErrorFaltaDato(String dato) {
        System.out.println("- ERROR: Falta registrar el " + dato + ".");
    }

    public void mostrarErrorVacio() {
        System.out.println("\n[!] No hay ningún registro temporal para guardar.");
    }

    public void mostrarExitoGuardado() {
        System.out.println("\n¡ÉXITO! La ficha de viaje ha sido guardada en la base de datos central.");
        System.out.println("Los registros temporales se han limpiado. Puede registrar un nuevo viaje.");
    }

    public boolean preguntarForzarGuardado(Scanner teclado) {
        System.out.println("\n[!] Advertencia: La Ficha de Viaje no cumple con los requisitos completos.");
        System.out.print("¿Desea forzar el guardado individual de los datos que SÍ registró? (S/N): ");
        String respuesta = teclado.nextLine();
        return respuesta.trim().equalsIgnoreCase("S");
    }

    public void mostrarGuardadoIndividualExito() {
        System.out.println("Registros individuales guardados en la BD exitosamente.");
    }

    public void mostrarGuardadoCancelado() {
        System.out.println("Guardado cancelado. Complete los datos faltantes e intente guardar de nuevo (Opción 5).");
    }
}
