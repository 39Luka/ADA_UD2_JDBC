package dao;

import javax.sql.DataSource;
import java.sql.*;

public class EmpleadoDao {

    public int insertarEmpleado(String dni, String nombre, String email, String departamento, Connection connection) throws SQLException {

        String query = "INSERT INTO empleados(dni, nombre, email, departamento) VALUES(?,?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, email);
            ps.setString(4, departamento);

            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new SQLException("Error al crear el empleado");
        }
    }
}
