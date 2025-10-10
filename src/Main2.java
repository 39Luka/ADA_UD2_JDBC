import dao.ClienteDao;
import dao.ProductoDao;
import model.Cliente;

import java.io.IOException;

public class Main2 {
    public static void main(String[] args) {
            ProductoDao productoDao = new ProductoDao();

            //productoDao.crearProducto("AAA-001", "Manzana", 1.5);
            //productoDao.actualizaStock("AAA-001", 8);
            //productoDao.actualizaStock("AAA-002", 8);
        productoDao.obtenerStock("AAA-001");
        productoDao.obtenerStock("AAA-002");





    }
}
