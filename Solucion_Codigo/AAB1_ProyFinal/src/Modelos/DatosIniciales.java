package Modelos;

/**
 * Carga los datos predefinidos del sistema (restaurantes, platos,
 * ingredientes e inventarios). Separa los DATOS de la LOGICA del main.
 */
public class DatosIniciales {

    public static PatiodeComidas crearPatio() {
        PatiodeComidas patio = new PatiodeComidas("PLAZA NORTE");
        patio.agregarRestaurante(crearMarisqueria());
        patio.agregarRestaurante(crearPollos());
        patio.agregarRestaurante(crearHamburguesas());
        patio.agregarRestaurante(crearPizzeria());
        patio.agregarRestaurante(crearComidaTipica());
        return patio;
    }

    // ================= RESTAURANTE 1 - MARISQUERIA =================
    private static Restaurante crearMarisqueria() {
        // Cada ingrediente se crea UNA sola vez
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

        // Los platos reutilizan esos mismos objetos
        Ingrediente[] ingCeviche = { camaron, limon, cebolla, cilantro };
        Ingrediente[] ingEncebollado = { albacora, yuca, cebolla, tomate, cilantro };
        Ingrediente[] ingArrozMarinero = { arroz, mariscos, pimiento };

        Plato[] platos = new Plato[Restaurante.MAX_PLATOS];
        platos[0] = new Plato("P01", "Ceviche de camaron", 6.50, true, ingCeviche);
        platos[1] = new Plato("P02", "Encebollado", 3.00, true, ingEncebollado);
        platos[2] = new Plato("P03", "Arroz marinero", 7.25, true, ingArrozMarinero);

        // El inventario apunta a los MISMOS ingredientes que usan los platos
        Inventario inventario = new Inventario(new Ingrediente[]{
            camaron, limon, cebolla, cilantro, albacora,
            yuca, tomate, arroz, mariscos, pimiento
        });

        return new Restaurante("R01", "Marisqueria El Puerto", platos, inventario);
    }

    // ================= RESTAURANTE 2 - POLLOS =================
    private static Restaurante crearPollos() {
        Ingrediente pollo = new Ingrediente("Pollo entero", 40);
        Ingrediente especias = new Ingrediente("Especias", 15);
        Ingrediente alitas = new Ingrediente("Alitas", 55);
        Ingrediente salsaBBQ = new Ingrediente("Salsa BBQ", 18);
        Ingrediente papas = new Ingrediente("Papas", 70);

        Ingrediente[] ingPolloBrasa = { pollo, especias };
        Ingrediente[] ingAlitas = { alitas, salsaBBQ, papas };

        Plato[] platos = new Plato[Restaurante.MAX_PLATOS];
        platos[0] = new Plato("P04", "Pollo a la brasa", 12.00, true, ingPolloBrasa);
        platos[1] = new Plato("P05", "Alitas BBQ", 8.50, true, ingAlitas);

        Inventario inventario = new Inventario(new Ingrediente[]{
            pollo, especias, alitas, salsaBBQ, papas
        });

        return new Restaurante("R02", "Brasa Dorada", platos, inventario);
    }

    // ================= RESTAURANTE 3 - HAMBURGUESAS =================
    private static Restaurante crearHamburguesas() {
        Ingrediente pan = new Ingrediente("Pan", 80);
        Ingrediente carne = new Ingrediente("Carne molida", 30);
        Ingrediente queso = new Ingrediente("Queso", 20);
        Ingrediente lechuga = new Ingrediente("Lechuga", 25);
        Ingrediente tomate = new Ingrediente("Tomate", 45);
        Ingrediente salsas = new Ingrediente("Salsas", 12);

        Ingrediente[] ingHamb = { pan, carne, queso, lechuga, tomate, salsas };

        Plato[] platos = new Plato[Restaurante.MAX_PLATOS];
        platos[0] = new Plato("P06", "Hamburguesa clasica", 5.75, true, ingHamb);

        Inventario inventario = new Inventario(new Ingrediente[]{
            pan, carne, queso, lechuga, tomate, salsas
        });

        return new Restaurante("R03", "Burger House", platos, inventario);
    }

    // ================= RESTAURANTE 4 - PIZZERIA =================
    private static Restaurante crearPizzeria() {
        Ingrediente masa = new Ingrediente("Masa", 50);
        Ingrediente salsaTomate = new Ingrediente("Salsa de tomate", 22);
        Ingrediente mozzarella = new Ingrediente("Queso mozzarella", 28);
        Ingrediente albahaca = new Ingrediente("Albahaca", 10);

        Ingrediente[] ingPizza = { masa, salsaTomate, mozzarella, albahaca };

        Plato[] platos = new Plato[Restaurante.MAX_PLATOS];
        platos[0] = new Plato("P07", "Pizza margarita", 9.00, true, ingPizza);

        Inventario inventario = new Inventario(new Ingrediente[]{
            masa, salsaTomate, mozzarella, albahaca
        });

        return new Restaurante("R04", "Pizzeria Bella", platos, inventario);
    }

    // ================= RESTAURANTE 5 - COMIDA TIPICA =================
    private static Restaurante crearComidaTipica() {
        Ingrediente verde = new Ingrediente("Verde", 90);
        Ingrediente chicharron = new Ingrediente("Chicharron", 20);
        Ingrediente queso = new Ingrediente("Queso", 20);
        Ingrediente pollo = new Ingrediente("Pollo", 38);
        Ingrediente arroz = new Ingrediente("Arroz", 100);
        Ingrediente naranjilla = new Ingrediente("Naranjilla", 16);
        Ingrediente cebolla = new Ingrediente("Cebolla", 40);
        Ingrediente culantro = new Ingrediente("Culantro", 14);

        Ingrediente[] ingBolon = { verde, chicharron, queso };
        Ingrediente[] ingSeco = { pollo, arroz, naranjilla, cebolla, culantro };

        Plato[] platos = new Plato[Restaurante.MAX_PLATOS];
        platos[0] = new Plato("P08", "Bolon de verde", 2.50, true, ingBolon);
        platos[1] = new Plato("P09", "Seco de pollo", 4.75, true, ingSeco);

        Inventario inventario = new Inventario(new Ingrediente[]{
            verde, chicharron, queso, pollo, arroz,
            naranjilla, cebolla, culantro
        });

        return new Restaurante("R05", "Sabor Manabita", platos, inventario);
    }
}
