package dao;

import javax.sql.DataSource;

public class EmpleadoDao {
    DataSource ds;

    EmpleadoDao(){
        ds = DsProvider.getDataSource();
    }

    public void insertarEmpleado(String dni, String nombre, String email, String departamento){

    }
}
