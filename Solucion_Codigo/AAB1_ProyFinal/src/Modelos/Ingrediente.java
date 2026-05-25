package Modelos;

import java.io.Serializable;

public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int stockActual;

    public Ingrediente(String nombre, int stockActual) {
        this.nombre = nombre;
        this.stockActual = stockActual;
    }

    public boolean hayStock(int cantidad) {
        return stockActual >= cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad <= stockActual) {
            stockActual = stockActual - cantidad;
        }
    }

    public void registrarEntrada(int cantidad) {
        if (cantidad > 0) {
            stockActual = stockActual + cantidad;
        }
    }

    public String resumenInventario() {
        return nombre + " -> stock: " + stockActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }
}
