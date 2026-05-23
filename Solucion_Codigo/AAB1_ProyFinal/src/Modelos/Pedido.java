package Modelos;

import java.util.ArrayList;
import java.util.Random;

public class Pedido implements Calculable {


    private int id;
    private ArrayList<LineaPedido> lineas;
    private Cliente cliente;
    private Repartidor repartidor;
    private Random random;

    public Pedido(ArrayList<LineaPedido> lineas, Cliente cliente, Repartidor repartidor) {
        this.id = random.nextInt(200) + 100;
        this.lineas = lineas;
        this.cliente = cliente;
        this.repartidor = repartidor;
        this.random = new Random();
    }
//FALTA
    @Override
    public double calcularTotal() {
        return 0;
    }

    public double calcularDelivery() {
        return 0;
    }
//    .....

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
