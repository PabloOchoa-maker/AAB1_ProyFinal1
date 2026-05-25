# -*- coding: utf-8 -*-
"""
Generador de UML (.dia) para el proyecto AAB1_ProyFinal.
Produce uml_diagrama.dia compatible con Dia (formato XML plano, namespace lysator).
"""

import os

# ----------------------------------------------------------------------
# Visibilidad UML (enum Dia)
PUB, PRI, PRO = 0, 1, 2

# ----------------------------------------------------------------------
# Datos: cada clase es (nombre, package, stereotype, abstract, attrs, ops)
# attrs: lista de (visibilidad, nombre, tipo)
# ops:   lista de (visibilidad, nombre, tipo_retorno, [(nombre_param, tipo_param), ...])
# ----------------------------------------------------------------------

CLASES = [
    # ---------------- MODELOS ----------------
    ("Calculable", "Modelos", "interface", True,
     [],
     [(PUB, "calcularTotal", "double", [])]),

    ("Cliente", "Modelos", "", False,
     [(PRI, "nombre", "String"),
      (PRI, "direccion", "String"),
      (PRI, "distanciaKm", "double")],
     [(PUB, "Cliente", "", [("nombre", "String"), ("direccion", "String"), ("distanciaKm", "double")])]),

    ("Combo", "Modelos", "", False,
     [(PRI, "id", "String"),
      (PRI, "nombre", "String"),
      (PRI, "precio", "double"),
      (PRI, "platos", "ArrayList<Plato>")],
     [(PUB, "Combo", "", [("id", "String"), ("nombre", "String"), ("precio", "double")]),
      (PUB, "Combo", "", [("id", "String"), ("nombre", "String"), ("precio", "double"), ("platos", "ArrayList<Plato>")]),
      (PUB, "agregarPlato", "void", [("p", "Plato")]),
      (PUB, "getAhorroVsPlatosSueltos", "double", []),
      (PUB, "descripcion", "String", [])]),

    ("DatosIniciales", "Modelos", "utility", False,
     [],
     [(PUB, "sembrar", "PatiodeComidas", []),
      (PRI, "crearMarisqueria", "Restaurante", []),
      (PRI, "crearPollos", "Restaurante", []),
      (PRI, "crearHamburguesas", "Restaurante", [])]),

    ("EstadoCargado", "Modelos", "", False,
     [(PRI, "patio", "PatiodeComidas"),
      (PRI, "clientes", "ArrayList<Cliente>"),
      (PRI, "repartidores", "ArrayList<Repartidor>"),
      (PRI, "pedidos", "ArrayList<Pedido>")],
     [(PUB, "EstadoCargado", "", [("patio", "PatiodeComidas"), ("clientes", "ArrayList<Cliente>"),
                                  ("repartidores", "ArrayList<Repartidor>"), ("pedidos", "ArrayList<Pedido>")]),
      (PUB, "estaVacio", "boolean", [])]),

    ("Ingrediente", "Modelos", "", False,
     [(PRI, "nombre", "String"),
      (PRI, "stockActual", "int")],
     [(PUB, "Ingrediente", "", [("nombre", "String"), ("stockActual", "int")]),
      (PUB, "hayStock", "boolean", [("cantidad", "int")]),
      (PUB, "reducirStock", "void", [("cantidad", "int")]),
      (PUB, "registrarEntrada", "void", [("cantidad", "int")]),
      (PUB, "resumenInventario", "String", [])]),

    ("LineaPedido", "Modelos", "", False,
     [(PRI, "plato", "Plato"),
      (PRI, "cantidad", "int"),
      (PRI, "precioUnitario", "double")],
     [(PUB, "LineaPedido", "", [("plato", "Plato"), ("cantidad", "int"), ("precioUnitario", "double")]),
      (PUB, "getSubtotal", "double", [])]),

    ("PatiodeComidas", "Modelos", "", False,
     [(PRI, "nombre", "String"),
      (PRI, "restaurantes", "ArrayList<Restaurante>")],
     [(PUB, "PatiodeComidas", "", [("nombre", "String")]),
      (PUB, "PatiodeComidas", "", [("nombre", "String"), ("restaurantes", "ArrayList<Restaurante>")]),
      (PUB, "agregarRestaurante", "void", [("r", "Restaurante")]),
      (PUB, "mostrarRestaurantes", "void", []),
      (PUB, "buscarPorId", "Restaurante", [("idRestaurante", "String")]),
      (PUB, "buscarPlatoPorId", "Plato", [("idPlato", "String")]),
      (PUB, "buscarComboPorId", "Combo", [("idCombo", "String")]),
      (PUB, "buscarIngredientePorNombre", "Ingrediente", [("nombreIngrediente", "String")]),
      (PUB, "getRestaurantesExistentes", "int", [])]),

    ("Pedido", "Modelos", "", False,
     [(PRI, "id", "int"),
      (PRI, "lineas", "ArrayList<LineaPedido>"),
      (PRI, "cliente", "Cliente"),
      (PRI, "repartidor", "Repartidor"),
      (PRI, "tarifaBaseDelivery", "double"),
      (PRI, "costoPorKm", "double")],
     [(PUB, "Pedido", "", [("lineas", "ArrayList<LineaPedido>"), ("cliente", "Cliente"), ("repartidor", "Repartidor")]),
      (PUB, "Pedido", "", [("id", "int"), ("lineas", "ArrayList<LineaPedido>"), ("cliente", "Cliente"), ("repartidor", "Repartidor")]),
      (PUB, "calcularTotal", "double", []),
      (PUB, "calcularSubtotalPlatos", "double", []),
      (PUB, "calcularDelivery", "double", [])]),

    ("PersistenciaEstado", "Modelos", "utility", False,
     [(PRI, "carpetaDatos", "String"),
      (PRI, "archivoEstado", "String")],
     [(PUB, "guardar", "void", [("estado", "EstadoCargado")]),
      (PUB, "cargar", "EstadoCargado", []),
      (PRI, "asegurarCarpeta", "void", []),
      (PRI, "estadoVacio", "EstadoCargado", [])]),

    ("Plato", "Modelos", "", False,
     [(PRI, "id", "String"),
      (PRI, "nombre", "String"),
      (PRI, "precio", "double"),
      (PRI, "disponible", "boolean"),
      (PRI, "ingredientes", "ArrayList<Ingrediente>")],
     [(PUB, "Plato", "", [("id", "String"), ("nombre", "String"), ("precio", "double"), ("disponible", "boolean")]),
      (PUB, "Plato", "", [("id", "String"), ("nombre", "String"), ("precio", "double"),
                          ("disponible", "boolean"), ("ingredientes", "ArrayList<Ingrediente>")]),
      (PUB, "agregarIngrediente", "void", [("ing", "Ingrediente")]),
      (PUB, "verificarStock", "boolean", [("cantidadPlatos", "int")]),
      (PUB, "descontarStockPor", "void", [("cantidadPlatos", "int")])]),

    ("Repartidor", "Modelos", "", False,
     [(PRI, "nombre", "String"),
      (PRI, "disponible", "boolean")],
     [(PUB, "Repartidor", "", [("nombre", "String")]),
      (PUB, "Repartidor", "", [("nombre", "String"), ("disponible", "boolean")]),
      (PUB, "asignarPedido", "void", []),
      (PUB, "liberar", "void", [])]),

    ("Restaurante", "Modelos", "", False,
     [(PRI, "id", "String"),
      (PRI, "nombre", "String"),
      (PRI, "platos", "ArrayList<Plato>"),
      (PRI, "combos", "ArrayList<Combo>")],
     [(PUB, "Restaurante", "", [("id", "String"), ("nombre", "String")]),
      (PUB, "Restaurante", "", [("id", "String"), ("nombre", "String"), ("platos", "ArrayList<Plato>")]),
      (PUB, "Restaurante", "", [("id", "String"), ("nombre", "String"),
                                ("platos", "ArrayList<Plato>"), ("combos", "ArrayList<Combo>")]),
      (PUB, "agregarPlato", "void", [("p", "Plato")]),
      (PUB, "agregarCombo", "void", [("c", "Combo")]),
      (PUB, "crearCombo", "Combo", [("id", "String"), ("nombre", "String"),
                                    ("precio", "double"), ("platosDelCombo", "ArrayList<Plato>")]),
      (PUB, "mostrarPlatos", "void", []),
      (PUB, "mostrarCombos", "void", []),
      (PUB, "buscarPlatoPorId", "Plato", [("idPlato", "String")]),
      (PUB, "buscarComboPorId", "Combo", [("idCombo", "String")]),
      (PUB, "getTodosLosIngredientes", "ArrayList<Ingrediente>", []),
      (PUB, "calcularTotal", "double", [])]),

    # ---------------- CONTROLADORES ----------------
    ("ClienteController", "Controlador", "", False,
     [(PRI, "clientes", "ArrayList<Cliente>"),
      (PRI, "patio", "PatiodeComidas"),
      (PRI, "repartidores", "ArrayList<Repartidor>"),
      (PRI, "pedidos", "ArrayList<Pedido>")],
     [(PUB, "ClienteController", "", [("clientes", "ArrayList<Cliente>"), ("patio", "PatiodeComidas"),
                                      ("repartidores", "ArrayList<Repartidor>"), ("pedidos", "ArrayList<Pedido>")]),
      (PUB, "registrar", "Cliente", [("nombre", "String"), ("direccion", "String"), ("distanciaKm", "double")]),
      (PUB, "listar", "ArrayList<Cliente>", []),
      (PUB, "buscarPorNombre", "Cliente", [("nombre", "String")]),
      (PUB, "obtenerPorIndice", "Cliente", [("indice", "int")]),
      (PUB, "hayClientes", "boolean", [])]),

    ("InventarioController", "Controlador", "", False,
     [(PRI, "patio", "PatiodeComidas"),
      (PRI, "clientes", "ArrayList<Cliente>"),
      (PRI, "repartidores", "ArrayList<Repartidor>"),
      (PRI, "pedidos", "ArrayList<Pedido>")],
     [(PUB, "InventarioController", "", [("patio", "PatiodeComidas"), ("clientes", "ArrayList<Cliente>"),
                                         ("repartidores", "ArrayList<Repartidor>"), ("pedidos", "ArrayList<Pedido>")]),
      (PUB, "registrarEntrada", "boolean", [("nombreIngrediente", "String"), ("cantidad", "int")]),
      (PUB, "resumenPorRestaurante", "ArrayList<String>", [("indiceRestaurante", "int")]),
      (PUB, "registrarEntradaDirecta", "boolean", [("ing", "Ingrediente"), ("cantidad", "int")]),
      (PUB, "hayStockPara", "boolean", [("lineas", "ArrayList<LineaPedido>")]),
      (PUB, "descontarStockPorPedido", "void", [("lineas", "ArrayList<LineaPedido>")])]),

    ("PedidoController", "Controlador", "", False,
     [(PRI, "pedidos", "ArrayList<Pedido>"),
      (PRI, "clientes", "ArrayList<Cliente>"),
      (PRI, "repartidores", "ArrayList<Repartidor>"),
      (PRI, "patio", "PatiodeComidas"),
      (PRI, "inventarioCtrl", "InventarioController")],
     [(PUB, "PedidoController", "", [("pedidos", "ArrayList<Pedido>"), ("clientes", "ArrayList<Cliente>"),
                                     ("repartidores", "ArrayList<Repartidor>"), ("patio", "PatiodeComidas"),
                                     ("inventarioCtrl", "InventarioController")]),
      (PUB, "crearPedido", "Pedido", [("cliente", "Cliente"), ("repartidor", "Repartidor"),
                                      ("lineas", "ArrayList<LineaPedido>")]),
      (PUB, "listar", "ArrayList<Pedido>", []),
      (PUB, "hayPedidos", "boolean", [])]),

    ("RepartidorController", "Controlador", "", False,
     [(PRI, "repartidores", "ArrayList<Repartidor>"),
      (PRI, "patio", "PatiodeComidas"),
      (PRI, "clientes", "ArrayList<Cliente>"),
      (PRI, "pedidos", "ArrayList<Pedido>")],
     [(PUB, "RepartidorController", "", [("repartidores", "ArrayList<Repartidor>"), ("patio", "PatiodeComidas"),
                                         ("clientes", "ArrayList<Cliente>"), ("pedidos", "ArrayList<Pedido>")]),
      (PUB, "registrar", "Repartidor", [("nombre", "String")]),
      (PUB, "listar", "ArrayList<Repartidor>", []),
      (PUB, "obtenerDisponible", "Repartidor", []),
      (PUB, "obtenerPorIndice", "Repartidor", [("indice", "int")]),
      (PUB, "marcarOcupado", "void", [("r", "Repartidor")]),
      (PUB, "marcarLibre", "void", [("r", "Repartidor")]),
      (PUB, "hayRepartidores", "boolean", [])]),

    ("ReporteController", "Controlador", "", False,
     [(PRI, "pedidos", "ArrayList<Pedido>"),
      (PRI, "patio", "PatiodeComidas")],
     [(PUB, "ReporteController", "", [("pedidos", "ArrayList<Pedido>"), ("patio", "PatiodeComidas")]),
      (PUB, "totalVentas", "double", []),
      (PUB, "totalPedidos", "int", []),
      (PUB, "platoMasVendido", "Plato", []),
      (PUB, "generarReporte", "String", [])]),

    ("RestauranteController", "Controlador", "", False,
     [(PRI, "patio", "PatiodeComidas")],
     [(PUB, "RestauranteController", "", [("patio", "PatiodeComidas")]),
      (PUB, "listar", "ArrayList<Restaurante>", []),
      (PUB, "obtenerPorIndice", "Restaurante", [("indice", "int")]),
      (PUB, "listarPlatosDe", "ArrayList<Plato>", [("indiceRestaurante", "int")]),
      (PUB, "listarCombosDe", "ArrayList<Combo>", [("indiceRestaurante", "int")]),
      (PUB, "getNombrePatio", "String", []),
      (PUB, "hayRestaurantes", "boolean", []),
      (PUB, "registrarRestaurante", "String", [("id", "String"), ("nombre", "String")]),
      (PUB, "registrarPlato", "String", [("indiceRestaurante", "int"), ("id", "String"), ("nombre", "String"),
                                         ("precio", "double"), ("disponible", "boolean")]),
      (PUB, "registrarCombo", "String", [("indiceRestaurante", "int"), ("id", "String"), ("nombre", "String"),
                                         ("precio", "double"), ("indicesPlatos", "ArrayList<Integer>")])]),

    # ---------------- VISTA ----------------
    ("AAB1_ProyFinal", "Vista", "", False,
     [],
     [(PUB, "main", "void", [("args", "String[]")])]),

    ("Vista", "Vista", "", False,
     [(PRI, "tcl", "Scanner"),
      (PRI, "clienteCtrl", "ClienteController"),
      (PRI, "repartidorCtrl", "RepartidorController"),
      (PRI, "restauranteCtrl", "RestauranteController"),
      (PRI, "inventarioCtrl", "InventarioController"),
      (PRI, "pedidoCtrl", "PedidoController"),
      (PRI, "reporteCtrl", "ReporteController")],
     [(PUB, "Vista", "", [("c", "ClienteController"), ("r", "RepartidorController"),
                          ("rest", "RestauranteController"), ("i", "InventarioController"),
                          ("p", "PedidoController"), ("rep", "ReporteController")]),
      (PUB, "iniciar", "void", []),
      (PRI, "mostrarMenuPrincipal", "void", []),
      (PRI, "menuPedidos", "void", []),
      (PRI, "menuClientes", "void", []),
      (PRI, "menuRepartidores", "void", []),
      (PRI, "menuRestaurantes", "void", []),
      (PRI, "menuInventario", "void", []),
      (PRI, "menuReporte", "void", [])]),
]

# ----------------------------------------------------------------------
# Layout: posición (x, y) en cm para cada clase
# ----------------------------------------------------------------------

LAYOUT = {
    # MODELOS - 4 columnas separadas 19 cm (boxes de 16cm + 3cm margen)
    "Calculable":        (2,  2),
    "Cliente":           (21, 2),
    "Combo":             (40, 2),
    "DatosIniciales":    (59, 2),
    "EstadoCargado":     (2,  26),
    "Ingrediente":       (21, 26),
    "LineaPedido":       (40, 26),
    "PatiodeComidas":    (59, 26),
    "Pedido":            (2,  48),
    "PersistenciaEstado":(21, 48),
    "Plato":             (40, 48),
    "Repartidor":        (59, 48),
    "Restaurante":       (2,  72),
    # CONTROLADOR - 3 columnas separadas 19 cm
    "ClienteController":     (2,  96),
    "InventarioController":  (21, 96),
    "PedidoController":      (40, 96),
    "RepartidorController":  (2,  118),
    "ReporteController":     (21, 118),
    "RestauranteController": (40, 118),
    # VISTA
    "AAB1_ProyFinal":  (2,  140),
    "Vista":           (21, 140),
}

# ----------------------------------------------------------------------
# Relaciones
# (tipo, origen, destino, label_multiplicidad_opcional)
# tipo: 'realizes' | 'composition' | 'dependency'
# ----------------------------------------------------------------------

RELACIONES = [
    # Realizes (interface)
    ("realizes", "Pedido", "Calculable", ""),
    ("realizes", "Restaurante", "Calculable", ""),

    # Composiciones (diamante)
    ("composition", "PatiodeComidas", "Restaurante", "1..*"),
    ("composition", "Restaurante", "Plato", "1..*"),
    ("composition", "Restaurante", "Combo", "0..*"),
    ("composition", "Plato", "Ingrediente", "0..*"),
    ("composition", "Combo", "Plato", "0..*"),
    ("composition", "Pedido", "LineaPedido", "1..*"),
    ("composition", "LineaPedido", "Plato", "1"),
    ("composition", "Pedido", "Cliente", "1"),
    ("composition", "Pedido", "Repartidor", "1"),
    ("composition", "EstadoCargado", "PatiodeComidas", "1"),
    ("composition", "EstadoCargado", "Cliente", "0..*"),
    ("composition", "EstadoCargado", "Repartidor", "0..*"),
    ("composition", "EstadoCargado", "Pedido", "0..*"),

    # Dependencias
    ("dependency", "Vista", "ClienteController", ""),
    ("dependency", "Vista", "RepartidorController", ""),
    ("dependency", "Vista", "RestauranteController", ""),
    ("dependency", "Vista", "InventarioController", ""),
    ("dependency", "Vista", "PedidoController", ""),
    ("dependency", "Vista", "ReporteController", ""),
    ("dependency", "AAB1_ProyFinal", "Vista", ""),
    ("dependency", "AAB1_ProyFinal", "DatosIniciales", ""),
    ("dependency", "AAB1_ProyFinal", "PersistenciaEstado", ""),
    ("dependency", "DatosIniciales", "PatiodeComidas", ""),
    ("dependency", "PersistenciaEstado", "EstadoCargado", ""),
    ("dependency", "PedidoController", "InventarioController", ""),
]

# ----------------------------------------------------------------------
# Helpers de generación XML
# ----------------------------------------------------------------------

def xml_escape(text):
    """Escapa caracteres especiales XML."""
    if text is None:
        return ""
    return (text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace('"', "&quot;"))


def s_str(text):
    """Dia usa cadenas con # alrededor; vacías son ##."""
    if text == "" or text is None:
        return "##"
    return "#" + xml_escape(text) + "#"


def attr_string(name, value):
    return f'      <dia:attribute name="{name}">\n        <dia:string>{s_str(value)}</dia:string>\n      </dia:attribute>\n'


def attr_enum(name, val):
    return f'      <dia:attribute name="{name}">\n        <dia:enum val="{val}"/>\n      </dia:attribute>\n'


def attr_bool(name, val):
    v = "true" if val else "false"
    return f'      <dia:attribute name="{name}">\n        <dia:boolean val="{v}"/>\n      </dia:attribute>\n'


def attr_real(name, val):
    return f'      <dia:attribute name="{name}">\n        <dia:real val="{val}"/>\n      </dia:attribute>\n'


def attr_int(name, val):
    return f'      <dia:attribute name="{name}">\n        <dia:int val="{val}"/>\n      </dia:attribute>\n'


def attr_color(name, val):
    return f'      <dia:attribute name="{name}">\n        <dia:color val="{val}"/>\n      </dia:attribute>\n'


def attr_point(name, x, y):
    return f'      <dia:attribute name="{name}">\n        <dia:point val="{x},{y}"/>\n      </dia:attribute>\n'


def attr_rect(name, x1, y1, x2, y2):
    return f'      <dia:attribute name="{name}">\n        <dia:rectangle val="{x1},{y1};{x2},{y2}"/>\n      </dia:attribute>\n'


def attr_font(name, family, style, font_name):
    return (f'      <dia:attribute name="{name}">\n'
            f'        <dia:font family="{family}" style="{style}" name="{font_name}"/>\n'
            f'      </dia:attribute>\n')


# ----------------------------------------------------------------------
# Generador de uml_attribute (atributo de clase)
# ----------------------------------------------------------------------

def make_uml_attribute(vis, name, type_):
    return (
        '        <dia:composite type="uml_attribute">\n'
        f'          <dia:attribute name="name"><dia:string>{s_str(name)}</dia:string></dia:attribute>\n'
        f'          <dia:attribute name="type"><dia:string>{s_str(type_)}</dia:string></dia:attribute>\n'
        '          <dia:attribute name="value"><dia:string>##</dia:string></dia:attribute>\n'
        '          <dia:attribute name="comment"><dia:string>##</dia:string></dia:attribute>\n'
        f'          <dia:attribute name="visibility"><dia:enum val="{vis}"/></dia:attribute>\n'
        '          <dia:attribute name="abstract"><dia:boolean val="false"/></dia:attribute>\n'
        '          <dia:attribute name="class_scope"><dia:boolean val="false"/></dia:attribute>\n'
        '        </dia:composite>\n'
    )


def make_uml_parameter(name, type_):
    return (
        '            <dia:composite type="uml_parameter">\n'
        f'              <dia:attribute name="name"><dia:string>{s_str(name)}</dia:string></dia:attribute>\n'
        f'              <dia:attribute name="type"><dia:string>{s_str(type_)}</dia:string></dia:attribute>\n'
        '              <dia:attribute name="value"><dia:string>##</dia:string></dia:attribute>\n'
        '              <dia:attribute name="comment"><dia:string>##</dia:string></dia:attribute>\n'
        '              <dia:attribute name="kind"><dia:enum val="0"/></dia:attribute>\n'
        '            </dia:composite>\n'
    )


def make_uml_operation(vis, name, ret_type, params):
    if params:
        params_inner = "".join(make_uml_parameter(pn, pt) for pn, pt in params)
        params_attr = (
            '          <dia:attribute name="parameters">\n'
            f'{params_inner}'
            '          </dia:attribute>\n'
        )
    else:
        params_attr = '          <dia:attribute name="parameters"/>\n'

    return (
        '        <dia:composite type="uml_operation">\n'
        f'          <dia:attribute name="name"><dia:string>{s_str(name)}</dia:string></dia:attribute>\n'
        f'          <dia:attribute name="type"><dia:string>{s_str(ret_type)}</dia:string></dia:attribute>\n'
        '          <dia:attribute name="comment"><dia:string>##</dia:string></dia:attribute>\n'
        '          <dia:attribute name="stereotype"><dia:string>##</dia:string></dia:attribute>\n'
        f'          <dia:attribute name="visibility"><dia:enum val="{vis}"/></dia:attribute>\n'
        '          <dia:attribute name="abstract"><dia:boolean val="false"/></dia:attribute>\n'
        '          <dia:attribute name="inheritance_type"><dia:enum val="2"/></dia:attribute>\n'
        '          <dia:attribute name="query"><dia:boolean val="false"/></dia:attribute>\n'
        '          <dia:attribute name="class_scope"><dia:boolean val="false"/></dia:attribute>\n'
        f'{params_attr}'
        '        </dia:composite>\n'
    )


# ----------------------------------------------------------------------
# Generador de UML - Class
# ----------------------------------------------------------------------

def calc_class_size(attrs, ops):
    """Estimación simple del tamaño del box (en cm)."""
    base_height = 2.2  # nombre + estereotipo
    width = 16.0  # ancho fijo amplio para firmas largas
    h = base_height
    if attrs:
        h += 0.55 * len(attrs) + 0.3
    else:
        h += 0.3
    if ops:
        # con wrap_after_char=35 algunas firmas largas se rompen en 2 lineas
        h += 0.55 * len(ops) * 1.4 + 0.3
    else:
        h += 0.3
    return width, h


def make_class_object(obj_id, x, y, nombre, stereotype, is_abstract, attrs, ops):
    width, height = calc_class_size(attrs, ops)

    attrs_xml = "".join(make_uml_attribute(v, n, t) for v, n, t in attrs)
    ops_xml = "".join(make_uml_operation(v, n, t, p) for v, n, t, p in ops)

    attrs_block = (
        '      <dia:attribute name="attributes">\n' + attrs_xml + '      </dia:attribute>\n'
        if attrs_xml else '      <dia:attribute name="attributes"/>\n'
    )
    ops_block = (
        '      <dia:attribute name="operations">\n' + ops_xml + '      </dia:attribute>\n'
        if ops_xml else '      <dia:attribute name="operations"/>\n'
    )

    stereo_text = ""
    if stereotype:
        stereo_text = stereotype  # se mostrará <<stereotype>>

    return (
        f'    <dia:object type="UML - Class" version="0" id="{obj_id}">\n'
        + attr_point("obj_pos", x, y)
        + attr_rect("obj_bb", x - 0.05, y - 0.05, x + width + 0.05, y + height + 0.05)
        + attr_point("elem_corner", x, y)
        + attr_real("elem_width", width)
        + attr_real("elem_height", height)
        + attr_string("name", nombre)
        + attr_string("stereotype", stereo_text)
        + attr_string("comment", "")
        + attr_bool("abstract", is_abstract)
        + attr_bool("suppress_attributes", False)
        + attr_bool("suppress_operations", False)
        + attr_bool("visible_attributes", True)
        + attr_bool("visible_operations", True)
        + attr_bool("visible_comments", False)
        + attr_bool("wrap_operations", True)
        + attr_int("wrap_after_char", 35)
        + attr_int("comment_line_length", 17)
        + attr_bool("comment_tagging", False)
        + attr_real("line_width", 0.10)
        + attr_color("line_color", "#000000")
        + attr_color("fill_color", "#ffffff")
        + attr_color("text_color", "#000000")
        + attr_font("normal_font", "monospace", "0", "Courier")
        + attr_font("abstract_font", "monospace", "88", "Courier-BoldOblique")
        + attr_font("polymorphic_font", "monospace", "8", "Courier-Oblique")
        + attr_font("classname_font", "sans", "80", "Helvetica-Bold")
        + attr_font("abstract_classname_font", "sans", "88", "Helvetica-BoldOblique")
        + attr_font("comment_font", "sans", "8", "Helvetica-Oblique")
        + attr_real("normal_font_height", 0.8)
        + attr_real("polymorphic_font_height", 0.8)
        + attr_real("abstract_font_height", 0.8)
        + attr_real("classname_font_height", 1.0)
        + attr_real("abstract_classname_font_height", 1.0)
        + attr_real("comment_font_height", 0.7)
        + attrs_block
        + ops_block
        + '      <dia:attribute name="template"><dia:boolean val="false"/></dia:attribute>\n'
        + '      <dia:attribute name="templates"/>\n'
        + '    </dia:object>\n'
    )


# ----------------------------------------------------------------------
# Generador de relaciones (líneas)
# ----------------------------------------------------------------------

def class_center(name):
    x, y = LAYOUT[name]
    # buscar la clase para calcular tamaño
    for cls in CLASES:
        if cls[0] == name:
            w, h = calc_class_size(cls[4], cls[5])
            return x + w / 2, y + h / 2, w, h
    return x, y, 8, 4


def class_anchor_point(name, target_name):
    """Devuelve un punto razonable en el borde de la clase hacia la otra."""
    cx, cy, w, h = class_center(name)
    tx, ty, _, _ = class_center(target_name)
    dx = tx - cx
    dy = ty - cy
    # Punto en el borde más cercano al destino
    if abs(dx) > abs(dy):
        if dx > 0:
            return cx + w / 2, cy
        else:
            return cx - w / 2, cy
    else:
        if dy > 0:
            return cx, cy + h / 2
        else:
            return cx, cy - h / 2


def make_relation_object(obj_id, rel_type, source, target, label):
    """Genera UML - Realizes / Association / Dependency con puntos libres."""
    x1, y1 = class_anchor_point(source, target)
    x2, y2 = class_anchor_point(target, source)

    # Bounding box
    bb_x1 = min(x1, x2) - 0.1
    bb_y1 = min(y1, y2) - 0.1
    bb_x2 = max(x1, x2) + 0.1
    bb_y2 = max(y1, y2) + 0.1

    # Cuatro puntos ortogonales (zigzag simple)
    mid_x = (x1 + x2) / 2
    orth_points = [
        (x1, y1),
        (mid_x, y1),
        (mid_x, y2),
        (x2, y2),
    ]
    orth_points_xml = "".join(f'        <dia:point val="{px},{py}"/>\n' for px, py in orth_points)
    orth_orient_xml = (
        '        <dia:enum val="0"/>\n'
        '        <dia:enum val="1"/>\n'
        '        <dia:enum val="0"/>\n'
    )

    common_head = (
        f'    <dia:object type="{rel_type}" version="{"1" if rel_type == "UML - Realizes" else ("1" if rel_type == "UML - Dependency" else "2")}" id="{obj_id}">\n'
        + attr_point("obj_pos", x1, y1)
        + attr_rect("obj_bb", bb_x1, bb_y1, bb_x2, bb_y2)
        + '      <dia:attribute name="meta">\n        <dia:composite type="dict"/>\n      </dia:attribute>\n'
        + '      <dia:attribute name="orth_points">\n'
        + orth_points_xml
        + '      </dia:attribute>\n'
        + '      <dia:attribute name="orth_orient">\n'
        + orth_orient_xml
        + '      </dia:attribute>\n'
        + attr_bool("orth_autoroute", True)
        + attr_color("text_colour", "#000000")
        + attr_color("line_colour", "#000000")
    )

    if rel_type == "UML - Association":
        # Asociación con composición (diamante negro en el origen)
        body = (
            attr_string("name", "")
            + attr_enum("direction", 0)
            + attr_bool("show_direction", False)
            + attr_enum("assoc_type", 2)  # 0=normal, 1=aggregation, 2=composition
            + attr_string("role_a", "")
            + attr_string("multipicity_a", "1")
            + attr_enum("visibility_a", 3)
            + attr_enum("inheritance_type_a", 2)
            + attr_bool("show_arrow_a", False)
            + attr_string("role_b", "")
            + attr_string("multipicity_b", label or "*")
            + attr_enum("visibility_b", 3)
            + attr_enum("inheritance_type_b", 2)
            + attr_bool("show_arrow_b", True)
        )
    elif rel_type == "UML - Realizes":
        body = (
            attr_string("stereotype", "")
            + attr_string("name", "")
            + attr_real("text_font_height", 0.8)
            + attr_font("text_font", "monospace", "0", "Courier")
        )
    elif rel_type == "UML - Dependency":
        body = (
            attr_string("name", "")
            + attr_string("stereotype", "")
            + attr_bool("draw_arrow", True)
            + attr_real("text_font_height", 0.8)
            + attr_font("text_font", "monospace", "0", "Courier")
        )
    else:
        body = ""

    return common_head + body + '    </dia:object>\n'


# ----------------------------------------------------------------------
# Generador de UML - LargePackage para agrupar visualmente por package
# ----------------------------------------------------------------------

def make_package_label(obj_id, x, y, w, h, name):
    """Caja UML - LargePackage con el nombre del paquete."""
    return (
        f'    <dia:object type="UML - LargePackage" version="0" id="{obj_id}">\n'
        + attr_point("obj_pos", x, y)
        + attr_rect("obj_bb", x - 0.05, y - 0.85, x + w + 0.05, y + h + 0.05)
        + attr_point("elem_corner", x, y)
        + attr_real("elem_width", w)
        + attr_real("elem_height", h)
        + attr_string("stereotype", "")
        + attr_string("name", name)
        + attr_color("line_colour", "#000000")
        + attr_color("fill_colour", "#ffffff")
        + attr_color("text_colour", "#000000")
        + attr_real("line_width", 0.10)
        + '    </dia:object>\n'
    )


# ----------------------------------------------------------------------
# Generación del archivo completo
# ----------------------------------------------------------------------

def generar_dia(output_path):
    next_id = [0]

    def nid():
        i = next_id[0]
        next_id[0] += 1
        return f"O{i}"

    objects_xml = []

    # 1. Paquetes (cajas grandes alrededor)
    objects_xml.append(make_package_label(nid(), 0.5, 0.5, 77, 93, "Modelos"))
    objects_xml.append(make_package_label(nid(), 0.5, 95.0, 58, 42, "Controlador"))
    objects_xml.append(make_package_label(nid(), 0.5, 139.0, 38, 18, "Vista"))

    # 2. Clases
    class_ids = {}
    for nombre, _pkg, stereo, abs_, attrs, ops in CLASES:
        cid = nid()
        class_ids[nombre] = cid
        x, y = LAYOUT[nombre]
        objects_xml.append(make_class_object(cid, x, y, nombre, stereo, abs_, attrs, ops))

    # 3. Relaciones
    type_map = {
        "realizes":    "UML - Realizes",
        "composition": "UML - Association",
        "dependency":  "UML - Dependency",
    }
    for tipo, src, dst, label in RELACIONES:
        rid = nid()
        rel_type = type_map[tipo]
        objects_xml.append(make_relation_object(rid, rel_type, src, dst, label))

    objects_str = "".join(objects_xml)

    # Plantilla completa del archivo .dia
    xml = (
        '<?xml version="1.0" encoding="UTF-8"?>\n'
        '<dia:diagram xmlns:dia="http://www.lysator.liu.se/~alla/dia/">\n'
        '  <dia:diagramdata>\n'
        '    <dia:attribute name="background">\n'
        '      <dia:color val="#ffffff"/>\n'
        '    </dia:attribute>\n'
        '    <dia:attribute name="pagebreak">\n'
        '      <dia:color val="#000099"/>\n'
        '    </dia:attribute>\n'
        '    <dia:attribute name="paper">\n'
        '      <dia:composite type="paper">\n'
        '        <dia:attribute name="name"><dia:string>#A3#</dia:string></dia:attribute>\n'
        '        <dia:attribute name="tmargin"><dia:real val="2.82"/></dia:attribute>\n'
        '        <dia:attribute name="bmargin"><dia:real val="2.82"/></dia:attribute>\n'
        '        <dia:attribute name="lmargin"><dia:real val="2.82"/></dia:attribute>\n'
        '        <dia:attribute name="rmargin"><dia:real val="2.82"/></dia:attribute>\n'
        '        <dia:attribute name="is_portrait"><dia:boolean val="true"/></dia:attribute>\n'
        '        <dia:attribute name="scaling"><dia:real val="1"/></dia:attribute>\n'
        '        <dia:attribute name="fitto"><dia:boolean val="false"/></dia:attribute>\n'
        '      </dia:composite>\n'
        '    </dia:attribute>\n'
        '    <dia:attribute name="grid">\n'
        '      <dia:composite type="grid">\n'
        '        <dia:attribute name="width_x"><dia:real val="1"/></dia:attribute>\n'
        '        <dia:attribute name="width_y"><dia:real val="1"/></dia:attribute>\n'
        '        <dia:attribute name="visible_x"><dia:int val="1"/></dia:attribute>\n'
        '        <dia:attribute name="visible_y"><dia:int val="1"/></dia:attribute>\n'
        '        <dia:composite type="color">\n'
        '          <dia:attribute name="red"><dia:real val="0.85"/></dia:attribute>\n'
        '          <dia:attribute name="green"><dia:real val="0.9"/></dia:attribute>\n'
        '          <dia:attribute name="blue"><dia:real val="0.95"/></dia:attribute>\n'
        '          <dia:attribute name="alpha"><dia:real val="1"/></dia:attribute>\n'
        '        </dia:composite>\n'
        '      </dia:composite>\n'
        '    </dia:attribute>\n'
        '    <dia:attribute name="color">\n'
        '      <dia:color val="#d8e5e5"/>\n'
        '    </dia:attribute>\n'
        '    <dia:attribute name="guides">\n'
        '      <dia:composite type="guides">\n'
        '        <dia:attribute name="hguides"/>\n'
        '        <dia:attribute name="vguides"/>\n'
        '      </dia:composite>\n'
        '    </dia:attribute>\n'
        '    <dia:attribute name="display">\n'
        '      <dia:composite type="display">\n'
        '        <dia:attribute name="antialiased"><dia:boolean val="false"/></dia:attribute>\n'
        '        <dia:attribute name="snap-to-grid"><dia:boolean val="true"/></dia:attribute>\n'
        '        <dia:attribute name="snap-to-object"><dia:boolean val="true"/></dia:attribute>\n'
        '        <dia:attribute name="show-grid"><dia:boolean val="true"/></dia:attribute>\n'
        '        <dia:attribute name="show-connection-points"><dia:boolean val="true"/></dia:attribute>\n'
        '      </dia:composite>\n'
        '    </dia:attribute>\n'
        '  </dia:diagramdata>\n'
        '  <dia:layer name="Fondo" visible="true" active="true">\n'
        + objects_str
        + '  </dia:layer>\n'
        '</dia:diagram>\n'
    )

    with open(output_path, "w", encoding="utf-8") as f:
        f.write(xml)

    return output_path


if __name__ == "__main__":
    script_dir = os.path.dirname(os.path.abspath(__file__))
    output = os.path.join(script_dir, "uml_diagrama.dia")
    path = generar_dia(output)
    print(f"OK -> {path}")
    print(f"   Clases: {len(CLASES)}")
    print(f"   Relaciones: {len(RELACIONES)}")
