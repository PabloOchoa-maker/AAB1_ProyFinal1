package aab1_proyfinal;

public class Repartidor {

    private String nombre;
    private boolean disponible;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.disponible = true;
    }
    
    public void asignarPedido() {
        setDisponible(false);
    }
    
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

}
