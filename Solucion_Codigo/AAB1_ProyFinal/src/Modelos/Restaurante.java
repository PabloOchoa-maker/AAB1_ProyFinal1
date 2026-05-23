package Modelos;

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
    
    public void mostrarPlatos(){
        int i = 0;
        while(platos[i] != null) {
            System.out.println( (i + 1) + ". " + platos[i].getNombre() + " $" + platos[i].getPrecio() + ( platos[i].isDisponible() ? " Disponible" : " No disponible"));
            i++;
        }
        System.out.println("0. Para salir MENU RESTAURANTES");
        System.out.println("Que plato va a aniadir al pedido: ");
    }
    
    
//    FALTA
    @Override
    public double calcularTotal() {
        return 0;
    }

    public Plato getPlatoMasVendido() {
        return null;
    }
    
//    ....

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Plato[] getPlatos() {
        return platos;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
