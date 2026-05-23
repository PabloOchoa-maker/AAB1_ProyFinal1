package Modelos;

public class Cliente {

    private String nombre;
    private String direccion;
    private double distanciaKm;

    public Cliente(String nombre, String direccion, double distanciaKm) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }
}
