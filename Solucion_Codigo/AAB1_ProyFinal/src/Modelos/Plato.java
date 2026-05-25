package Modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Plato implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private double precio;
    private boolean disponible;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String id, String nombre, double precio, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.ingredientes = new ArrayList<>();
    }

    public Plato(String id, String nombre, double precio, boolean disponible, ArrayList<Ingrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.ingredientes = ingredientes;
    }

    public void agregarIngrediente(Ingrediente ing) {
        ingredientes.add(ing);
    }

    public boolean verificarStock(int cantidadPlatos) {
        for (Ingrediente ing : ingredientes) {
            if (!ing.hayStock(cantidadPlatos)) {
                return false;
            }
        }
        return true;
    }

    public void descontarStockPor(int cantidadPlatos) {
        for (Ingrediente ing : ingredientes) {
            ing.reducirStock(cantidadPlatos);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
