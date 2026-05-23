package Modelos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EscrituraInformacion {

    private static String carpetaDatos = "datos";

    public static void guardarTodo(PatiodeComidas patio, ArrayList<Cliente> clientes, ArrayList<Repartidor> repartidores, ArrayList<Pedido> pedidos) {
        asegurarCarpeta();
        guardarPatio(patio);
        guardarRestaurantes(patio);
        guardarPlatos(patio);
        guardarIngredientes(patio);
        guardarCombos(patio);
        guardarComboPlatos(patio);
        guardarClientes(clientes);
        guardarRepartidores(repartidores);
        guardarPedidos(pedidos);
        guardarLineasPedido(pedidos);
    }

    private static void asegurarCarpeta() {
        File f = new File(carpetaDatos);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public static void guardarPatio(PatiodeComidas patio) {
        if (patio == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/patio.txt"))) {
            pw.println(patio.getNombre());
        } catch (IOException e) {
            System.out.println("Error guardando patio.txt: " + e.getMessage());
        }
    }

    public static void guardarRestaurantes(PatiodeComidas patio) {
        if (patio == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/restaurantes.txt"))) {
            for (Restaurante r : patio.getRestaurantes()) {
                pw.println(r.getId() + ";" + r.getNombre());
            }
        } catch (IOException e) {
            System.out.println("Error guardando restaurantes.txt: " + e.getMessage());
        }
    }

    public static void guardarPlatos(PatiodeComidas patio) {
        if (patio == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/platos.txt"))) {
            for (Restaurante r : patio.getRestaurantes()) {
                for (Plato p : r.getPlatos()) {
                    pw.println(p.getId() + ";" + r.getId() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + p.isDisponible());
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando platos.txt: " + e.getMessage());
        }
    }

    public static void guardarIngredientes(PatiodeComidas patio) {
        if (patio == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/ingredientes.txt"))) {
            for (Restaurante r : patio.getRestaurantes()) {
                for (Plato p : r.getPlatos()) {
                    for (Ingrediente ing : p.getIngredientes()) {
                        pw.println(ing.getNombre() + ";" + p.getId() + ";" + ing.getStockActual());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando ingredientes.txt: " + e.getMessage());
        }
    }

    public static void guardarCombos(PatiodeComidas patio) {
        if (patio == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/combos.txt"))) {
            for (Restaurante r : patio.getRestaurantes()) {
                for (Combo c : r.getCombos()) {
                    pw.println(c.getId() + ";" + r.getId() + ";" + c.getNombre() + ";" + c.getPrecio());
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando combos.txt: " + e.getMessage());
        }
    }

    public static void guardarComboPlatos(PatiodeComidas patio) {
        if (patio == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/combo_platos.txt"))) {
            for (Restaurante r : patio.getRestaurantes()) {
                for (Combo c : r.getCombos()) {
                    for (Plato p : c.getPlatos()) {
                        pw.println(c.getId() + ";" + p.getId());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando combo_platos.txt: " + e.getMessage());
        }
    }

    public static void guardarClientes(ArrayList<Cliente> clientes) {
        if (clientes == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/clientes.txt"))) {
            for (Cliente c : clientes) {
                pw.println(c.getNombre() + ";" + c.getDireccion() + ";" + c.getDistanciaKm());
            }
        } catch (IOException e) {
            System.out.println("Error guardando clientes.txt: " + e.getMessage());
        }
    }

    public static void guardarRepartidores(ArrayList<Repartidor> repartidores) {
        if (repartidores == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/repartidores.txt"))) {
            for (Repartidor r : repartidores) {
                pw.println(r.getNombre() + ";" + r.isDisponible());
            }
        } catch (IOException e) {
            System.out.println("Error guardando repartidores.txt: " + e.getMessage());
        }
    }

    public static void guardarPedidos(ArrayList<Pedido> pedidos) {
        if (pedidos == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/pedidos.txt"))) {
            for (Pedido p : pedidos) {
                String nomCliente = p.getCliente() != null ? p.getCliente().getNombre() : "";
                String nomRepartidor = p.getRepartidor() != null ? p.getRepartidor().getNombre() : "";
                pw.println(p.getId() + ";" + nomCliente + ";" + nomRepartidor + ";" + p.calcularTotal());
            }
        } catch (IOException e) {
            System.out.println("Error guardando pedidos.txt: " + e.getMessage());
        }
    }

    public static void guardarLineasPedido(ArrayList<Pedido> pedidos) {
        if (pedidos == null) {
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(carpetaDatos + "/lineas_pedido.txt"))) {
            for (Pedido p : pedidos) {
                for (LineaPedido l : p.getLineas()) {
                    String idPlato = l.getPlato() != null ? l.getPlato().getId() : "";
                    pw.println(p.getId() + ";" + idPlato + ";" + l.getCantidad() + ";" + l.getPrecioUnitario());
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando lineas_pedido.txt: " + e.getMessage());
        }
    }
}
