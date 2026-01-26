package erick.actividad2.calculadora;

/**
 * Esta clase sirve para sacar el modulo (residuo) de una division.
 */
public class Modulo {

    /**
     * Saca el residuo de dividir a entre b y lo regresa.
     *
     * @param a numero al que se le saca el residuo
     * @param b numero con el que se divide
     * @return residuo de a % b. Si b es 0, regresa 0.
     */
    public static int realizarOperacion(int a, int b) {
        int resultado;
        int aPositivo;
        int bPositivo;
        int signo;

        resultado = 0;
        signo = 1;

        if (b == 0) {
            System.out.println("No se puede sacar modulo con 0. Regresando 0.");
            return 0;
        }

        aPositivo = a;
        bPositivo = b;

        if (aPositivo < 0) {
            aPositivo = 0 - aPositivo;
            signo = 0 - signo;
        }

        if (bPositivo < 0) {
            bPositivo = 0 - bPositivo;
        }

        while (aPositivo >= bPositivo) {
            aPositivo = aPositivo - bPositivo;
        }

        resultado = aPositivo;
        if (signo < 0) {
            resultado = 0 - resultado;
        }

        return resultado;
    }
}
