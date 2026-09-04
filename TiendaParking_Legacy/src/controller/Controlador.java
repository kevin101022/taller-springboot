package controller;

import dao.Viaje_DAO;
import model.Api_Modelo;
import model.Carro_modelo;
import model.chofer_modelo;
import model.Motor_modelo;
import model.Pasajero_modelo;
import views.Menu_vista;

// Puente entre las vistas y los modelos. Solo arma y envía el objeto para guardarlo.
public class Controlador {

    // Conexión única para toda la app
    private static final Api_Modelo api = new Api_Modelo("root", "http://localhost", "1234");
    static {
        api.validar_conexion();
    }

    // Punto de entrada del flujo (Controlador coordina las vistas)
    @SuppressWarnings("resource")
    public void iniciar() {
        // Menu GUI - el flujo es dirigido por eventos de cada boton
        new Menu_vista(this).mostrarMenu();
    }

    //carro (indice 0)
    public Carro_modelo registrarCarro(String placa, String marca, String modelo){
        Carro_modelo obj_carro = new Carro_modelo(placa, marca, modelo);
        api.guardarEnFichaTemporal(0, obj_carro);
        return obj_carro;
    }

    // Sobrecarga
    public Carro_modelo registrarCarro(String placa, String marca){
        return registrarCarro(placa, marca, "No especificado");
    }

    //chofer (indice 2)
    public chofer_modelo registrarChofer(String nombre, String apellido, String cedula, String licencia){
        chofer_modelo obj_chofer = new chofer_modelo(nombre, apellido, cedula, licencia);
        api.guardarEnFichaTemporal(2, obj_chofer);
        return obj_chofer;
    }

    //motor (indice 1)
    public Motor_modelo registrarMotor(String tipo, String caballosFuerza, String numeroSerie){
        Motor_modelo obj_motor = new Motor_modelo(tipo, caballosFuerza, numeroSerie);
        api.guardarEnFichaTemporal(1, obj_motor);
        return obj_motor;
    }

    // Sobrecarga: usa 100 HP por defecto
    public Motor_modelo registrarMotor(String serie, String tipo){
        return registrarMotor(tipo, "100", serie);
    }

    //pasajero (indice 3)
    public Pasajero_modelo registrarPasajero(String nombre, String apellido, String cedula, String tipoDocumento, String genero){
        Pasajero_modelo obj_pasajero = new Pasajero_modelo(nombre, apellido, cedula, tipoDocumento, genero);
        api.guardarEnFichaTemporal(3, obj_pasajero);
        return obj_pasajero;
    }

    // Delega al modelo la consulta de completitud de la ficha
    public boolean validarFichaCompleta() {
        return api.validarFichaCompleta();
    }

    // Controlador coordina DAO y modelo: persiste la ficha y limpia si exito
    public String persistirFicha() {
        Viaje_DAO dao = new Viaje_DAO();
        String resultado = dao.insertarViaje(
            (Carro_modelo)    api.getFichaTemporal(0),
            (Motor_modelo)    api.getFichaTemporal(1),
            (chofer_modelo)   api.getFichaTemporal(2),
            (Pasajero_modelo) api.getFichaTemporal(3)
        );
        if (resultado.equals("Registro insertado en BD con exito")) {
            api.limpiarFicha();
        }
        return resultado;
    }
}
