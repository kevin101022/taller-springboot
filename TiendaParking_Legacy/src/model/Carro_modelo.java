package model;

public class Carro_modelo {
    //Inicializar variables
    public String placa_carro = "";
    String modelo_carro = "";
    String marca_carro = "";

    //Constructor
    public Carro_modelo(String dato_placa, String dato_marca, String dato_modelo){
        this.placa_carro = validarPlaca(dato_placa);
        this.marca_carro = validarMarca(dato_marca);
        this.modelo_carro = validarModelo(dato_modelo);
    }

    // Reglas de negocio y validaciones
    public String validarPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa del carro no puede estar vacía.");
        }
        if (placa.trim().length() < 3) {
            throw new IllegalArgumentException("La placa del carro debe tener al menos 3 caracteres.");
        }
        return placa.trim();
    }

    public String validarModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo del carro no puede estar vacío.");
        }
        return modelo.trim();
    }

    public String validarMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca del carro no puede estar vacía.");
        }
        return marca.trim();
    }

    //Getters
    public String getPlaca_carro() { return placa_carro; }
    public String getModelo_carro() { return modelo_carro; }
    public String getMarca_carro() { return marca_carro; }

    //Setters
    public void setPlaca_carro(String placa_carro) { this.placa_carro = validarPlaca(placa_carro); }
    public void setModelo_carro(String modelo_carro) { this.modelo_carro = validarModelo(modelo_carro); }
    public void setMarca_carro(String marca_carro) { this.marca_carro = validarMarca(marca_carro); }
}
