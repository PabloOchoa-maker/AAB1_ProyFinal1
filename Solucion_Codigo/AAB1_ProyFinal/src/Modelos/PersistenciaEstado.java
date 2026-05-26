package Modelos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersistenciaEstado {

    private static String carpetaDatos = "datos";
    private static String archivoEstado = "estado.dat";

    public static void guardar(EstadoCargado estado) {
        if (estado == null) {
            return;
        }
        asegurarCarpeta();
        File f = new File(carpetaDatos + "/" + archivoEstado);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(estado);
        } catch (IOException e) {
            System.out.println("Error guardando estado.dat: " + e.getMessage());
        }
    }

    public static EstadoCargado cargar() {
        asegurarCarpeta();
        File f = new File(carpetaDatos + "/" + archivoEstado);
        if (!f.exists()) {
            return estadoVacio();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object leido = ois.readObject();
            if (leido instanceof EstadoCargado) {
                return (EstadoCargado) leido;
            }
            return estadoVacio();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo estado.dat: " + e.getMessage());
            return estadoVacio();
        }
    }

    private static void asegurarCarpeta() {
        File c = new File(carpetaDatos);
        if (!c.exists()) {
            c.mkdirs();
        }
    }

    private static EstadoCargado estadoVacio() {
        return new EstadoCargado(null, new ArrayList<>(), new ArrayList<>(), 
                new ArrayList<>());
    }
}
