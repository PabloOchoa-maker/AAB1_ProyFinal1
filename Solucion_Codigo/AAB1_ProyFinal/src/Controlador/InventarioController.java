package Controlador;

import Modelos.Cliente;
import Modelos.EscrituraInformacion;
import Modelos.Ingrediente;
import Modelos.LineaPedido;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.Plato;
import Modelos.Repartidor;
import Modelos.Restaurante;
import java.util.ArrayList;

public class InventarioController {

    private PatiodeComidas patio;
    private ArrayList<Cliente> clientes;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Pedido> pedidos;

    public InventarioController(PatiodeComidas patio, ArrayList<Cliente> clientes, ArrayList<Repartidor> repartidores, ArrayList<Pedido> pedidos) {
        this.patio = patio;
        this.clientes = clientes;
        this.repartidores = repartidores;
        this.pedidos = pedidos;
    }

    public boolean registrarEntrada(String nombreIngrediente, int cantidad) {
        Ingrediente ing = patio.buscarIngredientePorNombre(nombreIngrediente);
        if (ing == null) {
            return false;
        }
        ing.registrarEntrada(cantidad);
        EscrituraInformacion.guardarTodo(patio, clientes, repartidores, pedidos);
        return true;
    }

    public ArrayList<String> resumenPorRestaurante(int indiceRestaurante) {
        ArrayList<String> resumen = new ArrayList<>();
        ArrayList<Restaurante> rests = patio.getRestaurantes();
        if (indiceRestaurante < 0 || indiceRestaurante >= rests.size()) {
            return resumen;
        }
        Restaurante r = rests.get(indiceRestaurante);
        for (Plato p : r.getPlatos()) {
            if (!p.getIngredientes().isEmpty()) {
                resumen.add("[Plato: " + p.getNombre() + "]");
                for (Ingrediente ing : p.getIngredientes()) {
                    resumen.add("  " + ing.resumenInventario());
                }
            }
        }
        return resumen;
    }

    public boolean registrarEntradaDirecta(Ingrediente ing, int cantidad) {
        if (ing == null || cantidad <= 0) {
            return false;
        }
        ing.registrarEntrada(cantidad);
        EscrituraInformacion.guardarTodo(patio, clientes, repartidores, pedidos);
        return true;
    }

    public boolean hayStockPara(ArrayList<LineaPedido> lineas) {
        for (LineaPedido l : lineas) {
            Plato p = l.getPlato();
            if (p == null || !p.verificarStock(l.getCantidad())) {
                return false;
            }
        }
        return true;
    }

    public void descontarStockPorPedido(ArrayList<LineaPedido> lineas) {
        for (LineaPedido l : lineas) {
            Plato p = l.getPlato();
            if (p != null) {
                p.descontarStockPor(l.getCantidad());
            }
        }
        EscrituraInformacion.guardarTodo(patio, clientes, repartidores, pedidos);
    }
}
