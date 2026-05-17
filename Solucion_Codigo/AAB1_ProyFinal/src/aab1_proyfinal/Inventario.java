package aab1_proyfinal;


public class Inventario {

    public static final int MAX_INGREDIENTES = 20;
    
    private Ingrediente[] ingredientes;
    private int cantidadIngredientes;
    
    public Inventario(Ingrediente[] ingredientes) {
        this.ingredientes = ingredientes;
        this.cantidadIngredientes = ingredientes.length;
    }
    
    public void agregarIngrediente() {
        System.out.println("AGREGANDO INGREDIENTE A INVENTARIO");
    }

    public void registrarEntrada(Ingrediente i) {
        
    }

    public String getResumenDiario() {
        return null;
    }

    public Ingrediente[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Ingrediente[] ingredientes) {
        this.ingredientes = ingredientes;
    }
}
