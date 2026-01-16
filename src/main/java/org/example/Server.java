package org.example;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Server {
    static class Vehicle {
        int id; String marca; String modelo; int año; int precio; String extras; int kilometros;
        Vehicle(int id, String marca, String modelo, int año, int precio, String extras, int kilometros){
            this.id=id;this.marca=marca;this.modelo=modelo;this.año=año;this.precio=precio;this.extras=extras;this.kilometros=kilometros;
        }
    }

    static class CheckoutRequest { String cliente; List<Integer> items; }
    static class Ticket { String id; String cliente; String fecha; List<Vehicle> items; int total; }

    private static final Gson gson = new Gson();

    private static String ticketAsText(Ticket t) {
        StringBuilder sb = new StringBuilder();
        sb.append("VÉLOCITÉ — Ticket de Compra\n");
        sb.append("Ticket ID: ").append(t.id).append("\n");
        sb.append("Cliente: ").append(t.cliente).append("\n");
        sb.append("Fecha: ").append(t.fecha).append("\n\n");
        sb.append("Items:\n");
        if (t.items == null || t.items.isEmpty()) {
            sb.append("- (sin items)\n");
        } else {
            for (Vehicle v : t.items) {
                sb.append("- ")
                  .append(v.marca).append(" ").append(v.modelo)
                  .append(" (").append(v.año).append(")")
                  .append(" — $").append(v.precio)
                  .append(" — ").append(v.kilometros).append(" km")
                  .append("\n");
            }
        }
        sb.append("\nTOTAL: $").append(t.total).append("\n");
        return sb.toString();
    }

    private static Path ensureTicketsDir() {
        try {
            Path dir = Paths.get("build", "tickets");
            Files.createDirectories(dir);
            return dir;
        } catch (IOException e) {
            throw new RuntimeException("Could not create build/tickets directory", e);
        }
    }

    public static void main(String[] args){
        port(4567);
        staticFiles.externalLocation("web");

        Path ticketsDir = ensureTicketsDir();
        Map<String, Ticket> ticketsIndex = new HashMap<>();

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1,"Ferrari","F8 Tributo",2023,280000,"V8 Twin-Turbo, 7-Speed DCT, Carbon Fiber Trim, Adaptive Dampers, Premium Audio",5800));
        vehicles.add(new Vehicle(2,"Lamborghini","Huracán EVO",2023,260000,"V10 NA, AWD, Torque Vectoring, MagneRide Suspension, Sport Exhaust",8200));
        vehicles.add(new Vehicle(3,"Porsche","911 Turbo S",2024,250000,"Twin-Turbo Flat-6, Sport Chrono, Launch Control, Ceramic Brakes, Premium Sound",3500));
        vehicles.add(new Vehicle(4,"McLaren","720S",2023,300000,"Carbon Fiber Monocoque, Active Aero, Track Mode, Proactive Chassis Control, Carbon Brakes",6100));
        vehicles.add(new Vehicle(5,"Mercedes-AMG","GT Black Series",2023,325000,"Handcrafted V8, Active Aerodynamics, Carbon Package, Track Telemetry, Performance Seats",4200));
        vehicles.add(new Vehicle(6,"Aston Martin","DB12",2024,245000,"Twin-Turbo V8, Luxury Leather Cabin, Adaptive Suspension, GT Touring Package, Premium Infotainment",2900));
        vehicles.add(new Vehicle(7,"Bentley","Continental GT",2024,230000,"Grand Touring, Mulliner Spec, Air Suspension, Naim Audio, Hand-Finished Wood & Leather",7500));
        vehicles.add(new Vehicle(8,"Rolls-Royce","Phantom",2024,450000,"V12 Power, Bespoke Interior, Starlight Headliner, Rear Comfort Suite, Ultra-Quiet Cabin",3200));
        vehicles.add(new Vehicle(9,"Bugatti","Chiron",2023,3000000,"W16 Quad-Turbo, 1500 HP, Carbon Fiber Body, Active Aero, Hypercar Cooling System",1800));
        vehicles.add(new Vehicle(10,"Maserati","MC20",2024,210000,"Twin-Turbo V6, Carbon Tub, Italian Design, Launch Control, Performance Brakes",4600));

        get("/api/vehicles", (req, res) -> {
            res.type("application/json");
            System.out.println("✅ API /api/vehicles called - returning " + vehicles.size() + " vehicles");
            return gson.toJson(vehicles);
        });

        post("/api/checkout", (req, res) -> {
            res.type("application/json");
            CheckoutRequest cr = gson.fromJson(req.body(), CheckoutRequest.class);
            Ticket t = new Ticket();
            t.id = UUID.randomUUID().toString();
            t.cliente = (cr == null || cr.cliente == null || cr.cliente.trim().isEmpty()) ? "(sin nombre)" : cr.cliente.trim();
            t.fecha = new Date().toString();
            t.items = new ArrayList<>();
            t.total = 0;
            if(cr != null && cr.items != null){
                for(Integer id : cr.items){
                    for(Vehicle v : vehicles){
                        if(v.id == id){
                            t.items.add(v);
                            t.total += v.precio;
                            break;
                        }
                    }
                }
            }

            String json = gson.toJson(t);
            String txt = ticketAsText(t);
            Path jsonPath = ticketsDir.resolve("ticket-" + t.id + ".json");
            Path txtPath = ticketsDir.resolve("ticket-" + t.id + ".txt");
            Files.write(jsonPath, json.getBytes(StandardCharsets.UTF_8));
            Files.write(txtPath, txt.getBytes(StandardCharsets.UTF_8));

            ticketsIndex.put(t.id, t);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("id", t.id);
            response.put("cliente", t.cliente);
            response.put("fecha", t.fecha);
            response.put("items", t.items);
            response.put("total", t.total);
            response.put("downloadTxt", "/api/tickets/" + t.id + "/txt");
            response.put("downloadJson", "/api/tickets/" + t.id + "/json");
            return gson.toJson(response);
        });

        get("/api/tickets/:id/json", (req, res) -> {
            res.type("application/json");
            String id = req.params(":id");
            Ticket t = ticketsIndex.get(id);
            if (t != null) return gson.toJson(t);

            Path jsonPath = ticketsDir.resolve("ticket-" + id + ".json");
            if (!Files.exists(jsonPath)) {
                res.status(404);
                return gson.toJson(Collections.singletonMap("error", "Ticket not found"));
            }
            return new String(Files.readAllBytes(jsonPath), StandardCharsets.UTF_8);
        });

        get("/api/tickets/:id/txt", (req, res) -> {
            res.type("text/plain; charset=utf-8");
            String id = req.params(":id");
            Path txtPath = ticketsDir.resolve("ticket-" + id + ".txt");
            if (!Files.exists(txtPath)) {
                res.status(404);
                return "Ticket not found";
            }
            res.header("Content-Disposition", "attachment; filename=\"ticket-" + id + ".txt\"");
            return new String(Files.readAllBytes(txtPath), StandardCharsets.UTF_8);
        });

        get("/api/ping", (req, res) -> "ok");
    }
}
