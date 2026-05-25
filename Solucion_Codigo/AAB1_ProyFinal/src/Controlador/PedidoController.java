package Controlador;

import Modelos.Cliente;
import Modelos.EstadoCargado;
import Modelos.LineaPedido;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.PersistenciaEstado;
import Modelos.Repartidor;
import java.util.ArrayList;

public class PedidoController {

    private ArrayList<Pedido> pedidos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Repartidor> repartidores;
    private PatiodeComidas patio;
    private InventarioController inventarioCtrl;

    public PedidoController(ArrayList<Pedido> pedidos, ArrayList<Cliente> clientes, ArrayList<Repartidor> repartidores, PatiodeComidas patio, InventarioController inventarioCtrl) {
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.repartidores = repartidores;
        this.patio = patio;
        this.inventarioCtrl = inventarioCtrl;
    }

    public Pedido crearPedido(Cliente cliente, Repartidor repartidor, ArrayList<LineaPedido> lineas) {
        if (cliente == null || lineas == null || lineas.isEmpty()) {
            return null;
        }
        if (!inventarioCtrl.hayStockPara(lineas)) {
            return null;
        }
        Pedido nuevo = new Pedido(lineas, cliente, repartidor);
        pedidos.add(nuevo);
        inventarioCtrl.descontarStockPorPedido(lineas);
        if (repartidor != null) {
            repartidor.asignarPedido();
        }
        PersistenciaEstado.guardar(new EstadoCargado(patio, clientes, repartidores, pedidos));
        return nuevo;
    }

    public ArrayList<Pedido> listar() {
        return pedidos;
    }

    public boolean hayPedidos() {
        return !pedidos.isEmpty();
    }
}
