package aab1_proyfinal;

public class Pedido implements Calculable {

    public static final int MAX_LINEAS = 10;

    private String id;
    private String estado;
    private double distanciaKm;
    private LineaPedido[] lineas;
    private Cliente cliente;
    private Repartidor repartidor;

    public Pedido(String id, String estado, double distanciaKm, LineaPedido[] lineas, Cliente cliente, Repartidor repartidor) {
        this.id = id;
        this.estado = estado;
        this.distanciaKm = distanciaKm;
        this.lineas = lineas;
        this.cliente = cliente;
        this.repartidor = repartidor;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public LineaPedido[] getLineas() {
        return lineas;
    }

    public void setLineas(LineaPedido[] lineas) {
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
