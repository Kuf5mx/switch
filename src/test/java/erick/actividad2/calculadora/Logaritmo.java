package erick.actividad2.calculadora;

/**
 * Esta clase sirve para calcular un logaritmo entero cuando es exacto.
 */
public class Logaritmo {

    // Encontrar exponente exacto: base^x = operando
    // Si no es exacto, regresar -1
    /**
     * Busca un exponente x tal que base^x = operando.
     * Si no existe exacto, regresa -1.
     *
     * @param base numero base (debe ser mayor que 1)
     * @param operando numero al que se le quiere sacar el logaritmo (debe ser >= 1)
     * @return el exponente x si es exacto. Si no es exacto, regresa -1.
     */
    public static int realizarOperacion(int base, int operando) {
        int exponente;
        int acumulador;

        if (base <= 1) {
            System.out.println("La base debe ser mayor que 1. Regresando -1.");
            return -1;
        }

        if (operando < 1) {
            System.out.println("El operando debe ser >= 1. Regresando -1.");
            return -1;
        }

        exponente = 0;
        acumulador = 1;

        while (acumulador < operando) {
            acumulador = Multiplicacion.realizarOperacion(acumulador, base);
            exponente = exponente + 1;
        }

        if (acumulador == operando) {
            return exponente;
        }

        return -1;
    }
}
