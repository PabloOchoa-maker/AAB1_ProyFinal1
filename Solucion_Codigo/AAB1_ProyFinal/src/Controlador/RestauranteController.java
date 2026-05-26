package Controlador;

import Modelos.Cliente;
import Modelos.Combo;
import Modelos.EstadoCargado;
import Modelos.Ingrediente;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.PersistenciaEstado;
import Modelos.Plato;
import Modelos.Repartidor;
import Modelos.Restaurante;
import java.util.ArrayList;

public class RestauranteController {

    private PatiodeComidas patio;
    private ArrayList<Cliente> clientes;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Pedido> pedidos;

    public RestauranteController(PatiodeComidas patio, ArrayList<Cliente> clientes, ArrayList<Repartidor> repartidores, ArrayList<Pedido> pedidos) {
        this.patio = patio;
        this.clientes = clientes;
        this.repartidores = repartidores;
        this.pedidos = pedidos;
    }

    public ArrayList<Restaurante> listar() {
        return patio.getRestaurantes();
    }

    public Restaurante obtenerPorIndice(int indice) {
        ArrayList<Restaurante> r = patio.getRestaurantes();
        if (indice < 0 || indice >= r.size()) {
            return null;
        }
        return r.get(indice);
    }

    public ArrayList<Plato> listarPlatosDe(int indiceRestaurante) {
        Restaurante r = obtenerPorIndice(indiceRestaurante);
        if (r == null) {
            return new ArrayList<>();
        }
        return r.getPlatos();
    }

    public ArrayList<Combo> listarCombosDe(int indiceRestaurante) {
        Restaurante r = obtenerPorIndice(indiceRestaurante);
        if (r == null) {
            return new ArrayList<>();
        }
        return r.getCombos();
    }

    public String getNombrePatio() {
        return patio.getNombre();
    }

    public boolean hayRestaurantes() {
        return !patio.getRestaurantes().isEmpty();
    }

    public String registrarRestaurante(String id, String nombre) {
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return "El id y el nombre son obligatorios.";
        }
        if (patio.buscarPorId(id.trim()) != null) {
            return "Ya existe un restaurante con el id " + id.trim() + ".";
        }
        patio.agregarRestaurante(new Restaurante(id.trim(), nombre.trim()));
        PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        return "Restaurante registrado correctamente.";
    }

    public String registrarPlato(int indiceRestaurante, String id, String nombre,
            double precio, boolean disponible) {
        return registrarPlato(indiceRestaurante, id, nombre, precio, disponible, new ArrayList<>());
    }

    public String registrarPlato(int indiceRestaurante, String id, String nombre,
            double precio, boolean disponible, ArrayList<Ingrediente> ingredientes) {
        Restaurante r = obtenerPorIndice(indiceRestaurante);
        if (r == null) {
            return "Restaurante invalido.";
        }
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return "El id y el nombre son obligatorios.";
        }
        if (r.buscarPlatoPorId(id.trim()) != null) {
            return "Ya existe un plato con el id " + id.trim() + " en este restaurante.";
        }
        if (precio <= 0) {
            return "El precio debe ser mayor que cero.";
        }
        ArrayList<Ingrediente> lista = ingredientes != null ? ingredientes : new ArrayList<>();
        r.agregarPlato(new Plato(id.trim(), nombre.trim(), precio, disponible, lista));
        PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        return "Plato registrado correctamente (" + lista.size() + " ingrediente(s)).";
    }

    public String registrarCombo(int indiceRestaurante, String id, String nombre,
            double precio, ArrayList<Integer> indicesPlatos) {
        Restaurante r = obtenerPorIndice(indiceRestaurante);
        if (r == null) {
            return "Restaurante invalido.";
        }
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return "El id y el nombre son obligatorios.";
        }
        if (r.buscarComboPorId(id.trim()) != null) {
            return "Ya existe un combo con el id " + id.trim() + ".";
        }
        if (precio <= 0) {
            return "El precio debe ser mayor que cero.";
        }
        if (indicesPlatos.size() < 2) {
            return "Un combo debe tener al menos 2 platos.";
        }
        ArrayList<Plato> platosCombo = new ArrayList<>();
        for (int idx : indicesPlatos) {
            Plato p = listarPlatosDe(indiceRestaurante).get(idx);
            platosCombo.add(p);
        }
        r.crearCombo(id.trim(), nombre.trim(), precio, platosCombo);
        PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        return "Combo registrado correctamente.";
    }
}
