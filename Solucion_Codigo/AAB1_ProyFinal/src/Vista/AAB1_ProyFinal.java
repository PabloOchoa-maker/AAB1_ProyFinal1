package Vista;

import Controlador.*;
import Modelos.*;

public class AAB1_ProyFinal {

    public static void main(String[] args) {
        EstadoCargado estado = LecturaInformacion.cargarTodo();
        PatiodeComidas patio;

        if (estado.estaVacio()) {
            System.out.println("(Primera ejecucion: sembrando datos iniciales)");
            patio = DatosIniciales.sembrar();
            EscrituraInformacion.guardarTodo(patio, estado.getClientes(), estado.getRepartidores(), estado.getPedidos());
        } else {
            patio = estado.getPatio();
        }

        InventarioController inventarioCtrl = new InventarioController(patio, estado.getClientes(), estado.getRepartidores(), estado.getPedidos());
        ClienteController clienteCtrl = new ClienteController(estado.getClientes(), patio, estado.getRepartidores(), estado.getPedidos());
        RepartidorController repartidorCtrl = new RepartidorController(estado.getRepartidores(), patio, estado.getClientes(), estado.getPedidos());
        RestauranteController restauranteCtrl = new RestauranteController(patio);
        PedidoController pedidoCtrl = new PedidoController(estado.getPedidos(), estado.getClientes(), estado.getRepartidores(), patio, inventarioCtrl);
        ReporteController reporteCtrl = new ReporteController(estado.getPedidos(), patio);

        Vista vista = new Vista(clienteCtrl, repartidorCtrl, restauranteCtrl, inventarioCtrl, pedidoCtrl, reporteCtrl);
        vista.iniciar();
    }
}
