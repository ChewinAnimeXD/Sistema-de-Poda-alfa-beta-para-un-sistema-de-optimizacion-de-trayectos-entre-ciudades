import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String[] nombresCiudades = {
            "Sogamoso", "Duitama", "Tunja", "Bogotá", "Medellín", "Cartagena", "Bucaramanga", "Valledupar"
    };

    private static int[][] distancias = {
            // Sogamoso  Duitama   Tunja    Bogotá    Medellín  Cartagena  Bucaramanga  Valledupar
            {0,        25,       50,      210,      550,      880,       290,          600},   // Sogamoso
            {25,       0,        25,      185,      525,      855,       265,          575},   // Duitama
            {50,       25,       0,       160,      500,      830,       240,          550},   // Tunja
            {210,      185,      160,     0,        340,      670,       420,          730},   // Bogotá
            {550,      525,      500,     340,      0,        330,       780,          1080},  // Medellín
            {880,      855,      830,     670,      330,      0,         1110,         1410},  // Cartagena
            {290,      265,      240,     420,      780,      1110,      0,           310},   // Bucaramanga
            {600,      575,      550,     730,      1080,     1410,      310,         0}      // Valledupar
    };

    private static List<String> mejorRuta;
    private static int mejorCosto = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String ciudadOrigen = "Sogamoso"; // Nombre de la ciudad de origen
        String ciudadDestino = "Bucaramanga"; // Nombre de la ciudad de destino

        List<String> rutaActual = new ArrayList<>();
        rutaActual.add(ciudadOrigen);

        podaAlfaBeta(ciudadOrigen, rutaActual, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, ciudadDestino);

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("---------------------------------Taller 1 - Ejercicio 3---------------------------------");
        System.out.println("-----Algoritmo Poda alfa-beta Para la busqueda de la ruta mas optima entre ciudades-----");
        System.out.println("-----------------------------Edwin Sanabria - Daniel Gómez------------------------------");
        System.out.println("----------------------------------------------------------------------------------------");
        
        System.out.println(" ");
        
        System.out.println("Ciudad de origen: " + ciudadOrigen);
        System.out.println("Ciudad de origen: " + ciudadDestino);
        
        
        System.out.println(" ");
        
        System.out.println("Ruta óptima: " + mejorRuta);
        System.out.println("Costo mínimo: " + mejorCosto);
    }

    private static void podaAlfaBeta(String ciudadActual, List<String> rutaActual, int costoActual, int alfa, int beta, String ciudadDestino) {
        if (ciudadActual.equals(ciudadDestino)) {
            // Llegamos a la ciudad de destino
            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                mejorRuta = new ArrayList<>(rutaActual);
            }
            return;
        }

        for (int ciudadSiguiente = 0; ciudadSiguiente < distancias.length; ciudadSiguiente++) {
            String nombreCiudadSiguiente = nombresCiudades[ciudadSiguiente];
            if (!rutaActual.contains(nombreCiudadSiguiente)) {
                // Si la ciudad aún no está en la ruta
                int nuevoCosto = costoActual + distancias[obtenerIndiceCiudad(ciudadActual)][ciudadSiguiente];

                if (nuevoCosto < beta) {
                    // Realizar recursión
                    rutaActual.add(nombreCiudadSiguiente);
                    podaAlfaBeta(nombreCiudadSiguiente, rutaActual, nuevoCosto, alfa, beta, ciudadDestino);
                    rutaActual.remove(rutaActual.size() - 1);

                    // Poda alfa-beta
                    alfa = Math.max(alfa, nuevoCosto);
                    if (alfa >= beta) {
                        break;
                    }
                }
            }
        }
    }

    private static int obtenerIndiceCiudad(String nombreCiudad) {
        for (int i = 0; i < nombresCiudades.length; i++) {
            if (nombreCiudad.equals(nombresCiudades[i])) {
                return i;
            }
        }
        return -1; // Devolver -1 si no se encuentra la ciudad
    }
}