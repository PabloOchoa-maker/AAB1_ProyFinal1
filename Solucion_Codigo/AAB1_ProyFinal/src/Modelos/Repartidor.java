package Modelos;

import java.io.Serializable;

public class Repartidor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private boolean disponible;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.disponible = true;
    }

    public Repartidor(String nombre, boolean disponible) {
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public void asignarPedido() {
        this.disponible = false;
    }

    public void liberar() {
        this.disponible = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
