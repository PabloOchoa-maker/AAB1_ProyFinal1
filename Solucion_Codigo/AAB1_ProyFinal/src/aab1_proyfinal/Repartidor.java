package aab1_proyfinal;

public class Repartidor {

    private String nombre;
    private boolean disponible;
    private String zonaCobertura;

    public Repartidor(String nombre, boolean disponible, String zonaCobertura) {
        this.nombre = nombre;
        this.disponible = disponible;
        this.zonaCobertura = zonaCobertura;
    }
//FALTA
    public void asignarPedido(Pedido p) {
    }
    
//    ,,..,

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }
}
