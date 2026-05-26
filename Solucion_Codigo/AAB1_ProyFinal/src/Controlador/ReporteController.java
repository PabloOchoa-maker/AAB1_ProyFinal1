package Controlador;

import Modelos.LineaPedido;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.Plato;
import Modelos.Restaurante;
import java.util.ArrayList;

public class ReporteController {

    private ArrayList<Pedido> pedidos;
    private PatiodeComidas patio;

    public ReporteController(ArrayList<Pedido> pedidos, PatiodeComidas patio) {
        this.pedidos = pedidos;
        this.patio = patio;
    }

    public double totalVentas() {
        double total = 0;
        for (Pedido p : pedidos) {
            total = total + p.calcularTotal();
        }
        return total;
    }

    public int totalPedidos() {
        return pedidos.size();
    }

    public Plato platoMasVendido() {
        Plato top = null;
        int maxCantidad = 0;

        for (Restaurante r : patio.getRestaurantes()) {
            for (Plato plato : r.getPlatos()) {
                int vendidos = contarVentasDe(plato);
                if (vendidos > maxCantidad) {
                    maxCantidad = vendidos;
                    top = plato;
                }
            }
        }
        return top;
    }

    private int contarVentasDe(Plato plato) {
        int total = 0;
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getLineas()) {
                if (l.getPlato() != null && l.getPlato().getId().equals(plato.getId())) {
                    total = total + l.getCantidad();
                }
            }
        }
        return total;
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== REPORTE DE ").append(patio.getNombre()).append(" =====\n");
        sb.append("Total de pedidos: ").append(totalPedidos()).append("\n");
        sb.append("Total ventas: $").append(totalVentas()).append("\n");
        Plato top = platoMasVendido();
        if (top != null) {
            sb.append("Plato mas vendido: ").append(top.getNombre()).append(" ($").append(top.getPrecio()).append(")\n");
        } else {
            sb.append("Plato mas vendido: (sin pedidos aun)\n");
        }
        return sb.toString();
    }
}
