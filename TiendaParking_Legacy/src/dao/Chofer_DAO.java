package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.chofer_modelo;

// DAO para la tabla choferes
public class Chofer_DAO {

    // INSERT standalone
    public int insertarChofer(chofer_modelo chofer) throws SQLException {
        Conexion_db objConexion = new Conexion_db();
        Connection con = objConexion.hacerConexion();
        return insertarChofer(con, chofer);
    }

    // INSERT con conexion compartida (para transacciones)
    public int insertarChofer(Connection con, chofer_modelo chofer) throws SQLException {
        String sql = "INSERT INTO choferes (cedula, nombre, apellido, licencia) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, chofer.getCedula_chofer());
        ps.setString(2, chofer.getNombre_chofer());
        ps.setString(3, chofer.getApellido_chofer());
        ps.setString(4, chofer.getLicencia_chofer());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            return keys.getInt(1);
        }
        return -1;
    }
}
