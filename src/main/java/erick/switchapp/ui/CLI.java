package erick.switchapp.ui;

import erick.switchapp.process.Switch;
import java.util.Scanner;

public class CLI {
    public static void start(){
        Switch switch_wifi;
        switch_wifi = new Switch();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hola, bienvenido al programa");
        System.out.println("Estado inicial del switch: "+ (switch_wifi.isOn() ? "prendido" : "apagado"));
        if(switch_wifi.isOn()) {
            System.out.println("Desea apagar el switch");
            String respuesta = scanner.nextLine();
            if(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("s")) {
                switch_wifi.apagar();
            }
        }else {
            System.out.println("Desea prender el switch");
            String respuesta = scanner.nextLine();
            if(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("s")) {
                switch_wifi.prender();
            }
        }
        System.out.println("El swtich está "+(switch_wifi.isOn() ? "prendido" : "apagado"));
        scanner.close();
    }
}