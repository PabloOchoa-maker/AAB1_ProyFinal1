package Controlador;

import Modelos.LineaPedido;
import Modelos.PatiodeComidas;
import Modelos.Pedido;
import Modelos.Plato;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        HashMap<String, Integer> conteo = new HashMap<>();
        HashMap<String, Plato> referencia = new HashMap<>();
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getLineas()) {
                Plato plato = l.getPlato();
                if (plato == null) {
                    continue;
                }
                String id = plato.getId();
                int actual = conteo.getOrDefault(id, 0);
                conteo.put(id, actual + l.getCantidad());
                referencia.put(id, plato);
            }
        }
        String idTop = null;
        int max = -1;
        for (Map.Entry<String, Integer> e : conteo.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                idTop = e.getKey();
            }
        }
        if (idTop == null) {
            return null;
        }
        return referencia.get(idTop);
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
