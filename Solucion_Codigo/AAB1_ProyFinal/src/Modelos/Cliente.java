package Modelos;

import java.util.Random;
import java.util.Scanner;

public class Cliente {
    private Scanner tcl;
    private String nombre;
    private String direccion;
    private double distanciaKm;
    private Random random;

    public Cliente() {
        this.random = new Random();
        System.out.println("===CREANDO CLIENTE===");
        Scanner tcl = new Scanner(System.in);
        System.out.println("Nombre del cliente: ");
        this.nombre = tcl.next();
        System.out.println("Direccion del cliente");
        this.direccion = tcl.next();
        this.distanciaKm = random.nextInt(15) + 1;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
