package Controlador;

import Modelos.Cliente;
import Modelos.EstadoCargado;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.PersistenciaEstado;
import Modelos.Repartidor;
import java.util.ArrayList;

public class RepartidorController {

    private ArrayList<Repartidor> repartidores;
    private PatiodeComidas patio;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public RepartidorController(ArrayList<Repartidor> repartidores, PatiodeComidas patio, ArrayList<Cliente> clientes, ArrayList<Pedido> pedidos) {
        this.repartidores = repartidores;
        this.patio = patio;
        this.clientes = clientes;
        this.pedidos = pedidos;
    }

    public Repartidor registrar(String nombre) {
        Repartidor r = new Repartidor(nombre);
        repartidores.add(r);
        PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        return r;
    }

    public ArrayList<Repartidor> listar() {
        return repartidores;
    }

    public Repartidor obtenerDisponible() {
        for (Repartidor r : repartidores) {
            if (r.isDisponible()) {
                return r;
            }
        }
        return null;
    }

    public Repartidor obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= repartidores.size()) {
            return null;
        }
        return repartidores.get(indice);
    }

    public void marcarOcupado(Repartidor r) {
        if (r != null) {
            r.asignarPedido();
            PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        }
    }

    public void marcarLibre(Repartidor r) {
        if (r != null) {
            r.liberar();
            PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        }
    }

    public boolean hayRepartidores() {
        return !repartidores.isEmpty();
    }
}
