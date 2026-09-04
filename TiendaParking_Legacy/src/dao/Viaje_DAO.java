package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Carro_modelo;
import model.Motor_modelo;
import model.chofer_modelo;
import model.Pasajero_modelo;

// DAO de transaccion atomica: inserta los 4 objetos y el viaje maestro en un solo commit
public class Viaje_DAO {

    public String insertarViaje(Carro_modelo carro, Motor_modelo motor,
                                chofer_modelo chofer, Pasajero_modelo pasajero) {
        Conexion_db objConexion = new Conexion_db();
        Connection con = null;

        try {
            con = objConexion.hacerConexion();
            con.setAutoCommit(false); // Inicio de transaccion

            // 1. Insertar carro
            Carro_DAO carroDAO = new Carro_DAO();
            int id_carro = carroDAO.insertarCarro(con, carro);

            // 2. Insertar motor
            Motor_DAO motorDAO = new Motor_DAO();
            int id_motor = motorDAO.insertarMotor(con, motor);

            // 3. Insertar chofer
            Chofer_DAO choferDAO = new Chofer_DAO();
            int id_chofer = choferDAO.insertarChofer(con, chofer);

            // 4. Insertar pasajero
            Pasajero_DAO pasajeroDAO = new Pasajero_DAO();
            int id_pasajero = pasajeroDAO.insertarPasajero(con, pasajero);

            // 5. Insertar registro maestro del viaje
            String sqlViaje = "INSERT INTO viajes (id_carro, id_motor, id_chofer, id_pasajero) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sqlViaje);
            ps.setInt(1, id_carro);
            ps.setInt(2, id_motor);
            ps.setInt(3, id_chofer);
            ps.setInt(4, id_pasajero);
            ps.executeUpdate();

            con.commit(); // Confirmar todo
            return "Registro insertado en BD con exito";

        } catch (SQLException e) {
            // Rollback total si cualquier insercion falla
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { /* rollback fallido, se ignora */ }
            }
            // El error se retorna como String; la vista es responsable de mostrarlo
            return "Error al insertar en BD: " + e.getMessage();
        }
    }
}
