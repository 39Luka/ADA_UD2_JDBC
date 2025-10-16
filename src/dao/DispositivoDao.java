package dao;

import model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DispositivoDao {



    public int comprobarDisponibilidadDelDispositivo(String etiqueta, Connection connection){
        String query = "SELECT * FROM dispositivos where etiqueta = ? AND estado = ? ";

        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, etiqueta);
            ps.setString(2, Estado.DISPONIBLE.name());

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        return rs.getInt(1);
                    }
                }

        } catch (SQLException e) {
            System.err.println("Error al comprobar disponibilidad del dispositivo");
        }
        return -1;
    }

    public int cambiarEstadoDispositivoAAsignado(String etiqueta, Connection connection){
        String query = "UPDATE dispositivos SET estado = ? where etiqueta = ? AND estado = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, Estado.ASIGNADO.name());
            ps.setString(2, etiqueta);
            ps.setString(3, Estado.DISPONIBLE.name());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado del dispositivo a asignado");
        }

        return 0;

    }
}
