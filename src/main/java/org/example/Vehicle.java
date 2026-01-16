package edu.erick;

public class Vehicle {
    private final int id;
    private final String marca;
    private final String modelo;
    private final int año;
    private final int precio;
    private final String extras;
    private final int kilometros;

    public Vehicle(int id, String marca, String modelo, int año, int precio, String extras, int kilometros) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
        this.extras = extras;
        this.kilometros = kilometros;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAño() {
        return año;
    }

    public int getPrecio() {
        return precio;
    }

    public String getExtras() {
        return extras;
    }

    public int getKilometros() {
        return kilometros;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s %s (%d) | $%,d | %,d km",
                id, marca, modelo, año, precio, kilometros);
    }

    public String detalles() {
        return String.format("""
                ═══════════════════════════════════════════════════════════
                ID: %d
                Marca: %s
                Modelo: %s
                Año: %d
                Precio: $%,d USD
                Kilometraje: %,d km
                Características: %s
                ═══════════════════════════════════════════════════════════""",
                id, marca, modelo, año, precio, kilometros, extras);
    }
}
