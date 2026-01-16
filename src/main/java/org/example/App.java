package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Venta de Vehículos (Interfaz de Línea de Texto) ---");
        System.out.println();

        List<Vehicle> vehicles = inventory.getVehicles();
        for (Vehicle v : vehicles) {
            System.out.println(v.getId() + ": " + v);
        }

        System.out.println();
        System.out.println("Ingrese los IDs de los vehículos a comprar, separados por comas (ej: V1,V3):");
        String line = scanner.nextLine().trim();
        String[] parts = line.split("\\s*,\\s*");

        List<Vehicle> selected = new ArrayList<>();
        for (String p : parts) {
            if (p.isBlank()) continue;
            Vehicle v = inventory.findById(p);
            if (v != null) selected.add(v);
            else System.out.println("ID no encontrado: " + p);
        }

        if (selected.isEmpty()) {
            System.out.println("No se seleccionó ningún vehículo. Saliendo.");
            return;
        }

        System.out.println("Nombre del cliente:");
        String client = scanner.nextLine().trim();

        Ticket ticket = new Ticket(client, selected, LocalDateTime.now());
        System.out.println();
        System.out.println(ticket);

        // Guardar ticket en disco
        try {
            Path ticketsDir = Path.of("build", "tickets");
            Files.createDirectories(ticketsDir);
            String filename = "ticket_" + DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(ticket.getDate()) + ".txt";
            Path out = ticketsDir.resolve(filename);
            Files.writeString(out, ticket.toString());
            System.out.println("Ticket guardado en: " + out.toString());
        } catch (IOException e) {
            System.out.println("No se pudo guardar el ticket: " + e.getMessage());
        }
    }
}
