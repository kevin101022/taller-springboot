package model;

import java.util.ArrayList;
import java.util.List;

public class Api_Modelo {
    // Inicializar variables
    public String root = "";
    public String url = "";
    public String clave = "";
    public boolean conectado = false;

    // Estructuras dinámicas (ArrayList) 
    private final List<chofer_modelo> lista_chofer = new ArrayList<>();
    private final List<Pasajero_modelo> lista_pasajero = new ArrayList<>();

    // Estructuras estáticas (Matrices 3x3 para strings)
    private final String[][] lista_carros = new String[3][3];
    private final String[][] lista_motores = new String[3][3];

    // Estructura temporal externa: almacena la ficha en espera de persistencia
    // Indices: 0=Carro, 1=Motor, 2=Chofer, 3=Pasajero
    private final Object[] fichaTemporal = new Object[4];

    // Constructor
    public Api_Modelo(String url, String usuario, String clave) {
        this.url = url;
        this.root = usuario;
        this.clave = clave;
    }

    // Getters públicos
    public String getRoot() { return root; }
    public String getUrl() { return url; }
    public String getClave() { return clave; }
    public boolean isConectado() { return conectado; }

    // Setters públicos
    public void setRoot(String root) { this.root = root; }
    public void setUrl(String url) { this.url = url; }
    public void setClave(String clave) { this.clave = clave; }
    public void setConectado(boolean conectado) { this.conectado = conectado; }

    // ==========================================
    // GESTION DE FICHA TEMPORAL
    // ==========================================

    // Guarda un objeto en el indice correspondiente de la ficha temporal
    public void guardarEnFichaTemporal(int indice, Object dato) {
        this.fichaTemporal[indice] = dato;
    }

    // Retorna true si los 4 conjuntos estan completos
    public boolean validarFichaCompleta() {
        return fichaTemporal[0] != null && fichaTemporal[1] != null
            && fichaTemporal[2] != null && fichaTemporal[3] != null;
    }

    // Obtiene el objeto en el indice dado
    public Object getFichaTemporal(int indice) {
        return fichaTemporal[indice];
    }

    // Vacia la ficha para permitir un nuevo registro
    public void limpiarFicha() {
        for (int i = 0; i < 4; i++) {
            fichaTemporal[i] = null;
        }
    }

    // Validar conexión a BD simulada
    public boolean validar_conexion() {
        this.conectado = this.clave.equals("1234");
        return this.conectado;
    }

    // Cierra sesión abierta
    public boolean desconexion() {
        if (this.conectado) {
            this.conectado = false;
            return true;
        }
        return false;
    }

    // Requiere conexión para operar
    private void exigirConexion() {
        if (!this.conectado) {
            throw new IllegalStateException("No existe una conexión activa con la API.");
        }
    }

    // ==========================================
    // CRUD CHOFER (Dinámico - Sin foreach)
    // ==========================================
    public boolean registrar_chofer(chofer_modelo chofer) {
        exigirConexion();
        return lista_chofer.add(chofer);
    }

    public chofer_modelo buscar_chofer(String cedula) {
        exigirConexion();
        for (int i = 0; i < lista_chofer.size(); i++) {
            if (lista_chofer.get(i).getCedula_chofer().equals(cedula)) {
                return lista_chofer.get(i);
            }
        }
        return null;
    }

    public boolean actualizar_chofer(String cedulaVieja, chofer_modelo nuevoChofer) {
        exigirConexion();
        for (int i = 0; i < lista_chofer.size(); i++) {
            if (lista_chofer.get(i).getCedula_chofer().equals(cedulaVieja)) {
                lista_chofer.set(i, nuevoChofer);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar_chofer(String cedula) {
        exigirConexion();
        for (int i = 0; i < lista_chofer.size(); i++) {
            if (lista_chofer.get(i).getCedula_chofer().equals(cedula)) {
                lista_chofer.remove(i);
                return true;
            }
        }
        return false;
    }

    // ==========================================
    // CRUD PASAJERO (Dinámico - Sin foreach)
    // ==========================================
    public boolean ingresar_pasajero(Pasajero_modelo pasajero) {
        exigirConexion();
        return lista_pasajero.add(pasajero);
    }

    public Pasajero_modelo buscar_pasajero(String cedula) {
        exigirConexion();
        for (int i = 0; i < lista_pasajero.size(); i++) {
            if (lista_pasajero.get(i).getCedula_pasajero().equals(cedula)) {
                return lista_pasajero.get(i);
            }
        }
        return null;
    }

    public boolean actualizar_pasajero(String cedulaVieja, Pasajero_modelo nuevoPasajero) {
        exigirConexion();
        for (int i = 0; i < lista_pasajero.size(); i++) {
            if (lista_pasajero.get(i).getCedula_pasajero().equals(cedulaVieja)) {
                lista_pasajero.set(i, nuevoPasajero);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar_pasajero(String cedula) {
        exigirConexion();
        for (int i = 0; i < lista_pasajero.size(); i++) {
            if (lista_pasajero.get(i).getCedula_pasajero().equals(cedula)) {
                lista_pasajero.remove(i);
                return true;
            }
        }
        return false;
    }

    // ==========================================
    // CRUD CARRO (Estático - Matriz - Recorrido anidado)
    // ==========================================
    public boolean ingresar_carro(Carro_modelo carro) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            if (lista_carros[f][0] == null) { 
                for (int c = 0; c < 3; c++) {
                    if (c == 0) {
                        lista_carros[f][c] = carro.getPlaca_carro();
                    } else if (c == 1) {
                        lista_carros[f][c] = carro.getMarca_carro();
                    } else if (c == 2) {
                        lista_carros[f][c] = carro.getModelo_carro();
                    }
                }
                return true;
            }
        }
        return false; // Matriz llena
    }

    public Carro_modelo buscar_carro(String placa) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (c == 0 && lista_carros[f][c] != null && lista_carros[f][c].equals(placa)) {
                    return new Carro_modelo(lista_carros[f][0], lista_carros[f][1], lista_carros[f][2]); 
                }
            }
        }
        return null;
    }

    public boolean actualizar_carro(String placaVieja, Carro_modelo nuevoCarro) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (c == 0 && lista_carros[f][c] != null && lista_carros[f][c].equals(placaVieja)) {
                    for (int k = 0; k < 3; k++) {
                        if (k == 0) lista_carros[f][k] = nuevoCarro.getPlaca_carro();
                        if (k == 1) lista_carros[f][k] = nuevoCarro.getMarca_carro();
                        if (k == 2) lista_carros[f][k] = nuevoCarro.getModelo_carro();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminar_carro(String placa) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (c == 0 && lista_carros[f][c] != null && lista_carros[f][c].equals(placa)) {
                    for (int k = 0; k < 3; k++) {
                        lista_carros[f][k] = null;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // ==========================================
    // CRUD MOTOR (Estático - Matriz - Recorrido anidado)
    // ==========================================
    public boolean ingresar_motor(Motor_modelo motor) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            if (lista_motores[f][0] == null) {
                for (int c = 0; c < 3; c++) {
                    if (c == 0) {
                        lista_motores[f][c] = motor.getNumero_serie();
                    } else if (c == 1) {
                        lista_motores[f][c] = motor.getTipo_motor();
                    } else if (c == 2) {
                        lista_motores[f][c] = motor.getCaballos_fuerza();
                    }
                }
                return true;
            }
        }
        return false;
    }

    public Motor_modelo buscar_motor(String numeroSerie) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (c == 0 && lista_motores[f][c] != null && lista_motores[f][c].equals(numeroSerie)) {
                    return new Motor_modelo(lista_motores[f][1], lista_motores[f][2], lista_motores[f][0]); // Tipo, Caballos, Serie
                }
            }
        }
        return null;
    }

    public boolean actualizar_motor(String serieVieja, Motor_modelo nuevoMotor) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (c == 0 && lista_motores[f][c] != null && lista_motores[f][c].equals(serieVieja)) {
                    for (int k = 0; k < 3; k++) {
                        if (k == 0) lista_motores[f][k] = nuevoMotor.getNumero_serie();
                        if (k == 1) lista_motores[f][k] = nuevoMotor.getTipo_motor();
                        if (k == 2) lista_motores[f][k] = nuevoMotor.getCaballos_fuerza();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminar_motor(String numeroSerie) {
        exigirConexion();
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (c == 0 && lista_motores[f][c] != null && lista_motores[f][c].equals(numeroSerie)) {
                    for (int k = 0; k < 3; k++) {
                        lista_motores[f][k] = null;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
