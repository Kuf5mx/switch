package erick.Act1.ui;

import java.util.Scanner;

import erick.Act1.process.Concesionaria;

/**
 * Interfaz de consola (CLI) del programa.
 */
public class CLI {
    /**
     * Inicia el menú y maneja la interacción con el usuario.
     */
    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);

        Concesionaria concesionaria = new Concesionaria();

        int opcion = -1;

        System.out.println("========================================");
        System.out.println("   BIENVENIDO A AUTOS PREMIUM EGR   ");
        System.out.println("========================================");
        System.out.print("\nPor favor, ingrese su nombre: ");
        String nombreCliente = scanner.nextLine();
        concesionaria.setNombreCliente(nombreCliente);
        System.out.println("\n¡Bienvenido " + concesionaria.getNombreCliente() + "!");
        System.out.println();

        while (opcion != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Mostrar catalogo de autos");
            System.out.println("2. Seleccionar auto para compra");
            System.out.println("3. Mostrar seleccion actual");
            System.out.println("4. Confirmar compra");
            System.out.println("0. Salir del programa");
            System.out.print("\nIngrese su opcion: ");

            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\nError: Debe ingresar un numero valido.");
                scanner.nextLine();
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n========================================");
                    System.out.println("       CATALOGO DE VEHICULOS           ");
                    System.out.println("========================================");
                    for (int i = 0; i < concesionaria.getNombreAutos().length; i++) {
                        System.out.println((i + 1) + ". " + concesionaria.getNombreAutos()[i] + " - $" + concesionaria.getPrecioAutos()[i]);
                    }
                    System.out.println("========================================");
                    break;

                case 2:
                    System.out.println("\n--- SELECCION DE AUTO ---");
                    System.out.println("Autos disponibles:");
                    for (int i = 0; i < concesionaria.getNombreAutos().length; i++) {
                        System.out.println((i + 1) + ". " + concesionaria.getNombreAutos()[i]);
                    }
                    System.out.print("\nIngrese el numero del auto que desea: ");

                    try {
                        int numeroAuto = scanner.nextInt();

                        if (numeroAuto >= 1 && numeroAuto <= concesionaria.getNombreAutos().length) {
                            System.out.print("Ingrese el color que desea (Rojo, Azul, Negro, Blanco, Gris): ");
                            scanner.nextLine();
                            String colorElegido = scanner.nextLine();

                            int resultado = concesionaria.seleccionarAuto(numeroAuto, colorElegido);
                            if (resultado == 1) {
                                System.out.println("\n¡Primer auto seleccionado correctamente!");
                                System.out.println("Has elegido: " + concesionaria.getAuto1() + " en color " + concesionaria.getColor1());
                                System.out.println("Puedes elegir un segundo auto si lo deseas.");
                            } else if (resultado == 2) {
                                System.out.println("\n¡Segundo auto seleccionado correctamente!");
                                System.out.println("Has elegido: " + concesionaria.getAuto2() + " en color " + concesionaria.getColor2());
                            } else {
                                System.out.println("\nYa has seleccionado 2 autos. No puedes elegir mas.");
                            }
                        } else {
                            System.out.println("\nError: Numero invalido. Intente de nuevo.");
                        }
                    } catch (Exception e) {
                        System.out.println("\nError: Debe ingresar un numero valido.");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    System.out.println("\n--- SELECCION ACTUAL ---");
                    if (concesionaria.isHayAuto1() || concesionaria.isHayAuto2()) {
                        if (concesionaria.isHayAuto1()) {
                            System.out.println("Auto 1: " + concesionaria.getAuto1() + " - Color: " + concesionaria.getColor1());
                            System.out.println("Precio: $" + concesionaria.getPrecio1());
                        }
                        if (concesionaria.isHayAuto2()) {
                            System.out.println("Auto 2: " + concesionaria.getAuto2() + " - Color: " + concesionaria.getColor2());
                            System.out.println("Precio: $" + concesionaria.getPrecio2());
                        }
                        if (concesionaria.isHayAuto1() && concesionaria.isHayAuto2()) {
                            double total = concesionaria.getPrecio1() + concesionaria.getPrecio2();
                            System.out.println("----------------------------");
                            System.out.println("Precio total: $" + total);
                        }
                    } else {
                        System.out.println("No has seleccionado ningun auto todavia.");
                    }
                    break;

                case 4:
                    System.out.println("\n========================================");
                    System.out.println("       CONFIRMACION DE COMPRA          ");
                    System.out.println("========================================");

                    if (concesionaria.isHayAuto1() || concesionaria.isHayAuto2()) {
                        System.out.println("\n*** COMPROBANTE DE VENTA ***");
                        System.out.println("----------------------------");
                        System.out.println("Cliente: " + concesionaria.getNombreCliente());
                        System.out.println("----------------------------");

                        if (concesionaria.isHayAuto1()) {
                            System.out.println("Auto 1: " + concesionaria.getAuto1());
                            System.out.println("Color: " + concesionaria.getColor1());
                            System.out.println("Precio: $" + concesionaria.getPrecio1());
                            System.out.println();
                        }
                        if (concesionaria.isHayAuto2()) {
                            System.out.println("Auto 2: " + concesionaria.getAuto2());
                            System.out.println("Color: " + concesionaria.getColor2());
                            System.out.println("Precio: $" + concesionaria.getPrecio2());
                            System.out.println();
                        }

                        System.out.println("----------------------------");

                        if (concesionaria.isHayAuto1() && concesionaria.isHayAuto2()) {
                            double total = concesionaria.getPrecio1() + concesionaria.getPrecio2();
                            System.out.println("TOTAL A PAGAR: $" + total);
                        } else {
                            if (concesionaria.isHayAuto1()) {
                                System.out.println("TOTAL A PAGAR: $" + concesionaria.getPrecio1());
                            } else {
                                System.out.println("TOTAL A PAGAR: $" + concesionaria.getPrecio2());
                            }
                        }

                        System.out.println("----------------------------");
                        System.out.println("\n¡Felicidades por tu compra " + concesionaria.getNombreCliente() + "!");
                        System.out.println("Gracias por confiar en AUTOS PREMIUM EGR");
                        System.out.println("¡Disfruta tu nuevo vehiculo!, espero y vuelvas :)");

                        concesionaria.reiniciarCompra();
                    } else {
                        System.out.println("\nNo puedes confirmar una compra sin seleccionar un auto.");
                        System.out.println("Por favor, selecciona un auto primero (opcion 2).");
                    }
                    break;

                case 0:
                    System.out.println("\n========================================");
                    System.out.println("Gracias por visitar AUTOS PREMIUM EGR");
                    System.out.println("¡Hasta pronto!");
                    System.out.println("========================================");
                    break;

                default:
                    System.out.println("\nOpcion no valida. Por favor intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}
