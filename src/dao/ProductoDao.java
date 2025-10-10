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

    public void actualizaStock(String sku, int stock){
        String query = "{ call sp_set_stock(?,?,?) }";
        try(Connection con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query)){

            cs.setString(1, sku);
            cs.setInt(2, stock);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();
            System.out.println("Nuevo stock: " + cs.getInt(3));

        } catch (SQLException e) {
            if ("45000".equals(e.getSQLState())){
            System.out.println("Error: "+e.getMessage());}
            else{
                System.out.println("Error: Otro tipo de error SQL");

            }
        }

    }

    public void obtenerStock(String sku){
        String query = "{ ? = call fn_stock_actual(?) }";
        try(Connection con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query)){

            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, sku);

            cs.execute();
            System.out.println("Stock: " + cs.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
