package erick.actividad2.calculadora;

/**
 * Esta clase sirve para multiplicar dos numeros.
 */
public class Multiplicacion {

    /**
     * Multiplica dos numeros y regresa el resultado.
     *
     * @param a primer numero
     * @param b segundo numero
     * @return resultado de a * b
     */
    public static int realizarOperacion(int a, int b) {
        int resultado;
        int contador;
        int aPositivo;
        int bPositivo;
        int signo;

        resultado = 0;
        contador = 0;
        signo = 1;

        aPositivo = a;
        bPositivo = b;

        if (aPositivo < 0) {
            aPositivo = 0 - aPositivo;
            signo = 0 - signo;
        }

        if (bPositivo < 0) {
            bPositivo = 0 - bPositivo;
            signo = 0 - signo;
        }

        while (contador < bPositivo) {
            resultado = resultado + aPositivo;
            contador = contador + 1;
        }

        if (signo < 0) {
            resultado = 0 - resultado;
        }

        return resultado;
    }
}
