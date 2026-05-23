package Vista;

import Controlador.ClienteController;
import Controlador.InventarioController;
import Controlador.PedidoController;
import Controlador.RepartidorController;
import Controlador.ReporteController;
import Controlador.RestauranteController;
import Modelos.Cliente;
import Modelos.Combo;
import Modelos.LineaPedido;
import Modelos.Pedido;
import Modelos.Plato;
import Modelos.Repartidor;
import Modelos.Restaurante;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Vista {

    private Scanner tcl;
    private ClienteController clienteCtrl;
    private RepartidorController repartidorCtrl;
    private RestauranteController restauranteCtrl;
    private InventarioController inventarioCtrl;
    private PedidoController pedidoCtrl;
    private ReporteController reporteCtrl;

    public Vista(ClienteController c, RepartidorController r, RestauranteController rest, InventarioController i, PedidoController p, ReporteController rep) {
        this.tcl = new Scanner(System.in);
        this.tcl.useLocale(Locale.US);
        this.clienteCtrl = c;
        this.repartidorCtrl = r;
        this.restauranteCtrl = rest;
        this.inventarioCtrl = i;
        this.pedidoCtrl = p;
        this.reporteCtrl = rep;
    }

    public void iniciar() {
        System.out.println("========BIENVENIDO A " + restauranteCtrl.getNombrePatio() + "========");
        System.out.println("");
        int opc;
        do {
            mostrarMenuPrincipal();
            opc = leerEntero();
            switch (opc) {
                case 1:
                    menuPedidos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuRepartidores();
                    break;
                case 4:
                    menuRestaurantes();
                    break;
                case 5:
                    menuInventario();
                    break;
                case 6:
                    menuReporte();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opc != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("");
        System.out.println("===" + restauranteCtrl.getNombrePatio() + "===");
        System.out.println("1. PEDIDOS");
        System.out.println("2. GESTION DE CLIENTES");
        System.out.println("3. GESTION DE REPARTIDORES");
        System.out.println("4. GESTION DE RESTAURANTES");
        System.out.println("5. GESTION DE INVENTARIO");
        System.out.println("6. REPORTE DE VENTAS Y PLATO MAS VENDIDO");
        System.out.println("0. SALIR");
        System.out.print("Ingrese numero de opcion: ");
    }

    private void menuPedidos() {
        if (!clienteCtrl.hayClientes()) {
            System.out.println("NO EXISTEN CLIENTES REGISTRADOS. Registre uno en el menu 2.");
            return;
        }
        if (!restauranteCtrl.hayRestaurantes()) {
            System.out.println("NO HAY RESTAURANTES en el patio.");
            return;
        }

        System.out.println("=== ELIJA EL CLIENTE ===");
        ArrayList<Cliente> clientes = clienteCtrl.listar();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
        }
        System.out.print("Numero de cliente: ");
        int idxCliente = leerEntero() - 1;
        Cliente cliente = clienteCtrl.obtenerPorIndice(idxCliente);
        if (cliente == null) {
            System.out.println("Cliente invalido");
            return;
        }

        Repartidor repartidor = repartidorCtrl.obtenerDisponible();
        if (repartidor == null) {
            System.out.println("Aviso: no hay repartidores disponibles, el pedido se crea sin repartidor.");
        } else {
            System.out.println("Repartidor asignado: " + repartidor.getNombre());
        }

        ArrayList<LineaPedido> lineas = new ArrayList<>();
        int opcRest;
        do {
            System.out.println("");
            System.out.println("=== RESTAURANTES DEL PATIO ===");
            ArrayList<Restaurante> rests = restauranteCtrl.listar();
            for (int i = 0; i < rests.size(); i++) {
                System.out.println((i + 1) + ". " + rests.get(i).getNombre());
            }
            System.out.println("0. Terminar pedido");
            System.out.print("Numero de restaurante: ");
            opcRest = leerEntero();
            if (opcRest == 0) {
                break;
            }
            Restaurante r = restauranteCtrl.obtenerPorIndice(opcRest - 1);
            if (r == null) {
                System.out.println("Restaurante invalido");
                continue;
            }
            agregarPlatosDe(r, lineas);
            mostrarCombosInformativos(r);
        } while (true);

        if (lineas.isEmpty()) {
            System.out.println("Pedido cancelado (sin lineas)");
            return;
        }

        Pedido p = pedidoCtrl.crearPedido(cliente, repartidor, lineas);
        if (p == null) {
            System.out.println("No se pudo crear el pedido (falta stock o datos invalidos)");
            return;
        }
        System.out.println("");
        System.out.println("PEDIDO #" + p.getId() + " creado");
        System.out.println("Subtotal platos: $" + p.calcularSubtotalPlatos());
        System.out.println("Delivery: $" + p.calcularDelivery());
        System.out.println("TOTAL: $" + p.calcularTotal());
    }

    private void agregarPlatosDe(Restaurante r, ArrayList<LineaPedido> lineas) {
        ArrayList<Plato> platos = r.getPlatos();
        int opcPlato;
        do {
            System.out.println("");
            System.out.println("=== MENU DE " + r.getNombre() + " ===");
            for (int i = 0; i < platos.size(); i++) {
                Plato p = platos.get(i);
                System.out.println((i + 1) + ". " + p.getNombre() + " $" + p.getPrecio() + (p.isDisponible() ? " (Disponible)" : " (No disponible)"));
            }
            System.out.println("0. Terminar con este restaurante");
            System.out.print("Plato: ");
            opcPlato = leerEntero();
            if (opcPlato == 0) {
                break;
            }
            if (opcPlato < 1 || opcPlato > platos.size()) {
                System.out.println("Plato invalido");
                continue;
            }
            Plato p = platos.get(opcPlato - 1);
            System.out.print("Cantidad: ");
            int cant = leerEntero();
            if (cant <= 0) {
                System.out.println("Cantidad invalida");
                continue;
            }
            if (!p.verificarStock(cant)) {
                System.out.println("Sin stock suficiente para " + cant + " unidades de " + p.getNombre());
                continue;
            }
            lineas.add(new LineaPedido(p, cant, p.getPrecio()));
            System.out.println("Agregado: " + cant + "x " + p.getNombre());
        } while (true);
    }

    private void mostrarCombosInformativos(Restaurante r) {
        if (r.getCombos().isEmpty()) {
            return;
        }
        System.out.println("");
        System.out.println("(Combos disponibles en " + r.getNombre() + ":)");
        for (Combo c : r.getCombos()) {
            System.out.println("  - " + c.descripcion() + " | ahorra $" + c.getAhorroVsPlatosSueltos());
        }
    }

    private void menuClientes() {
        int opc;
        do {
            System.out.println("");
            System.out.println("=== GESTION DE CLIENTES ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opc = leerEntero();
            if (opc == 1) {
                System.out.print("Nombre: ");
                String nombre = tcl.next();
                tcl.nextLine();
                System.out.print("Direccion: ");
                String direccion = tcl.nextLine();
                System.out.print("Distancia en km: ");
                double km = leerDouble();
                clienteCtrl.registrar(nombre, direccion, km);
                System.out.println("Cliente registrado");
            } else if (opc == 2) {
                ArrayList<Cliente> lista = clienteCtrl.listar();
                if (lista.isEmpty()) {
                    System.out.println("(sin clientes)");
                } else {
                    for (int i = 0; i < lista.size(); i++) {
                        Cliente c = lista.get(i);
                        System.out.println((i + 1) + ". " + c.getNombre() + " | " + c.getDireccion() + " | " + c.getDistanciaKm() + " km");
                    }
                }
            }
        } while (opc != 0);
    }

    private void menuRepartidores() {
        int opc;
        do {
            System.out.println("");
            System.out.println("=== GESTION DE REPARTIDORES ===");
            System.out.println("1. Registrar repartidor");
            System.out.println("2. Listar repartidores");
            System.out.println("3. Liberar repartidor");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opc = leerEntero();
            if (opc == 1) {
                System.out.print("Nombre: ");
                String nombre = tcl.next();
                repartidorCtrl.registrar(nombre);
                System.out.println("Repartidor registrado");
            } else if (opc == 2) {
                ArrayList<Repartidor> lista = repartidorCtrl.listar();
                if (lista.isEmpty()) {
                    System.out.println("(sin repartidores)");
                } else {
                    for (int i = 0; i < lista.size(); i++) {
                        Repartidor r = lista.get(i);
                        System.out.println((i + 1) + ". " + r.getNombre() + (r.isDisponible() ? " [LIBRE]" : " [OCUPADO]"));
                    }
                }
            } else if (opc == 3) {
                ArrayList<Repartidor> lista = repartidorCtrl.listar();
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println((i + 1) + ". " + lista.get(i).getNombre());
                }
                System.out.print("Numero: ");
                int idx = leerEntero() - 1;
                Repartidor r = repartidorCtrl.obtenerPorIndice(idx);
                if (r != null) {
                    repartidorCtrl.marcarLibre(r);
                    System.out.println("Repartidor liberado");
                } else {
                    System.out.println("Indice invalido");
                }
            }
        } while (opc != 0);
    }

    private void menuRestaurantes() {
        ArrayList<Restaurante> rests = restauranteCtrl.listar();
        System.out.println("");
        System.out.println("=== RESTAURANTES DEL PATIO ===");
        if (rests.isEmpty()) {
            System.out.println("(sin restaurantes)");
            return;
        }
        for (int i = 0; i < rests.size(); i++) {
            Restaurante r = rests.get(i);
            System.out.println((i + 1) + ". " + r.getNombre() + " (" + r.getPlatos().size() + " platos, " + r.getCombos().size() + " combos)");
        }
        System.out.print("Ver detalle de cual (0 para volver): ");
        int idx = leerEntero() - 1;
        Restaurante r = restauranteCtrl.obtenerPorIndice(idx);
        if (r == null) {
            return;
        }
        System.out.println("");
        System.out.println("--- Platos ---");
        for (Plato p : r.getPlatos()) {
            System.out.println("  " + p.getId() + " - " + p.getNombre() + " $" + p.getPrecio());
        }
        System.out.println("--- Combos ---");
        if (r.getCombos().isEmpty()) {
            System.out.println("  (sin combos)");
        } else {
            for (Combo c : r.getCombos()) {
                System.out.println("  " + c.descripcion());
            }
        }
    }

    private void menuInventario() {
        int opc;
        do {
            System.out.println("");
            System.out.println("=== GESTION DE INVENTARIO ===");
            System.out.println("1. Ver inventario de un restaurante");
            System.out.println("2. Registrar entrada de ingrediente");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opc = leerEntero();
            if (opc == 1) {
                ArrayList<Restaurante> rests = restauranteCtrl.listar();
                for (int i = 0; i < rests.size(); i++) {
                    System.out.println((i + 1) + ". " + rests.get(i).getNombre());
                }
                System.out.print("Restaurante: ");
                int idx = leerEntero() - 1;
                ArrayList<String> resumen = inventarioCtrl.resumenPorRestaurante(idx);
                if (resumen.isEmpty()) {
                    System.out.println("(sin ingredientes o restaurante invalido)");
                } else {
                    for (String s : resumen) {
                        System.out.println("  " + s);
                    }
                }
            } else if (opc == 2) {
                System.out.print("Nombre del ingrediente: ");
                String nombre = tcl.next();
                tcl.nextLine();
                System.out.print("Cantidad a ingresar: ");
                int cant = leerEntero();
                boolean ok = inventarioCtrl.registrarEntrada(nombre, cant);
                System.out.println(ok ? "Stock actualizado" : "Ingrediente no encontrado");
            }
        } while (opc != 0);
    }

    private void menuReporte() {
        System.out.println("");
        System.out.println(reporteCtrl.generarReporte());
    }

    private int leerEntero() {
        while (!tcl.hasNextInt()) {
            tcl.next();
            System.out.print("Numero invalido, intente de nuevo: ");
        }
        return tcl.nextInt();
    }

    private double leerDouble() {
        while (true) {
            String s = tcl.next();
            try {
                return Double.parseDouble(s.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.print("Numero invalido, intente de nuevo: ");
            }
        }
    }
}
