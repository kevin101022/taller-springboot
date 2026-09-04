package model;

public class chofer_modelo {

    // Inicializar variables
    public String nombre_chofer = "";
    String apellido_chofer = "";
    String cedula_chofer = "";
    String licencia_chofer = "";

    // Constructor
    public chofer_modelo(String dato_nombre, String dato_apellido, String dato_cedula, String dato_licencia){
        this.nombre_chofer = validarNombre(dato_nombre);
        this.apellido_chofer = validarApellido(dato_apellido);
        this.cedula_chofer = validarCedula(dato_cedula);
        this.licencia_chofer = validarLicencia(dato_licencia);
    }

    // Reglas de negocio y validaciones
    public String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del chofer no puede estar vacío.");
        }
        return nombre.trim();
    }

    public String validarApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del chofer no puede estar vacío.");
        }
        return apellido.trim();
    }

    public String validarCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula del chofer no puede estar vacía.");
        }
        if (!cedula.trim().matches("\\d+")) {
            throw new IllegalArgumentException("La cédula del chofer debe contener solo números.");
        }
        return cedula.trim();
    }

    public String validarLicencia(String licencia) {
        if (licencia == null || licencia.trim().isEmpty()) {
            throw new IllegalArgumentException("La licencia del chofer no puede estar vacía.");
        }
        return licencia.trim();
    }

    // Getters
    public String getNombre_chofer() { return nombre_chofer; }
    public String getApellido_chofer() { return apellido_chofer; }
    public String getCedula_chofer() { return cedula_chofer; }
    public String getLicencia_chofer() { return licencia_chofer; }

    // Setters
    public void setNombre_chofer(String nombre_chofer) { this.nombre_chofer = validarNombre(nombre_chofer); }
    public void setApellido_chofer(String apellido_chofer) { this.apellido_chofer = validarApellido(apellido_chofer); }
    public void setCedula_chofer(String cedula_chofer) { this.cedula_chofer = validarCedula(cedula_chofer); }
    public void setLicencia_chofer(String licencia_chofer) { this.licencia_chofer = validarLicencia(licencia_chofer); }
}
