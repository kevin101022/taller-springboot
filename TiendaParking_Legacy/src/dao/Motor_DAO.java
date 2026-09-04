package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Motor_modelo;

// DAO para la tabla motores
public class Motor_DAO {

    // INSERT standalone
    public int insertarMotor(Motor_modelo motor) throws SQLException {
        Conexion_db objConexion = new Conexion_db();
        Connection con = objConexion.hacerConexion();
        return insertarMotor(con, motor);
    }

    // INSERT con conexion compartida (para transacciones)
    public int insertarMotor(Connection con, Motor_modelo motor) throws SQLException {
        String sql = "INSERT INTO motores (numero_serie, tipo, caballos_fuerza) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, motor.getNumero_serie());
        ps.setString(2, motor.getTipo_motor());
        ps.setString(3, motor.getCaballos_fuerza());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            return keys.getInt(1);
        }
        return -1;
    }
}
