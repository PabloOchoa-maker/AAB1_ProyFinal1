package Modelos;

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

//        Lista clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
//        LISTA REPARTIDORES
        ArrayList<Repartidor> repartidores = new ArrayList<>();
        
        // ===== Aqui va la LOGICA del programa: menu, pedidos, reportes... =====
        
        
        do {
            System.out.println("===RESTAURANTES===");
            System.out.println("1. PEDIDOS") ;
            System.out.println("2. GESTION DE CLIENTES");
            System.out.println("3. GESTION DE REPARTIDORES");
            System.out.println("4. GESTION DE RESTAURANTES") ;
            System.out.println("5. GESTION DE INVENTARIO") ;
            System.out.println("6. REPORTE DE VENTAS Y PLATO MAS VENDIDO") ;
            System.out.println("0. SALIR");
            System.out.println("Ingrese numero de opcion: ");
            opc = tcl.nextInt();
            
            switch (opc) {
//                PEDIDOS
                case 1:
                    if (!clientes.isEmpty()) {
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
                                    int opcPlato = 1;
        //                            ANOTACION DE SUB PEDIDOS
                                    while(opcPlato != 0) {
                                        patio.getRestaurantes()[opc1 - 1].mostrarPlatos();
                                        opcPlato = tcl.nextInt();
                                        if (opcPlato != 0 && patio.getRestaurantes()[opc1 - 1].getPlatos().length > (opcPlato - 1)) {
                                            System.out.println("Ingrese numero de platos a adquirir: ");
                                            int cant = tcl.nextInt();
                                            System.out.println("");
                                            subPedidos.add(new LineaPedido(patio.getRestaurantes()[opc1 - 1].getPlatos()[opcPlato - 1], cant, patio.getRestaurantes()[opc1 - 1].getPlatos()[opcPlato - 1].getPrecio()));
                                        } else{
                                            System.out.println("NO existe plato");
                                        }                                
        // FALTA REALIZAR EL CAMBIO REDUCIR STOCK EN INGREDIENTES DESPUES DE CREAR EL SUB PEDIDO
                                    }   
                                    System.out.println("");
                            } else {
                                System.out.println("!!! :D ESPERAMOS MAS PEDIDOS !!!");
                            }

    //                    UNA VEZ AQUI SE UNEN TODOS LOS SUB PEDIDOS EN UN PEDIDO
    //                    SE ELIGE PARA QUE CLIENTE VA EL PEDIDO
    //                    MAS LOGICA
//                            Pedido pedido = new Pedido(subPedidos, cliente, repartidor)
                        } while (opc1 != 0);
                    } else {
                        System.out.println("NO EXISTEN CLIENTES REGISTRADOS -->: REGISTRE CLIENTE EN EL MENU PRINCIPAL PARA ACCEDER A ESTA OPCION");
                    }

                    break;
                    
                case 2:
                    int opc2 = 1;
                    while (opc2 != 0) {                        
                        System.out.println("====MENU GESTION CLIENTES====");
                        Cliente cliente = new Cliente();
                        clientes.add(cliente);
                        System.out.println("");
                        System.out.println("1. Registrar otro cliente");
                        System.out.println("0. SALIR A MENU PRINCIPAL");
                        opc2 = tcl.nextInt();
                        System.out.println("");
                    }
                    
                    break;
                
                case 3:
                    
                    break;
                
                case 4:
                    
                    break;
            }
            
        } while (opc != 0);
        
    }

}
