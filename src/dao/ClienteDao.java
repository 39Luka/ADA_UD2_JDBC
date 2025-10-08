package dao;


import com.zaxxer.hikari.HikariDataSource;
import dto.ResumenCliente;
import model.Pedido;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDao {

    DataSource ds;

    public ClienteDao() throws IOException {
        ds = DsProvider.getDataSource();
    }

    public boolean updateClientName(int id, String newName) throws SQLException {
        String query = "UPDATE cliente SET nombre = ? WHERE id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, newName);
            ps.setInt(2, id);

            int update = ps.executeUpdate();
            return update == 1;
        }
    }

    public List<Pedido> getPedidosByCliente(int clienteId) throws SQLException {
        String query = "SELECT * FROM pedido WHERE cliente_Id = ? ORDER BY fecha ASC, id ASC";
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(new Pedido(
                            rs.getInt("id"),
                            rs.getInt("cliente_Id"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getDouble("total")
                    ));
                }
            }
        }

        return pedidos;
    }

    public int deleteClienteYPedidos(int clienteId) throws SQLException {
        String query = "DELETE FROM cliente WHERE id = ?";
        try(Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(query)){

            ps.setInt(1, clienteId);

            return ps.executeUpdate();
        }
    }


    public List<ResumenCliente> totalPedidosPorCliente(Date desde, Date hasta) throws SQLException {
        String query = "SELECT c.nombre, COUNT(p.id) AS numPedido, COALESCE(SUM(p.total), 0) AS totalGastado " +
                "FROM cliente c " +
                "JOIN pedido p ON c.id = p.cliente_id " +
                "WHERE p.fecha >= ? AND p.fecha <= ? " +
                "GROUP BY c.nombre " +
                "ORDER BY totalGastado DESC, nombre ASC";

        List<ResumenCliente> resumenClientes = new ArrayList<>();
        try (Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(query)){

            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));

            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    resumenClientes.add(new ResumenCliente(
                            rs.getString("nombre"),
                            rs.getInt("numPedido"),
                            rs.getDouble("totalGastado")
                            ));
                }
            }
        }

        return resumenClientes;
    }
}
