package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;

public final class DsProvider {

    private static final HikariDataSource ds;
    private static final LecturaPropiedades lp;

    static {
        try {
            lp = new LecturaPropiedades();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(lp.getUrl());
        cfg.setUsername(lp.getUser());
        cfg.setPassword(lp.getPassword());

        // Configuración del pool
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(2);
        cfg.setConnectionTimeout(10000); // ms
        cfg.setIdleTimeout(600000);      // ms
        cfg.setMaxLifetime(1800000);     // ms

        ds = new HikariDataSource(cfg);
    }

    private DsProvider() {
    }

    public static DataSource getDataSource() { return ds; }
}



