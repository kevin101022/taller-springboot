package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Carro_modelo;

// DAO para la tabla carros
public class Carro_DAO {

    // INSERT standalone
    public int insertarCarro(Carro_modelo carro) throws SQLException {
        Conexion_db objConexion = new Conexion_db();
        Connection con = objConexion.hacerConexion();
        return insertarCarro(con, carro);
    }

    // INSERT con conexion compartida (para transacciones)
    public int insertarCarro(Connection con, Carro_modelo carro) throws SQLException {
        String sql = "INSERT INTO carros (placa, marca, modelo) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, carro.getPlaca_carro());
        ps.setString(2, carro.getMarca_carro());
        ps.setString(3, carro.getModelo_carro());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            return keys.getInt(1);
        }
        return -1;
    }
}
