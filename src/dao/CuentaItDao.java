package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuentaItDao {

    public void crearCuentaIt(String dni, String nombre, int empleadoId, Connection connection) throws SQLException {
        String query = "INSERT INTO cuentas_it(username, empleados_id) VALUES(?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,dni+nombre);
            ps.setInt(2, empleadoId);

            ps.execute();
        } catch (SQLException e) {
            throw new SQLException("Error al crear la cuenta IT");
        }

    }
}
