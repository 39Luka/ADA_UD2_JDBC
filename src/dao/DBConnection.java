package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final LecturaPropiedades lp;

    DBConnection() throws IOException {
        lp = new LecturaPropiedades();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(lp.getUrl(), lp.getUser(), lp.getPassword());
    }


}
