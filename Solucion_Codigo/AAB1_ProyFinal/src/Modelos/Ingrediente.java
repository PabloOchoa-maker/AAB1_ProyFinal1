package Modelos;

public class Ingrediente {

    private String nombre;
    private int stockActual;

    public Ingrediente(String nombre, int stockActual) {
        this.nombre = nombre;
        this.stockActual = stockActual;
    }
//FALTa
    public boolean hayStock() {
        return false;
    }

    public void reducirStock(int cantidad) {
        
    }
//....
    
    
//    INVENTARIO
    public void registrarEntrada() {
        
    }
    
//    MOSTRA RESUMEN
    public void resumenInventario(){
        
    }
    public String getNombre() {
        return nombre;
    }

    public int getStockActual() {
        return stockActual;
    }

}
