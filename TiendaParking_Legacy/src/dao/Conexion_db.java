package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_db {

    private String url;
    private String usuario;
    private String clave;
    private Connection conex;

    public Conexion_db() {
        url     = "jdbc:mysql://localhost:3306/tiendaparking?useSSL=false&serverTimezone=UTC";
        usuario = "root";
        clave   = "";
    }

    // Retorna conexion activa
    public Connection hacerConexion() throws SQLException {
        try {
            // Forzar carga del driver por si el IDE no lo auto-descubre
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("El Driver JDBC de MySQL no se encontro en el classpath. Verifica si el conector (.jar) esta agregado al proyecto.", e);
        }
        conex = DriverManager.getConnection(url, usuario, clave);
        return conex;
    }
}
