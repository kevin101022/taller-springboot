package model;

public class Motor_modelo {
    //Inicializar variables
    public String tipo_motor = "";
    String caballos_fuerza = "";
    String numero_serie = "";

    //Constructor
    public Motor_modelo(String dato_tipo_motor, String dato_caballos_fuerza, String dato_numero_serie){
        this.tipo_motor = validarTipoMotor(dato_tipo_motor);
        this.caballos_fuerza = validarCaballosFuerza(dato_caballos_fuerza);
        this.numero_serie = validarNumeroSerie(dato_numero_serie);
    }

    // Reglas de negocio y validaciones
    public String validarTipoMotor(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de motor no puede estar vacío.");
        }
        return tipo.trim();
    }

    public String validarCaballosFuerza(String hp) {
        if (hp == null || hp.trim().isEmpty()) {
            throw new IllegalArgumentException("Los caballos de fuerza no pueden estar vacíos.");
        }
        try {
            double valor = Double.parseDouble(hp.trim());
            if (valor <= 0) {
                throw new IllegalArgumentException("Los caballos de fuerza deben ser un número mayor a cero.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los caballos de fuerza deben ser un valor numérico válido.");
        }
        return hp.trim();
    }

    public String validarNumeroSerie(String serie) {
        if (serie == null || serie.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de serie del motor no puede estar vacío.");
        }
        return serie.trim();
    }

    //Getters
    public String getTipo_motor() { return tipo_motor; }
    public String getCaballos_fuerza() { return caballos_fuerza; }
    public String getNumero_serie() { return numero_serie; }

    //Setters
    public void setTipo_motor(String tipo_motor) { this.tipo_motor = validarTipoMotor(tipo_motor); }
    public void setCaballos_fuerza(String caballos_fuerza) { this.caballos_fuerza = validarCaballosFuerza(caballos_fuerza); }
    public void setNumero_serie(String numero_serie) { this.numero_serie = validarNumeroSerie(numero_serie); }
}
