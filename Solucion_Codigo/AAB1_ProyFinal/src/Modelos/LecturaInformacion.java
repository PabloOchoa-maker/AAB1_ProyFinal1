package Modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaInformacion {

    private static String carpetaDatos = "datos";

    public static EstadoCargado cargarTodo() {
        PatiodeComidas patio = cargarPatio();
        if (patio != null) {
            cargarRestaurantes(patio);
            cargarPlatos(patio);
            cargarIngredientes(patio);
            cargarCombos(patio);
            cargarComboPlatos(patio);
        }
        ArrayList<Cliente> clientes = cargarClientes();
        ArrayList<Repartidor> repartidores = cargarRepartidores();
        ArrayList<Pedido> pedidos = cargarPedidos(patio, clientes, repartidores);
        return new EstadoCargado(patio, clientes, repartidores, pedidos);
    }

    private static PatiodeComidas cargarPatio() {
        File f = new File(carpetaDatos + "/patio.txt");
        if (!f.exists()) {
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = br.readLine();
            if (linea == null || linea.trim().isEmpty()) {
                return null;
            }
            return new PatiodeComidas(linea.trim());
        } catch (IOException e) {
            System.out.println("Error leyendo patio.txt: " + e.getMessage());
            return null;
        }
    }

    private static void cargarRestaurantes(PatiodeComidas patio) {
        File f = new File(carpetaDatos + "/restaurantes.txt");
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    Restaurante r = new Restaurante(partes[0], partes[1]);
                    patio.getRestaurantes().add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo restaurantes.txt: " + e.getMessage());
        }
    }

    private static void cargarPlatos(PatiodeComidas patio) {
        File f = new File(carpetaDatos + "/platos.txt");
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    String idPlato = partes[0];
                    String idRest = partes[1];
                    String nombre = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    boolean disponible = Boolean.parseBoolean(partes[4]);
                    Restaurante r = patio.buscarPorId(idRest);
                    if (r != null) {
                        r.agregarPlato(new Plato(idPlato, nombre, precio, disponible));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo platos.txt: " + e.getMessage());
        }
    }

    private static void cargarIngredientes(PatiodeComidas patio) {
        File f = new File(carpetaDatos + "/ingredientes.txt");
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 3) {
                    String nombre = partes[0];
                    String idPlato = partes[1];
                    int stock = Integer.parseInt(partes[2]);
                    Plato p = patio.buscarPlatoPorId(idPlato);
                    if (p != null) {
                        p.agregarIngrediente(new Ingrediente(nombre, stock));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo ingredientes.txt: " + e.getMessage());
        }
    }

    private static void cargarCombos(PatiodeComidas patio) {
        File f = new File(carpetaDatos + "/combos.txt");
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    String idCombo = partes[0];
                    String idRest = partes[1];
                    String nombre = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    Restaurante r = patio.buscarPorId(idRest);
                    if (r != null) {
                        r.agregarCombo(new Combo(idCombo, nombre, precio));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo combos.txt: " + e.getMessage());
        }
    }

    private static void cargarComboPlatos(PatiodeComidas patio) {
        File f = new File(carpetaDatos + "/combo_platos.txt");
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    String idCombo = partes[0];
                    String idPlato = partes[1];
                    Combo c = patio.buscarComboPorId(idCombo);
                    Plato p = patio.buscarPlatoPorId(idPlato);
                    if (c != null && p != null) {
                        c.agregarPlato(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo combo_platos.txt: " + e.getMessage());
        }
    }

    private static ArrayList<Cliente> cargarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        File f = new File(carpetaDatos + "/clientes.txt");
        if (!f.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 3) {
                    lista.add(new Cliente(partes[0], partes[1], Double.parseDouble(partes[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo clientes.txt: " + e.getMessage());
        }
        return lista;
    }

    private static ArrayList<Repartidor> cargarRepartidores() {
        ArrayList<Repartidor> lista = new ArrayList<>();
        File f = new File(carpetaDatos + "/repartidores.txt");
        if (!f.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    lista.add(new Repartidor(partes[0], Boolean.parseBoolean(partes[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo repartidores.txt: " + e.getMessage());
        }
        return lista;
    }

    private static ArrayList<Pedido> cargarPedidos(PatiodeComidas patio, ArrayList<Cliente> clientes, ArrayList<Repartidor> repartidores) {
        ArrayList<Pedido> lista = new ArrayList<>();
        File f = new File(carpetaDatos + "/pedidos.txt");
        if (!f.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    int id = Integer.parseInt(partes[0]);
                    Cliente c = buscarCliente(clientes, partes[1]);
                    Repartidor r = buscarRepartidor(repartidores, partes[2]);
                    Pedido p = new Pedido(id, new ArrayList<>(), c, r);
                    lista.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo pedidos.txt: " + e.getMessage());
        }
        cargarLineasPedido(lista, patio);
        return lista;
    }

    private static void cargarLineasPedido(ArrayList<Pedido> pedidos, PatiodeComidas patio) {
        File f = new File(carpetaDatos + "/lineas_pedido.txt");
        if (!f.exists() || patio == null) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    int idPedido = Integer.parseInt(partes[0]);
                    String idPlato = partes[1];
                    int cantidad = Integer.parseInt(partes[2]);
                    double precioU = Double.parseDouble(partes[3]);
                    Pedido p = buscarPedido(pedidos, idPedido);
                    Plato plato = patio.buscarPlatoPorId(idPlato);
                    if (p != null && plato != null) {
                        p.getLineas().add(new LineaPedido(plato, cantidad, precioU));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo lineas_pedido.txt: " + e.getMessage());
        }
    }

    private static Cliente buscarCliente(ArrayList<Cliente> clientes, String nombre) {
        for (Cliente c : clientes) {
            if (c.getNombre().equals(nombre)) {
                return c;
            }
        }
        return null;
    }

    private static Repartidor buscarRepartidor(ArrayList<Repartidor> repartidores, String nombre) {
        for (Repartidor r : repartidores) {
            if (r.getNombre().equals(nombre)) {
                return r;
            }
        }
        return null;
    }

    private static Pedido buscarPedido(ArrayList<Pedido> pedidos, int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
