import dao.AuditoriaDao;
import dao.ClienteDao;
import model.Auditoria;

import java.io.IOException;
import java.sql.SQLException;

public class Main3 {


    public static void main(String[] args) {


            AuditoriaDao auditoriaDao = new AuditoriaDao();

            Auditoria a1 = new Auditoria("Auditoria 1", "Auditoria 1");
            Auditoria a2 = new Auditoria(null, "Auditoria 2");

            Auditoria[] auditorias = {a1, a2};

            auditoriaDao.insertarAuditoriasSinAutoCommit(auditorias);
        }

}