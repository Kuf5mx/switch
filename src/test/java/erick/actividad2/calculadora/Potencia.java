package erick.actividad2.calculadora;

/**
 * Esta clase sirve para calcular potencias.
 */
public class Potencia {

    /**
     * Calcula base elevado a exponente y regresa el resultado.
     *
     * @param base numero base
     * @param exponente numero de veces que se multiplica la base
     * @return base^exponente. Si exponente es negativo, regresa 0.
     */
    public static int realizarOperacion(int base, int exponente) {
        int resultado;
        int contador;

        if (exponente < 0) {
            System.out.println("No se acepta exponente negativo. Regresando 0.");
            return 0;
        }

        resultado = 1;
        contador = 0;

        while (contador < exponente) {
            resultado = Multiplicacion.realizarOperacion(resultado, base);
            contador = contador + 1;
        }

        return resultado;
    }
}
