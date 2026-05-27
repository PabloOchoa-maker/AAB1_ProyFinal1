package Vista;

import Controlador.ClienteController;
import Controlador.PedidoController;
import Controlador.RepartidorController;
import Controlador.RestauranteController;
import Modelos.Cliente;
import Modelos.LineaPedido;
import Modelos.Pedido;
import Modelos.Plato;
import Modelos.Repartidor;
import Modelos.Restaurante;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PanelPedidos extends JPanel {

    private final ClienteController clienteCtrl;
    private final RepartidorController repartidorCtrl;
    private final RestauranteController restauranteCtrl;
    private final PedidoController pedidoCtrl;

    private JComboBox<String> comboClientes;
    private JComboBox<String> comboRestaurantes;
    private JComboBox<String> comboPlatos;
    private JTextField txtCantidad;

    private DefaultTableModel modeloCarrito;
    private JTable tablaCarrito;
    private ArrayList<LineaPedido> carrito = new ArrayList<>();

    private JLabel lblSubtotal;
    private JLabel lblRepartidor;

    public PanelPedidos(ClienteController clienteCtrl, RepartidorController repartidorCtrl,
            RestauranteController restauranteCtrl, PedidoController pedidoCtrl) {
        this.clienteCtrl = clienteCtrl;
        this.repartidorCtrl = repartidorCtrl;
        this.restauranteCtrl = restauranteCtrl;
        this.pedidoCtrl = pedidoCtrl;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        construirSeleccion();
        construirCarrito();
        construirResumen();
        refrescar();
    }

    private void construirSeleccion() {
        JPanel norte = new JPanel(new BorderLayout(5, 5));

        JPanel cliente = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        cliente.setBorder(BorderFactory.createTitledBorder("Cliente"));
        comboClientes = new JComboBox<>();
        cliente.add(new JLabel("Seleccionar cliente:"));
        cliente.add(comboClientes);
        norte.add(cliente, BorderLayout.NORTH);

        JPanel item = new JPanel(new GridLayout(2, 1, 5, 5));
        item.setBorder(BorderFactory.createTitledBorder("Agregar plato al pedido"));

        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        comboRestaurantes = new JComboBox<>();
        comboPlatos = new JComboBox<>();
        comboRestaurantes.addActionListener(e -> cargarPlatos());
        fila1.add(new JLabel("Restaurante:"));
        fila1.add(comboRestaurantes);
        fila1.add(new JLabel("Plato:"));
        fila1.add(comboPlatos);

        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        txtCantidad = new JTextField("1", 4);
        JButton btnAgregar = new JButton("Agregar al carrito");
        btnAgregar.addActionListener(e -> agregarAlCarrito());
        fila2.add(new JLabel("Cantidad:"));
        fila2.add(txtCantidad);
        fila2.add(btnAgregar);

        item.add(fila1);
        item.add(fila2);
        norte.add(item, BorderLayout.CENTER);

        add(norte, BorderLayout.NORTH);
    }

    private void construirCarrito() {
        modeloCarrito = new DefaultTableModel(
                new String[]{"Plato", "Cantidad", "Precio unit.", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCarrito = new JTable(modeloCarrito);
        tablaCarrito.setRowHeight(22);
        tablaCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tablaCarrito);
        scroll.setBorder(BorderFactory.createTitledBorder("Carrito"));
        add(scroll, BorderLayout.CENTER);
    }

    private void construirResumen() {
        JPanel sur = new JPanel(new BorderLayout(5, 5));

        JPanel info = new JPanel(new GridLayout(2, 1));
        lblSubtotal = new JLabel("Subtotal platos: $0.00");
        lblRepartidor = new JLabel("Repartidor: (sin asignar)");
        info.add(lblSubtotal);
        info.add(lblRepartidor);
        info.setBorder(BorderFactory.createTitledBorder("Resumen"));
        sur.add(info, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnQuitar = new JButton("Quitar seleccionado");
        JButton btnVaciar = new JButton("Vaciar carrito");
        JButton btnConfirmar = new JButton("Confirmar pedido");
        btnQuitar.addActionListener(e -> quitarSeleccion());
        btnVaciar.addActionListener(e -> vaciarCarrito());
        btnConfirmar.addActionListener(e -> confirmarPedido());
        acciones.add(btnQuitar);
        acciones.add(btnVaciar);
        acciones.add(btnConfirmar);
        sur.add(acciones, BorderLayout.SOUTH);

        add(sur, BorderLayout.SOUTH);
    }

    private void cargarPlatos() {
        comboPlatos.removeAllItems();
        int idx = comboRestaurantes.getSelectedIndex();
        if (idx < 0) {
            return;
        }
        Restaurante r = restauranteCtrl.obtenerPorIndice(idx);
        if (r == null) {
            return;
        }
        for (Plato p : r.getPlatos()) {
            String etiqueta = p.getNombre() + " - $" + p.getPrecio()
                    + (p.isDisponible() ? "" : " (No disponible)");
            comboPlatos.addItem(etiqueta);
        }
    }

    private void agregarAlCarrito() {
        if (comboClientes.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente.");
            return;
        }
        int idxR = comboRestaurantes.getSelectedIndex();
        int idxP = comboPlatos.getSelectedIndex();
        if (idxR < 0 || idxP < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona restaurante y plato.");
            return;
        }
        Restaurante r = restauranteCtrl.obtenerPorIndice(idxR);
        if (r == null || idxP >= r.getPlatos().size()) {
            return;
        }
        Plato p = r.getPlatos().get(idxP);
        int cant;
        try {
            cant = Integer.parseInt(txtCantidad.getText().trim());
            if (cant <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad invalida.");
            return;
        }
        if (!p.verificarStock(cant)) {
            JOptionPane.showMessageDialog(this,
                    "Sin stock suficiente para " + cant + " unidades de " + p.getNombre());
            return;
        }
        carrito.add(new LineaPedido(p, cant, p.getPrecio()));
        modeloCarrito.addRow(new Object[]{
            p.getNombre(), cant, p.getPrecio(), p.getPrecio() * cant
        });
        actualizarSubtotal();
    }

    private void quitarSeleccion() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila < 0) {
            return;
        }
        carrito.remove(fila);
        modeloCarrito.removeRow(fila);
        actualizarSubtotal();
    }

    private void vaciarCarrito() {
        carrito.clear();
        modeloCarrito.setRowCount(0);
        actualizarSubtotal();
    }

    private void actualizarSubtotal() {
        double sub = 0;
        for (LineaPedido l : carrito) {
            sub += l.getCantidad() * l.getPrecioUnitario();
        }
        lblSubtotal.setText("Subtotal platos: $" + sub);
        Repartidor disp = repartidorCtrl.obtenerDisponible();
        lblRepartidor.setText("Repartidor: "
                + (disp != null ? disp.getNombre() : "(sin disponibles)"));
    }

    private void confirmarPedido() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio.");
            return;
        }
        int idxCliente = comboClientes.getSelectedIndex();
        Cliente cliente = clienteCtrl.obtenerPorIndice(idxCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente invalido.");
            return;
        }
        Repartidor repartidor = repartidorCtrl.obtenerDisponible();
        Pedido p = pedidoCtrl.crearPedido(cliente, repartidor, carrito);
        if (p == null) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo crear el pedido (falta stock o datos invalidos).",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String resumen = "PEDIDO #" + p.getId() + " creado\n\n"
                + "Subtotal platos: $" + p.calcularSubtotalPlatos() + "\n"
                + "IVA (15%):       $" + p.calcularIva() + "\n"
                + "Delivery:        $" + p.calcularDelivery() + "\n"
                + "TOTAL:           $" + p.calcularTotal();
        JOptionPane.showMessageDialog(this, resumen,
                "Pedido confirmado", JOptionPane.INFORMATION_MESSAGE);
        carrito = new ArrayList<>();
        modeloCarrito.setRowCount(0);
        actualizarSubtotal();
    }

    public void refrescar() {
        comboClientes.removeAllItems();
        for (Cliente c : clienteCtrl.listar()) {
            comboClientes.addItem(c.getNombre());
        }
        comboRestaurantes.removeAllItems();
        for (Restaurante r : restauranteCtrl.listar()) {
            comboRestaurantes.addItem(r.getNombre());
        }
        actualizarSubtotal();
    }
}
