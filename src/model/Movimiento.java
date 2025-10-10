package model;

import java.time.LocalDate;

public class Movimiento {
    int id;
    int productoId;
    Tipo tipo;
    int cantidad;
    String motivo;
    LocalDate fecha;

}

enum Tipo {
    ENTRADA, SALIDA, AJUSTE
}
