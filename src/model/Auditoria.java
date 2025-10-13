package model;

import java.time.LocalDateTime;

public class Auditoria {
    private String evento;
    private String detalle;
    private LocalDateTime ps;

    public Auditoria(String evento, String detalle) {
        this.evento = evento;
        this.detalle = detalle;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getPs() {
        return ps;
    }

    public void setPs(LocalDateTime ps) {
        this.ps = ps;
    }
}
