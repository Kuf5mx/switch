package edu.erick;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Vehicle> items = new ArrayList<>();

    public void add(Vehicle vehicle) {
        items.add(vehicle);
    }

    public boolean remove(int id) {
        return items.removeIf(v -> v.getId() == id);
    }

    public List<Vehicle> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt(Vehicle::getPrecio).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public void mostrar() {
        if (items.isEmpty()) {
            System.out.println("\n🛒 Carrito vacío\n");
            return;
        }

        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("                      🛒 CARRITO DE COMPRA");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        for (int i = 0; i < items.size(); i++) {
            Vehicle v = items.get(i);
            System.out.printf("%d. %s %s - $%,d USD%n", 
                    i + 1, v.getMarca(), v.getModelo(), v.getPrecio());
        }
        
        System.out.println("───────────────────────────────────────────────────────────");
        System.out.printf("TOTAL: $%,d USD%n", getTotal());
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }
}
