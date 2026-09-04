package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pasajero_modelo;

// DAO para la tabla pasajeros
public class Pasajero_DAO {

    // INSERT standalone
    public int insertarPasajero(Pasajero_modelo pasajero) throws SQLException {
        Conexion_db objConexion = new Conexion_db();
        Connection con = objConexion.hacerConexion();
        return insertarPasajero(con, pasajero);
    }

    // INSERT con conexion compartida (para transacciones)
    public int insertarPasajero(Connection con, Pasajero_modelo pasajero) throws SQLException {
        String sql = "INSERT INTO pasajeros (cedula, nombre, apellido) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, pasajero.getCedula_pasajero());
        ps.setString(2, pasajero.getNombre_pasajero());
        ps.setString(3, pasajero.getApellido_pasajero());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            return keys.getInt(1);
        }
        return -1;
    }
}
