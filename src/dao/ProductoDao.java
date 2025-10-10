package dao;

import javax.sql.DataSource;

public class ProductoDao {

    DataSource ds;

    public ProductoDao(){
        ds = DsProvider.getDataSource();
    }


}
