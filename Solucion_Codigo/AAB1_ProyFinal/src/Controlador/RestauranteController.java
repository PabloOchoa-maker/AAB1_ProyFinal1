package Controlador;

import Modelos.Combo;
import Modelos.PatiodeComidas;
import Modelos.Plato;
import Modelos.Restaurante;
import java.util.ArrayList;

public class RestauranteController {

    private PatiodeComidas patio;

    public RestauranteController(PatiodeComidas patio) {
        this.patio = patio;
    }

    public ArrayList<Restaurante> listar() {
        return patio.getRestaurantes();
    }

    public Restaurante obtenerPorIndice(int indice) {
        ArrayList<Restaurante> r = patio.getRestaurantes();
        if (indice < 0 || indice >= r.size()) {
            return null;
        }
        return r.get(indice);
    }

    public ArrayList<Plato> listarPlatosDe(int indiceRestaurante) {
        Restaurante r = obtenerPorIndice(indiceRestaurante);
        if (r == null) {
            return new ArrayList<>();
        }
        return r.getPlatos();
    }

    public ArrayList<Combo> listarCombosDe(int indiceRestaurante) {
        Restaurante r = obtenerPorIndice(indiceRestaurante);
        if (r == null) {
            return new ArrayList<>();
        }
        return r.getCombos();
    }

    public String getNombrePatio() {
        return patio.getNombre();
    }

    public boolean hayRestaurantes() {
        return !patio.getRestaurantes().isEmpty();
    }
}
