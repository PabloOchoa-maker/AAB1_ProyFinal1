package Modelos;

import java.util.ArrayList;

public class EstadoCargado {

    private PatiodeComidas patio;
    private ArrayList<Cliente> clientes;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Pedido> pedidos;

    public EstadoCargado(PatiodeComidas patio, ArrayList<Cliente> clientes, ArrayList<Repartidor> repartidores, ArrayList<Pedido> pedidos) {
        this.patio = patio;
        this.clientes = clientes;
        this.repartidores = repartidores;
        this.pedidos = pedidos;
    }

    public boolean estaVacio() {
        return patio == null || patio.getRestaurantes().isEmpty();
    }

    public PatiodeComidas getPatio() {
        return patio;
    }

    public void setPatio(PatiodeComidas patio) {
        this.patio = patio;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Repartidor> getRepartidores() {
        return repartidores;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}
