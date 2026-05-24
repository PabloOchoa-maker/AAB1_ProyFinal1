package Controlador;

import Modelos.LineaPedido;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.Plato;
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
        ArrayList<Plato> platosVistos = new ArrayList<>();
        ArrayList<Integer> cantidades = new ArrayList<>();

        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getLineas()) {
                Plato plato = l.getPlato();
                if (plato == null) {
                    continue;
                }
                int pos = -1;
                for (int i = 0; i < platosVistos.size(); i++) {
                    if (platosVistos.get(i).getId().equals(plato.getId())) {
                        pos = i;
                        break;
                    }
                }
                if (pos == -1) {
                    platosVistos.add(plato);
                    cantidades.add(l.getCantidad());
                } else {
                    cantidades.set(pos, cantidades.get(pos) + l.getCantidad());
                }
            }
        }

        if (platosVistos.isEmpty()) {
            return null;
        }

        int idxTop = 0;
        for (int i = 1; i < cantidades.size(); i++) {
            if (cantidades.get(i) > cantidades.get(idxTop)) {
                idxTop = i;
            }
        }
        return platosVistos.get(idxTop);
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== REPORTE DE ").append(patio.getNombre()).append(" =====\n");
        sb.append("Total de pedidos: ").append(totalPedidos()).append("\n");
        sb.append("Total ventas (con delivery): $").append(totalVentas()).append("\n");
        Plato top = platoMasVendido();
        if (top != null) {
            sb.append("Plato mas vendido: ").append(top.getNombre()).append(" ($").append(top.getPrecio()).append(")\n");
        } else {
            sb.append("Plato mas vendido: (sin pedidos aun)\n");
        }
        return sb.toString();
    }
}
