package Vista;

import Controlador.RepartidorController;
import Modelos.Repartidor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PanelRepartidores extends JPanel {

    private final RepartidorController repartidorCtrl;
    private DefaultTableModel modelo;
    private JTable tabla;

    public PanelRepartidores(RepartidorController repartidorCtrl) {
        this.repartidorCtrl = repartidorCtrl;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        construirTabla();
        construirBotones();
        refrescar();
    }

    private void construirTabla() {
        String[] columnas = {"#", "Nombre", "Estado"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Repartidores"));
        add(scroll, BorderLayout.CENTER);
    }

    private void construirBotones() {
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRegistrar = new JButton("Registrar repartidor");
        JButton btnLiberar = new JButton("Liberar seleccionado");
        btnRegistrar.addActionListener(e -> registrar());
        btnLiberar.addActionListener(e -> liberar());
        acciones.add(btnRegistrar);
        acciones.add(btnLiberar);
        add(acciones, BorderLayout.SOUTH);
    }

    private void registrar() {
        String nombre = JOptionPane.showInputDialog(this,
                "Nombre del repartidor:", "Registrar repartidor",
                JOptionPane.PLAIN_MESSAGE);
        if (nombre == null) {
            return;
        }
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        repartidorCtrl.registrar(nombre);
        refrescar();
    }

    private void liberar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un repartidor de la tabla.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Repartidor r = repartidorCtrl.obtenerPorIndice(fila);
        if (r != null) {
            repartidorCtrl.marcarLibre(r);
            refrescar();
        }
    }

    public void refrescar() {
        modelo.setRowCount(0);
        ArrayList<Repartidor> lista = repartidorCtrl.listar();
        for (int i = 0; i < lista.size(); i++) {
            Repartidor r = lista.get(i);
            modelo.addRow(new Object[]{
                i + 1,
                r.getNombre(),
                r.isDisponible() ? "LIBRE" : "OCUPADO"
            });
        }
    }
}
