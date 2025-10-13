package dao;

import model.Auditoria;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuditoriaDao {

    DataSource ds;

    public AuditoriaDao(){
        ds = DsProvider.getDataSource();
    }

    public void insertarAuditorias(Auditoria[] auditorias){
        String query = "INSERT INTO auditoria (evento, detalle, ts) VALUES (?, ?, NOW())";

            try (Connection con = ds.getConnection();
                 PreparedStatement ps = con.prepareStatement(query)) {
                for (Auditoria a : auditorias) {

                    System.out.println(con.getAutoCommit());

                    ps.setString(1, a.getEvento());
                    ps.setString(2, a.getDetalle());

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


    }

    public void insertarAuditoriasSinAutoCommit(Auditoria[] auditorias){
        String query = "INSERT INTO auditoria (evento, detalle, ts) VALUES (?, ?, NOW())";

        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            try {
            for (Auditoria a : auditorias) {

                System.out.println(con.getAutoCommit());

                ps.setString(1, a.getEvento());
                ps.setString(2, a.getDetalle());

                ps.executeUpdate();
            }
            con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new SQLException("Error al insertar la auditoria" + e.getMessage());
            }finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
