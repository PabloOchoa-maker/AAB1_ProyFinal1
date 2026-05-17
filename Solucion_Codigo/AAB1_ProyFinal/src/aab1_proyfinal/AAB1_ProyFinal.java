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
            System.out.println("1. RESTAURANTES Y PEDIDOS") ;
            System.out.println("2. GESTION DE CLIENTES") ;
            System.out.println("3. GESTION DE INVENTARIO") ;
            System.out.println("4. REPORTE DE VENTAS Y PLATO MAS VENDIDO") ;
            System.out.println("0. SALIR");
            System.out.println("Ingrese num"
                    + "ero de opcion: ");
            opc = tcl.nextInt();
            
            switch (opc) {
//                PEDIDOS
                case 1:
//                    HACER BUCLE WHILE HASTA QUE LA PERSONA QUIERA DEJAR DE ANIADIR SUB PEDIDOS
                    System.out.println("===REALICE EL PEDIDO AQUI===");
//                    MENU DE RESTAURANTES
                    patio.mostrarRestaurantes();
                    System.out.println("Ingrese numero de restaurante: ");
                    int opc1 = tcl.nextInt();
                    System.out.println("");
                    if (opc1 <= patio.getRestaurantesExistentes() && opc1 > 0 ) {
//                        MENU PLATOS DEL RESTAURANTE
                        System.out.println("===MENU DE " + patio.getRestaurantes()[opc1 - 1].getNombre() + "===");
                        for (int i = 0; i < patio.getRestaurantesExistentes(); i++) {
                            int opcPlato;
                            int posSubPedidos = 0;
//                            ANOTACION DE SUB PEDIDOS
                            LineaPedido[] subPedidos = new LineaPedido[Pedido.MAX_LINEAS];
                            
                            do {
                                patio.getRestaurantes()[opc1 - 1].mostrarPlatos();
                                opcPlato = tcl.nextInt();
                                System.out.println("Ingrese numero de platos a adquirir: ");
                                subPedidos[posSubPedidos] = new LineaPedido(patio.getRestaurantes()[opc1 - 1].getPlatos()[opcPlato - 1], tcl.nextInt(), patio.getRestaurantes()[opc1 - 1].getPlatos()[opcPlato - 1].getPrecio());
//                                Crea un subPedido en la posicion posSubpedidos en el arreglo subPedidos /|\

// FALTA REALIZAR EL CAMBIO REDUCIR STOCK EN INGREDIENTES DESPUES DE CREAR EL SUB PEDIDO
                                posSubPedidos++;
                            } while (opcPlato != 0);
                            
                            System.out.println("");
                            break;
                        }
                    } else {
                        System.out.println("ERROR!, NUMERO NO VALIDO INTENTE DE NUEVO");
                    }
                    
//                    UNA VEZ AQUI SE UNEN TODOS LOS SUB PEDIDOS EN UN PEDIDO
//                    SE ELIGE PARA QUE CLIENTE VA EL PEDIDO
//                    MAS LOGICA
                    
                    break;
                    
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
