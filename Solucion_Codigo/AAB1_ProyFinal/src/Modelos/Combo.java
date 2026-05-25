package Modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private double precio;
    private ArrayList<Plato> platos;

    public Combo(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.platos = new ArrayList<>();
    }

    public Combo(String id, String nombre, double precio, ArrayList<Plato> platos) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.platos = platos;
    }

    public void agregarPlato(Plato p) {
        platos.add(p);
    }

    public double getAhorroVsPlatosSueltos() {
        double sumaPlatos = 0;
        for (Plato p : platos) {
            sumaPlatos = sumaPlatos + p.getPrecio();
        }
        return sumaPlatos - precio;
    }

    public String descripcion() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" $").append(precio).append(" -> [");
        for (int i = 0; i < platos.size(); i++) {
            sb.append(platos.get(i).getNombre());
            if (i < platos.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
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

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }
}
