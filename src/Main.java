import dao.ClienteDao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {


    public static void main(String[] args) {

        try {

            // Testeo del metodo updateClienteName
            /*
            String newName = "Silvia";
            ClienteDao clienteDao = new ClienteDao();

            System.out.println(clienteDao.updateClientName(1,newName)); //Debe devolver true en la consola
            */

            //Testeo del metodo getPedidosByCliente
       /*     ClienteDao clienteDao = new ClienteDao();
            System.out.println(clienteDao.getPedidosByCliente(1));*/


            //Testeo de metodo deleteClienteYPedidos
           /* ClienteDao clienteDao = new ClienteDao();
            System.out.println(clienteDao.deleteClienteYPedidos(1));*/

            //Testeo del metodo totalPedidosPorCliente
           /* ClienteDao clienteDao = new ClienteDao();
            LocalDate desde = LocalDate.of(2014, 1, 1);
            LocalDate hasta = LocalDate.of(2017, 12, 31);

            System.out.println(clienteDao.totalPedidosPorCliente(Date.valueOf(desde),Date.valueOf(hasta)));*/

            //Testeo de metodo generarIndiceCompuesto

            ClienteDao clienteDao = new ClienteDao();
            clienteDao.generarIndiceCompuesto();

        } catch (IOException e) {
            System.err.println("Error: Problemas de la entrada o salida");
        } catch (SQLException e) {
            System.out.println("Error: Problemas con las consultas SQL");
        }


    }
}