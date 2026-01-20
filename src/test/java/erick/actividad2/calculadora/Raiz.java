package erick.actividad2.calculadora;

/**
 * Esta clase sirve para calcular una raiz cuadrada entera.
 */
public class Raiz {

    // Para esta tarea: Raiz.realizarOperacion(2, operando) = raiz cuadrada positiva (entera)
    /**
     * Calcula la raiz cuadrada entera de un numero y la regresa.
     *
     * @param indice indice de la raiz (solo funciona con 2)
     * @param operando numero al que se le saca la raiz
     * @return raiz cuadrada entera (redondeada hacia abajo). Si no se puede, regresa 0.
     */
    public static int realizarOperacion(int indice, int operando) {
        int resultado;
        int candidato;
        int candidatoCuadrado;

        if (indice != 2) {
            System.out.println("Solo se hizo para indice 2 (raiz cuadrada). Regresando 0.");
            return 0;
        }

        if (operando < 0) {
            System.out.println("No hay raiz cuadrada real para negativos. Regresando 0.");
            return 0;
        }

        resultado = 0;
        candidato = 0;

        while (true) {
            candidatoCuadrado = Multiplicacion.realizarOperacion(candidato, candidato);

            if (candidatoCuadrado == operando) {
                resultado = candidato;
                break;
            }

            if (candidatoCuadrado > operando) {
                resultado = candidato - 1;
                break;
            }

            candidato = candidato + 1;
        }

        return resultado;
    }
}
