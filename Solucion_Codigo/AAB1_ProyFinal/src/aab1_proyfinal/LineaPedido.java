package aab1_proyfinal;

public class LineaPedido {
    
    
    
    private Plato plato;
    private int cantidad;
    private double precioUnitario;

    public LineaPedido(Plato plato, int cantidad, double precioUnitario) {
        this.plato = plato;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
//FALTA
    public double getSubtotal() {
        return 0;
    }
//   ....

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
