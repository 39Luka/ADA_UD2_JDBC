package model;

import java.time.LocalDateTime;

public class Movimiento {
    int id;
    int productoId;
    Tipo tipo;
    int cantidad;
    String motivo;
    LocalDateTime fecha;

}

enum Tipo {
    ENTRADA, SALIDA, AJUSTE
}
