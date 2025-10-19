package dao;

import javax.sql.DataSource;
import java.sql.*;

public class EmpleadoDao {

    public int insertarEmpleado(String dni, String nombre, String email, String departamento, Connection connection) throws SQLException {

        String query = "INSERT INTO empleados(dni, nombre, email, departamento, fecha_alta) VALUES(?,?,?,?,NOW())";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, email);
            ps.setString(4, departamento);

            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idEmpleado =  rs.getInt(1);
                    System.out.println("Empleado creado con ID: " + idEmpleado);
                    return idEmpleado;

                }else{
                    throw new SQLException("No se generó el ID del empleado");
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al crear el empleado");
        }

    }
}

