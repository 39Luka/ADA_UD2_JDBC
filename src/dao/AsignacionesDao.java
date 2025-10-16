package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AsignacionesDao {

    public void generarUnaAsignacion(int empleadoId, int dispositivoId, Connection connection){
        String query = "INSERT INTO asignaciones(empleado_id, dispositivo_id, observaciones) VALUES(?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, empleadoId);
            ps.setInt(2, dispositivoId);
            ps.setString(3, "Onboarding OK");

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al generar la asignación");
        }

    }
}
