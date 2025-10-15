package service;

import dao.DsProvider;
import dao.EmpleadoDao;
import model.Empleado;

import javax.sql.DataSource;

public class Service {

    DataSource ds = DsProvider.getDataSource();

    public void onboarding(Empleado empleado, String etiquetaDispositivo){


    }
}
