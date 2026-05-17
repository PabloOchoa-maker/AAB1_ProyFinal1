package aab1_proyfinal;

public class Restaurante implements Calculable {

    public static final int MAX_PLATOS = 6;

    private String id;
    private String nombre;
    private Plato[] platos;
    private Inventario inventario;

    public Restaurante(String id, String nombre, Plato[] platos, Inventario inventario) {
        this.id = id;
        this.nombre = nombre;
        this.platos = platos;
        this.inventario = inventario;
    }

    public void agregarInventario(Inventario inv){
        this.inventario = inv;
    }
    
    @Override
    public double calcularTotal() {
        return 0;
    }

    public Plato getPlatoMasVendido() {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Plato[] getPlatos() {
        return platos;
    }

    public void setPlatos(Plato[] platos) {
        this.platos = platos;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
