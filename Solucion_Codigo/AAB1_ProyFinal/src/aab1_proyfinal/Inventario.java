package aab1_proyfinal;


public class Inventario {

    public static final int MAX_INGREDIENTES = 20;
    
    private Ingrediente[] ingredientes;
    private int cantidadIngredientes;
    
    public Inventario(Ingrediente[] ingredientes) {
        this.ingredientes = ingredientes;
        this.cantidadIngredientes = ingredientes.length;
    }
    
    
//    STRING PARA RGISTRAR LO QUE VA A OCURRIENDO EN EL DIA EN EL SISTEMA
//    COMO REDUCCION DE STOCK 
    public void registrarEntrada(Ingrediente i) {
        
    }
//    MOSTRA RESUMEN
    public void resumenInventario(){
        
    }

    public String getResumenDiario() {
        return null;
    }

    public Ingrediente[] getIngredientes() {
        return ingredientes;
    }

}
