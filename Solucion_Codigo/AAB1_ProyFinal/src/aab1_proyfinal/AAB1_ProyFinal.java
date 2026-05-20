package aab1_proyfinal;

import java.util.ArrayList;
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
            System.out.println("1. PEDIDOS") ;
            System.out.println("2. GESTION DE CLIENTES") ;
            System.out.println("3. GESTION DE RESTAURANTES") ;
            System.out.println("4. GESTION DE INVENTARIO") ;
            System.out.println("5. REPORTE DE VENTAS Y PLATO MAS VENDIDO") ;
            System.out.println("0. SALIR");
            System.out.println("Ingrese numero de opcion: ");
            opc = tcl.nextInt();
            
            switch (opc) {
//                PEDIDOS
                case 1:
                    int opc1;
                    ArrayList<LineaPedido> subPedidos = new ArrayList<>();
                    do {
                        System.out.println("===REALICE EL PEDIDO AQUI===");
    //                    MENU DE RESTAURANTES
                        patio.mostrarRestaurantes();
                        System.out.println("Ingrese numero de restaurante: ");
                        opc1 = tcl.nextInt();
                        System.out.println("");
                        if (opc1 <= patio.getRestaurantesExistentes() && opc1 > 0 ) {
    //                        MENU PLATOS DEL RESTAURANTE
                            System.out.println("===MENU DE " + patio.getRestaurantes()[opc1 - 1].getNombre() + "===");
                                int opcPlato;
    //                            ANOTACION DE SUB PEDIDOS
                                do {
                                    patio.getRestaurantes()[opc1 - 1].mostrarPlatos();
                                    opcPlato = tcl.nextInt();
                                    System.out.println("Ingrese numero de platos a adquirir: ");
                                    subPedidos.add(new LineaPedido(patio.getRestaurantes()[opc1 - 1].getPlatos()[opcPlato - 1], tcl.nextInt(), patio.getRestaurantes()[opc1 - 1].getPlatos()[opcPlato - 1].getPrecio()));
    //                                Crea un subPedido en la posicion posSubpedidos en el arreglo subPedidos /|\
    // FALTA REALIZAR EL CAMBIO REDUCIR STOCK EN INGREDIENTES DESPUES DE CREAR EL SUB PEDIDO

                                } while (opcPlato != 0);

                                System.out.println("");
                        } else {
                            System.out.println("!!! :D ESPERAMOS MAS PEDIDOS !!!");
                        }

//                    UNA VEZ AQUI SE UNEN TODOS LOS SUB PEDIDOS EN UN PEDIDO
//                    SE ELIGE PARA QUE CLIENTE VA EL PEDIDO
//                    MAS LOGICA
//                            Pedido pedido = new Pedido()

                        break;
                    } while (opc1 != 0);
                    
                case 2:
                    
                    break;
                
                case 3:
                    
                    break;
                
                case 4:
                    
                    break;
            }
            
        } while (opc != 0);
        
    }

}
