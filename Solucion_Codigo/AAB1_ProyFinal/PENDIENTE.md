# AAB1_ProyFinal — Qué falta por hacer

> Sistema de Gestión de Delivery y Pedidos para un Patio de Comidas (Tema 6).
> Estado revisado: el **menú de navegación** funciona; la **lógica de negocio** está
> casi toda en placeholder (`return 0`, `return null`, métodos vacíos).

---

## 1. Bugs a corregir YA (el menú actual falla)

### `AAB1_ProyFinal.java`
- ~~**Línea 36** — validación con `<` en vez de `<=` y `opc` en vez de `opc1`.~~
  ✅ **CORREGIDO** — ahora `if (opc1 <= patio.getRestaurantesExistentes() && opc1 > 0)`.
- **El `for` de mostrar platos** — el `for` no sirve para nada: itera pero hace `break`
  siempre en la primera vuelta. Reemplazar por la llamada directa:
  ```java
  patio.getRestaurantes()[opc1 - 1].mostrarPlatos();
  ```
- **`nextInt()` + texto** — cuando empieces a leer nombres de cliente con
  `nextLine()`, el `nextInt()` deja el salto de línea pegado. Hacer
  `tcl.nextLine();` después de cada `nextInt()` antes de leer Strings.

### `Restaurante.java` — `mostrarPlatos()` (línea 21)
- `while(platos[i] != null)` revienta con `ArrayIndexOutOfBoundsException`
  si el arreglo se llena. Corregir la condición:
  ```java
  while (i < platos.length && platos[i] != null)
  ```

---

## 2. Métodos sin lógica (devuelven valor falso)

| Clase | Método | Qué debe hacer |
|---|---|---|
| `Ingrediente` | `hayStock()` | `return stockActual > 0;` |
| `Ingrediente` | `reducirStock(int cantidad)` | `stockActual -= cantidad;` (validar que no quede negativo) |
| `Plato` | `verificarStock()` | recorrer `ingredientes[]` y devolver `false` si alguno no tiene stock |
| `LineaPedido` | `getSubtotal()` | `return cantidad * precioUnitario;` |
| `Pedido` | `calcularTotal()` | suma de `getSubtotal()` de cada línea + impuesto + delivery |
| `Pedido` | `calcularDelivery()` | tarifa según `distanciaKm` (definir la fórmula) |
| `Restaurante` | `calcularTotal()` | facturación total del restaurante (suma de sus pedidos) |
| `Restaurante` | `getPlatoMasVendido()` | el plato con más unidades vendidas |
| `Inventario` | `registrarEntrada(Ingrediente i)` | sumar stock al ingrediente que llega |
| `Inventario` | `resumenInventario()` | imprimir cada ingrediente con su stock |
| `Inventario` | `getResumenDiario()` | devolver el resumen como String |
| `PatiodeComidas` | `generarReporte()` | reporte global: restaurante que más facturó + plato estrella |
| `Repartidor` | `asignarPedido(Pedido p)` | enlazar repartidor ↔ pedido, marcar no disponible |

---

## 3. Funcionalidad del menú por completar

### Opción 1 — RESTAURANTES Y PEDIDOS
Hoy solo muestra los platos. Falta el flujo completo de pedido:
1. Elegir restaurante ✅ (ya está)
2. Mostrar platos ✅ (ya está, pero con el bug del `for`)
3. **Falta:** elegir plato + cantidad
4. **Falta:** verificar stock antes de aceptar (`Plato.verificarStock()`)
5. **Falta:** crear `LineaPedido` y acumular en un `LineaPedido[]`
6. **Falta:** pedir datos del `Cliente` (nombre, dirección, teléfono)
7. **Falta:** pedir `distanciaKm` para el delivery
8. **Falta:** crear el `Pedido`, calcular total + impuesto + delivery
9. **Falta:** reducir stock de los ingredientes (`reducirStock`)
10. **Falta:** guardar el pedido en un arreglo de pedidos

### Opción 2 — GESTIÓN DE CLIENTES
- `case 2:` está vacío. Falta: registrar cliente, listar clientes.
- No existe ningún arreglo de `Cliente[]` en `main`.

### Opción 3 — GESTIÓN DE INVENTARIO
- `case 3:` está vacío. Falta: elegir restaurante, registrar entrada de
  ingredientes (`registrarEntrada`), ver resumen (`resumenInventario`).

### Opción 4 — REPORTE DE VENTAS Y PLATO MÁS VENDIDO
- `case 4:` está vacío. Falta llamar a `patio.generarReporte()` y mostrarlo.

---

## 4. Cosas que aún NO existen en el proyecto

- **Arreglo de pedidos** en `main` (algo como `Pedido[] pedidos = new Pedido[N]`)
  con su contador. Sin esto no se puede registrar ni reportar nada.
- **Clientes y Repartidores**: las clases existen pero nunca se instancian.
  Decidir si se piden por teclado o se precargan en `DatosIniciales`.
- **Impuesto de ley**: el enunciado lo pide. Definir el porcentaje (constante).
- **Fórmula del delivery**: el enunciado dice que varía según los km.
  Definir la regla (ej. tarifa base + valor por km).

---

## 5. Serialización (.dat) — pendiente, para el final

> Hacer esto al final, cuando la lógica funcione. No cambia nada existente.

- Añadir `implements Serializable` + `serialVersionUID = 1L` a las 9 clases
  de dominio (Ingrediente, Plato, Inventario, Restaurante, PatiodeComidas,
  Cliente, Repartidor, LineaPedido, Pedido). `Calculable` no se toca.
- Crear `GestorArchivos.java` con `guardarPatio` / `cargarPatio` y
  `guardarPedidos` / `cargarPedidos`.
- En `main`: cargar desde `.dat` al iniciar (si no existe → `DatosIniciales`),
  y guardar al elegir SALIR.

---

## Orden sugerido para terminar

1. Corregir los **bugs de la sección 1** (rompen el menú actual).
2. Métodos simples de la sección 2: `hayStock`, `reducirStock`,
   `verificarStock`, `getSubtotal`. Son una o dos líneas cada uno.
3. Crear el arreglo de pedidos y completar el **flujo de la opción 1**.
4. Opciones 2, 3 y 4 del menú.
5. `calcularTotal` / reportes / plato más vendido.
6. Serialización (sección 5) al final.
