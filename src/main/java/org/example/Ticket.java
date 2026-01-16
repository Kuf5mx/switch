package edu.erick;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ticket {
    private final String clientName;
    private final List<Vehicle> items;
    private final LocalDateTime date;

    public Ticket(String clientName, List<Vehicle> items, LocalDateTime date) {
        this.clientName = clientName;
        this.items = items;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getTotal() {
        double sum = 0;
        for (Vehicle v : items) sum += v.getPrice();
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====== TICKET DE VENTA ======\n");
        sb.append("Cliente: ").append(clientName).append("\n");
        sb.append("Fecha: ").append(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");
        sb.append("Vehículos comprados:\n");
        for (Vehicle v : items) {
            sb.append(" - ").append(v.getId()).append(" -> ").append(v.toString()).append("\n");
        }
        sb.append("\nTotal a pagar: $").append(String.format("%.2f", getTotal())).append("\n");
        sb.append("==============================\n");
        return sb.toString();
    }
}
