package erick.actividad2.calculadora;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner;
        int opcion;

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Calculadora ===");
            System.out.println("1) Suma");
            System.out.println("2) Resta");
            System.out.println("3) Multiplicacion");
            System.out.println("4) Division (entera)");
            System.out.println("5) Modulo");
            System.out.println("6) Potencia");
            System.out.println("7) Raiz cuadrada (indice 2)");
            System.out.println("8) Logaritmo (exacto)");
            System.out.println("0) Salir");
            System.out.print("Elige una opcion: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no valida. Escribe un numero.");
                scanner.next();
                continue;
            }

            opcion = scanner.nextInt();

            if (opcion == 0) {
                System.out.println("Saliendo...");
                break;
            }

            if (opcion >= 1 && opcion <= 5) {
                int a;
                int b;
                int resultado;

                System.out.print("Escribe el primer numero: ");
                a = scanner.nextInt();
                System.out.print("Escribe el segundo numero: ");
                b = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        resultado = Suma.realizarOperacion(a, b);
                        System.out.println("Resultado (suma): " + resultado);
                        break;
                    case 2:
                        resultado = Resta.realizarOperacion(a, b);
                        System.out.println("Resultado (resta): " + resultado);
                        break;
                    case 3:
                        resultado = Multiplicacion.realizarOperacion(a, b);
                        System.out.println("Resultado (multiplicacion): " + resultado);
                        break;
                    case 4:
                        resultado = Division.realizarOperacion(a, b);
                        System.out.println("Resultado (division entera): " + resultado);
                        break;
                    case 5:
                        resultado = Modulo.realizarOperacion(a, b);
                        System.out.println("Resultado (modulo): " + resultado);
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

                continue;
            }

            if (opcion == 6) {
                int base;
                int exponente;
                int resultado;

                System.out.print("Escribe la base: ");
                base = scanner.nextInt();
                System.out.print("Escribe el exponente: ");
                exponente = scanner.nextInt();

                resultado = Potencia.realizarOperacion(base, exponente);
                System.out.println("Resultado (potencia): " + resultado);
                continue;
            }

            if (opcion == 7) {
                int operando;
                int resultado;

                System.out.print("Escribe el numero para sacar raiz cuadrada: ");
                operando = scanner.nextInt();

                resultado = Raiz.realizarOperacion(2, operando);
                System.out.println("Resultado (raiz cuadrada entera): " + resultado);
                continue;
            }

            if (opcion == 8) {
                int base;
                int operando;
                int resultado;

                System.out.print("Escribe la base (mayor que 1): ");
                base = scanner.nextInt();
                System.out.print("Escribe el operando (>= 1): ");
                operando = scanner.nextInt();

                resultado = Logaritmo.realizarOperacion(base, operando);
                System.out.println("Resultado (logaritmo exacto): " + resultado);
                continue;
            }

            System.out.println("Opcion no valida.");
        }

        scanner.close();
    }
}
