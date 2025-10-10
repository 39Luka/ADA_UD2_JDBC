package model;

import java.time.LocalDate;

public class Producto {
    int id;
    String sku;
    String nombre;
    int stock;
    double precio;
    boolean activo;
    LocalDate createdAt;

    public Producto(int id, String sku, String nombre, int stock, double precio, boolean activo, LocalDate createdAt) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.activo = activo;
        this.createdAt = createdAt;
    }
}
