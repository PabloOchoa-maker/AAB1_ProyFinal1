package Vista;

import Controlador.ClienteController;
import Controlador.InventarioController;
import Controlador.PedidoController;
import Controlador.RepartidorController;
import Controlador.ReporteController;
import Controlador.RestauranteController;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

public class VentanaSwing {

    private final ClienteController clienteCtrl;
    private final RepartidorController repartidorCtrl;
    private final RestauranteController restauranteCtrl;
    private final InventarioController inventarioCtrl;
    private final PedidoController pedidoCtrl;
    private final ReporteController reporteCtrl;

    public VentanaSwing(ClienteController clienteCtrl, RepartidorController repartidorCtrl,
            RestauranteController restauranteCtrl, InventarioController inventarioCtrl,
            PedidoController pedidoCtrl, ReporteController reporteCtrl) {
        this.clienteCtrl = clienteCtrl;
        this.repartidorCtrl = repartidorCtrl;
        this.restauranteCtrl = restauranteCtrl;
        this.inventarioCtrl = inventarioCtrl;
        this.pedidoCtrl = pedidoCtrl;
        this.reporteCtrl = reporteCtrl;
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {
            }
            construirYMostrar();
        });
    }

    private void construirYMostrar() {
        JFrame frame = new JFrame("Patio de Comidas - " + restauranteCtrl.getNombrePatio());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));

        PanelPedidos panelPedidos = new PanelPedidos(
                clienteCtrl, repartidorCtrl, restauranteCtrl, pedidoCtrl);
        PanelClientes panelClientes = new PanelClientes(clienteCtrl);
        PanelRepartidores panelRepartidores = new PanelRepartidores(repartidorCtrl);
        PanelRestaurantes panelRestaurantes = new PanelRestaurantes(restauranteCtrl);
        PanelInventario panelInventario = new PanelInventario(inventarioCtrl, restauranteCtrl);
        PanelReporte panelReporte = new PanelReporte(reporteCtrl);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Pedidos", panelPedidos);
        tabs.addTab("Clientes", panelClientes);
        tabs.addTab("Repartidores", panelRepartidores);
        tabs.addTab("Restaurantes", panelRestaurantes);
        tabs.addTab("Inventario", panelInventario);
        tabs.addTab("Reporte", panelReporte);

        ChangeListener refrescador = e -> {
            int idx = tabs.getSelectedIndex();
            switch (idx) {
                case 0:
                    panelPedidos.refrescar();
                    break;
                case 1:
                    panelClientes.refrescar();
                    break;
                case 2:
                    panelRepartidores.refrescar();
                    break;
                case 3:
                    panelRestaurantes.refrescar();
                    break;
                case 4:
                    panelInventario.refrescar();
                    break;
                case 5:
                    panelReporte.refrescar();
                    break;
            }
        };
        tabs.addChangeListener(refrescador);

        frame.add(tabs);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
