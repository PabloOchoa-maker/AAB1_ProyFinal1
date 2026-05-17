package aab1_proyfinal;

public class PatiodeComidas {

    public static final int MAX_RESTAURANTES = 6;

    private String nombre;
    private Restaurante[] restaurantes;
    private int restaurantesExistentes;

    public PatiodeComidas(String nombre) {
        this.nombre = nombre;
        this.restaurantes = new Restaurante[MAX_RESTAURANTES];
        this.restaurantesExistentes = 0;
    }
    
    public void agregarRestaurante(Restaurante r) {
        System.out.println("AGREGANDO " + r.getNombre() + " AL PATIO DE COMIDAS");
        System.out.println("");
        
        if (restaurantesExistentes < MAX_RESTAURANTES) {
            restaurantes[restaurantesExistentes] = r;
            restaurantesExistentes++;
        } else {
            System.out.println("NO HAY CAPACIDAD PARA MAS RESTAURANTES");
        }
    }

    public String generarReporte() {
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Restaurante[] getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(Restaurante[] restaurantes) {
        this.restaurantes = restaurantes;
    }
    
    

}
