package edu.erick;

import java.util.Scanner;

public class Main {
    private static final Inventory inventory = new Inventory();
    private static final Cart cart = new Cart();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarBienvenida();
        
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1 -> verVehiculos();
                case 2 -> verDetalles();
                case 3 -> agregarAlCarrito();
                case 4 -> quitarDelCarrito();
                case 5 -> verCarrito();
                case 6 -> realizarCheckout();
                case 7 -> {
                    System.out.println("\n✨ Gracias por visitar VÉLOCITÉ. ¡Hasta pronto!\n");
                    salir = true;
                }
                default -> System.out.println("\n❌ Opción inválida. Intenta de nuevo.\n");
            }
        }
        
        scanner.close();
    }

    private static void mostrarBienvenida() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║                       VÉLOCITÉ                            ║");
        System.out.println("║              Luxury Automotive Excellence                 ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
    }

    private static void mostrarMenu() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                       MENÚ PRINCIPAL                      ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Ver vehículos en exhibición                          ║");
        System.out.println("║  2. Ver detalles de un vehículo                          ║");
        System.out.println("║  3. Agregar vehículo al carrito                          ║");
        System.out.println("║  4. Quitar vehículo del carrito                          ║");
        System.out.println("║  5. Ver carrito y total                                  ║");
        System.out.println("║  6. Realizar compra (Checkout)                           ║");
        System.out.println("║  7. Salir                                                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.print("\nSelecciona una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void verVehiculos() {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("               VEHÍCULOS EN EXHIBICIÓN");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        
        for (Vehicle v : inventory.getVehicles()) {
            System.out.println(v);
        }
        
        System.out.println("\n═══════════════════════════════════════════════════════════\n");
    }

    private static void verDetalles() {
        System.out.print("\nIngresa el ID del vehículo: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Vehicle v = inventory.findById(id);
            
            if (v == null) {
                System.out.println("\n❌ Vehículo no encontrado.\n");
            } else {
                System.out.println(v.detalles());
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ ID inválido.\n");
        }
    }

    private static void agregarAlCarrito() {
        System.out.print("\nIngresa el ID del vehículo a agregar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Vehicle v = inventory.findById(id);
            
            if (v == null) {
                System.out.println("\n❌ Vehículo no encontrado.\n");
            } else {
                cart.add(v);
                System.out.printf("\n✅ %s %s agregado al carrito.\n\n", v.getMarca(), v.getModelo());
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ ID inválido.\n");
        }
    }

    private static void quitarDelCarrito() {
        if (cart.isEmpty()) {
            System.out.println("\n❌ El carrito está vacío.\n");
            return;
        }
        
        cart.mostrar();
        System.out.print("Ingresa el ID del vehículo a quitar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            if (cart.remove(id)) {
                System.out.println("\n✅ Vehículo eliminado del carrito.\n");
            } else {
                System.out.println("\n❌ Vehículo no encontrado en el carrito.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ ID inválido.\n");
        }
    }

    private static void verCarrito() {
        cart.mostrar();
    }

    private static void realizarCheckout() {
        if (cart.isEmpty()) {
            System.out.println("\n❌ El carrito está vacío. Agrega vehículos antes de realizar la compra.\n");
            return;
        }
        
        System.out.println("\n⚠️  Checkout pendiente de implementación.\n");
    }
}
