package Vista;

import Controlador.InventarioController;
import Controlador.RestauranteController;
import Modelos.Ingrediente;
import Modelos.Plato;
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

public class PanelInventario extends JPanel {

    private final InventarioController inventarioCtrl;
    private final RestauranteController restauranteCtrl;

    private JComboBox<String> comboRestaurantes;
    private JComboBox<String> comboPlatos;
    private DefaultTableModel modeloIngredientes;
    private JTable tablaIngredientes;
    private JTextField txtCantidad;

    public PanelInventario(InventarioController inventarioCtrl, RestauranteController restauranteCtrl) {
        this.inventarioCtrl = inventarioCtrl;
        this.restauranteCtrl = restauranteCtrl;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        construirSelectores();
        construirTabla();
        construirFormulario();
        refrescar();
    }

    private void construirSelectores() {
        JPanel selectores = new JPanel(new GridLayout(2, 2, 5, 5));
        selectores.setBorder(BorderFactory.createTitledBorder("Seleccion"));
        comboRestaurantes = new JComboBox<>();
        comboPlatos = new JComboBox<>();
        comboRestaurantes.addActionListener(e -> cargarPlatos());
        comboPlatos.addActionListener(e -> cargarIngredientes());
        selectores.add(new JLabel("Restaurante:"));
        selectores.add(comboRestaurantes);
        selectores.add(new JLabel("Plato:"));
        selectores.add(comboPlatos);
        add(selectores, BorderLayout.NORTH);
    }

    private void construirTabla() {
        String[] columnas = {"#", "Ingrediente", "Stock actual"};
        modeloIngredientes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaIngredientes = new JTable(modeloIngredientes);
        tablaIngredientes.setRowHeight(22);
        tablaIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tablaIngredientes);
        scroll.setBorder(BorderFactory.createTitledBorder("Ingredientes del plato"));
        add(scroll, BorderLayout.CENTER);
    }

    private void construirFormulario() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        form.setBorder(BorderFactory.createTitledBorder("Registrar entrada de stock"));
        txtCantidad = new JTextField(8);
        JButton btnEntrada = new JButton("Registrar entrada");
        btnEntrada.addActionListener(e -> registrarEntrada());
        form.add(new JLabel("Cantidad:"));
        form.add(txtCantidad);
        form.add(btnEntrada);
        add(form, BorderLayout.SOUTH);
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
            comboPlatos.addItem(p.getNombre());
        }
    }

    private void cargarIngredientes() {
        modeloIngredientes.setRowCount(0);
        Plato p = platoSeleccionado();
        if (p == null) {
            return;
        }
        ArrayList<Ingrediente> ings = p.getIngredientes();
        for (int i = 0; i < ings.size(); i++) {
            Ingrediente ing = ings.get(i);
            modeloIngredientes.addRow(new Object[]{
                i + 1,
                ing.getNombre(),
                ing.getStockActual()
            });
        }
    }

    private Plato platoSeleccionado() {
        int idxR = comboRestaurantes.getSelectedIndex();
        int idxP = comboPlatos.getSelectedIndex();
        if (idxR < 0 || idxP < 0) {
            return null;
        }
        Restaurante r = restauranteCtrl.obtenerPorIndice(idxR);
        if (r == null) {
            return null;
        }
        ArrayList<Plato> platos = r.getPlatos();
        if (idxP >= platos.size()) {
            return null;
        }
        return platos.get(idxP);
    }

    private void registrarEntrada() {
        Plato p = platoSeleccionado();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Selecciona restaurante y plato.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int filaIng = tablaIngredientes.getSelectedRow();
        if (filaIng < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un ingrediente de la tabla.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int cant;
        try {
            cant = Integer.parseInt(txtCantidad.getText().trim());
            if (cant <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad invalida.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Ingrediente ing = p.getIngredientes().get(filaIng);
        boolean ok = inventarioCtrl.registrarEntradaDirecta(ing, cant);
        if (ok) {
            txtCantidad.setText("");
            cargarIngredientes();
            JOptionPane.showMessageDialog(this, "Stock actualizado.",
                    "OK", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el stock.",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void refrescar() {
        comboRestaurantes.removeAllItems();
        ArrayList<Restaurante> rests = restauranteCtrl.listar();
        for (Restaurante r : rests) {
            comboRestaurantes.addItem(r.getNombre());
        }
    }
}
