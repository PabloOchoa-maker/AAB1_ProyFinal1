package Vista;

import Controlador.ClienteController;
import Modelos.Cliente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelClientes extends JPanel {

    private final ClienteController clienteCtrl;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtKm;

    public PanelClientes(ClienteController clienteCtrl) {
        this.clienteCtrl = clienteCtrl;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        construirTabla();
        construirFormulario();
        refrescar();
    }

    private void construirTabla() {
        String[] columnas = {"#", "Nombre", "Direccion", "Distancia (km)"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Clientes registrados"));
        add(scroll, BorderLayout.CENTER);
    }

    private void construirFormulario() {
        JPanel form = new JPanel(new GridLayout(2, 1, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Registrar nuevo cliente"));

        JPanel campos = new JPanel(new GridLayout(1, 6, 5, 5));
        txtNombre = new JTextField();
        txtDireccion = new JTextField();
        txtKm = new JTextField();
        campos.add(new JLabel("Nombre:"));
        campos.add(txtNombre);
        campos.add(new JLabel("Direccion:"));
        campos.add(txtDireccion);
        campos.add(new JLabel("Distancia km:"));
        campos.add(txtKm);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRegistrar = new JButton("Registrar cliente");
        btnRegistrar.addActionListener(e -> registrar());
        acciones.add(btnRegistrar);

        form.add(campos);
        form.add(acciones);
        add(form, BorderLayout.SOUTH);
    }

    private void registrar() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String sKm = txtKm.getText().trim().replace(',', '.');

        if (nombre.isEmpty() || direccion.isEmpty() || sKm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double km;
        try {
            km = Double.parseDouble(sKm);
            if (km < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Distancia invalida.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        clienteCtrl.registrar(nombre, direccion, km);
        txtNombre.setText("");
        txtDireccion.setText("");
        txtKm.setText("");
        refrescar();
        JOptionPane.showMessageDialog(this, "Cliente registrado.",
                "OK", JOptionPane.INFORMATION_MESSAGE);
    }

    public void refrescar() {
        modelo.setRowCount(0);
        ArrayList<Cliente> lista = clienteCtrl.listar();
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            modelo.addRow(new Object[]{
                i + 1,
                c.getNombre(),
                c.getDireccion(),
                c.getDistanciaKm()
            });
        }
    }
}
