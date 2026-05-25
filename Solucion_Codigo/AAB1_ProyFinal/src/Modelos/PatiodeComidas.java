package Modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class PatiodeComidas implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Restaurante> restaurantes;

    public PatiodeComidas(String nombre) {
        this.nombre = nombre;
        this.restaurantes = new ArrayList<>();
    }

    public PatiodeComidas(String nombre, ArrayList<Restaurante> restaurantes) {
        this.nombre = nombre;
        this.restaurantes = restaurantes;
    }

    public void agregarRestaurante(Restaurante r) {
        System.out.println("AGREGANDO " + r.getNombre() + " AL PATIO DE COMIDAS");
        System.out.println("");
        restaurantes.add(r);
    }

    public void mostrarRestaurantes() {
        for (int i = 0; i < restaurantes.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantes.get(i).getNombre());
        }
    }

    public Restaurante buscarPorId(String idRestaurante) {
        for (Restaurante r : restaurantes) {
            if (r.getId().equals(idRestaurante)) {
                return r;
            }
        }
        return null;
    }

    public Plato buscarPlatoPorId(String idPlato) {
        for (Restaurante r : restaurantes) {
            Plato p = r.buscarPlatoPorId(idPlato);
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    public Combo buscarComboPorId(String idCombo) {
        for (Restaurante r : restaurantes) {
            Combo c = r.buscarComboPorId(idCombo);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

    public Ingrediente buscarIngredientePorNombre(String nombreIngrediente) {
        for (Restaurante r : restaurantes) {
            for (Plato p : r.getPlatos()) {
                for (Ingrediente ing : p.getIngredientes()) {
                    if (ing.getNombre().equalsIgnoreCase(nombreIngrediente)) {
                        return ing;
                    }
                }
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public int getRestaurantesExistentes() {
        return restaurantes.size();
    }
}
