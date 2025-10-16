package service;

import dao.*;
import model.Empleado;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Service {

    DataSource ds = DsProvider.getDataSource();

    public void onboarding(Empleado empleado, String etiquetaDispositivo){
        try(Connection connection = ds.getConnection()){
            try {
                connection.setAutoCommit(false);
                EmpleadoDao empleadoDao = new EmpleadoDao();
                int idEmpleado = empleadoDao.insertarEmpleado(empleado.getDni(), empleado.getNombre(), empleado.getEmail(), empleado.getDepartamento(), connection);

                CuentaItDao cuentaItDao = new CuentaItDao();
                cuentaItDao.crearCuentaIt(empleado.getDni(), empleado.getNombre(), idEmpleado,connection);

                DispositivoDao dispositivoDao = new DispositivoDao();
                int idDispositivo = dispositivoDao.comprobarDisponibilidadDelDispositivo(etiquetaDispositivo,connection);
                boolean disponibilidadDispositivo = idDispositivo != -1;

                if(disponibilidadDispositivo){
                    dispositivoDao.cambiarEstadoDispositivoAAsignado(etiquetaDispositivo,connection);

                    AsignacionesDao asignacionesDao = new AsignacionesDao();
                    asignacionesDao.generarUnaAsignacion(idEmpleado, idDispositivo, connection);
                }else{
                    throw new Exception("El dispositivo seleccionado no esta disponible");
                }


                connection.commit();
            }catch (Exception e){
                System.err.println(e.getMessage());
                connection.rollback();
            }finally {
                connection.setAutoCommit(true);
            }



        } catch (SQLException e) {
            System.err.println("Error con la conexión a la base de datos");
        }


    }
}
