package Controlador;

import Modelos.Cliente;
import Modelos.EscrituraInformacion;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.Repartidor;
import java.util.ArrayList;

public class ClienteController {

    private ArrayList<Cliente> clientes;
    private PatiodeComidas patio;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Pedido> pedidos;

    public ClienteController(ArrayList<Cliente> clientes, PatiodeComidas patio, ArrayList<Repartidor> repartidores, ArrayList<Pedido> pedidos) {
        this.clientes = clientes;
        this.patio = patio;
        this.repartidores = repartidores;
        this.pedidos = pedidos;
    }

    public Cliente registrar(String nombre, String direccion, double distanciaKm) {
        Cliente c = new Cliente(nombre, direccion, distanciaKm);
        clientes.add(c);
        EscrituraInformacion.guardarTodo(patio, clientes, repartidores, pedidos);
        return c;
    }

    public ArrayList<Cliente> listar() {
        return clientes;
    }

    public Cliente buscarPorNombre(String nombre) {
        for (Cliente c : clientes) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public Cliente obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= clientes.size()) {
            return null;
        }
        return clientes.get(indice);
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }
}
