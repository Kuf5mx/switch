package erick.Act1.process;

/**
 * Programa básico de consola que simula una venta sencilla de autos.
 */
public class Concesionaria {
    private String[] nombreAutos = {"Porsche 911 Turbo 2024", "BMW M8 Competition 2024", "Mercedes-AMG GT 2024", "Audi R8 V10 Plus 2024", "Ferrari Roma 2024"};
    private double[] precioAutos = {185000.00, 145000.00, 160000.00, 195000.00, 250000.00};

    private String nombreCliente = "";
    private String auto1 = "";
    private String auto2 = "";
    private String color1 = "";
    private String color2 = "";
    private double precio1 = 0.0;
    private double precio2 = 0.0;
    private boolean hayAuto1 = false;
    private boolean hayAuto2 = false;

    /**
     * Guarda el nombre del cliente.
     *
     * @param nombreCliente nombre del cliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Retorna el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Retorna el catálogo de nombres de autos.
     *
     * @return arreglo con nombres de autos
     */
    public String[] getNombreAutos() {
        return nombreAutos;
    }

    /**
     * Retorna el catálogo de precios de autos.
     *
     * @return arreglo con precios de autos
     */
    public double[] getPrecioAutos() {
        return precioAutos;
    }

    /**
     * Retorna el primer auto seleccionado.
     *
     * @return auto 1
     */
    public String getAuto1() {
        return auto1;
    }

    /**
     * Retorna el segundo auto seleccionado.
     *
     * @return auto 2
     */
    public String getAuto2() {
        return auto2;
    }

    /**
     * Retorna el color del primer auto.
     *
     * @return color 1
     */
    public String getColor1() {
        return color1;
    }

    /**
     * Retorna el color del segundo auto.
     *
     * @return color 2
     */
    public String getColor2() {
        return color2;
    }

    /**
     * Retorna el precio del primer auto.
     *
     * @return precio 1
     */
    public double getPrecio1() {
        return precio1;
    }

    /**
     * Retorna el precio del segundo auto.
     *
     * @return precio 2
     */
    public double getPrecio2() {
        return precio2;
    }

    /**
     * Indica si ya existe un primer auto seleccionado.
     *
     * @return true si hay auto 1
     */
    public boolean isHayAuto1() {
        return hayAuto1;
    }

    /**
     * Indica si ya existe un segundo auto seleccionado.
     *
     * @return true si hay auto 2
     */
    public boolean isHayAuto2() {
        return hayAuto2;
    }

    /**
     * Selecciona un auto (primero o segundo) y guarda también el color.
     * Devuelve 1 si guardó el primer auto, 2 si guardó el segundo, o 0 si ya hay 2 autos.
     *
     * @param numeroAuto número elegido por el usuario (1..n)
     * @param colorElegido color elegido por el usuario
     * @return 1, 2 o 0 según el caso
     */
    public int seleccionarAuto(int numeroAuto, String colorElegido) {
        if (!hayAuto1) {
            auto1 = nombreAutos[numeroAuto - 1];
            precio1 = precioAutos[numeroAuto - 1];
            color1 = colorElegido;
            hayAuto1 = true;
            return 1;
        } else if (!hayAuto2) {
            auto2 = nombreAutos[numeroAuto - 1];
            precio2 = precioAutos[numeroAuto - 1];
            color2 = colorElegido;
            hayAuto2 = true;
            return 2;
        }
        return 0;
    }

    /**
     * Reinicia la compra, dejando la selección vacía.
     */
    public void reiniciarCompra() {
        auto1 = "";
        auto2 = "";
        color1 = "";
        color2 = "";
        precio1 = 0.0;
        precio2 = 0.0;
        hayAuto1 = false;
        hayAuto2 = false;
    }
}
