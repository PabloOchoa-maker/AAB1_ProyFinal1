package Modelos;

public class Plato {

    public static final int MAX_INGREDIENTES = 10;

    private String id;
    private String nombre;
    private double precio;
    private boolean disponible;
    private Ingrediente[] ingredientes;

    public Plato(String id, String nombre, double precio, boolean disponible, Ingrediente[] ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.ingredientes = ingredientes;
    }
//FALTA
    public boolean verificarStock() {
        return false;
    }
//    .....

    public double getPrecio() {
        return precio;
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

    public boolean isDisponible() {
        return disponible;
    }

    public Ingrediente[] getIngredientes() {
        return ingredientes;
    }

}
