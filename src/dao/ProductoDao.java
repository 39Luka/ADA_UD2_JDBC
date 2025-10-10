package dao;

import dto.ProductoStock;
import model.Producto;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoDao {

    DataSource ds;

    public ProductoDao(){
        ds = DsProvider.getDataSource();
    }



    public Producto buscarPorSku(String sku){
        String query = "SELECT * from productos where sku = ?";
        try(Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, sku);
            try(ResultSet rs = ps.executeQuery()){
                    Producto producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("sku"),
                            rs.getString("nombre"),
                            rs.getInt("stock"),
                            rs.getDouble("precio"),
                            rs.getBoolean("activo"),
                            rs.getDate("created_at").toLocalDate()
                    );
                    return producto;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductoStock> ConsultarStock(){
        String query = "SELECT nombre, stock from productos";
        List<ProductoStock> productoStocks = new ArrayList<>();
        try(Connection con = ds.getConnection();
        Statement s = con.createStatement()){

            try (ResultSet rs = s.executeQuery(query) ){
                while (rs.next()){
                    productoStocks.add(new ProductoStock(
                            rs.getString("nombre"),
                            rs.getInt("stock")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productoStocks;
    }

    public void crearProducto(String sku, String nombre, double precio){
        String query = "{ call sp_crear_producto(?,?,?,?) }";
        try(Connection con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query)){
            cs.setString(1, sku);
            cs.setString(2,nombre);
            cs.setDouble(3,precio);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            System.out.println("Id Producto creado: "+ cs.getInt(4));
        } catch (SQLException e) {
            if("45000".equals(e.getSQLState())){
                System.out.println("Error: "+ e.getMessage());
            }else{
                System.out.println("Error: Otro tipo de error SQL");
            }
        }
    }
}
