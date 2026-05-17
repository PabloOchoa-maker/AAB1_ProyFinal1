package aab1_proyfinal;

import java.util.Scanner;

public class AAB1_ProyFinal {

    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);

        // Carga de datos predefinidos (restaurantes, platos, ingredientes, inventarios)
        PatiodeComidas patio = DatosIniciales.crearPatio();

        System.out.println("========BIENVENIDO A " + patio.getNombre() + "========");
        System.out.println("");

        // ===== Aqui va la LOGICA del programa: menu, pedidos, reportes... =====

    }

}
