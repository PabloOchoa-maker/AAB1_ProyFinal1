package aab1_proyfinal;

import java.util.Scanner;

public class AAB1_ProyFinal {

    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        int opc;
        // Carga de datos predefinidos (restaurantes, platos, ingredientes, inventarios)
        PatiodeComidas patio = DatosIniciales.crearPatio();

        System.out.println("========BIENVENIDO A " + patio.getNombre() + "========");
        System.out.println("");

        // ===== Aqui va la LOGICA del programa: menu, pedidos, reportes... =====
            
        do {
            System.out.println("===RESTAURANTES===");
            System.out.println("1. " + patio.getRestaurantes()[0].getNombre());
            System.out.println("2. " + patio.getRestaurantes()[1].getNombre());
            System.out.println("3. " + patio.getRestaurantes()[2].getNombre());
            System.out.println("4. " + patio.getRestaurantes()[3].getNombre());
            System.out.println("5. " + patio.getRestaurantes()[4].getNombre());
            System.out.println("Ingrese numero de restaurante");
            opc = tcl.nextInt();
            
        } while (opc != 0);
        
    }

}
