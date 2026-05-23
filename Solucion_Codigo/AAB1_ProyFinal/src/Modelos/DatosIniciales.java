package Modelos;

import java.util.ArrayList;

public class DatosIniciales {

    public static PatiodeComidas sembrar() {
        PatiodeComidas patio = new PatiodeComidas("PLAZA NORTE");
        patio.agregarRestaurante(crearMarisqueria());
        patio.agregarRestaurante(crearPollos());
        patio.agregarRestaurante(crearHamburguesas());
        return patio;
    }

    private static Restaurante crearMarisqueria() {
        Restaurante r = new Restaurante("R01", "Marisqueria El Puerto");

        Ingrediente camaron = new Ingrediente("Camaron", 50);
        Ingrediente limon = new Ingrediente("Limon", 120);
        Ingrediente cebolla = new Ingrediente("Cebolla", 40);
        Ingrediente cilantro = new Ingrediente("Cilantro", 30);
        Ingrediente albacora = new Ingrediente("Albacora", 35);
        Ingrediente yuca = new Ingrediente("Yuca", 60);
        Ingrediente tomate = new Ingrediente("Tomate", 45);
        Ingrediente arroz = new Ingrediente("Arroz", 100);
        Ingrediente mariscos = new Ingrediente("Mariscos mixtos", 25);
        Ingrediente pimiento = new Ingrediente("Pimiento", 20);

        ArrayList<Ingrediente> ingCeviche = new ArrayList<>();
        ingCeviche.add(camaron);
        ingCeviche.add(limon);
        ingCeviche.add(cebolla);
        ingCeviche.add(cilantro);

        ArrayList<Ingrediente> ingEncebollado = new ArrayList<>();
        ingEncebollado.add(albacora);
        ingEncebollado.add(yuca);
        ingEncebollado.add(cebolla);
        ingEncebollado.add(tomate);
        ingEncebollado.add(cilantro);

        ArrayList<Ingrediente> ingArrozMarinero = new ArrayList<>();
        ingArrozMarinero.add(arroz);
        ingArrozMarinero.add(mariscos);
        ingArrozMarinero.add(pimiento);

        Plato ceviche = new Plato("P01", "Ceviche de camaron", 6.50, true, ingCeviche);
        Plato encebollado = new Plato("P02", "Encebollado", 3.00, true, ingEncebollado);
        Plato arrozMar = new Plato("P03", "Arroz marinero", 7.25, true, ingArrozMarinero);

        r.agregarPlato(ceviche);
        r.agregarPlato(encebollado);
        r.agregarPlato(arrozMar);

        ArrayList<Plato> platosCombo = new ArrayList<>();
        platosCombo.add(ceviche);
        platosCombo.add(encebollado);
        r.crearCombo("C01", "Combo del mar", 8.50, platosCombo);

        return r;
    }

    private static Restaurante crearPollos() {
        Restaurante r = new Restaurante("R02", "Brasa Dorada");

        Ingrediente pollo = new Ingrediente("Pollo entero", 40);
        Ingrediente especias = new Ingrediente("Especias", 15);
        Ingrediente alitas = new Ingrediente("Alitas", 55);
        Ingrediente salsaBBQ = new Ingrediente("Salsa BBQ", 18);
        Ingrediente papas = new Ingrediente("Papas", 70);

        ArrayList<Ingrediente> ingPolloBrasa = new ArrayList<>();
        ingPolloBrasa.add(pollo);
        ingPolloBrasa.add(especias);

        ArrayList<Ingrediente> ingAlitas = new ArrayList<>();
        ingAlitas.add(alitas);
        ingAlitas.add(salsaBBQ);
        ingAlitas.add(papas);

        Plato polloBrasa = new Plato("P04", "Pollo a la brasa", 12.00, true, ingPolloBrasa);
        Plato alitasBBQ = new Plato("P05", "Alitas BBQ", 8.50, true, ingAlitas);

        r.agregarPlato(polloBrasa);
        r.agregarPlato(alitasBBQ);

        return r;
    }

    private static Restaurante crearHamburguesas() {
        Restaurante r = new Restaurante("R03", "Burger House");

        Ingrediente pan = new Ingrediente("Pan", 80);
        Ingrediente carne = new Ingrediente("Carne molida", 30);
        Ingrediente queso = new Ingrediente("Queso", 20);
        Ingrediente lechuga = new Ingrediente("Lechuga", 25);
        Ingrediente tomate = new Ingrediente("Tomate", 45);
        Ingrediente salsas = new Ingrediente("Salsas", 12);

        ArrayList<Ingrediente> ingHamb = new ArrayList<>();
        ingHamb.add(pan);
        ingHamb.add(carne);
        ingHamb.add(queso);
        ingHamb.add(lechuga);
        ingHamb.add(tomate);
        ingHamb.add(salsas);

        Plato hamb = new Plato("P06", "Hamburguesa clasica", 5.75, true, ingHamb);
        r.agregarPlato(hamb);

        return r;
    }
}
