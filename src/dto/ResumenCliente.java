package dto;

public record ResumenCliente(String nombre, int numPedidos, double totalGastado) {
    @Override
    public String toString() {
        return "ResumenCliente{" +
                "nombre='" + nombre + '\'' +
                ", numPedidos=" + numPedidos +
                ", totalGastado=" + totalGastado +
                '}';
    }
}
