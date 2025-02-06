package primerpractico;

import java.util.*;

public class Optimizador {
    // Variables para almacenar el código original, el código optimizado,
    // las constantes encontradas y las variables utilizadas en las expresiones.
    private final String codigoOriginal;
    private final StringBuilder codigoOptimizado;
    private final Map<String, Integer> constantes;
    private final Set<String> variablesUtilizadas;

    // Constructor que inicializa las variables
    public Optimizador(String codigoOriginal) {
        this.codigoOriginal = codigoOriginal; // Código original a optimizar
        this.codigoOptimizado = new StringBuilder(); // Código optimizado, inicialmente vacío
        this.constantes = new HashMap<>(); // Mapa para almacenar las constantes encontradas
        this.variablesUtilizadas = new HashSet<>(); // Conjunto para almacenar las variables utilizadas
    }

    // Método principal que realiza la optimización
    public String optimizar() {
        analizarVariablesUtilizadas(); // Analiza las variables utilizadas en las expresiones
        eliminarOperacionesRedundantes(); // Elimina operaciones redundantes
        return codigoOptimizado.toString(); // Devuelve el código optimizado
    }

    // Método que analiza las variables utilizadas en las expresiones
    private void analizarVariablesUtilizadas() {
        String[] lineas = codigoOriginal.split("\n"); // Divide el código original por líneas
        for (String linea : lineas) {
            String[] partes = linea.split("="); // Divide la línea en la parte izquierda y derecha de la asignación
            if (partes.length > 1) {
                String derecha = partes[1].trim(); // Obtiene la parte derecha de la asignación
                // Divide la expresión de la derecha en tokens (por operadores y caracteres)
                for (String token : derecha.split("[ +\\-*/<>&|(),]+")) {
                    token = token.trim(); // Elimina espacios adicionales
                    // Si el token no es vacío y no es un número, se trata de una variable
                    if (!token.isEmpty() && !Character.isDigit(token.charAt(0))) {
                        variablesUtilizadas.add(token); // Agrega la variable al conjunto de variables utilizadas
                    }
                }
            }
        }
    }

    // Método que elimina operaciones redundantes y optimiza el código
    private void eliminarOperacionesRedundantes() {
        String[] lineas = codigoOriginal.split("\n"); // Divide el código original por líneas
        for (String linea : lineas) {
            linea = linea.trim(); // Elimina espacios adicionales
            if (esAsignacion(linea)) { // Si la línea es una asignación
                String[] partes = linea.split("="); // Divide en izquierda y derecha
                String izquierda = partes[0].trim(); // Variable a la izquierda
                String derecha = partes[1].trim().replace(";", ""); // Expresión a la derecha

                // Si la expresión ya es constante, se puede optimizar directamente
                if (variablesUtilizadas.contains(izquierda)) {
                    Integer valorConstante = evaluarExpresion(derecha); // Intenta evaluar la expresión
                    if (valorConstante != null) { // Si la expresión es constante
                        constantes.put(izquierda, valorConstante); // Se agrega la constante al mapa
                        codigoOptimizado.append(izquierda).append(" = ").append(valorConstante).append(";\n"); // Se reemplaza la asignación por la constante
                    } else { // Si la expresión no es constante
                        String expresionOptimizada = reemplazarConstantes(derecha); // Reemplaza las constantes en la expresión
                        codigoOptimizado.append(izquierda).append(" = ").append(expresionOptimizada).append(";\n"); // Se guarda la asignación optimizada
                    }
                } else { // Si la variable no ha sido utilizada, se deja la línea tal cual
                    codigoOptimizado.append(linea).append("\n");
                }
            } else { // Si no es una asignación, se guarda la línea tal cual
                codigoOptimizado.append(linea).append("\n");
            }
        }
    }

    // Método que evalúa una expresión para ver si se puede convertir en un valor constante
    private Integer evaluarExpresion(String expresion) {
        try {
            return Integer.parseInt(expresion); // Si la expresión es un número, lo devuelve como entero
        } catch (NumberFormatException e) { // Si no es un número, intenta procesar la expresión
            String[] tokens = expresion.split(" "); // Divide la expresión en tokens
            if (tokens.length == 3) { // Si la expresión tiene 3 partes (ej. "a + b")
                Integer a = obtenerValor(tokens[0].trim()); // Obtiene el valor de la primera parte
                Integer b = obtenerValor(tokens[2].trim()); // Obtiene el valor de la tercera parte
                String operador = tokens[1]; // Obtiene el operador
                if (a != null && b != null) { // Si ambos valores son válidos
                    return switch (operador) { // Evalúa la expresión según el operador
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        case "/" -> (b != 0) ? a / b : null; // Evita la división por cero
                        default -> null; // Si el operador no es válido, devuelve null
                    };
                }
            }
        }
        return null; // Si no se puede evaluar, devuelve null
    }

    // Método que obtiene el valor de una variable o constante
    private Integer obtenerValor(String token) {
        if (constantes.containsKey(token)) { // Si es una constante
            return constantes.get(token); // Devuelve el valor de la constante
        }
        try {
            return Integer.parseInt(token); // Si es un número, lo convierte en entero
        } catch (NumberFormatException ignored) {} // Si no es un número ni una constante, ignora el error
        return null; // Si no se puede obtener el valor, devuelve null
    }

    // Método que reemplaza las constantes en una expresión con sus valores
    private String reemplazarConstantes(String expresion) {
        for (Map.Entry<String, Integer> entry : constantes.entrySet()) {
            expresion = expresion.replace(entry.getKey(), entry.getValue().toString()); // Reemplaza cada constante por su valor
        }
        return expresion; // Devuelve la expresión con las constantes reemplazadas
    }

    // Método que determina si una línea es una asignación válida
    private boolean esAsignacion(String linea) {
        return linea.contains("=") && !linea.matches(".*(if|goto|call|return).*"); // Es una asignación si contiene '=' y no es una línea de control de flujo
    }
}
