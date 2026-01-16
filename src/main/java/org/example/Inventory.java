package edu.erick;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public Inventory() {
        loadVehicles();
    }

    private void loadVehicles() {
        vehicles.add(new Vehicle(1, "Ferrari", "F8 Tributo", 2023, 280000, "V8 Twin-Turbo, 7-Speed DCT, Carbon Fiber Trim, Adaptive Dampers, Premium Audio", 5800));
        vehicles.add(new Vehicle(2, "Lamborghini", "Huracán EVO", 2023, 260000, "V10 NA, AWD, Torque Vectoring, MagneRide Suspension, Sport Exhaust", 8200));
        vehicles.add(new Vehicle(3, "Porsche", "911 Turbo S", 2024, 250000, "Twin-Turbo Flat-6, Sport Chrono, Launch Control, Ceramic Brakes, Premium Sound", 3500));
        vehicles.add(new Vehicle(4, "McLaren", "720S", 2023, 300000, "Carbon Fiber Monocoque, Active Aero, Track Mode, Proactive Chassis Control, Carbon Brakes", 6100));
        vehicles.add(new Vehicle(5, "Mercedes-AMG", "GT Black Series", 2023, 325000, "Handcrafted V8, Active Aerodynamics, Carbon Package, Track Telemetry, Performance Seats", 4200));
        vehicles.add(new Vehicle(6, "Aston Martin", "DB12", 2024, 245000, "Twin-Turbo V8, Luxury Leather Cabin, Adaptive Suspension, GT Touring Package, Premium Infotainment", 2900));
        vehicles.add(new Vehicle(7, "Bentley", "Continental GT", 2024, 230000, "Grand Touring, Mulliner Spec, Air Suspension, Naim Audio, Hand-Finished Wood & Leather", 7500));
        vehicles.add(new Vehicle(8, "Rolls-Royce", "Phantom", 2024, 450000, "V12 Power, Bespoke Interior, Starlight Headliner, Rear Comfort Suite, Ultra-Quiet Cabin", 3200));
        vehicles.add(new Vehicle(9, "Bugatti", "Chiron", 2023, 3000000, "W16 Quad-Turbo, 1500 HP, Carbon Fiber Body, Active Aero, Hypercar Cooling System", 1800));
        vehicles.add(new Vehicle(10, "Maserati", "MC20", 2024, 210000, "Twin-Turbo V6, Carbon Tub, Italian Design, Launch Control, Performance Brakes", 4600));
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle findById(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) return v;
        }
        return null;
    }
}
