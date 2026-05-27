package Vista;

import Controlador.RestauranteController;
import Modelos.Combo;
import Modelos.Ingrediente;
import Modelos.Plato;
import Modelos.Restaurante;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelRestaurantes extends JPanel {

    private final RestauranteController restauranteCtrl;

    private DefaultListModel<String> modeloLista;
    private JList<String> listaRestaurantes;
    private DefaultTableModel modeloPlatos;
    private DefaultTableModel modeloCombos;
    private JTable tablaPlatos;
    private JTable tablaCombos;

    public PanelRestaurantes(RestauranteController restauranteCtrl) {
        this.restauranteCtrl = restauranteCtrl;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        construirContenido();
        refrescar();
    }

    private void construirContenido() {
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                construirPanelIzquierdo(), construirPanelDerecho());
        split.setDividerLocation(220);
        add(split, BorderLayout.CENTER);
    }

    private JPanel construirPanelIzquierdo() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Restaurantes"));

        modeloLista = new DefaultListModel<>();
        listaRestaurantes = new JList<>(modeloLista);
        listaRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaRestaurantes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPlatosYCombos();
            }
        });
        panel.add(new JScrollPane(listaRestaurantes), BorderLayout.CENTER);

        JButton btnAgregar = new JButton("Agregar restaurante");
        btnAgregar.addActionListener(e -> agregarRestaurante());
        panel.add(btnAgregar, BorderLayout.SOUTH);
        return panel;
    }

    private JTabbedPane construirPanelDerecho() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Platos", construirPanelPlatos());
        tabs.addTab("Combos", construirPanelCombos());
        return tabs;
    }

    private JPanel construirPanelPlatos() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        modeloPlatos = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Disponible"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPlatos = new JTable(modeloPlatos);
        tablaPlatos.setRowHeight(22);
        panel.add(new JScrollPane(tablaPlatos), BorderLayout.CENTER);

        JButton btnPlato = new JButton("Agregar plato");
        btnPlato.addActionListener(e -> agregarPlato());
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.add(btnPlato);
        panel.add(acciones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel construirPanelCombos() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        modeloCombos = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCombos = new JTable(modeloCombos);
        tablaCombos.setRowHeight(22);
        panel.add(new JScrollPane(tablaCombos), BorderLayout.CENTER);

        JButton btnCombo = new JButton("Registrar combo");
        btnCombo.addActionListener(e -> registrarCombo());
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.add(btnCombo);
        panel.add(acciones, BorderLayout.SOUTH);
        return panel;
    }

    private int indiceRestauranteSeleccionado() {
        return listaRestaurantes.getSelectedIndex();
    }

    private void cargarPlatosYCombos() {
        modeloPlatos.setRowCount(0);
        modeloCombos.setRowCount(0);
        int idx = indiceRestauranteSeleccionado();
        if (idx < 0) {
            return;
        }
        Restaurante r = restauranteCtrl.obtenerPorIndice(idx);
        if (r == null) {
            return;
        }
        for (Plato p : r.getPlatos()) {
            modeloPlatos.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getPrecio(),
                p.isDisponible() ? "Si" : "No"
            });
        }
        for (Combo c : r.getCombos()) {
            modeloCombos.addRow(new Object[]{
                c.getId(), c.getNombre(), c.getPrecio()
            });
        }
    }

    private void agregarRestaurante() {
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        int op = JOptionPane.showConfirmDialog(this, form, "Agregar restaurante",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (op != JOptionPane.OK_OPTION) {
            return;
        }
        String mensaje = restauranteCtrl.registrarRestaurante(
                txtId.getText(), txtNombre.getText());
        JOptionPane.showMessageDialog(this, mensaje);
        refrescar();
    }

    private void agregarPlato() {
        int idxRest = indiceRestauranteSeleccionado();
        if (idxRest < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un restaurante.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DialogoPlato dialogo = new DialogoPlato();
        dialogo.setVisible(true);
        if (!dialogo.confirmado) {
            return;
        }
        String mensaje = restauranteCtrl.registrarPlato(
                idxRest, dialogo.id, dialogo.nombre, dialogo.precio,
                dialogo.disponible, dialogo.ingredientes);
        JOptionPane.showMessageDialog(this, mensaje);
        cargarPlatosYCombos();
    }

    private void registrarCombo() {
        int idxRest = indiceRestauranteSeleccionado();
        if (idxRest < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un restaurante.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Restaurante r = restauranteCtrl.obtenerPorIndice(idxRest);
        if (r == null || r.getPlatos().size() < 2) {
            JOptionPane.showMessageDialog(this,
                    "El restaurante necesita al menos 2 platos para crear un combo.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();

        DefaultListModel<String> modeloPlatosCombo = new DefaultListModel<>();
        ArrayList<Plato> platos = r.getPlatos();
        for (Plato p : platos) {
            modeloPlatosCombo.addElement(p.getNombre() + " ($" + p.getPrecio() + ")");
        }
        JList<String> listaPlatos = new JList<>(modeloPlatosCombo);
        listaPlatos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPlatos = new JScrollPane(listaPlatos);
        scrollPlatos.setPreferredSize(new Dimension(280, 140));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.add(filaFormulario("ID:", txtId));
        form.add(filaFormulario("Nombre:", txtNombre));
        form.add(filaFormulario("Precio:", txtPrecio));
        form.add(new JLabel("Selecciona platos (Ctrl+click, minimo 2):"));
        form.add(scrollPlatos);

        int op = JOptionPane.showConfirmDialog(this, form, "Registrar combo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (op != JOptionPane.OK_OPTION) {
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio invalido.");
            return;
        }
        int[] seleccionados = listaPlatos.getSelectedIndices();
        ArrayList<Integer> indices = new ArrayList<>();
        for (int s : seleccionados) {
            indices.add(s);
        }
        String mensaje = restauranteCtrl.registrarCombo(idxRest,
                txtId.getText(), txtNombre.getText(), precio, indices);
        JOptionPane.showMessageDialog(this, mensaje);
        cargarPlatosYCombos();
    }

    private JPanel filaFormulario(String etiqueta, JTextField campo) {
        JPanel fila = new JPanel(new BorderLayout(5, 0));
        JLabel lbl = new JLabel(etiqueta, SwingConstants.LEFT);
        lbl.setPreferredSize(new Dimension(70, 24));
        fila.add(lbl, BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        return fila;
    }

    public void refrescar() {
        modeloLista.clear();
        ArrayList<Restaurante> rests = restauranteCtrl.listar();
        for (Restaurante r : rests) {
            modeloLista.addElement(r.getNombre());
        }
        if (!modeloLista.isEmpty()) {
            listaRestaurantes.setSelectedIndex(0);
        } else {
            modeloPlatos.setRowCount(0);
            modeloCombos.setRowCount(0);
        }
    }

    // Dialogo interno para crear plato con ingredientes
    private class DialogoPlato extends JDialog {

        boolean confirmado = false;
        String id;
        String nombre;
        double precio;
        boolean disponible;
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();

        private JTextField txtId = new JTextField();
        private JTextField txtNombre = new JTextField();
        private JTextField txtPrecio = new JTextField();
        private JCheckBox chkDisponible = new JCheckBox("Disponible", true);
        private DefaultTableModel modeloIng;
        private JTable tablaIng;
        private JTextField txtNombreIng = new JTextField();
        private JTextField txtStockIng = new JTextField();

        DialogoPlato() {
            super((java.awt.Frame) null, "Agregar plato", true);
            setLayout(new BorderLayout(10, 10));
            ((JPanel) getContentPane()).setBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel datos = new JPanel(new GridLayout(4, 2, 5, 5));
            datos.setBorder(BorderFactory.createTitledBorder("Datos del plato"));
            datos.add(new JLabel("ID:"));
            datos.add(txtId);
            datos.add(new JLabel("Nombre:"));
            datos.add(txtNombre);
            datos.add(new JLabel("Precio:"));
            datos.add(txtPrecio);
            datos.add(new JLabel(""));
            datos.add(chkDisponible);
            add(datos, BorderLayout.NORTH);

            JPanel panelIng = new JPanel(new BorderLayout(5, 5));
            panelIng.setBorder(BorderFactory.createTitledBorder("Ingredientes"));
            modeloIng = new DefaultTableModel(new String[]{"Nombre", "Stock"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaIng = new JTable(modeloIng);
            tablaIng.setRowHeight(22);
            JScrollPane sc = new JScrollPane(tablaIng);
            sc.setPreferredSize(new Dimension(360, 120));
            panelIng.add(sc, BorderLayout.CENTER);

            JPanel filaIng = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            txtNombreIng.setColumns(12);
            txtStockIng.setColumns(6);
            JButton btnAddIng = new JButton("+ Agregar");
            btnAddIng.addActionListener(e -> agregarIngrediente());
            filaIng.add(new JLabel("Nombre:"));
            filaIng.add(txtNombreIng);
            filaIng.add(new JLabel("Stock:"));
            filaIng.add(txtStockIng);
            filaIng.add(btnAddIng);
            panelIng.add(filaIng, BorderLayout.SOUTH);
            add(panelIng, BorderLayout.CENTER);

            JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnOk = new JButton("Aceptar");
            JButton btnCancel = new JButton("Cancelar");
            btnOk.addActionListener(e -> aceptar());
            btnCancel.addActionListener(e -> dispose());
            acciones.add(btnOk);
            acciones.add(btnCancel);
            add(acciones, BorderLayout.SOUTH);

            pack();
            setLocationRelativeTo(PanelRestaurantes.this);
        }

        private void agregarIngrediente() {
            String n = txtNombreIng.getText().trim();
            String s = txtStockIng.getText().trim();
            if (n.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre del ingrediente requerido.");
                return;
            }
            int stock;
            try {
                stock = Integer.parseInt(s);
                if (stock < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stock invalido.");
                return;
            }
            ingredientes.add(new Ingrediente(n, stock));
            modeloIng.addRow(new Object[]{n, stock});
            txtNombreIng.setText("");
            txtStockIng.setText("");
        }

        private void aceptar() {
            id = txtId.getText().trim();
            nombre = txtNombre.getText().trim();
            disponible = chkDisponible.isSelected();
            try {
                precio = Double.parseDouble(txtPrecio.getText().trim().replace(',', '.'));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio invalido.");
                return;
            }
            confirmado = true;
            dispose();
        }
    }
}
