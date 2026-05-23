package Modelos;

import java.util.ArrayList;

public class Restaurante implements Calculable {

    private String id;
    private String nombre;
    private ArrayList<Plato> platos;
    private ArrayList<Combo> combos;

    public Restaurante(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.platos = new ArrayList<>();
        this.combos = new ArrayList<>();
    }

    public Restaurante(String id, String nombre, ArrayList<Plato> platos) {
        this.id = id;
        this.nombre = nombre;
        this.platos = platos;
        this.combos = new ArrayList<>();
    }

    public Restaurante(String id, String nombre, ArrayList<Plato> platos, ArrayList<Combo> combos) {
        this.id = id;
        this.nombre = nombre;
        this.platos = platos;
        this.combos = combos;
    }

    public void agregarPlato(Plato p) {
        platos.add(p);
    }

    public void agregarCombo(Combo c) {
        combos.add(c);
    }

    public Combo crearCombo(String id, String nombre, double precio, ArrayList<Plato> platosDelCombo) {
        Combo nuevo = new Combo(id, nombre, precio, platosDelCombo);
        combos.add(nuevo);
        return nuevo;
    }

    public void mostrarPlatos() {
        for (int i = 0; i < platos.size(); i++) {
            Plato p = platos.get(i);
            System.out.println((i + 1) + ". " + p.getNombre() + " $" + p.getPrecio() + (p.isDisponible() ? " Disponible" : " No disponible"));
        }
        System.out.println("0. Para salir MENU RESTAURANTES");
        System.out.println("Que plato va a aniadir al pedido: ");
    }

    public void mostrarCombos() {
        if (combos.isEmpty()) {
            System.out.println("(este restaurante todavia no tiene combos)");
            return;
        }
        for (int i = 0; i < combos.size(); i++) {
            System.out.println((i + 1) + ". " + combos.get(i).descripcion());
        }
    }

    public Plato buscarPlatoPorId(String idPlato) {
        for (Plato p : platos) {
            if (p.getId().equals(idPlato)) {
                return p;
            }
        }
        return null;
    }

    public Combo buscarComboPorId(String idCombo) {
        for (Combo c : combos) {
            if (c.getId().equals(idCombo)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Ingrediente> getTodosLosIngredientes() {
        ArrayList<Ingrediente> todos = new ArrayList<>();
        for (Plato p : platos) {
            for (Ingrediente ing : p.getIngredientes()) {
                if (!todos.contains(ing)) {
                    todos.add(ing);
                }
            }
        }
        return todos;
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (Plato p : platos) {
            total = total + p.getPrecio();
        }
        return total;
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

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }

    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
    }
}
