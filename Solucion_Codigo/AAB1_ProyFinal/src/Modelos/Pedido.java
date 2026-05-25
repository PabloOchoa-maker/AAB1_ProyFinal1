package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pedido implements Calculable, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private ArrayList<LineaPedido> lineas;
    private Cliente cliente;
    private Repartidor repartidor;

    private static Random random = new Random();
    private static double tarifaBaseDelivery = 1.50;
    private static double costoPorKm = 0.50;

    public Pedido(ArrayList<LineaPedido> lineas, Cliente cliente, Repartidor repartidor) {
        this.id = random.nextInt(200) + 100;
        this.lineas = lineas;
        this.cliente = cliente;
        this.repartidor = repartidor;
    }

    public Pedido(int id, ArrayList<LineaPedido> lineas, Cliente cliente, Repartidor repartidor) {
        this.id = id;
        this.lineas = lineas;
        this.cliente = cliente;
        this.repartidor = repartidor;
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (LineaPedido l : lineas) {
            total = total + l.getSubtotal();
        }
        return total + calcularDelivery();
    }

    public double calcularSubtotalPlatos() {
        double total = 0;
        for (LineaPedido l : lineas) {
            total = total + l.getSubtotal();
        }
        return total;
    }

    public double calcularDelivery() {
        if (cliente == null) {
            return tarifaBaseDelivery;
        }
        return tarifaBaseDelivery + (cliente.getDistanciaKm() * costoPorKm);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }
}
