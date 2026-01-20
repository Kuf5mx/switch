package erick.actividad2.calculadora;

/**
 * Esta clase sirve para dividir dos numeros.
 * La division es entera (sin decimales).
 */
public class Division {

    /**
     * Divide a entre b (division entera) y regresa el resultado.
     *
     * @param a numero que se va a dividir
     * @param b numero por el que se divide
     * @return resultado entero de a / b. Si b es 0, regresa 0.
     */
    public static int realizarOperacion(int a, int b) {
        int resultado;
        int acumulador;
        int aPositivo;
        int bPositivo;
        int signo;

        resultado = 0;
        acumulador = 0;
        signo = 1;

        if (b == 0) {
            System.out.println("No se puede dividir entre 0. Regresando 0.");
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
            signo = 0 - signo;
        }

        acumulador = aPositivo;
        while (acumulador >= bPositivo) {
            acumulador = acumulador - bPositivo;
            resultado = resultado + 1;
        }

        if (signo < 0) {
            resultado = 0 - resultado;
        }

        return resultado;
    }
}
