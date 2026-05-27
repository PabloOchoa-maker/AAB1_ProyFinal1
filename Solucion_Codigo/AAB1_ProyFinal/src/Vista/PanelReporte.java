package Vista;

import Controlador.ReporteController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelReporte extends JPanel {

    private final ReporteController reporteCtrl;
    private JTextArea txtReporte;

    public PanelReporte(ReporteController reporteCtrl) {
        this.reporteCtrl = reporteCtrl;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        construirArea();
        construirBoton();
        refrescar();
    }

    private void construirArea() {
        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        txtReporte.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(txtReporte);
        scroll.setBorder(BorderFactory.createTitledBorder("Reporte de ventas"));
        add(scroll, BorderLayout.CENTER);
    }

    private void construirBoton() {
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnActualizar = new JButton("Actualizar reporte");
        btnActualizar.addActionListener(e -> refrescar());
        acciones.add(btnActualizar);
        add(acciones, BorderLayout.SOUTH);
    }

    public void refrescar() {
        txtReporte.setText(reporteCtrl.generarReporte());
        txtReporte.setCaretPosition(0);
    }
}
